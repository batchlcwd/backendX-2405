package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "create train",
            description = "This api create new trains"
    )
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
    @Operation(
            summary = "get all trains",
            description = "This api get all trains"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "request successful"),
            @ApiResponse(responseCode = "404", description = "train not found")
    })

    @GetMapping
    public List<TrainDTO> getAllTrains() {
        return trainService.getAllTrains();
    }

    // get detail

    @Operation(
            summary = "get train by id",
            description = "This api get train by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "request successful"),
            @ApiResponse(responseCode = "404", description = "train not found"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrainDTO> getTrainById(
            @Parameter(description = "Id of train to get details") @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(trainService.getTrainById(id), HttpStatus.OK);
    }

    //update train

    @Operation(
            summary = "update train",
            description = "This api update train"
    )
    @PutMapping("/{id}")
    public ResponseEntity<TrainDTO> updateTrain(
            @PathVariable("id") Long id,
            @RequestBody TrainDTO trainDTO
    ) {
        return new ResponseEntity<>(trainService.updateTrain(id, trainDTO), HttpStatus.OK);
    }

    // delete trains
    @Operation(
            summary = "delete train",
            description = "This api delete train"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(
            @PathVariable("id") Long id
    ) {
        trainService.deleteTrain(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
