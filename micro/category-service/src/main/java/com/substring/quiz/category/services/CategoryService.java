package com.substring.quiz.category.services;

import com.substring.quiz.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(String categoryId, CategoryDto categoryDto);

    CategoryDto getCategory(String categoryId);

    void deleteCategory(String categoryId);

    List<CategoryDto> getAllCategories();


}
