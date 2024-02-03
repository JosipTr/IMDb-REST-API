package com.example.imdb.authentication.model;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtDecoder {
	private final JwtProperties jwtProperties;

	public JwtDecoder(JwtProperties jwtProperties) {
		super();
		this.jwtProperties = jwtProperties;
	}
	
	public DecodedJWT decode(String token) {
		return JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey()))
				.build()
				.verify(token);
	}
}
