package com.example.imdb.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler({IllegalArgumentException.class})
	protected ResponseEntity<String> handleException(Exception exception, WebRequest request) {
		
		return handleInternalException("Error", HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<String> handleInternalException(String body, HttpStatus httpStatus) {
		return new ResponseEntity<>(body, httpStatus);
	}
}
