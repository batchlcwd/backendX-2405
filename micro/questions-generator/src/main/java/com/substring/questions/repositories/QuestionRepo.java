package com.substring.questions.repositories;

import com.substring.questions.collections.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepo extends MongoRepository<Question,String> {
}
