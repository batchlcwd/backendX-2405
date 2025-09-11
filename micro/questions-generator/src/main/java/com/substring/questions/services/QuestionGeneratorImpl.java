package com.substring.questions.services;


import com.substring.questions.repositories.QuestionRepo;
import com.substring.questions.collections.Question;
import com.substring.questions.dto.QuestionDto;
import com.substring.questions.functions.QuizDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionGeneratorImpl implements QuestionGenerator {

    private Logger logger = LoggerFactory.getLogger(QuestionGeneratorImpl.class);

    private QuestionRepo questionRepository;

    private ChatClient chatClient;

    private ModelMapper modelMapper;

    public QuestionGeneratorImpl(ChatClient.Builder builder, QuestionRepo questionRepository, ModelMapper modelMapper) {
        this.chatClient = builder.build();
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void generateAndSaveQuestions(QuizDto quizDto) {

        List<QuestionDto> questionDtos = this.generateQuestions(quizDto.getTitle(), 10, quizDto.getDescription());
        List<Question> questionDtoList = questionDtos.stream().map(questionDto -> {
            questionDto.setQuizId(quizDto.getId());
            return this.modelMapper.map(questionDto, Question.class);
        }).toList();
        questionRepository.saveAll(questionDtoList);
        this.logger.info("Questions saved successfully");
        questionDtoList.forEach(e -> logger.info(e.getQuestion()));


    }

    @Override
    public List<QuestionDto> generateQuestions(String quizName, int numberOfQuestions, String description) {


        String systemString = """
                As as Coding,Technology,Programing and Frameworks expert, your primary role is to generate high-quality questionDtos for quizzes.
                """;

        String promptString = """
                Generate {numberOfQuestions} questionDtos for {quizName} quiz.
                Having description: {description}
                """;

        Map<String, Object> valuesForPrompt = Map.of(
                "numberOfQuestions", numberOfQuestions,
                "quizName", quizName,
                "description", description
        );


        return this.chatClient
                .prompt()
                .system(systemString)
                .user(userSpec -> userSpec.text(promptString).params(valuesForPrompt))
                .call()
                .entity(new ParameterizedTypeReference<List<QuestionDto>>() {
                });
    }
}
