package com.substring.quiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient

public class QuestionsGenerator implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(QuestionsGenerator.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
