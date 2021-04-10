package com.sermar.javaTwitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sermar.javaTwitter.entities.TweetEntity;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long>{

	
	
}
