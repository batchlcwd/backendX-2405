package com.substring.irctc.controllers;

import com.substring.irctc.entity.Train;
import com.substring.irctc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Controller+ ResponseBody== RestController
@RequestMapping("/trains")
public class TrainController {


    @Autowired
    private TrainService trainService;


    //get all

    //    @RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping
    public List<Train> all() {
        return this.trainService.all();
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
    public Train get(@PathVariable("trainNo") String trainNo) {
        return this.trainService.get(trainNo);
    }


    //add train

    @PostMapping
    public Train add(@RequestBody Train train) {
        return this.trainService.add(train);
    }


    @DeleteMapping("/{trainNo}")
    public void delete(@PathVariable String trainNo){
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
////        data
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

}
