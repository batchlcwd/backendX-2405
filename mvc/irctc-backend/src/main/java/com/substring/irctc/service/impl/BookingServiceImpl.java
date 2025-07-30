package com.substring.irctc.service.impl;

import com.substring.irctc.dto.BookingPassengerDto;
import com.substring.irctc.dto.BookingRequest;
import com.substring.irctc.dto.BookingResponse;
import com.substring.irctc.dto.StationDto;
import com.substring.irctc.entity.*;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.*;
import com.substring.irctc.service.BookingService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {


    private BookingRepository bookingRepository;
    private BookingPassengerRepository bookingPassengerRepository;
    private UserRepo userRepo;
    private TrainScheduleRepository trainScheduleRepository;
    private TrainRepository trainRepository;
    private StationRepo stationRepo;
    private ModelMapper modelMapper;

    private TrainSeatRepository trainSeatRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingPassengerRepository bookingPassengerRepository, UserRepo userRepo, TrainScheduleRepository trainScheduleRepository, TrainRepository trainRepository, StationRepo stationRepo, ModelMapper modelMapper, TrainSeatRepository trainSeatRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingPassengerRepository = bookingPassengerRepository;
        this.userRepo = userRepo;
        this.trainScheduleRepository = trainScheduleRepository;
        this.trainRepository = trainRepository;
        this.stationRepo = stationRepo;
        this.modelMapper = modelMapper;
        this.trainSeatRepository = trainSeatRepository;
    }

    // create logic for booking::: change as you want.
    @Override
    @Transactional
    public synchronized BookingResponse createBooking(BookingRequest bookingRequest) {

        User user = userRepo.findById(bookingRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + bookingRequest.getUserId()));

        TrainSchedule trainSchedule = this.trainScheduleRepository.findById(bookingRequest.getTrainScheduleId()).orElseThrow(() -> new ResourceNotFoundException("Train Schedule not found with id: " + bookingRequest.getTrainScheduleId()));

        Station sourceStation = stationRepo.findById(bookingRequest.getSourceStationId()).orElseThrow(() -> new ResourceNotFoundException("Source Station not found with id: " + bookingRequest.getSourceStationId()));

        Station destinationStation = stationRepo.findById(bookingRequest.getDestinationStationId()).orElseThrow(() -> new ResourceNotFoundException("Destination Station not found with id: " + bookingRequest.getDestinationStationId()));


        List<TrainSeat> coaches = trainSchedule.getTrainSeats();


        //it will sort coaches based on trainSeatOrder
        coaches.sort((s1, s2) -> s1.getTrainSeatOrder() - s2.getTrainSeatOrder());


        //selected coaches of same type

        List<TrainSeat> selectedCoaches = coaches.stream().filter(coach -> coach.getCoachType() == bookingRequest.getCoachType()).toList();


        //total number of requested seats
        int totalRequestedSeat = bookingRequest.getPassengers().size();


        //kis coach se book krenge
        TrainSeat coachToBookSeat = null;

        for (TrainSeat coach : selectedCoaches) {
            if (coach.isSeatAvailable(totalRequestedSeat)) {
                coachToBookSeat = coach;
                break;
            }
        }


        if (coachToBookSeat == null) {
            throw new ResourceNotFoundException("No seats available in this type of coach");
        }


        //book seats
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setSourceStation(sourceStation);
        booking.setDestinationStation(destinationStation);
        booking.setTrainSchedule(trainSchedule);
        booking.setUser(user);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setJourneyDate(trainSchedule.getRunDate());
        booking.setPnr(UUID.randomUUID().toString());
        //set total fare
        booking.setTotalFare(new BigDecimal(totalRequestedSeat * coachToBookSeat.getPrice()));


        Payment payment = new Payment();
        payment.setAmount(booking.getTotalFare());
        payment.setPaymentMethod(null);
        payment.setTansactionId(null);
        payment.setPaymentStatus(PaymentStatus.NOT_PAID);
        payment.setBooking(booking);
        // set the payment
        booking.setPayment(payment);

        List<BookingPassenger> bookingPassengers = new ArrayList<>();

        for (BookingPassengerDto bookingPassengerDto : bookingRequest.getPassengers()) {

            BookingPassenger passenger = modelMapper.map(bookingPassengerDto, BookingPassenger.class);
            passenger.setBooking(booking);
            passenger.setTrainSeat(coachToBookSeat);
            passenger.setSeatNumber(coachToBookSeat.getSeatNumberToAssign() + "");
            coachToBookSeat.setSeatNumberToAssign(coachToBookSeat.getSeatNumberToAssign() + 1);
            coachToBookSeat.setAvailableSeats(coachToBookSeat.getAvailableSeats() - 1);

            bookingPassengers.add(passenger);


        }

        booking.setPassengers(bookingPassengers);

        Booking savedBooking = bookingRepository.save(booking);

        //error
        this.trainSeatRepository.save(coachToBookSeat);


        // going to create response

        BookingResponse bookingResponse = new BookingResponse();

        bookingResponse.setBookingId(savedBooking.getId());
        bookingResponse.setPnr(savedBooking.getPnr());
        bookingResponse.setTotalFare(savedBooking.getTotalFare());
        bookingResponse.setBookingStatus(savedBooking.getBookingStatus());
        bookingResponse.setSourceStation(modelMapper.map(sourceStation, StationDto.class));
        bookingResponse.setDestinationStation(modelMapper.map(destinationStation, StationDto.class));
        bookingResponse.setJourneyDate(trainSchedule.getRunDate());
        bookingResponse.setPaymentStatus(savedBooking.getPayment().getPaymentStatus());

        bookingResponse.setPassengers(
                savedBooking.getPassengers().stream().map(passenger -> {
                    BookingPassengerDto bookingPassengerDto = modelMapper.map(passenger, BookingPassengerDto.class);
                    bookingPassengerDto.setCoachId(passenger.getTrainSeat().getId() + "");
                    return bookingPassengerDto;
                }).toList()

        );


        TrainRoute sourceRoute = trainSchedule.getTrain().getRoutes().stream().filter(route -> route.getStation().getId().equals(sourceStation.getId())).findFirst().get();
        bookingResponse.setDepartureTime(sourceRoute.getDepartureTime());
        bookingResponse.setArrivalTime(sourceRoute.getArrivalTime());

        return bookingResponse;
    }
}
