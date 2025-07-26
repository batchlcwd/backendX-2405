package com.substring.quiz;

import com.substring.quiz.collections.Quiz;
import com.substring.quiz.repositories.QuizRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class QuizServiceApplication implements CommandLineRunner {

	@Autowired
	private QuizRepository quizRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Quiz quiz = Quiz.builder()
//				.id(UUID.randomUUID().toString())
//				.title("Python basic quiz")
//				.description("This is python basics quiz")
//				.maxMarks(100)
//				.timeLimit(30)
//				.createdBy("Durgesh")
//				.noOfQuestions(10)
//				.imageUrl("https://images.unsplash.com/photo-1498050108023-c5249f4df085?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80")
//				.live(true)
//				.passingMarks(30)
//				.build();
//
//
//		Quiz saved = quizRepository.save(quiz);
//		System.out.println("quiz saved with id "+saved.getId());


	}
}
