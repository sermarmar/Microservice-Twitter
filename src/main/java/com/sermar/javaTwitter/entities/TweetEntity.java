package com.sermar.javaTwitter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ttweet")
public class TweetEntity {

	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "texto")
	private String texto;
	@Column(name = "localizacion")
	private String localizacion;
	@Column(name = "validacion")
	private Boolean validacion;
	
}
