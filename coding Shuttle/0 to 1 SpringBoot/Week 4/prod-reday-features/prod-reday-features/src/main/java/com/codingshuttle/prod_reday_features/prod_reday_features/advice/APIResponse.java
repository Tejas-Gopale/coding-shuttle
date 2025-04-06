package com.codingshuttle.prod_reday_features.prod_reday_features.advice;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
public class APIResponse<T> {

	//@JsonFormat(pattern = "hh-mm-ss dd-mm-yy")
	private LocalDateTime timeStamp;
	
	private T data;
	
	private APIError error;
	
	public APIResponse() {
		this.timeStamp = LocalDateTime.now();
	}
	
	public APIResponse(T data){
		this();
		this.data = data;
	}
	
	public APIResponse(APIError error) {
		this();
		this.error = error;
	}
	
}
