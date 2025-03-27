package com.devathon.slytherin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages = "com.devathon.slytherin.model")
//@EnableJpaRepositories(basePackages = "com.devathon.slytherin.repositories")
//@ComponentScan(basePackages = "com.devathon.slytherin")
public class MarketPlaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketPlaceApplication.class, args);
	}

}
