package com.example.imdb.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler({ IllegalArgumentException.class, UserNotFoundException.class, IOException.class, FileNotFoundException.class, NoSuchElementException.class })
	public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		if (ex instanceof IllegalArgumentException) {

			HttpStatus status = HttpStatus.BAD_REQUEST;
			IllegalArgumentException iaexc = (IllegalArgumentException) ex;

			return handleIllegalArgumentException(iaexc, headers, status, request);
		} 
		else if (ex instanceof UserNotFoundException) {

			HttpStatus status = HttpStatus.BAD_REQUEST;
			UserNotFoundException unfe = (UserNotFoundException) ex;

			return handleUserNotFoundException(unfe, headers, status, request);
		}else if (ex instanceof IOException) {

			HttpStatus status = HttpStatus.BAD_REQUEST;
			IOException ioe = (IOException) ex;

			return handleIOException(ioe, headers, status, request);
		}
		else if (ex instanceof FileNotFoundException) {

			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			FileNotFoundException ioe = (FileNotFoundException) ex;

			return handleFileNotFoundException(ioe, headers, status, request);
		}
		else if (ex instanceof NoSuchElementException) {

			HttpStatus status = HttpStatus.BAD_REQUEST;
			NoSuchElementException ioe = (NoSuchElementException) ex;

			return handleNoSuchElementException(ioe, headers, status, request);
		}
		else {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return handleExceptionInternal(ex, null, headers, status, request);
		}
	}

	/** Customize the response for UserNotFoundException. */
	protected ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = ex.getMessage();
		logger.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, new ApiError(error), headers, status, request);
	}

	protected ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = ex.getMessage();
		logger.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, new ApiError(error), headers, status, request);
	}
	
	protected ResponseEntity<ApiError> handleIOException(IOException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = ex.getMessage();
		logger.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, new ApiError(error), headers, status, request);
	}
	
	protected ResponseEntity<ApiError> handleFileNotFoundException(FileNotFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = ex.getMessage();
		logger.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, new ApiError(error), headers, status, request);
	}
	
	protected ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = ex.getMessage();
		logger.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, new ApiError(error), headers, status, request);
	}

	/** A single place to customize the response body of all Exception types. */
	protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}

		return new ResponseEntity<>(body, headers, status);
	}
}