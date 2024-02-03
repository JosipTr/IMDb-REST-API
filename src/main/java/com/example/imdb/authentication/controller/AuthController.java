package com.example.imdb.authentication.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.authentication.UserPrincipal;
import com.example.imdb.authentication.dto.LoginDto;
import com.example.imdb.authentication.dto.LoginResponseDto;
import com.example.imdb.authentication.dto.RegisterDto;
import com.example.imdb.authentication.model.JwtIssuer;
import com.example.imdb.authentication.service.AuthService;
import com.example.imdb.core.ApiError;

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
	public ResponseEntity<Object> login(@RequestBody LoginDto request) {
		try {
		var authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(request.email(), request.password()));
		var principal = (UserPrincipal)authentication.getPrincipal();
		var token = jwtIssuer.issueToken(principal.getId(), principal.getUsername());
		return ResponseEntity.ok(new LoginResponseDto(principal.getId(), token));
		} catch (Exception e) {
			//Return exception here because can't throw it in CustomUserDetailsController
			return ResponseEntity.badRequest().body(new ApiError("Bad credentials"));
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody RegisterDto request) {
			authService.saveUser(request);
			var authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(request.email(), request.password()));
			var principal = (UserPrincipal)authentication.getPrincipal();
			var token = jwtIssuer.issueToken(principal.getId(), principal.getUsername());
			return new ResponseEntity<Object>(new LoginResponseDto(principal.getId(), token), HttpStatus.CREATED);
	}
}
