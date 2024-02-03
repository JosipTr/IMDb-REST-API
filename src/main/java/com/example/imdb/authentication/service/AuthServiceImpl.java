package com.example.imdb.authentication.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.imdb.authentication.dto.RegisterDto;
import com.example.imdb.authentication.dto.UserDto;
import com.example.imdb.authentication.entity.UserEntity;
import com.example.imdb.authentication.repository.AuthRepository;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final AuthRepository authRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthServiceImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
		super();
		this.authRepository = authRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDto getUserById(String id) {
		Optional<UserEntity> userEntity =  authRepository.findById(id);
		if (!userEntity.isPresent()) throw new IllegalArgumentException("User not found");
		return userEntity.get().toUserDto();
	}

	@Override
	public UserDto getUserByEmail(String email) {
		Optional<UserEntity> userEntity =  authRepository.getUserByEmail(email);
		if (!userEntity.isPresent()) throw new IllegalArgumentException("User not found");
		return userEntity.get().toUserDto();
	}

	@Override
	public UserEntity saveUser(RegisterDto registerRequest) throws IllegalArgumentException{
		UserEntity user = UserEntity.fromRegisterDto(registerRequest);
		user.setPassword(passwordEncoder.encode(registerRequest.password()));
		UserEntity savedUser = authRepository.save(user);
		return savedUser;
	}
	
	
	
}
