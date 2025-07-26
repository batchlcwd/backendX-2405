package com.substring.quiz.services;

import com.substring.quiz.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "CATEGORY-SERVICE")
public interface CategoryFeignService {

    // get all the categories
    @GetMapping("/api/v1/categories")
    List<CategoryDto> findAll();

    // get single category
    @GetMapping("/api/v1/categories/{categoryId}")
    CategoryDto findById(@PathVariable String categoryId);


    //create new category
    @PostMapping("/api/v1/categories")
    CategoryDto create(@RequestBody CategoryDto categoryDto);

    //update category
    @PutMapping("/api/v1/categories/{categoryId}")
    CategoryDto update(@PathVariable String categoryId, @RequestBody CategoryDto categoryDto);

    //delete category
    @DeleteMapping("/api/v1/categories/{categoryId}")
    void delete(@PathVariable String categoryId);
}
