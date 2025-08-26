package com.substring.quiz.controllers;

import com.substring.quiz.dto.QuizDto;
import com.substring.quiz.services.QuizService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

    private final QuizService quizService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    //create quiz
    @PostMapping
    public ResponseEntity<QuizDto> create(@RequestBody QuizDto quizDto) {
        return new ResponseEntity<>(quizService.create(quizDto), HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{quizId}")
    public ResponseEntity<QuizDto> update(@PathVariable("quizId") String quizId, @RequestBody QuizDto quizDto) {
        return new ResponseEntity<>(quizService.update(quizId, quizDto), HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> delete(@PathVariable("quizId") String quizId) {
        quizService.delete(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get all
//    int i = 0;

    @GetMapping
    public ResponseEntity<List<QuizDto>> findAll() {

//        logger.info("Fetching all quizzes {}", i);
//        i++;
//        if (i < 3) {
//            throw new RuntimeException("Quiz service is down. Please try again later..CB");
//
//        } else {
//            return new ResponseEntity<>(quizService.findAll(), HttpStatus.OK);
//        }


        return new ResponseEntity<>(quizService.findAll(), HttpStatus.OK);
    }


//    public ResponseEntity<List<QuizDto>> quizFallback(Throwable e) {
//        System.out.println("Quiz service is down. Please try again later..CB");
//        return new ResponseEntity<>(List.of(), HttpStatus.SERVICE_UNAVAILABLE);
//    }


    //get by id
    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDto> findById(@PathVariable("quizId") String quizId) {
        return new ResponseEntity<>(quizService.findById(quizId), HttpStatus.OK);
    }


    //get by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<QuizDto>> findByCategory(@PathVariable("categoryId") String categoryId) {
        return new ResponseEntity<>(quizService.findByCategory(categoryId), HttpStatus.OK);
    }


}
