package com.example.imdb.authentication.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.authentication.JwtIssuer;
import com.example.imdb.authentication.UserPrincipal;
import com.example.imdb.authentication.entity.UserEntity;
import com.example.imdb.authentication.model.LoginRequest;
import com.example.imdb.authentication.model.LoginResponse;
import com.example.imdb.authentication.model.RegisterRequest;
import com.example.imdb.authentication.service.AuthService;

@RestController
public class AuthController {
	
	private final AuthService authService;
	private final AuthenticationManager authenticationManager;
	private final JwtIssuer jwtIssuer;

	public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtIssuer jwtIssuer) {
		super();
		this.authService = authService;
		this.authenticationManager = authenticationManager;
		this.jwtIssuer = jwtIssuer;
	}

	@GetMapping("/")
	public String getPublic() {
		return "Public page";
	}
	
	@GetMapping("/private")
	public String getPrivate() {
		return "Private page";
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
		try {
		var authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(request.email(), request.password()));
		if (!authentication.isAuthenticated()) return new ResponseEntity<Object>("Failure", HttpStatus.BAD_REQUEST);
		var principal = (UserPrincipal)authentication.getPrincipal();
		var token = jwtIssuer.issueToken(principal.getId(), principal.getUsername());
		return ResponseEntity.ok(new LoginResponse(principal.getId(), token));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>("Failure", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
		try {
			UserEntity user = authService.saveUser(request);
			var authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(user.email(), user.password()));
			if (!authentication.isAuthenticated()) return new ResponseEntity<Object>("Failure", HttpStatus.BAD_REQUEST);
			var principal = (UserPrincipal)authentication.getPrincipal();
			var token = jwtIssuer.issueToken(principal.getId(), principal.getUsername());
			return new ResponseEntity<Object>(new LoginResponse(principal.getId(), token), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>("Registration failed", HttpStatus.BAD_REQUEST);
		}
	}
}
