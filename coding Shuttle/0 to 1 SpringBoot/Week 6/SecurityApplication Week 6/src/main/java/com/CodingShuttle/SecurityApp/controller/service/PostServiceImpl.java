package com.CodingShuttle.SecurityApp.controller.service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.CodingShuttle.SecurityApp.dto.PostDto;
import com.CodingShuttle.SecurityApp.entity.Post;
import com.CodingShuttle.SecurityApp.entity.User;
import com.CodingShuttle.SecurityApp.exceptions.ResourceNotFoundException;
import com.CodingShuttle.SecurityApp.repositories.PostRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

	private final PostRepo postRepo;
	private final ModelMapper mapper;
	Logger log =  LoggerFactory.getLogger(UserService.class);
	public List<PostDto> getAllPost() {
		return postRepo
				.findAll()
				.stream()
				.map(postEntity -> mapper.map(postEntity, PostDto.class))
				.collect(Collectors.toList());
	}

	public PostDto getPostById(Long postId) {
	User user=	(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
	log.info("User {user}" , user);
	
	System.out.println("Inside the GetPost By Id " + postId);
	Post  postEntity = 	 postRepo.findById(postId)
			.orElseThrow(() -> new ResourceNotFoundException("Post Not Present with this id: " + postId));
		 return mapper.map(postEntity, PostDto.class);
	}

	public PostDto createNewPost(PostDto inputPost) {
		Post postEntity =mapper.map(inputPost, Post.class);
		return mapper.map(postEntity, PostDto.class);
	}
}
