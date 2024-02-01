package com.example.imdb.authentication.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.imdb.authentication.entity.UserEntity;
import com.example.imdb.authentication.model.RegisterRequest;
import com.example.imdb.authentication.model.UserModel;
import com.example.imdb.authentication.repository.AuthRepository;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final AuthRepository authRepository;

	public AuthServiceImpl(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	@Override
	public UserEntity getUserById(String id) {
		Optional<UserModel> userModel =  authRepository.findById(id);
		if (!userModel.isPresent()) throw new IllegalArgumentException("User not found");
		return userModel.get().toUserEntity();
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		Optional<UserModel> userModel =  authRepository.getUserByEmail(email);
		if (!userModel.isPresent()) throw new IllegalArgumentException("User not found");
		return userModel.get().toUserEntity();
	}

	@Override
	public UserEntity saveUser(RegisterRequest registerRequest) throws IllegalArgumentException{
		UserModel userModel = UserModel.fromRegisterRequest(registerRequest);
		UserModel savedUser = authRepository.save(userModel);
		return savedUser.toUserEntity();
	}
	
	
	
}
