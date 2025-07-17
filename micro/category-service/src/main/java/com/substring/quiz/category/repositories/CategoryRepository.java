package com.substring.quiz.category.repositories;

import com.substring.quiz.category.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,String> {
}
