package com.sermar.javaTwitter.service;

import java.util.List;

import com.sermar.javaTwitter.controller.dto.TweetDTO;

public interface ITweetService {
	
	public List<TweetDTO> getTweets();
	
	public Long marcarTweet(Long id);
	
}
