package com.CodingShuttle.SecurityApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingShuttle.SecurityApp.controller.service.PostService;
import com.CodingShuttle.SecurityApp.dto.PostDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/post")
@RequiredArgsConstructor // for using the dependent class
public class PostController {

	private final PostService postService;
	
	@GetMapping
	public List<PostDto> getAllPost(){
		return  postService.getAllPost();
	}
	
	@GetMapping("/{postId}")
	public PostDto getPostById(@PathVariable Long postId) {
		return postService.getPostById(postId);
	}
	
	@PostMapping
	public PostDto createNewPost(@RequestBody PostDto inputPost) {
		return postService.createNewPost(inputPost);
	}
	
}
