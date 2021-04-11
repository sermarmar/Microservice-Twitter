package com.sermar.javaTwitter.service;

import java.util.List;

import com.sermar.javaTwitter.controller.dto.HashtagDTO;
import com.sermar.javaTwitter.controller.dto.TweetDTO;

public interface ITweetService {
	
	public List<TweetDTO> getTweets();
	
	public void marcarTweet(Long id);
	
	public List<TweetDTO> getTweetsValids(String user);
	
	public List<HashtagDTO> getHashtags();
	
}
