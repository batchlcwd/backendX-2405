package com.substring.smartresult.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {


    @RequestMapping
    public String index() {
        System.out.println("home page");
        return "index";
    }

}
