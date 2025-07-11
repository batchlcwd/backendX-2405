package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainSeatDto;
import com.substring.irctc.service.TrainSeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-seats")
public class TrainSeatController {

    private TrainSeatService trainSeatService;

    public TrainSeatController(TrainSeatService trainSeatService) {
        this.trainSeatService = trainSeatService;
    }


    //create
    @PostMapping
    public ResponseEntity<TrainSeatDto> createSeat(
          @RequestBody TrainSeatDto trainSeatDto) {
        TrainSeatDto createdSeat = trainSeatService.createSeatInfor(trainSeatDto);
        return ResponseEntity.status(201).body(createdSeat);
    }


    //get dibba of train schedule
    @Operation(
            summary = "get train seats of train schedule",
            description = "This api get train seats[coaches] of train schedule"
    )
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<TrainSeatDto>> getSeatsByScheduleId(
            @Parameter(description = "Id of train schedule to get details") @PathVariable Long scheduleId) {
        List<TrainSeatDto> seats = trainSeatService.getSeatInfoByTrainScheduleId(scheduleId);
        return ResponseEntity.ok(seats);
    }

    //delete seat info
    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> deleteSeatInfo(@PathVariable Long seatId) {
        trainSeatService.deleteSeatInfo(seatId);
        return ResponseEntity.noContent().build();
    }

    //update seat info
    @PutMapping("/{seatId}")
    public ResponseEntity<TrainSeatDto> updateSeatInfo(
            @PathVariable Long seatId,
            @RequestBody TrainSeatDto trainSeatDto) {
        TrainSeatDto updatedSeat = trainSeatService.updateSeatInfo(seatId, trainSeatDto);
        return ResponseEntity.ok(updatedSeat);
    }


}
