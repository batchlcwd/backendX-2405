package com.substring.quiz.category.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/config")
public class ConfigController {
    @Value("${config.value}")
    private  String configValue;


    @GetMapping
    public String configValue(){
        System.out.println("Config value is "+configValue);
        return  configValue;
    }
}
