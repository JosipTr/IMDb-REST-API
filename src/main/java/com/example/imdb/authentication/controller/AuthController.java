package com.example.imdb.authentication.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.authentication.JwtHandler;
import com.example.imdb.authentication.UserPrincipal;
import com.example.imdb.authentication.dto.LoginDto;
import com.example.imdb.authentication.dto.LoginResponseDto;
import com.example.imdb.authentication.dto.RegisterDto;
import com.example.imdb.authentication.service.AuthService;
import com.example.imdb.core.ApiError;

@RestController
public class AuthController {
	
	private final AuthService authService;
	private final AuthenticationManager authenticationManager;
	private final JwtHandler jwtHandler;

	public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtHandler jwtHandler) {
		super();
		this.authService = authService;
		this.authenticationManager = authenticationManager;
		this.jwtHandler = jwtHandler;
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
	public ResponseEntity<Object> login(@RequestBody LoginDto request) {
		try {
		LoginResponseDto response = loginUser(request.email(), request.password());
		return ResponseEntity.ok(response);
		} catch (Exception e) {
			//Return exception here because can't throw it in CustomUserDetailsController
			return ResponseEntity.badRequest().body(new ApiError("Bad credentials"));
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody RegisterDto request) {
			authService.saveUser(request);
			LoginResponseDto response = loginUser(request.email(), request.password());
			return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
	
	private LoginResponseDto loginUser(String email, String password) {
		var authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(email, password));
		var principal = (UserPrincipal)authentication.getPrincipal();
		var token = jwtHandler.createToken(principal.getId());
		return new LoginResponseDto(principal.getId(), token);
	}
}
