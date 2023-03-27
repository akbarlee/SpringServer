package com.akbarlee.SpringServer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class SpringServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringServerApplication.class, args);

	}


}
