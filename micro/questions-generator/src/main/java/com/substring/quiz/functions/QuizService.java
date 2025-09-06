package com.substring.quiz.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.logging.Logger;

@Slf4j
@Configuration
public class QuizService {
    private Logger logger=Logger.getLogger(QuizService.class.getName());

    @Bean(name = "getQuizBinding")
    public Consumer<QuizDto> getQuizBinding() {
        return quizDto -> {
            logger.info("Quiz created event received:");
            System.out.println("Quiz created event received:");
            System.out.println(quizDto.getTitle());
            System.out.println(quizDto.getId());
        };
    }


}
