package com.api.gateway.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private RoleConverter roleConverter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http.cors(Customizer.withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
//                        .pathMatchers("/admin/**").hasRole("ADMIN")
//                        .pathMatchers("/user/**").hasRole("USER")
//                        .pathMatchers(HttpMethod.POST, "/category/**").hasAnyRole("NORMAL", "ADMIN")
//                        .pathMatchers(HttpMethod.GET).hasAnyRole("NORMAL", "ADMIN")
//                        .pathMatchers(HttpMethod.POST).hasRole("ADMIN")
//                        .pathMatchers(HttpMethod.PUT).hasRole("ADMIN")
//                        .pathMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//                        .anyExchange()
//                        .authenticated())
                        .anyExchange().permitAll())
                .oauth2ResourceServer(
                        config -> config.jwt(jwt -> jwt.jwtAuthenticationConverter(roleExtractor())));

        return http.build();
    }

    private Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> roleExtractor() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(roleConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);


    }
}
