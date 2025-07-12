package com.substring.irctc;

import com.substring.irctc.service.TrainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IrctcBackendApplicationTests {

    @Autowired
    private TrainService trainService;

    @Test
    void contextLoads() {

        this.trainService.getAllTrains();

    }

}
