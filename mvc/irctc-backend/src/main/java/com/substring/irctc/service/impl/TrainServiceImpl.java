package com.substring.irctc.service.impl;

import com.substring.irctc.dto.AvailableTrainResponse;
import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.dto.UserTrainSearchRequest;
import com.substring.irctc.entity.*;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.StationRepo;
import com.substring.irctc.repositories.TrainRepository;
import com.substring.irctc.repositories.TrainScheduleRepository;
import com.substring.irctc.service.TrainService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service

public class TrainServiceImpl implements TrainService {


    private StationRepo stationRepository;
    private ModelMapper modelMapper;
    private TrainRepository trainRepository;

    private TrainScheduleRepository trainScheduleRepository;

    public TrainServiceImpl(StationRepo stationRepository, ModelMapper modelMapper, TrainRepository trainRepository, TrainScheduleRepository trainScheduleRepository) {
        this.stationRepository = stationRepository;
        this.modelMapper = modelMapper;
        this.trainRepository = trainRepository;
        this.trainScheduleRepository = trainScheduleRepository;
    }

    @Override
    public TrainDTO createTrain(TrainDTO trainDTO) {
        Station sourceStation = stationRepository.findById(trainDTO.getSourceStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Source station not found with id: " + trainDTO.getSourceStation().getId()));
        Station destinationStation = stationRepository.findById(trainDTO.getDestinationStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Destination station not found with id: " + trainDTO.getDestinationStation().getId()));
        Train train = modelMapper.map(trainDTO, Train.class);
        train.setSourceStation(sourceStation);
        train.setDestinationStation(destinationStation);
        Train savedTrain = trainRepository.save(train);
        return modelMapper.map(savedTrain, TrainDTO.class);
    }

    @Override
    public List<TrainDTO> getAllTrains() {
        List<Train> all = trainRepository.findAll();
        return all.stream().map(train -> modelMapper.map(train, TrainDTO.class)).toList();
    }

    @Override
    public TrainDTO getTrainById(Long id) {
        Train train = trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + id));
        return modelMapper.map(train, TrainDTO.class);
    }

    @Override
    public TrainDTO updateTrain(Long id, TrainDTO trainDTO) {
        Train existingTrain = trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + id));
        existingTrain.setName(trainDTO.getName());
        existingTrain.setNumber(trainDTO.getNumber());
        existingTrain.setTotalDistance(trainDTO.getTotalDistance());
        //fetch source and destination stations
        Station sourceStation = stationRepository.findById(trainDTO.getSourceStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Source station not found with id: " + trainDTO.getSourceStation().getId()));
        Station destinationStation = stationRepository.findById(trainDTO.getDestinationStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Destination station not found with id: " + trainDTO.getDestinationStation().getId()));


        existingTrain.setSourceStation(sourceStation);
        existingTrain.setDestinationStation(destinationStation);
        Train updatedTrain = trainRepository.save(existingTrain);
        return modelMapper.map(updatedTrain, TrainDTO.class);
    }

    @Override
    public void deleteTrain(Long id) {
        Train existingTrain = trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + id));
        trainRepository.delete(existingTrain);
    }

    //    This method is for user to search for trains on specific date from source to destination with available seats
    @Override
    public List<AvailableTrainResponse> userTrainSearch(UserTrainSearchRequest request) {
// Fetch trains from repository with source and destination
        List<Train> matchedTrains = this.trainRepository.findTrainBySourceAndDestination(
                request.getSourceStationId(), request.getDestinationStationId());

// Use stream to filter valid trains directly
        List<AvailableTrainResponse> responseList = matchedTrains.stream()
                .filter(train -> {
                    // Find source and destination station orders in a single loop
                    Integer sourceStationOrder = null;
                    Integer destinationStationOrder = null;

                    for (TrainRoute trainRoute : train.getRoutes()) {
                        if (trainRoute.getStation().getId().equals(request.getSourceStationId())) {
                            sourceStationOrder = trainRoute.getStationOrder();
                        } else if (trainRoute.getStation().getId().equals(request.getDestinationStationId())) {
                            destinationStationOrder = trainRoute.getStationOrder();
                        }

                        // Early exit if both station orders are found
                        if (sourceStationOrder != null && destinationStationOrder != null) {
                            break;
                        }
                    }

                    // Check for station order validity and the schedule date condition
                    boolean validOrder = sourceStationOrder != null && destinationStationOrder != null &&
                            sourceStationOrder < destinationStationOrder;

                    boolean runOnThatDay = train.getSchedules().stream()
                            .anyMatch(schedule -> schedule.getRunDate().equals(request.getJourneyDate()));

                    return validOrder && runOnThatDay;
                })
                .map(train -> {
                    // Find the matching schedule for the journey date
                    TrainSchedule trainSchedule = train.getSchedules().stream()
                            .filter(sch -> sch.getRunDate().equals(request.getJourneyDate()))
                            .findFirst().orElse(null);

                    if (trainSchedule == null) {
                        return null;  // Skip if there's no matching schedule
                    }

                    // Find the source route for the train
                    TrainRoute sourceRoute = train.getRoutes().stream()
                            .filter(route -> route.getStation().getId().equals(request.getSourceStationId()))
                            .findFirst().orElse(null);

                    if (sourceRoute == null) {
                        return null;  // Skip if there's no source route
                    }

                    // Map seat types and prices
                    Map<CoachType, Integer> seatMap = new HashMap<>();
                    Map<CoachType, Double> priceMap = new HashMap<>();

                    trainSchedule.getTrainSeats().forEach(trainSeat -> {
                        seatMap.merge(trainSeat.getCoachType(), trainSeat.getAvailableSeats(), Integer::sum);
                        priceMap.putIfAbsent(trainSeat.getCoachType(), trainSeat.getPrice());
                    });

                    // Build the response for the valid train
                    return AvailableTrainResponse.builder()
                            .trainId(train.getId())
                            .trainNumber(train.getNumber())
                            .trainName(train.getName())
                            .departureTime(sourceRoute.getDepartureTime())
                            .arrivalTime(sourceRoute.getArrivalTime())
                            .seatsAvailable(seatMap)
                            .priceByCoach(priceMap)
                            .scheduleDate(trainSchedule.getRunDate())
                            .trainScheduleId(trainSchedule.getId())
                            .build();
                })
                .filter(Objects::nonNull)  // Remove null responses (invalid trains)
                .collect(Collectors.toList());  // Collect results into a list

// Return the final list of valid available train responses
        return responseList;
    }


}
