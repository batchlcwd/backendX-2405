package com.substring.irctc.service;

import com.substring.irctc.dto.TrainRouteDto;

import java.util.List;

public interface TrainRouteService {

    TrainRouteDto addRoute(TrainRouteDto dto);




    //get train routes by train ID
    List<TrainRouteDto> getRoutesByTrain(Long trainId);


    //update train route
    TrainRouteDto updateRoute(Long id, TrainRouteDto dto);



    // delete train route
    void deleteRoute(Long id);

}
