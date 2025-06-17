package com.substring.irctc.service;

import com.substring.irctc.dto.PagedResponse;
import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.entity.Train;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.TrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {


    private TrainRepository trainRepository;


    private ModelMapper modelMapper;


    public TrainService(TrainRepository trainRepository, ModelMapper modelMapper) {
        this.trainRepository = trainRepository;
        this.modelMapper = modelMapper;
    }

    //add train
    public TrainDTO add(TrainDTO trainDTO) {

//        convert karna padega dto to entity
//        Train train = new Train();
//        train.setTainNo(trainDTO.getTainNo());
//        train.setName(trainDTO.getName());
//        train.setRouteName(trainDTO.getRouteName());

        Train train = modelMapper.map(trainDTO, Train.class);
        Train savedTrain = trainRepository.save(train);
        //convert entity into dto
//        TrainDTO dto = new TrainDTO();
//        dto.setTainNo(savedTrain.getTainNo());
//        dto.setName(savedTrain.getName());
//        dto.setRouteName(savedTrain.getRouteName());
        TrainDTO dto = modelMapper.map(savedTrain, TrainDTO.class);
        return dto;
    }

    //get all
    public PagedResponse<TrainDTO> all(int page, int size, String sortBy, String sortDir) {

        //kuch aisa likhna hai ki pagination implement ho jaye
        //sorting
        Sort sort = sortBy.trim().toLowerCase().equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        //db se data fetch karne ka logic: we can get train from database
        Page<Train> trainPage = trainRepository.findAll(pageable);
        //list of train to list of train dtos
        Page<TrainDTO> trainDTOPage = trainPage.map(train -> modelMapper.map(train, TrainDTO.class));

        return PagedResponse.fromPage(trainDTOPage);
    }

    //get single
    public TrainDTO get(String trainNo) {
        Train train = trainRepository.
                findById(trainNo).orElseThrow(() -> new ResourceNotFoundException("Train not found"));
        return modelMapper.map(train, TrainDTO.class);

    }

    //delete
    public void delete(String trainNo) {
        Train train = trainRepository.
                findById(trainNo).orElseThrow(() -> new ResourceNotFoundException("Train not found"));

        trainRepository.delete(train);
    }
}
