package com.substring.irctc.controllers.user;

import com.substring.irctc.dto.AvailableTrainResponse;
import com.substring.irctc.dto.UserTrainSearchRequest;
import com.substring.irctc.service.TrainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/trains")
public class TrainController {

    private  final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    //search trains
    @PostMapping("/search")
    public ResponseEntity<List<AvailableTrainResponse>> searchTrains(
            @RequestBody UserTrainSearchRequest userTrainSearchRequest
            ) {

        List<AvailableTrainResponse> availableTrainResponses = trainService.userTrainSearch(userTrainSearchRequest);
        return new ResponseEntity<>(availableTrainResponses, HttpStatus.OK);
    }

}


