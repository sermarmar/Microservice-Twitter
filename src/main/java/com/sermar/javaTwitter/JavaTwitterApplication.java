package com.sermar.javaTwitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties
public class JavaTwitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTwitterApplication.class, args);
	}

}
