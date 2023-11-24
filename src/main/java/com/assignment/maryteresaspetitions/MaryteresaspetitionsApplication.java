package com.assignment.maryteresaspetitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MaryteresaspetitionsApplication {

	// This REST endpoint method is not required for any of the features of Petitions application,
	// however is left here to confirm the application works when packaged as a war file.
	@RequestMapping("/hello")
	public String hello2() {
		return "Hello Again";
	}


	public static void main(String[] args) {
		SpringApplication.run(MaryteresaspetitionsApplication.class, args);
	}

}
