package com.substring.dynamic.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public String about(){
        System.out.println("executing about logic");



        return "about";
    }

}
