package com.substring.irctc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("IRCTC Clone API by BackendX")
                                .version("1.0.0")
                                .description("API documentation for IRCTC project")
                                .termsOfService("https://www.irctc.co.in/terms-of-service")
                                .contact(new io.swagger.v3.oas.models.info.Contact()
                                        .name("IRCTC Support")
                                        .url("https://www.irctc.co.in/contact-us")
                                        .email("abc@gmail.com")
                                ))
                ;

    }
}
