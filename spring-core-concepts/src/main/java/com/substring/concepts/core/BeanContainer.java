package com.substring.concepts.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.substring.concepts.core","com.substring.concepts.config"})
public class BeanContainer {

    @Bean(name = "carBean")
    public String carName() {
        return "Tata Safari";
    }

    @Bean(name = "engineBean")
    public String engineName() {
        return "Ford";
    }


}
