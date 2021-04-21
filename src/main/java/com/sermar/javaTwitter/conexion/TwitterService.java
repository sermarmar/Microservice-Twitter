package com.sermar.javaTwitter.conexion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterService {
	
	@Value("${key}")
	private String key;
	@Value("${secret}")
	private String secret;
	@Value("${token}")
	private String token;
	@Value("${tsecret}")
	private String tokenSecret;
	
	public ConfigurationBuilder conectarTwitter() {
		
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.setDebugEnabled(true);
		config.setOAuthConsumerKey(this.key);
		config.setOAuthConsumerSecret(this.secret);
		config.setOAuthAccessToken(this.token);
		config.setOAuthAccessTokenSecret(this.tokenSecret);

		return config;
	}


}
