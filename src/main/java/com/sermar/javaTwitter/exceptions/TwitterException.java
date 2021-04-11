package com.sermar.javaTwitter.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TwitterException extends RuntimeException{

	public TwitterException(String message) {
	    super(message);
	}
	
}
