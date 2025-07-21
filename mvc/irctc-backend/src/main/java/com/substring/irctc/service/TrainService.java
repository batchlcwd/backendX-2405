package com.substring.irctc.service;

import com.substring.irctc.dto.AvailableTrainResponse;
import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.dto.UserTrainSearchRequest;

import java.time.LocalDate;
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


    //    search trains for booking
    List<AvailableTrainResponse> userTrainSearch(UserTrainSearchRequest request);

}
