package com.CodingShuttle.SecurityApp.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import lombok.Data;

@Data
public class APIError {

	private LocalDateTime timeStamp;
	private String error;
	private HttpStatusCode statusCode;
	
	public APIError() {
		this.timeStamp = LocalDateTime.now();
	}
	
	public APIError(String error, HttpStatusCode statusCode) {
		this();
		this.error = error;
		this.statusCode = statusCode;
	}
}
