package com.example.WorkHoursManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WorkHoursManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkHoursManagerApplication.class, args);
	}

}
