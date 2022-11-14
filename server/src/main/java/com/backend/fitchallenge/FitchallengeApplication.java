package com.backend.fitchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FitchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitchallengeApplication.class, args);
	}

}
