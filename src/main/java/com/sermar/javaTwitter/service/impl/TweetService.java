package com.sermar.javaTwitter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sermar.javaTwitter.conexion.TwitterService;
import com.sermar.javaTwitter.controller.dto.HashtagDTO;
import com.sermar.javaTwitter.controller.dto.TweetDTO;
import com.sermar.javaTwitter.entities.TweetEntity;
import com.sermar.javaTwitter.exceptions.TwitterException;
import com.sermar.javaTwitter.repository.TweetRepository;
import com.sermar.javaTwitter.service.ITweetService;
import com.sermar.javaTwitter.utils.ParseUtil;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TweetService implements ITweetService{
	
	@Autowired
	TweetRepository tweetRepository;
	
	@Autowired
	TwitterService twitterService;
	
	@Value("${key}")
	private String key;
	@Value("${secret}")
	private String secret;
	@Value("${token}")
	private String token;
	@Value("${tsecret}")
	private String tokenSecret;

	//Allí podemos consultar los tweets desde Twitter
	public List<TweetDTO> getTweets() {
		
		List<TweetDTO> lstTweetsDTO = new ArrayList<TweetDTO>();
		
		
		//ConfigurationBuilder cb = twitterService.conectarTwitter();	
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.setDebugEnabled(true);
		config.setOAuthConsumerKey(this.key);
		config.setOAuthConsumerSecret(this.secret);
		config.setOAuthAccessToken(this.token);
		config.setOAuthAccessTokenSecret(this.tokenSecret);
		Twitter tf = new TwitterFactory(config.build()).getInstance();
		
		List<Status> lstStatus = new ArrayList<Status>();
		List<User> users = new ArrayList<User>();
		try {
			lstStatus = tf.getUserTimeline("snap");
		} catch (twitter4j.TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
