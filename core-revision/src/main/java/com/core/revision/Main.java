package com.core.revision;

public class Main {
    public static void main(String[] args) {
        System.out.println("start");

        Car car1 = new Car();

        car1.start();
        car1.display();
        car1.accelerate();
        car1.stop();

        Car car2 = new Car("blue", "audi", 200);

        car2.start();
        car2.display();
        car2.accelerate();
        car2.stop();

        Student student = new Student();


        String name = "Abc";
        String name1 = new String(name);

  

    }
}
