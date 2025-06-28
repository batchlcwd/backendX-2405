package com.substring.irctc.service.impl;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.entity.Station;
import com.substring.irctc.entity.Train;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.StationRepo;
import com.substring.irctc.repositories.TrainRepository;
import com.substring.irctc.service.TrainService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class TrainServiceImpl implements TrainService {


    private StationRepo stationRepository;
    private ModelMapper modelMapper;
    private TrainRepository trainRepository;

    public TrainServiceImpl(StationRepo stationRepository, ModelMapper modelMapper, TrainRepository trainRepository) {
        this.stationRepository = stationRepository;
        this.modelMapper = modelMapper;
        this.trainRepository = trainRepository;
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

    // any more logic related to train service can be added here
}
