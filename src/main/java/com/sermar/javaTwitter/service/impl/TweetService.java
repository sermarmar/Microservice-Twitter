package com.sermar.javaTwitter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sermar.javaTwitter.controller.dto.TweetDTO;
import com.sermar.javaTwitter.entities.TweetEntity;
import com.sermar.javaTwitter.repository.TweetRepository;
import com.sermar.javaTwitter.service.ITweetService;
import com.sermar.javaTwitter.utils.ParseUtil;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Service
public class TweetService implements ITweetService{
	
	@Autowired
	TweetRepository tweetRepository;

	public List<TweetDTO> getTweets() {
		
		List<TweetDTO> lstTweetsDTO = new ArrayList<TweetDTO>();
		
		/*Twitter twitter = TwitterFactory.getSingleton();
		List<Status> lstStatuses = twitter.getHomeTimeline();
		for (Status status : lstStatuses) {
			status.
		}*/
		
		List<TweetEntity> lstTweets = tweetRepository.findAll();
		
		for (TweetEntity tweetEntity : lstTweets) {
			lstTweetsDTO.add(ParseUtil.tweetEntityToDTO(tweetEntity));
		}
		
		
		return lstTweetsDTO;
	}

	public Long marcarTweet(Long id) {
		
		
		
		return null;
	}

}
