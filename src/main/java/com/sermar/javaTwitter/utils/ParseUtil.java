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
	
	public static TweetEntity tweetToEntity (Status tweet) {
		
		return TweetEntity.builder()
				.id(tweet.getId())
				.usuario(tweet.getUser().getScreenName())
				.texto(tweet.getText())
				.localizacion(tweet.getUser().getLocation())
				.validacion(false)
				.build();
		
	}
	
	public static TweetDTO tweetEntityToDTO (TweetEntity tweet) {
		
		return TweetDTO.builder()
				.id(tweet.getId())
				.usuario(tweet.getUsuario())
				.texto(tweet.getTexto())
				.localizacion(tweet.getLocalizacion())
				.validacion(tweet.getValidacion())
				.build();
		
	}

}
