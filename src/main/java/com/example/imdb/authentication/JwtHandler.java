package com.example.imdb.authentication;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

import jakarta.servlet.http.HttpServletRequest;

//@Configuration
//@ConfigurationProperties("security.jwt")
@Component
public class JwtHandler {
	private final String secret;
	private final JWTVerifier jwtVerifier;
	
	public JwtHandler(@Value("$security.jwt.secret-key") String secret) {
		super();
		this.secret = secret;
		this.jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
	}

	public String createToken(String id) {
		return JWT.create()
				.withSubject(id)
				.withIssuedAt(new Date())
				.withExpiresAt(Instant.now().plusSeconds(300))
				.sign(Algorithm.HMAC256(secret));
	}
	
	
	public Boolean verifyToken(String token) {
		try {
		 jwtVerifier.verify(token);
		return Boolean.TRUE;
		} catch (JWTVerificationException e) {
			return Boolean.FALSE;
		}
	}
	
	public String getSubject(String token) {
		return JWT.decode(token).getSubject();
	}
	
	public String extractJwt(HttpServletRequest request) {
	    String bearer = request.getHeader("Authorization");
	    if (bearer == null || bearer.isEmpty()) return null;
	    String token = bearer.substring(7);
	    return token;
	  }
}

