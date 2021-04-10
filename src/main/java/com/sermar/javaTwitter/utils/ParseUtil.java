package com.sermar.javaTwitter.utils;

import com.sermar.javaTwitter.controller.dto.TweetDTO;
import com.sermar.javaTwitter.entities.TweetEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ParseUtil {
	
	public static TweetDTO tweetEntityToDTO (TweetEntity tweet) {
		
		TweetDTO tweetDTO = TweetDTO.builder()
				.usuario(tweet.getUsuario())
				.texto(tweet.getTexto())
				.localizacion(tweet.getLocalizacion())
				.validacion(tweet.getValidacion())
				.build();
		
		return tweetDTO;
	}

}
