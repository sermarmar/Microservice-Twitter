package com.sermar.javaTwitter.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Ttweet")
public class TweetEntity {

	@Id
	private Long id;
	private String usuario;
	private String texto;
	private String localizacion;
	private Boolean validacion;
	
}
