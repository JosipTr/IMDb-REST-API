package com.example.imdb.authentication;

import java.util.Optional;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.imdb.authentication.entity.UserEntity;
import com.example.imdb.authentication.repository.AuthRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final AuthRepository authRepository;

	public CustomUserDetailsService(AuthRepository authRepository) {
		super();
		this.authRepository = authRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = authRepository.getUserByEmail(email);
		if (!userEntity.isPresent()) throw new UsernameNotFoundException("Invalid email or password");
		UserEntity user = userEntity.get();
		return user.toUserPrincipal();
	}
	
}
