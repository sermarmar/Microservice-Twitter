package com.sermar.javaTwitter.service;

import java.util.List;

import com.sermar.javaTwitter.controller.dto.HashtagDTO;
import com.sermar.javaTwitter.controller.dto.TweetDTO;

import twitter4j.TwitterException;

public interface ITweetService {
	
	public List<TweetDTO> getTweets() throws TwitterException;
	
	public void marcarTweet(Long id);
	
	public List<TweetDTO> getTweetsValids(String user);
	
	public List<HashtagDTO> getHashtags();
	
}
