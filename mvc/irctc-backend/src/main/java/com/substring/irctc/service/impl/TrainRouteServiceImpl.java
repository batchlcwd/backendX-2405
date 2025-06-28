package com.substring.irctc.service.impl;

import com.substring.irctc.dto.TrainRouteDto;
import com.substring.irctc.entity.Station;
import com.substring.irctc.entity.Train;
import com.substring.irctc.entity.TrainRoute;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.StationRepo;
import com.substring.irctc.repositories.TrainRepository;
import com.substring.irctc.repositories.TrainRouteRepository;
import com.substring.irctc.service.TrainRouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainRouteServiceImpl implements TrainRouteService {


    private TrainRepository trainRepository;
    private StationRepo stationRepo;
    private TrainRouteRepository trainRouteRepository;
    private ModelMapper modelMapper;

    public TrainRouteServiceImpl(TrainRepository trainRepository, StationRepo stationRepo, TrainRouteRepository trainRouteRepository, ModelMapper modelMapper) {
        this.trainRepository = trainRepository;
        this.stationRepo = stationRepo;
        this.trainRouteRepository = trainRouteRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public TrainRouteDto addRoute(TrainRouteDto dto) {

        Train train = this.trainRepository.findById(dto.getTrain().getId()).orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + dto.getTrain().getId()));
        Station station = this.stationRepo.findById(dto.getStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Station not found with ID: " + dto.getStation().getId()));

        // Convert DTO to Entity
        TrainRoute trainRoute = modelMapper.map(dto, TrainRoute.class);
        trainRoute.setTrain(train);
        trainRoute.setStation(station);
        // Save the TrainRoute entity
        TrainRoute savedTrainRoute = trainRouteRepository.save(trainRoute);
        // Convert saved entity back to DTO
        TrainRouteDto savedTrainRouteDto = modelMapper.map(savedTrainRoute, TrainRouteDto.class);
        return savedTrainRouteDto;
    }

    @Override
    public List<TrainRouteDto> getRoutesByTrain(Long trainId) {
        Train train = this.trainRepository.findById(trainId).orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + trainId));
        List<TrainRoute> trainRoutes = this.trainRouteRepository.findByTrainId(trainId);
        List<TrainRouteDto> trainRouteDtos = trainRoutes.stream().map(trainRoute -> modelMapper.map(trainRoute, TrainRouteDto.class)).toList();
        return trainRouteDtos;
    }

    @Override
    public TrainRouteDto updateRoute(Long id, TrainRouteDto dto) {

        TrainRoute existingRoute = trainRouteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train route not found with ID: " + id));

        Station station = this.stationRepo.findById(dto.getStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Station not found with ID: " + dto.getStation().getId()));
        Train train = this.trainRepository.findById(dto.getTrain().getId()).orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + dto.getTrain().getId()));
        // Update the existing route with new values
        existingRoute.setStation(station);
        existingRoute.setTrain(train);
        existingRoute.setStationOrder(dto.getStationOrder());
        existingRoute.setArrivalTime(dto.getArrivalTime());
        existingRoute.setDepartureTime(dto.getDepartureTime());
        existingRoute.setHaltMinutes(dto.getHaltMinutes());
        existingRoute.setDistanceFromSource(dto.getDistanceFromSource());
        // Save the updated route
        TrainRoute updatedRoute = trainRouteRepository.save(existingRoute);
        // Convert updated entity back to DTO
        TrainRouteDto updatedRouteDto = modelMapper.map(updatedRoute, TrainRouteDto.class);
        return updatedRouteDto;
    }

    @Override
    public void deleteRoute(Long id) {

        TrainRoute existingRoute = trainRouteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train route not found with ID: " + id));

        // Delete the route
        trainRouteRepository.delete(existingRoute);
    }
}
