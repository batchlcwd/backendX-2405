package com.substring.concepts.config;

import org.springframework.beans.factory.annotation.Autowired;

public class Student {



    private String name;

    @Autowired
    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void study(){
        System.out.println("Student is studying");
    }
}

