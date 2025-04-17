package com.core.revision.optional;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {

        Optional<Object> blankOptional = Optional.empty();
        Optional<String> helloOptional = Optional.of("Hello");


        System.out.println(blankOptional.isPresent());
        System.out.println(helloOptional.isPresent());


        blankOptional.ifPresent(item -> {
            System.out.println(item);
        });

        helloOptional.ifPresent(item -> {
            System.out.println(item);
            System.out.println(item.length());
        });

        blankOptional.ifPresentOrElse(item -> {

                },
                () -> {
                    System.out.println("blankOptional is empty");
                });


    }
}
