package com.example.imdb.authentication;

import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthProvider implements AuthenticationProvider{

	private final JwtHandler jwtHandler;

	public JwtAuthProvider(JwtHandler jwtHandler) {
		super();
		this.jwtHandler = jwtHandler;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (String) authentication.getCredentials();
		final Boolean isVerified = jwtHandler.verifyToken(token);
		if (isVerified) return JwtAuthToken.authenticated(jwtHandler.getSubject(token));
		throw new UsernameNotFoundException("Bad credentials");
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return JwtAuthToken.class.isAssignableFrom(authentication);
	}

	
}
