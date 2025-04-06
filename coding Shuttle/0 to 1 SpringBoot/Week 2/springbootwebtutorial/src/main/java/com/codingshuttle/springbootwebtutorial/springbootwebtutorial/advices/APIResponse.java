package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class APIResponse<T> {

	//@JsonFormat(pattern = "hh-mm-ss dd-mm-yy")
	private LocalDateTime timeStamp;
	
	private T data;
	
	private ApiError error;
	
	public APIResponse() {
		this.timeStamp = LocalDateTime.now();
	}
	
	public APIResponse(T data){
		this();
		this.data = data;
	}
	
	public APIResponse(ApiError error) {
		this();
		this.error = error;
	}
	
}
