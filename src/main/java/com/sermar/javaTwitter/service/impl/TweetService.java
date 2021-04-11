package com.sermar.javaTwitter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sermar.javaTwitter.controller.dto.HashtagDTO;
import com.sermar.javaTwitter.controller.dto.TweetDTO;
import com.sermar.javaTwitter.entities.TweetEntity;
import com.sermar.javaTwitter.exceptions.TwitterException;
import com.sermar.javaTwitter.repository.TweetRepository;
import com.sermar.javaTwitter.service.ITweetService;
import com.sermar.javaTwitter.utils.ParseUtil;

@Service
public class TweetService implements ITweetService{
	
	@Autowired
	TweetRepository tweetRepository;

	//Allí podemos consultar los tweets desde Twitter
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

	//Debemos insertar un tweet seleccionado desde Twitter a la base de datos.
	public void marcarTweet(Long id) {
		
		Optional<TweetEntity> tweetOpt = tweetRepository.findById(id);
		if(tweetOpt.isPresent()) {
			TweetEntity tweet = tweetOpt.get();
			tweet.setValidacion(!tweet.getValidacion());
			
			tweetRepository.saveAndFlush(tweet);
		}
		else {
			throw new TwitterException("No existe el ID en Twitter");
		}
		
	}

	
	public List<TweetDTO> getTweetsValids(String user) {
		List<TweetDTO> lstTweetsDTO = new ArrayList<TweetDTO>();
		
		List<TweetEntity> lstTweets = tweetRepository.findByUsuario(user);
		
		for (TweetEntity tweetEntity : lstTweets) {
			lstTweetsDTO.add(ParseUtil.tweetEntityToDTO(tweetEntity));
		}

		return lstTweetsDTO;
	}

	public List<HashtagDTO> getHashtags() {
		// TODO Auto-generated method stub
		return null;
	}

}
