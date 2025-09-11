package com.substring.questions.services;


import com.substring.questions.dto.QuestionDto;
import com.substring.questions.functions.QuizDto;

import java.util.List;

public interface QuestionGenerator {


    void generateAndSaveQuestions(QuizDto quizDto);

    List<QuestionDto> generateQuestions(String quizName, int numberOfQuestions, String description);

}
