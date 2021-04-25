package com.sermar.javaTwitter.controller.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TweetDTO implements Serializable{
	
	private static final long serialVersionUID = 2265887114877642967L;
	
	private Long id;
	private String usuario;
	private String texto;
	private String localizacion;
	private Boolean validacion;
	
}
