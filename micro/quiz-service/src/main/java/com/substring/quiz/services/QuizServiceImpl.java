package com.substring.quiz.services;

import com.substring.quiz.collections.Quiz;
import com.substring.quiz.dto.CategoryDto;
import com.substring.quiz.dto.QuizDto;
import com.substring.quiz.repositories.QuizRepository;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(QuizServiceImpl.class);

    private final QuizRepository quizRepository;

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;

    private final CategoryService categoryService;

    private final CategoryFeignService categoryFeignService;

    private StreamBridge streamBridge;

    public QuizServiceImpl(QuizRepository quizRepository, ModelMapper modelMapper, RestTemplate restTemplate, CategoryService categoryService, CategoryFeignService categoryFeignService, StreamBridge streamBridge) {
        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.categoryService = categoryService;
        this.categoryFeignService = categoryFeignService;
        this.streamBridge = streamBridge;
    }

    @Override
    public QuizDto create(QuizDto quizDto) {

        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        quiz.setId(UUID.randomUUID().toString());

        // validate category:
        String url = "lb://CATEGORY-SERVICE/api/v1/categories/" + quizDto.getCategoryId();
        logger.info(url);
        // call to category service
        CategoryDto category = restTemplate.getForObject(url, CategoryDto.class);
        logger.info("category exists: " + category.getTitle());

        Quiz savedQuiz = quizRepository.save(quiz);
        QuizDto quizDto1 = modelMapper.map(savedQuiz, QuizDto.class);
        quizDto1.setCategoryDto(category);


        //quiz created event ko publish kar diya
        publishQuizCreatedEvent(quizDto1);

        return quizDto1;


    }


    //event public
    private void publishQuizCreatedEvent(QuizDto quizDto) {
        logger.info("Quiz created going to publish quiz created event:");
        var status = this.streamBridge.send("quizCreatedBinding-out-0", quizDto);
        if (status)
            logger.info("event is sent to broker");
        else
            logger.info("event is not sent to  broker");
    }

    @Override
    public QuizDto update(String quizId, QuizDto quizDto) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setTitle(quizDto.getTitle());
        quiz.setDescription(quizDto.getDescription());
        quiz.setMaxMarks(quizDto.getMaxMarks());
        quiz.setTimeLimit(quizDto.getTimeLimit());
        quiz.setCreatedBy(quizDto.getCreatedBy());
        quiz.setNoOfQuestions(quizDto.getNoOfQuestions());
        quiz.setImageUrl(quizDto.getImageUrl());
        quiz.setLive(quizDto.getLive());
        quiz.setPassingMarks(quizDto.getPassingMarks());
        quiz.setCategoryId(quizDto.getCategoryId());
        Quiz updatedQuiz = quizRepository.save(quiz);
        QuizDto quizDto1 = modelMapper.map(updatedQuiz, QuizDto.class);
        return quizDto1;


    }

    @Override
    public void delete(String quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quizRepository.delete(quiz);
    }

    @Override

    public List<QuizDto> findAll() {
        List<Quiz> all = quizRepository.findAll();


        // getting category of all quiz


        List<QuizDto> quizDtos = all.stream().map(quiz -> {
            String categoryId = quiz.getCategoryId();
            QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
            //call to quiz service using webclient
            CategoryDto categoryDto = this.categoryService.findById(categoryId);
            quizDto.setCategoryDto(categoryDto);
            return quizDto;
        }).toList();


        return quizDtos;
    }


    @Override
    @CircuitBreaker(name = "quizCB", fallbackMethod = "quizFallback")
    public QuizDto findById(String quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        String categoryId = quiz.getCategoryId();

        String url = "lb://CATEGORY-SERVICE/api/v1/categories/afafa" + categoryId;
        logger.info(url);
        // call to category service
        CategoryDto category = restTemplate.getForObject(url, CategoryDto.class);
        //Thread waits for response
        logger.info("category exists: {}", category.getTitle());
        logger.info("Call completed");

        quizDto.setCategoryDto(category);
        return quizDto;
    }

    public QuizDto quizFallback(String quizId, Throwable t) {

        logger.error("Category not found");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitle("Fallback category");
        return new QuizDto();
    }


    @Override
    public List<QuizDto> findByCategory(String categoryId) {
        List<Quiz> all = quizRepository.findByCategoryId(categoryId);
        return all.stream().map(quiz -> {
            QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
            ///  call category service to get category and put in category dto
            CategoryDto categoryDto = null;
            try {
                categoryDto = categoryFeignService.findById(quizDto.getCategoryId());
            } catch (FeignException.NotFound ex) {
                logger.error("category not found");
            }
            quizDto.setCategoryDto(categoryDto);
            return quizDto;

        }).toList();


    }
}
