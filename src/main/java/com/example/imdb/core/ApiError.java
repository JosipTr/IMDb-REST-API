package com.example.imdb.core;

public class ApiError {
	private final String errorMessage;

	public ApiError(String errorMessages) {
		super();
		this.errorMessage = errorMessages;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	
}
