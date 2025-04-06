package com.codingshuttle.prod_reday_features.prod_reday_features.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codingshuttle.prod_reday_features.prod_reday_features.exceptions.ResourceNotFoundException;


@RestControllerAdvice
public class GlobaleExceptionHandelr {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIError> handelResourceNotFoundException(ResourceNotFoundException exception){
		System.out.println(exception.getLocalizedMessage());
		APIError apiError = new APIError(exception.getLocalizedMessage(),HttpStatus.NOT_FOUND);
		return new  ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
	}
}
