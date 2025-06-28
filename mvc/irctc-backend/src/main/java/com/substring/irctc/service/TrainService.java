package com.substring.irctc.service;

import com.substring.irctc.dto.TrainDTO;

import java.util.List;

public interface TrainService {

    // create train
    public TrainDTO createTrain(TrainDTO trainDTO);

    //list trains
    public List<TrainDTO> getAllTrains();

    //get train by id
    public TrainDTO getTrainById(Long id);

    //update train
    public TrainDTO updateTrain(Long id, TrainDTO trainDTO);

    //delete train
    public void deleteTrain(Long id);
}
