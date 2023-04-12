package com.akbarlee.SpringServer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class SpringServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringServerApplication.class, args);

	}


}
