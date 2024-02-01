package com.example.imdb.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtAuthProvider implements AuthenticationProvider{

	private final JwtDecoder jwtDecoder;
	
	public JwtAuthProvider(JwtDecoder jwtDecoder) {
		this.jwtDecoder = jwtDecoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final DecodedJWT jwt = jwtDecoder.decode((String) authentication.getCredentials());
		return JwtAuthToken.authenticated(jwt.getSubject());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return JwtAuthToken.class.isAssignableFrom(authentication);
	}

	
}
