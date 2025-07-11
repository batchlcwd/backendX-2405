package com.substring.irctc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "IRCTC Clone API by BackendX",
        version = "1.0.0",
        description = "API documentation for IRCTC project",
        termsOfService = "https://www.irctc.co.in/terms-of-service",
        contact = @Contact(
                name = "IRCTC Support",
                url = "https://www.irctc.co.in/contact-us",
                email = "abc@gmail.com"
        )

)
        ,
        security = @SecurityRequirement(name="bearerAuth")
)

@SecurityScheme(
        name = "bearerAuth",
        type= SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)

public class OpenApiConfig {

//    @Bean
//    public OpenAPI openAPI() {
//
//        return new OpenAPI()
//                .info(
//                        new Info()
//                                .title("IRCTC Clone API by BackendX")
//                                .version("1.0.0")
//                                .description("API documentation for IRCTC project")
//                                .termsOfService("https://www.irctc.co.in/terms-of-service")
//                                .contact(new io.swagger.v3.oas.models.info.Contact()
//                                        .name("IRCTC Support")
//                                        .url("https://www.irctc.co.in/contact-us")
//                                        .email("abc@gmail.com")
//                                ))
//                ;
//
//    }
}
