package com.docker.exmaple.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/demo")
public class DockerExampleController {

    @Value("${app.data.value}")
    private String data;

    @GetMapping
    public String getDemo() {
        return "Hello Docker";

    }

    @GetMapping("/data")
    public Map<String, Object> getDemo2() {
        return Map.of("message", data);
    }

}
