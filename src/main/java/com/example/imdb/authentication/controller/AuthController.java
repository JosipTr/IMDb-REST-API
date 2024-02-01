package com.example.imdb.authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.authentication.entity.UserEntity;
import com.example.imdb.authentication.model.RegisterRequest;
import com.example.imdb.authentication.service.AuthService;

@RestController
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@GetMapping("/")
	public String getPublic() {
		return "Public page";
	}
	
	@GetMapping("/private")
	public String getPrivate() {
		return "Private page";
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
		try {
			UserEntity user = authService.saveUser(request);
			return new ResponseEntity<String>("Registration successful\nWelcome " + user.email(), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Registration failed", HttpStatus.BAD_REQUEST);
		}
	}
}
