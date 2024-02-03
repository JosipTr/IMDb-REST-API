package com.example.imdb.core;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2515899423187996190L;
	
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	
}
