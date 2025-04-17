package com.core.revision;

public class Car {

    //properties--> jab ap car ka design karoge. [car ka design]
    private String color;
    private String model;
    private int speed;

    String brand;


    //constructor--> jab ap actual product banaoge. [car]

    public Car() {
        this.color = "red";
        this.model = "BMW";
        this.speed = 100;
    }

    public Car(String color, String model, int speed) {
        this.color = color;
        this.model = model;
        this.speed = speed;
    }

    //behavior--> jab ap car ka use karoge. [car ka use]
    public void start() {
        System.out.println("Car is starting");
        System.out.println(this.model + "car started: ");
    }

    public void stop() {
        System.out.println("Car is stopping");
        System.out.println(this.model + "car stopped: ");
    }

    public void accelerate() {
        System.out.println("Car is accelerating");
        System.out.println(this.model + "car accelerated: ");
    }

    public void brake() {
        System.out.println("Car is braking");
        System.out.println(this.model + "car braked: ");
    }

    public void display() {
        System.out.println("Car color: " + this.color);
        System.out.println("Car model: " + this.model);
        System.out.println("Car speed: " + this.speed);
    }

}
