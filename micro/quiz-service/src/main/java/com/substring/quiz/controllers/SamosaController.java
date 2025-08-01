package com.substring.quiz.controllers;

import com.substring.quiz.dto.CategoryDto;
import com.substring.quiz.services.CategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/samosa")
public class SamosaController {


    private final CategoryService categoryService;

    public SamosaController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }


}
