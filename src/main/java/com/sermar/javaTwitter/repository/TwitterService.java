package com.sermar.javaTwitter.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sermar.javaTwitter.utils.Constants;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterService {
	
	private Twitter twitter;
	
	public TwitterService() {
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.setDebugEnabled(true)
		.setOAuthConsumerKey(Constants.TWITTER_API_KEY)
		.setOAuthConsumerSecret(Constants.TWITTER_API_SECRET_KEY)
		.setOAuthAccessToken(Constants.TWITTER_API_ACCESS_TOKEN)
		.setOAuthAccessTokenSecret(Constants.TWITTER_API_TOKEN_SECRET);
		config.setTweetModeExtended(true);
		
		this.twitter = new TwitterFactory(config.build()).getInstance();
	}	

	public List<Status> getTweets(){
		
		Query queryES = new Query(Constants.TWITTER_API_QUERY).lang(Constants.TWITTER_API_SPAIN);
		Query queryIT = new Query(Constants.TWITTER_API_QUERY).lang(Constants.TWITTER_API_ITALIA);
		Query queryFR = new Query(Constants.TWITTER_API_QUERY).lang(Constants.TWITTER_API_FRANCE);
		
		try {
			
			List<Status> tweets = new ArrayList<>();
			tweets.addAll(this.twitter.search(queryES).getTweets());
			tweets.addAll(this.twitter.search(queryIT).getTweets());
			tweets.addAll(this.twitter.search(queryFR).getTweets());
			return tweets;
		} catch (TwitterException e) {
			return new ArrayList<>();
		}
		
		
	}


}
