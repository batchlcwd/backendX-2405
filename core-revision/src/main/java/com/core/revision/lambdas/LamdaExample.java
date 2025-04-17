package com.core.revision.lambdas;

import java.util.List;
import java.util.function.Consumer;

public class LamdaExample {
    public static void main(String[] args) {


        Cal cal = (a, b) -> a + b;

        System.out.println(cal.add(10, 20));

        List<Integer> list = List.of(1, 2, 3, 4, 5);



        list.forEach( value-> System.out.println(value) );


    }
}
