package com.substring.questions.functions;

import com.substring.questions.services.QuestionGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.logging.Logger;

@Slf4j
@Configuration
public class QuizService {

    @Autowired
    private QuestionGenerator questionGenerator;
    private Logger logger=Logger.getLogger(QuizService.class.getName());

    @Bean(name = "getQuizBinding")
    public Function<QuizDto,String> getQuizBinding() {
        return quizDto -> {
            logger.info("Quiz created event received:");
            System.out.println("Quiz created event received:");
            System.out.println(quizDto.getTitle());
            System.out.println(quizDto.getId());
            this.questionGenerator.generateAndSaveQuestions(quizDto);
            return "Quiz created successfully";

        };
    }



}
