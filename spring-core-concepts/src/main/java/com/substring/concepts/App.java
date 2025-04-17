package com.substring.concepts;

import com.substring.concepts.core.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//
//        Engine engine = new Engine("Ford");
//        Car tataSafari = new Car("Safari", engine);
//        tataSafari.start();
//
//
//        Engine engine2 = new Engine("Tata");
//        Car nexon = new Car("Nexon", engine2);
//        nexon.start();

//        BeanFactory

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Car car = context.getBean("car", Car.class);
        car.start();


    }
}
