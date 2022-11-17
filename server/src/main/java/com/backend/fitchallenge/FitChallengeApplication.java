package com.backend.fitchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FitChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitChallengeApplication.class, args);
	}

}
