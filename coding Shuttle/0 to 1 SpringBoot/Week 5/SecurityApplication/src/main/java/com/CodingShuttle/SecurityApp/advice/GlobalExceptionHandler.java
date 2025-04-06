package com.CodingShuttle.SecurityApp.advice;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.CodingShuttle.SecurityApp.exceptions.ResourceNotFoundException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
//	 
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIError> handleResourceNotFoundException(ResourceNotFoundException exception){
		APIError apiError = new APIError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}
	 
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<APIError> handelAuthenticationException(AuthenticationException ex){
		APIError apiError = new APIError(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIError> handleBadCredentialsException(BadCredentialsException ex) {
        APIError apiError = new APIError(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(JwtException.class)
    public ResponseEntity<APIError> handleJwtException(JwtException ex) {
        APIError apiError = new APIError(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }
	
	
}
