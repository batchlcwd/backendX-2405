package com.api.gateway.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class FallbackController {

    @GetMapping("/categoryFallback")
    public Mono<String> categoryServiceFallback() {
        return Mono.just("Category service is down. Please try again later..CB");
    }
}
