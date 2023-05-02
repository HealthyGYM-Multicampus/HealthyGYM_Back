package com.mul.HealthyGYM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class SpringBootReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactApplication.class, args);
	}

}
