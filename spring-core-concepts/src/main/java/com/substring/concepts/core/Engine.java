package com.substring.concepts.core;

public class Engine {

    private  String companyName;

    public Engine(String companyName) {
        this.companyName = companyName;
    }

    public Engine() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void start() {


        System.out.println(companyName+" engine starting");
        System.out.println("Engine started");
        System.out.println(
                "----------------------\n"
        );
    }


}
