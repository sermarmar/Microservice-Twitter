package com.sermar.javaTwitter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sermar.javaTwitter.controller.dto.HashtagDTO;
import com.sermar.javaTwitter.controller.dto.TweetDTO;
import com.sermar.javaTwitter.entities.TweetEntity;
import com.sermar.javaTwitter.repository.TweetRepository;
import com.sermar.javaTwitter.repository.TwitterService;
import com.sermar.javaTwitter.service.ITweetService;
import com.sermar.javaTwitter.utils.Constants;
import com.sermar.javaTwitter.utils.ParseUtil;

import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.Query;
import twitter4j.QueryResult;
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
		
		List<TweetDTO> lstTweets = new ArrayList<TweetDTO>();
		List<TweetEntity> tweets = tweetRepository.findAll();

		if(!tweets.isEmpty()) {
			for (TweetEntity tweet : tweets) {
				lstTweets.add(ParseUtil.tweetEntityToDTO(tweet));
			}
		}

		return lstTweets;
	}

	//Debemos insertar un tweet seleccionado desde Twitter a la base de datos.
	public void marcarTweet(Long id) throws TwitterException {
		
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

	
	public List<TweetDTO> getTweetsValids(String user) throws TwitterException {
		List<TweetDTO> lstTweets = new ArrayList<TweetDTO>();
		
		List<TweetEntity> tweets = tweetRepository.findByUsuarioAndValidacion(user, true);
		
		if(!tweets.isEmpty()) {
			for (TweetEntity tweet : tweets) {
				lstTweets.add(ParseUtil.tweetEntityToDTO(tweet));
			}
		} else {
			throw new TwitterException("No tiene tweets validados");
		}

		return lstTweets;
	}

	//Es para hacer una clasisificación de hangouts.
	public List<HashtagDTO> getHashtags() {
		
		List<TweetEntity> tweets = tweetRepository.findAll();
		String hashtag = null;
		List<HashtagDTO> lstHastags = new ArrayList<HashtagDTO>();
		
		for (TweetEntity tweet : tweets) {
			String texto = tweet.getTexto();
			
			int position = texto.indexOf("#");
			while(position != -1) {
				if(texto.indexOf("#", position + 1) != -1) {
					String has = texto.substring(texto.indexOf("#", position), texto.indexOf("#", position + 1));
					hashtag = has.split(" ")[0];
				}
				else {
					if(texto.indexOf(" ", position) != -1) {
						String has = texto.substring(texto.indexOf("#", position), texto.indexOf(texto.substring(texto.indexOf(" ", position))));
						hashtag = has.split(" ")[0];
					}
					else {
						hashtag = texto.substring(texto.indexOf("#", position));
					}
				}
				
				System.out.println(hashtag);
				
				//Si no existe se guarda en la memoria
//				lstHastags.add(HashtagDTO.builder()
//						.hashtag(hashtag)
//						.contador(1)
//						.build());
				if(lstHastags.isEmpty()) {
					lstHastags.add(HashtagDTO.builder()
							.hashtag(hashtag)
							.contador(1)
							.build());
				} else {
					boolean exist = false;
					for (HashtagDTO hasDTO : lstHastags) {
						if(hasDTO.getHashtag().equals(hashtag)) {
							hasDTO.setContador(hasDTO.getContador()+1);
							exist = true;
						}
					}
					if(!exist) {
						lstHastags.add(HashtagDTO.builder()
								.hashtag(hashtag)
								.contador(1)
								.build());
					}
					
				}
				
				position = texto.indexOf("#", position + 1);
				
			}
			
		}
		
		lstHastags = lstHastags.stream().sorted((o1, o2) -> o2.getContador().compareTo(o1.getContador())).limit(10L).collect(Collectors.toList());
		
		return lstHastags;
	}

	//Sirve para guardar los tweets en la base de datos
	public void savedTweets() {
		List<Status> lstStatus = twitterService.getTweets();	

		if(!lstStatus.isEmpty()) {
			for (Status status : lstStatus) {
				if(tweetRepository.findById(status.getId()).isEmpty() && status.getUser().getFollowersCount() >= Constants.TWITTER_API_MAX_FOLLOWERS) {
					tweetRepository.saveAndFlush(ParseUtil.tweetToEntity(status));
				}
			}
		}
	}

}
