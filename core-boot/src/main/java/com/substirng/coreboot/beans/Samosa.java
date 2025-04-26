package com.substirng.coreboot.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component("mySamosa")
public class Samosa implements InitializingBean , DisposableBean {

    private LocalDate localDate;
    private Scanner scanner;

    public Samosa() {
        System.out.println("Samosa bean is created");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
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

    @Override
    public void destroy() throws Exception {
        System.out.println("destroying samosa bean");
        this.localDate=null;
        System.out.println("date is set to: "+localDate);
        this.scanner.close();
        System.out.println("scanner is closed");
    }
}
