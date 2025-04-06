package com.codingshuttle.prod_reday_features.prod_reday_features.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingshuttle.prod_reday_features.prod_reday_features.dto.PostDto;
import com.codingshuttle.prod_reday_features.prod_reday_features.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/post")
public class PostController {
	
//	use the service interface insted of implemenations
	@Autowired
	private  PostService postService;

	@GetMapping
	public List<PostDto> getAllPost(){
	return	postService.getAllPosts();
	}
	
	@PostMapping
	public PostDto createNewPost(@RequestBody @Valid PostDto inputPost) {
		System.out.println("Inside the Controller");
		return postService.createNewPostDto(inputPost);
	}
	
	@GetMapping(path = "/{postId}")
	public PostDto getPostById(@PathVariable Long postId) {
		return postService.getPostById(postId);
	}
	
	@PutMapping(path = "/{postId}")
	public PostDto updatePostById(@PathVariable Long postId, @RequestBody PostDto postDto) {
		return postService.updatePostById(postId, postDto);
	}
}
