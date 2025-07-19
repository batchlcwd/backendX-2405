package com.substring.quiz.category.services.impl;

import com.substring.quiz.category.dto.CategoryDto;
import com.substring.quiz.category.entities.Category;
import com.substring.quiz.category.repositories.CategoryRepository;
import com.substring.quiz.category.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository repository;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setId(UUID.randomUUID().toString());
        Category savedCategory = repository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {

        Category category = repository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));

        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setActive(categoryDto.isActive());


        Category savedCategory = repository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);


    }

    @Override
    public CategoryDto getCategory(String categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        repository.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = repository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();

    }
}
