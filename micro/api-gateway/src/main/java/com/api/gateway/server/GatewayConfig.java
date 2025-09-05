
package com.api.gateway.server;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("category-service", route -> route.path("/category/**")
                        .filters(f ->
                                f.rewritePath("/category/?(?<remaining>.*)", "/${remaining}")
                                        .circuitBreaker(c -> c.setName("categoryCB").setFallbackUri("forward:/categoryFallback"))
                                        .requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver())
                                        )


                        )
                        .uri("lb://CATEGORY-SERVICE")
                )
                .route("quiz-service", route -> route.path("/quiz/**")
                        .filters(f -> f.rewritePath("/quiz/?(?<remaining>.*)", "/${remaining}")
                                .retry(retry ->
                                        retry.setMethods(HttpMethod.GET)
                                                .setRetries(3)
                                                .setBackoff(Duration.ofMillis(50), Duration.ofMillis(600), 2, true))
                                .requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver()))
                        )

                        .uri("lb://QUIZ-SERVICE"))


                .build();
    }


    @Bean
    public KeyResolver userKeyResolver() {

        return exchange -> {
            System.out.println(exchange.getRequest().getHeaders().getHost().getHostName());
            return Mono.just(exchange.getRequest().getHeaders().getHost().getHostName());
        };
    }


    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(5, 5);
    }
}
