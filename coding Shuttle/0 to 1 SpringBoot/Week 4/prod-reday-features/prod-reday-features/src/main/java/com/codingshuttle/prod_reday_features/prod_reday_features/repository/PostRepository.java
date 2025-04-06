package com.codingshuttle.prod_reday_features.prod_reday_features.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingshuttle.prod_reday_features.prod_reday_features.entity.PostEntity;

@Repository
public interface PostRepository  extends JpaRepository<PostEntity, Long>{
	
}
