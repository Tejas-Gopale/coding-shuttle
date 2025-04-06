package com.CodingShuttle.SecurityApp.controller.service;

import java.util.List;

import com.CodingShuttle.SecurityApp.dto.PostDto;

public interface PostService {

	public List<PostDto> getAllPost();

	public PostDto getPostById(Long postId);

	public PostDto createNewPost(PostDto inputPost) ;

	
	
}
