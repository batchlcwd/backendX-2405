package com.substring.smartresult.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/results")
public class ResultController {


    // roll number
    @RequestMapping("/input")
    public String viewResult() {
        System.out.println("Results page");
        return "view_result";
    }



    @RequestMapping("/view")
    public String getResult() {
        // Logic to fetch and return the result
        return "result_data"; // Placeholder for actual result data
    }


    @RequestMapping("/add")
    public String addResult() {
        // Logic to fetch and return the result
        return "add_result"; // Placeholder for actual result data
    }


}
