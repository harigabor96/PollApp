package com.example.PollApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.validation.Validation;
import javax.validation.Validator;

@SpringBootApplication
public class PollAppApplication {

	@Bean
	public Validator validator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}

	public static void main(String[] args) {
		SpringApplication.run(PollAppApplication.class, args);
	}

}