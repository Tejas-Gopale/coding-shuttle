package com.codingshuttle.prod_reday_features.prod_reday_features.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingshuttle.prod_reday_features.prod_reday_features.entity.PostEntity;

import jakarta.persistence.EntityManagerFactory;

@RestController
@RequestMapping(path = "/audit")
public class AdminAuditController {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	//get the all the logs for the single postid
	@GetMapping(path = "/post/{postId}")
	public List<PostEntity> getPostRevesion(@PathVariable Long postId){
		AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
	
	List<Number> revisions =reader.getRevisions(PostEntity.class, postId);
	return	revisions
		.stream()
		.map(revisionNumber -> reader.find(PostEntity.class, postId ,revisionNumber))
		.collect(Collectors.toList());
		
		
	}
}
