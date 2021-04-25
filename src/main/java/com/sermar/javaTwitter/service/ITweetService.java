package com.sermar.javaTwitter.service;

import java.util.List;

import com.sermar.javaTwitter.controller.dto.HashtagDTO;
import com.sermar.javaTwitter.controller.dto.TweetDTO;

import twitter4j.TwitterException;

public interface ITweetService {
	
	public List<TweetDTO> getTweets();
	
	public String marcarTweet(Long id) throws TwitterException;
	
	public List<TweetDTO> getTweetsValids(String user) throws TwitterException;
	
	public List<HashtagDTO> getHashtags();
	
	public void savedTweets();
	
}
