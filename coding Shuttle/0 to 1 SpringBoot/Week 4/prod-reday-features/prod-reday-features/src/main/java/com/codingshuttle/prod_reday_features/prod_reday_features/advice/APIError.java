package com.codingshuttle.prod_reday_features.prod_reday_features.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class APIError {

	private LocalDateTime timeStamp;
	
	private String error;
	
	private HttpStatus statusCode;

	public APIError(String error, HttpStatus statusCode) {
		this();
		this.error = error;
		this.statusCode = statusCode;
	}

	public APIError() {
		this.timeStamp = LocalDateTime.now();
	}
	
	
}
