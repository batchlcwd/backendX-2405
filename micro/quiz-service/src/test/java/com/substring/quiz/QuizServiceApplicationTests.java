package com.substring.quiz;

import com.substring.quiz.dto.CategoryDto;
import com.substring.quiz.services.CategoryFeignService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class QuizServiceApplicationTests {


    @Autowired
    private CategoryFeignService categoryFeignService;

    @Test
    public void testFeignAllCategories() {

        System.out.println("Getting all categories");
        List<CategoryDto> all = categoryFeignService.findAll();
        all.forEach(categoryDto -> System.out.println(categoryDto.getTitle()));


//        Assertions.assertEquals(4, all.size());
        Assertions.assertNotNull(all);


    }
    @Test
    public void testFeignSingleCategory() {

        System.out.println("Getting single category");
        CategoryDto categoryDto = categoryFeignService.findById("00608a0d-fa94-4fca-be09-98ee470d3948");
        System.out.println(categoryDto.getTitle());
        Assertions.assertNotNull(categoryDto);
    }

}
