package com.testing_app.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message){
		super(message);
	}
}
