package com.codingshuttle.caching.advices;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codingshuttle.caching.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

		
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getLocalizedMessage());                  
        return ResponseEntity.notFound().build();
    }

	
	@ExceptionHandler(StaleObjectStateException.class)
	public ResponseEntity<?>  handleStableOnjectStateException(StaleObjectStateException ex){
		   log.error(ex.getLocalizedMessage());
		   return new ResponseEntity<>("Stale data\n",HttpStatus.CONFLICT);
	}
}
