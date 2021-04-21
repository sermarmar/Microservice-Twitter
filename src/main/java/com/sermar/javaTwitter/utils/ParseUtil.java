package com.sermar.javaTwitter.utils;

import com.sermar.javaTwitter.controller.dto.TweetDTO;
import com.sermar.javaTwitter.entities.TweetEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import twitter4j.Status;

@Data
@AllArgsConstructor
@Builder
public class ParseUtil {
	
	public static TweetDTO tweetEntityToDTO (Status tweet) {
		
		return TweetDTO.builder()
				.usuario(tweet.getUser().getName())
				.texto(tweet.getText())
				.localizacion(tweet.getUser().getLocation())
				.build();
		
	}

}
