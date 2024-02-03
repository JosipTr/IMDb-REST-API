package com.example.imdb.authentication.repository;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.imdb.authentication.entity.UserEntity;

@Repository
public interface AuthRepository extends MongoRepository<UserEntity, String>{
	
	@Query("{email:'?0'}")
	Optional<UserEntity> getUserByEmail(String email);
}
