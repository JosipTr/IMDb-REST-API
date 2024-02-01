package com.example.imdb.authentication;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtIssuer {
	
	private final JwtProperties jwtProperties;
	
	public JwtIssuer(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
	}

	public String issueToken(String id, String email) {
		return JWT.create()
				.withSubject(id)
				.withClaim("email", email)
				.sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
	}
}
