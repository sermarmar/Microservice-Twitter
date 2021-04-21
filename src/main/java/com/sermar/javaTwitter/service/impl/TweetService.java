package com.sermar.javaTwitter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sermar.javaTwitter.conexion.TwitterService;
import com.sermar.javaTwitter.controller.dto.HashtagDTO;
import com.sermar.javaTwitter.controller.dto.TweetDTO;
import com.sermar.javaTwitter.entities.TweetEntity;
import com.sermar.javaTwitter.repository.TweetRepository;
import com.sermar.javaTwitter.service.ITweetService;
import com.sermar.javaTwitter.utils.ParseUtil;

import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterResponse;
import twitter4j.User;
import twitter4j.api.FriendsFollowersResources;
import twitter4j.api.TimelinesResources;
import twitter4j.api.TweetsResources;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TweetService implements ITweetService{
	
	@Autowired
	TweetRepository tweetRepository;
	
	@Autowired
	TwitterService twitterService;

	//Allí podemos consultar los tweets desde Twitter
	public List<TweetDTO> getTweets() throws TwitterException {
		
		List<TweetDTO> lstTweetsDTO = new ArrayList<TweetDTO>();
		
		
		ConfigurationBuilder cb = twitterService.conectarTwitter();	
		Twitter tf = new TwitterFactory(cb.build()).getInstance();
		
		List<Status> lstStatus = tf.getUserTimeline();
		TweetsResources tweets = tf.tweets();
		FriendsFollowersResources friends = tf.friendsFollowers();
		TimelinesResources timeLines = tf.timelines();

		/*if(!lstStatus.isEmpty()) {
			for (Status status : lstStatus) {
				lstTweetsDTO.add(ParseUtil.tweetEntityToDTO(status));
			}
		}*/
		
		/*if(!lstStatus.isEmpty()) {
			for (Status status : tweets.lookup(1350165423L)) {
				lstTweetsDTO.add(ParseUtil.tweetEntityToDTO(status));
			}
		}*/
		
		PagableResponseList<User> ids = tf.getBlocksList();
		ids.toArray();
		
		
		if(!timeLines.getUserTimeline().isEmpty()) {
			for (Status status : timeLines.getUserTimeline()) {
				lstTweetsDTO.add(ParseUtil.tweetEntityToDTO(status));
			}
		}
		
		/*List<TweetEntity> lstTweets = tweetRepository.findAll();
		
		for (TweetEntity tweetEntity : lstTweets) {
			lstTweetsDTO.add(ParseUtil.tweetEntityToDTO(tweetEntity));
		}*/

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
			//throw new TwitterException("No existe el ID en Twitter");
		}
		
	}

	
	public List<TweetDTO> getTweetsValids(String user) {
		List<TweetDTO> lstTweetsDTO = new ArrayList<TweetDTO>();
		
		List<TweetEntity> lstTweets = tweetRepository.findByUsuario(user);
		
		/*for (TweetEntity tweetEntity : lstTweets) {
			lstTweetsDTO.add(ParseUtil.tweetEntityToDTO(tweetEntity));
		}*/

		return lstTweetsDTO;
	}

	public List<HashtagDTO> getHashtags() {
		// TODO Auto-generated method stub
		return null;
	}

}
