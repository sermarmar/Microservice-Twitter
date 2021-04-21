package com.sermar.javaTwitter.conexion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sermar.javaTwitter.utils.Constants;

import twitter4j.conf.ConfigurationBuilder;

@Component
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
		
//		ConfigurationBuilder config = new ConfigurationBuilder();
//		config.setDebugEnabled(true)
//		.setOAuthConsumerKey(this.key)
//		.setOAuthConsumerSecret(this.secret)
//		.setOAuthAccessToken(this.token)
//		.setOAuthAccessTokenSecret(this.tokenSecret);
//		config.setTweetModeExtended(true);
//
//		return config;
		
//		ConfigurationBuilder config = new ConfigurationBuilder();
//		config.setDebugEnabled(true)
//		.setOAuthConsumerKey("HczroUeChuBOs1cf3drj4iHkc")
//		.setOAuthConsumerSecret("kHOxoeFgAnaKg9Vc7eUxpO5AL5q1zmjdy7jtTjJUf8ihTtzeK2")
//		.setOAuthAccessToken("3430250164-L5VqyDrnj8XNhLNfjXqkD1JOUgF3CR4DrLLwONp")
//		.setOAuthAccessTokenSecret("ACmXgPPxmg1MBAxjDuylYZ5O2Tmc9MY5qtMQ5GL1NeQo3");
//		config.setTweetModeExtended(true);
//		
//		return config;
		
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.setDebugEnabled(true)
		.setOAuthConsumerKey(Constants.TWITTER_API_KEY)
		.setOAuthConsumerSecret(Constants.TWITTER_API_SECRET_KEY)
		.setOAuthAccessToken(Constants.TWITTER_API_ACCESS_TOKEN)
		.setOAuthAccessTokenSecret(Constants.TWITTER_API_TOKEN_SECRET);
		config.setTweetModeExtended(true);
		
		return config;
	}


}
