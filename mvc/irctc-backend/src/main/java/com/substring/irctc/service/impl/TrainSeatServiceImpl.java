package com.substring.irctc.service.impl;

import com.substring.irctc.dto.TrainSeatDto;
import com.substring.irctc.entity.TrainSchedule;
import com.substring.irctc.entity.TrainSeat;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.TrainScheduleRepository;
import com.substring.irctc.repositories.TrainSeatRepository;
import com.substring.irctc.service.TrainSeatService;
import jakarta.transaction.Transactional;
import lombok.Synchronized;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainSeatServiceImpl implements TrainSeatService {

    private TrainSeatRepository trainSeatRepository;

    private TrainScheduleRepository trainScheduleRepository;

    private ModelMapper modelMapper;

    public TrainSeatServiceImpl(TrainSeatRepository trainSeatRepository, TrainScheduleRepository trainScheduleRepository, ModelMapper modelMapper) {
        this.trainSeatRepository = trainSeatRepository;
        this.trainScheduleRepository = trainScheduleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public TrainSeatDto createSeatInfor(TrainSeatDto trainSeatDto) {

        TrainSchedule trainSchedule = trainScheduleRepository.findById(trainSeatDto.getTrainScheduleId()).orElseThrow(() -> new ResourceNotFoundException("Train Schedule not found with id: " + trainSeatDto.getTrainScheduleId()));
        TrainSeat trainSeat = modelMapper.map(trainSeatDto, TrainSeat.class);
        trainSeat.setTrainSchedule(trainSchedule);
        TrainSeat savedTrainSeat = trainSeatRepository.save(trainSeat);
        return modelMapper.map(savedTrainSeat, TrainSeatDto.class);


    }

    @Override
    public List<TrainSeatDto> getSeatInfoByTrainScheduleId(Long scheduleId) {
        List<TrainSeat> trainSeats = trainSeatRepository.findByTrainScheduleId(scheduleId);
        return trainSeats.stream().map(trainSeat -> modelMapper.map(trainSeat, TrainSeatDto.class)).toList();
    }

    @Override
    public void deleteSeatInfo(Long seatId) {
        TrainSeat trainSeat = trainSeatRepository.findById(seatId).orElseThrow(() -> new ResourceNotFoundException("Train Seat not found with id: " + seatId));
        trainSeatRepository.delete(trainSeat);
    }

    @Override
    public TrainSeatDto updateSeatInfo(Long seatId, TrainSeatDto trainSeatDto) {
        TrainSeat trainSeat = trainSeatRepository.findById(seatId).orElseThrow(() -> new ResourceNotFoundException("Train Seat not found with id: " + seatId));
        TrainSchedule trainSchedule = trainScheduleRepository.findById(trainSeatDto.getTrainScheduleId()).orElseThrow(() -> new ResourceNotFoundException("Train Schedule not found with id: " + trainSeatDto.getTrainScheduleId()));
        trainSeat.setTrainSchedule(trainSchedule);
        trainSeat.setCoachType(trainSeatDto.getCoachType());
        trainSeat.setAvailableSeats(trainSeatDto.getAvailableSeats());
        trainSeat.setPrice(trainSeatDto.getPrice());
        trainSeat.setTotalSeats(trainSeatDto.getTotalSeats());
        trainSeat.setSeatNumberToAssign(trainSeatDto.getSeatNumberToAssign());
        trainSeat.setTrainSeatOrder(trainSeatDto.getTrainSeatOrder());
        TrainSeat updatedTrainSeat = trainSeatRepository.save(trainSeat);
        return modelMapper.map(updatedTrainSeat, TrainSeatDto.class);
    }

    @Synchronized
    public List<Integer> bookSeat(int seatToBook, Long seatId)
    {
        TrainSeat trainSeat = trainSeatRepository.findById(seatId).orElseThrow(() -> new ResourceNotFoundException("Train Seat not found with id: " + seatId));
        if (trainSeat.isSeatAvailable(seatToBook)) {
            trainSeat.setAvailableSeats(trainSeat.getAvailableSeats() - seatToBook);
            List<Integer> bookedSeats = new ArrayList<>();
            for (int i = 1; i <= seatToBook; i++) {
                bookedSeats.add(trainSeat.getSeatNumberToAssign());
                trainSeat.setSeatNumberToAssign(trainSeat.getSeatNumberToAssign() + 1);
            }

            trainSeatRepository.save(trainSeat);
            return bookedSeats;
        } else {
            throw new IllegalStateException("No seats available in this coach");
        }
    }
}
