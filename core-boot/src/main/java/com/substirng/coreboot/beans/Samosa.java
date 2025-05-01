package com.substirng.coreboot.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component("mySamosa")
@Scope("application") // prototype scope
public class Samosa  {

    private LocalDate localDate;
    private Scanner scanner;

    public Samosa() {
        System.out.println("Samosa bean is created");
    }



    @PostConstruct
    public void init() throws Exception {
        this.localDate=LocalDate.now();
        System.out.println("date is set to: "+localDate);
        this.scanner=new Scanner(System.in);
        System.out.println("scanner is created");
        System.out.println(("======================"));
    }

    public void eat(){
        System.out.println("you are eating samosa");
        System.out.println("enter your name");
        String name = scanner.next();
        System.out.println(name+" is eating samosa");
        System.out.println("----------------------");
    }


    @PreDestroy
    public void cleanup() throws Exception {
        System.out.println("destroying samosa bean");
        this.localDate=null;
        System.out.println("date is set to: "+localDate);
        this.scanner.close();
        System.out.println("scanner is closed");
    }

//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//
//        if(bean instanceof Samosa){
//            System.out.println("pre- samosa bean");
//        }
//
//
//        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//
//        if(bean instanceof Samosa){
//            System.out.println("post-- samosa bean");
//        }
//   return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
//    }
}
