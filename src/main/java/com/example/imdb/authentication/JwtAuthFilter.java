package com.example.imdb.authentication;

import java.io.IOException;
import java.util.Collections;

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
		var headers = Collections.list(request.getHeaderNames());

		if (!headers.contains("authorization")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = jwtHandler.extractJwt(request);
		JwtAuthToken authRequest = JwtAuthToken.unauthenticated(token);
		var authentication = authenticationManager.authenticate(authRequest);
		var context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		
		filterChain.doFilter(request, response);
		return;
		
	}

	
}
