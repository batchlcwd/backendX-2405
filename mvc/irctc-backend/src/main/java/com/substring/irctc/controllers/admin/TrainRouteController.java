package com.substring.irctc.controllers.admin;

import com.substring.irctc.dto.TrainRouteDto;
import com.substring.irctc.service.TrainRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-routes")
public class TrainRouteController {

    private TrainRouteService trainRouteService;

    public TrainRouteController(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }


    //create train route
    @PostMapping
    public ResponseEntity<TrainRouteDto> createTrainRoute(

            @RequestBody  TrainRouteDto trainRouteDto)
    {
        TrainRouteDto createdRoute = trainRouteService.addRoute(trainRouteDto);
        return ResponseEntity.status(201).body(createdRoute);
    }

    // List train routes by train ID
     @GetMapping("/train/{trainId}")
    public ResponseEntity<List<TrainRouteDto>> getRoutesByTrain(@PathVariable Long trainId) {
         List<TrainRouteDto> routes = trainRouteService.getRoutesByTrain(trainId);
         return ResponseEntity.ok(routes);
     }


    // Update train route
    @PutMapping("/{id}")
    public ResponseEntity<TrainRouteDto> updateTrainRoute(
            @PathVariable Long id,
            @RequestBody TrainRouteDto trainRouteDto) {
        TrainRouteDto updatedRoute = trainRouteService.updateRoute(id, trainRouteDto);
        return ResponseEntity.ok(updatedRoute);
    }

    // Delete train route
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainRoute(@PathVariable Long id) {
        trainRouteService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }


    // Additional methods for train route management can be added here



}
