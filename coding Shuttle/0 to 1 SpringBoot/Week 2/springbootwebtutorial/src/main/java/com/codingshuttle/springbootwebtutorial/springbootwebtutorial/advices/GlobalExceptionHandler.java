package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(NoSuchElementException.class)
//	public ResponseEntity<String> handelResourceNotFound(NoSuchElementException exception) {
//		return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
//	}
	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ApiError> handelResourceNotFound(ResourceNotFoundException exception) {
//		ApiError apiError = ApiError.builder()
//					.status(HttpStatus.NOT_FOUND)
//					.message(exception.getMessage())
//					.build();
//		
//		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//	}
	
	//Api Response handler 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse<?>> handelResourceNotFound(ResourceNotFoundException exception) {
		ApiError apiError = ApiError.builder()
					.status(HttpStatus.NOT_FOUND)
					.message(exception.getMessage())
					.build();
		
		return buildErrorResponseEnity(apiError);
	}
	


//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ApiError> handelInteralServerError( Exception exception){
//		ApiError apiError = ApiError
//				.builder()
//				.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.message(exception.getMessage()).build();
//		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<?>> handelInteralServerError( Exception exception){
		ApiError apiError = ApiError
				.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.message(exception.getMessage()).build();
		return new ResponseEntity<> (new APIResponse(apiError),apiError.getStatus());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<APIResponse<?>> handelInputValidationsError(MethodArgumentNotValidException exception){
	List<String> errors =	exception
		.getBindingResult()
		.getAllErrors()
		.stream()
		.map(error -> error.getDefaultMessage())
		.collect(Collectors.toList());
		
	ApiError apiError = ApiError
			.builder()
			.status(HttpStatus.BAD_REQUEST)
			//.message(errors.toString()).build();
			.message("Input Validation Faild").subError(errors).build();
	return new ResponseEntity<> (new APIResponse(apiError),apiError.getStatus());
	}
	
//	 internal Method
	private ResponseEntity<APIResponse<?>> buildErrorResponseEnity(ApiError apiError) {
		
		return new ResponseEntity<> (new APIResponse(apiError),apiError.getStatus());
	}
}
