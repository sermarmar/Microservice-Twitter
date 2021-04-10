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

import com.sermar.javaTwitter.controller.dto.TweetDTO;
import com.sermar.javaTwitter.service.ITweetService;

@RestController("/rest")
public class TwitterController {
	
	@Autowired
	ITweetService tweetService;
	
	@GetMapping(value = "getTweets")
	public ResponseEntity<List<TweetDTO>> getTweets(){
		
		List<TweetDTO> lstTweets = new ArrayList<>();
		
		lstTweets = tweetService.getTweets();
		
		return ResponseEntity.ok(lstTweets);
		
	}
	
	@PutMapping(value = "marcarTweet/{id}")
	public ResponseEntity marcatTweet(@PathVariable("id") Long id){
		
		tweetService.getTweets();
		
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	

}
