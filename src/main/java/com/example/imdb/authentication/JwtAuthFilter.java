package com.example.imdb.authentication;

import java.io.IOException;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private final AuthenticationManager authenticationManager;
	private final JwtHandler jwtHandler;

	public JwtAuthFilter(@Lazy AuthenticationManager authenticationManager, JwtHandler jwtHandler) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtHandler = jwtHandler;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = jwtHandler.extractJwt(request);
		if (token == null || token.isEmpty()) {
			filterChain.doFilter(request, response);
			return;
		}
		JwtAuthToken authRequest = JwtAuthToken.unauthenticated(token);
		var authentication = authenticationManager.authenticate(authRequest);
		var context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		
		filterChain.doFilter(request, response);
		return;
		
	}

	
}
