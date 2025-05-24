package com.substring.dynamic.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/page")
public class PageController {


    @RequestMapping("/home")
    public String home(Model model) {
        //logic
        System.out.println("home controller method executed");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String name = "Nikhil";
        model.addAttribute("currentDate", currentDateTime);
        model.addAttribute("username", name);
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        System.out.println("executing about logic");
        return "about";


    }

}
