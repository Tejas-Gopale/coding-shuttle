package com.codingshuttle.prod_reday_features.prod_reday_features.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.codingshuttle.prod_reday_features.prod_reday_features.dto.PostDto;
import com.codingshuttle.prod_reday_features.prod_reday_features.entity.PostEntity;
import com.codingshuttle.prod_reday_features.prod_reday_features.exceptions.ResourceNotFoundException;
import com.codingshuttle.prod_reday_features.prod_reday_features.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
public class PostServiceImplementation implements PostService{
	
	
	private PostRepository postRepository;
	
	private final ModelMapper modelMapper;
	
	public PostServiceImplementation(PostRepository postRepository, ModelMapper modelMapper) {
		super();
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}
		
	
	@Override
	public List<PostDto> getAllPosts() {
		
		return postRepository
				.findAll()
				.stream()
				.map(postEntity -> modelMapper.map(postEntity, PostDto.class))
				.collect(Collectors.toList());
	}

//	public PostDto createNewPostDto(PostDto inputPost) {
//	    PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
//	    return modelMapper.map(postRepository.save(postEntity), PostDto.class);
//	}
	
	public PostDto createNewPostDto(PostDto inputPost) {
		System.out.println(inputPost.getTitle()+"\t" + inputPost.getDescription());
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        System.out.println("service layer " + postEntity.getDescription()+"\t\t" + postEntity.getTitle());
       
        return modelMapper.map(postRepository.save(postEntity), PostDto.class);
    }


	@Override
	public PostDto getPostById(Long postId) {
			PostEntity postEntity = postRepository
					.findById(postId)
					.orElseThrow(() -> new ResourceNotFoundException("Id not presend"));
		return modelMapper.map(postEntity , PostDto.class);
	}


	@Override
	public PostDto updatePostById(Long postId, PostDto postDto) {
		//olderpost
		PostEntity olderpost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Not found Corresponding this id" + postId));
		
		// set the post id as it will be not nnull
		postDto.setId(postId);
		// map the old post with the new post
		modelMapper.map(postDto, olderpost);
		
		 PostEntity savePostEntity = postRepository.save(olderpost);
		 return modelMapper.map(savePostEntity,PostDto.class);
		 
	
	}


}
