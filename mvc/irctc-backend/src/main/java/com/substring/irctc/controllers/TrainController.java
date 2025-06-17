package com.substring.irctc.controllers;

import com.substring.irctc.dto.PagedResponse;
import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.dto.TrainImageResponse;
import com.substring.irctc.entity.Train;
import com.substring.irctc.service.TrainImageService;
import com.substring.irctc.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
//Controller+ ResponseBody== RestController
@RequestMapping("/trains")
public class TrainController {



    private TrainService trainService;


    private TrainImageService trainImageService;

    public TrainController(TrainService trainService, TrainImageService trainImageService) {
        this.trainService = trainService;
        this.trainImageService = trainImageService;
    }


    //get all

    //    @RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping
    public PagedResponse<TrainDTO> all(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir

    ) {

        return this.trainService.all(page, size, sortBy, sortDir);

    }

    // get single

    /// trains/1234
    /// trains/34636
    /// trains?trainNo=1234

//    @GetMapping("/{trainNo}/{trainName}")
//    public Train get(@PathVariable("trainNo") String trainNo, @PathVariable String trainName) {
//        return this.trainService.get(trainNo);
//    }
    @GetMapping("/{trainNo}")
    public ResponseEntity<TrainDTO> get(@PathVariable("trainNo") String trainNo) {

        //


        return new ResponseEntity<>(this.trainService.get(trainNo), HttpStatus.OK);
    }


    //add train

    @PostMapping
    public ResponseEntity<TrainDTO> add(@Valid @RequestBody TrainDTO trainDTO) {
        return new ResponseEntity<>(this.trainService.add(trainDTO), HttpStatus.CREATED);
    }


    @DeleteMapping("/{trainNo}")
    public void delete(@PathVariable String trainNo) {
        this.trainService.delete(trainNo);
    }

//
//    //GET
//    @RequestMapping("/all")
//    @ResponseBody
//    public List<Train> listTrain(){
//
//        System.out.println("all trains is here");
//
//        Train train1=new Train();
//        train1.setTainNo("1234");
//        train1.setName("LKO-DELHI SUPERFast");
//        train1.setCoches(10);
//
//
//        Train train2=new Train();
//        train2.setTainNo("1235");
//        train2.setName("LKO-MUMBAI SUPERFast");
//        train2.setCoches(12);
//
//
//        List<Train> trainList=new ArrayList<>();
//        trainList.add(train1);
//        trainList.add(train2);
//
//

    /// /        data
//        return trainList;
//    }
//
//
//    @RequestMapping("/get-one")
//    @ResponseBody
//    public Train getTrain(){
//        Train train2=new Train();
//        train2.setTainNo("1235");
//        train2.setName("LKO-MUMBAI SUPERFast");
//        train2.setCoches(12);
//
//        return train2;
//
//    }


    //for example handing
//    @ExceptionHandler(NoSuchElementException.class)
//    public ErrorResponse handleNoSuchException(NoSuchElementException exception) {
//        ErrorResponse response
//                = new ErrorResponse("Train not found !! " + exception.getMessage(), "404", false);
//        return response;
//
//    }


    @PostMapping("/upload/{trainNo}")
    public TrainImageResponse uploadTrainImage(
            @PathVariable String trainNo,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        return trainImageService.upload(image, trainNo);
    }


}
