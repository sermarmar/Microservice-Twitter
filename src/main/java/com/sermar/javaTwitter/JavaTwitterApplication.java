package com.sermar.javaTwitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.sermar.javaTwitter.service.impl.TweetService;

@SpringBootApplication
@ConfigurationProperties
@EnableScheduling
public class JavaTwitterApplication {
	
	@Autowired
	TweetService tweetService;
	

	public static void main(String[] args) {
		
		SpringApplication.run(JavaTwitterApplication.class, args);
		
	}
	
	@Scheduled(fixedDelay = 600000, initialDelay = 3000)
	public void savedTwitter() {
		System.out.println("Iniciando el guardado de Tweets");
		tweetService.savedTweets();
		System.out.println("Fin de tweets");
	}

}
