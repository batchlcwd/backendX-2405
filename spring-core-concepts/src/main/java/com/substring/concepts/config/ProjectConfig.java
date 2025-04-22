package com.substring.concepts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ProjectConfig {

    @Bean
    public Student student() {
        return new Student("");
    }
}
