package com.codingshuttle.prod_reday_features.prod_reday_features.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingshuttle.prod_reday_features.prod_reday_features.dto.PostDto;


public interface PostService {

	List<PostDto> getAllPosts();
	
	PostDto createNewPostDto(PostDto inputPost);

	PostDto getPostById(Long postId);

	PostDto updatePostById(Long postId, PostDto postDto);
	
}
