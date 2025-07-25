package com.substring.quiz.services;

import com.substring.quiz.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "category-service", url = "http://localhost:9091/api/v1")
public interface CategoryFeignService {

    // get all the categories
    @GetMapping("/categories")
    List<CategoryDto> findAll();

    // get single category
    @GetMapping("/categories/{categoryId}")
    CategoryDto findById(@PathVariable String categoryId);


    //create new category
    @PostMapping("/categories")
    CategoryDto create(@RequestBody CategoryDto categoryDto);

    //update category
    @PutMapping("/categories/{categoryId}")
    CategoryDto update(@PathVariable String categoryId, @RequestBody CategoryDto categoryDto);

    //delete category
    @DeleteMapping("/categories/{categoryId}")
    void delete(@PathVariable String categoryId);
}
