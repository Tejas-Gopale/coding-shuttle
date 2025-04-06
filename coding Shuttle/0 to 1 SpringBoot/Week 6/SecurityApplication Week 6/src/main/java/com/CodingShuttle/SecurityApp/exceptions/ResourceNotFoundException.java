package com.CodingShuttle.SecurityApp.exceptions;


public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
