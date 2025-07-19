package com.substring.quiz.repositories;

import com.substring.quiz.collections.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    List<Quiz> findByTitle(String title);
    List<Quiz> findByCategoryId(String categoryId);
}
