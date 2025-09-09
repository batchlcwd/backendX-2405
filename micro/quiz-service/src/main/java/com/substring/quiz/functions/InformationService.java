package com.substring.quiz.functions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Configuration
public class InformationService {

    @Bean
    public Supplier<String> getInformation() {
        return () -> "This is quiz information working for quiz app";
    }

    @Bean
    public Function<Information, String> consumeInformation() {
        return (information -> {
            System.out.println(information.name());
            System.out.println(information.phone());
            return "Information processed name : " + information.name() + " phone: " + information.phone();
        });
    }


    // receive acknowledgement
    @Bean
    public Consumer<String> acknowledgeInformation() {
        return information->{
            System.out.println("acknowledgement received");
            System.out.println(information);
        };
    }


}


