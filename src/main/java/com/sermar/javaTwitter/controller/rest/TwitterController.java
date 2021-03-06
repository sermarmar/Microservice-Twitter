package com.sermar.javaTwitter.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sermar.javaTwitter.controller.dto.HashtagDTO;
import com.sermar.javaTwitter.controller.dto.TweetDTO;
import com.sermar.javaTwitter.service.ITweetService;

import twitter4j.TwitterException;

//Está en desarrollo, ya que no tengo acceso a Twitter de momento, disculpa la molestia.

@RestController("/rest")
public class TwitterController {
	
	@Autowired
	ITweetService tweetService;
	
	//Este endpoint podemos consultar los tweets de usuarios que tengan más de 1500 seguidores y pueden leer en diferentes idiomas que tiene que ser español, fracés e italiano
	@GetMapping(value = "getTweets")
	public ResponseEntity getTweets(){
		
		List<TweetDTO> lstTweets = new ArrayList<>();
		
		lstTweets = tweetService.getTweets();

		return ResponseEntity.ok(lstTweets);
		
	}
	
	//Este endpoint podemos guardar un tweet seleccionado para marcar como validado y guardarlo en la base de datos.
	@PutMapping(value = "marcarTweet/{id}")
	public ResponseEntity marcatTweet(@PathVariable("id") Long id){
		
		String valid = null;
		
		try {
			valid = tweetService.marcarTweet(id);
		} catch (TwitterException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity("Se ha marcado '"+valid+"' correctamente", HttpStatus.OK);
		
	}
	
	//Este endpoint podemos consultar los tweets validados que está guardado en la base de datos
	@GetMapping(value = "getTweetsValids/{user}")
	public ResponseEntity getTweetsValids(@PathVariable("user") String user){
		List<TweetDTO> lstTweets = new ArrayList<>();
		
		try {
			lstTweets = tweetService.getTweetsValids(user);
		} catch (TwitterException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(lstTweets);
	}
	

	//Este endpoint podemos consultar las clasificaciones de hashtags más usados, solo debe recuperar 10 hashtags distintos.
	@GetMapping(value = "getHashtag")
	public ResponseEntity<List<HashtagDTO>> getHashtags(){
		List<HashtagDTO> lstHashtags = tweetService.getHashtags();
		
		return ResponseEntity.ok(lstHashtags);
	}
	

}
