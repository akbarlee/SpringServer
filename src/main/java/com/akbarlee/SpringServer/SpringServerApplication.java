package com.akbarlee.SpringServer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;


@SpringBootApplication
@EnableJpaRepositories
public class SpringServerApplication {




	public static void main(String[] args) {

		SpringApplication.run(SpringServerApplication.class, args);

	}


}
