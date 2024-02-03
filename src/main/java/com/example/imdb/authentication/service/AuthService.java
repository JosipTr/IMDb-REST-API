package com.example.imdb.authentication.service;

import com.example.imdb.authentication.dto.RegisterDto;

import com.example.imdb.authentication.dto.UserDto;
import com.example.imdb.authentication.entity.UserEntity;

public interface AuthService {
	UserDto getUserById(String id);
	UserDto getUserByEmail(String email);
	UserEntity saveUser(RegisterDto registerDto);
}
