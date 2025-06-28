package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.service.TrainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminTrainController")
@RequestMapping("/admin/trains")

public class TrainController {


    private TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    //create

    @PostMapping
    public ResponseEntity<TrainDTO> createTrain(
            @RequestBody TrainDTO trainDTO
    ) {
//        System.out.println(trainDTO.getNumber());
//        System.out.println(trainDTO.getName());
//        System.out.println(trainDTO.getSourceStation().getId());
        return new ResponseEntity<>(trainService.createTrain(trainDTO), HttpStatus.CREATED);
    }

    // list

    @GetMapping
    public List<TrainDTO> getAllTrains() {
        return trainService.getAllTrains();
    }

    // get detail
    @GetMapping("/{id}")
    public ResponseEntity<TrainDTO> getTrainById(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(trainService.getTrainById(id), HttpStatus.OK);
    }

    //update train

    @PutMapping("/{id}")
    public ResponseEntity<TrainDTO> updateTrain(
            @PathVariable("id") Long id,
            @RequestBody TrainDTO trainDTO
    ) {
        return new ResponseEntity<>(trainService.updateTrain(id, trainDTO), HttpStatus.OK);
    }

    // delete trains
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(
            @PathVariable("id") Long id
    ) {
        trainService.deleteTrain(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //trains:
}
