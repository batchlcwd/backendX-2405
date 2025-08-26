package com.substring.quiz.services;

import com.substring.quiz.dto.CategoryDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class CategoryServiceWebclientImpl implements CategoryService {

    private final RestTemplate restTemplate;

    private final WebClient webClient;

    private final WebClient.Builder webClientBuilder;

    private ModelMapper modelMapper;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CategoryServiceWebclientImpl.class);

    public CategoryServiceWebclientImpl(RestTemplate restTemplate, WebClient.Builder webClientBuilder, ModelMapper modelMapper) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
        this.modelMapper = modelMapper;
        this.webClient = webClientBuilder.baseUrl("lb://CATEGORY-SERVICE").build();

    }

    @Override
    @CircuitBreaker(name = "quizCB", fallbackMethod = "quizFallback")

    public CategoryDto findById(String categoryId) {

        CategoryDto category = this.webClient
                .get()
                .uri("/api/v1/categories/{categoryId}", categoryId)
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .block();
        return category;

    }


    public CategoryDto quizFallback(String categoryId, Throwable t) {

        logger.error("Category not found");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitle("Fallback category");
        return categoryDto;
    }


    @Override
    public List<CategoryDto> findAll() {
        return this.webClient
                .get()
                .uri("/api/v1/categories")
                .retrieve()
                .bodyToFlux(CategoryDto.class)
                .collectList()
                .block();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        return this.webClient
                .post()
                .uri("/api/v1/categories")
                .bodyValue(categoryDto)
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .block();

    }

    @Override
    public CategoryDto update(String categoryId, CategoryDto categoryDto) {
        return this.webClient
                .put()
                .uri("/api/v1/categories/{categoryId}", categoryId)
                .bodyValue(categoryDto)
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .block();
    }

    @Override
    public void delete(String categoryId) {
        this.webClient.delete()
                .uri("/api/v1/categories/{categoryId}", categoryId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
