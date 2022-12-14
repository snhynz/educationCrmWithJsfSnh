package com.example.educationCrm;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootApplication
public class EducationCrmWithJsfApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationCrmWithJsfApplication.class, args);
	}
}
