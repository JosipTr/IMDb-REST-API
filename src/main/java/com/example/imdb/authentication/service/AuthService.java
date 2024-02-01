package com.example.imdb.authentication.service;

import com.example.imdb.authentication.entity.UserEntity;
import com.example.imdb.authentication.model.RegisterRequest;

public interface AuthService {
	UserEntity getUserById(String id);
	UserEntity getUserByEmail(String email);
	UserEntity saveUser(RegisterRequest registerRequest);
}
