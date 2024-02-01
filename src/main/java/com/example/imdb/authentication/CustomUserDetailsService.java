package com.example.imdb.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.imdb.authentication.entity.UserEntity;
import com.example.imdb.authentication.service.AuthService;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final AuthService authService;

	public CustomUserDetailsService(AuthService authService) {
		super();
		this.authService = authService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = authService.getUserByEmail(email);
		return new UserPrincipal(user.id(), user.email());
	}
	
}
