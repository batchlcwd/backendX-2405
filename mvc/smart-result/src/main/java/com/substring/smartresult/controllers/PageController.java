package com.substring.smartresult.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {


    @RequestMapping
    public String index() {
//        HttpSession session = request.getSession();
//        session.setAttribute("username", "JohnDoe");
//

        System.out.println("home page");
        return "index";
    }

    @GetMapping("/login-page")
    public String loginPage(){
        return  "login_page";
    }

}
