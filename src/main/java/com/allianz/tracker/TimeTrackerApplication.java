package com.allianz.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.allianz.tracker"})
public class TimeTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeTrackerApplication.class, args);
	}

}
