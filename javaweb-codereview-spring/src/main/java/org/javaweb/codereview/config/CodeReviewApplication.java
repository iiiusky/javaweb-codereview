package org.javaweb.codereview.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "org.javaweb.codereview.*")
public class CodeReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeReviewApplication.class, args);
	}

}

