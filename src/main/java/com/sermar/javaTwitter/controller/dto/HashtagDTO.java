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
public class HashtagDTO  implements Serializable{
	
	private static final long serialVersionUID = -1076317493926272629L;
	
	private Integer contador;
	private String hashtag;

}
