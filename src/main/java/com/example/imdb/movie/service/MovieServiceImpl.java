package com.example.imdb.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.imdb.movie.entity.MovieEntity;
import com.example.imdb.movie.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository repository;

	public MovieServiceImpl(MovieRepository repository) {
		this.repository = repository;
	}

	@Override
	public MovieEntity getMovieById(int id) {
		return repository.getMovieById(id);
	}

	@Override
	public List<MovieEntity> getAllMovies() {
		return repository.getAllMovies();
	}

	@Override
	public List<MovieEntity> getMoviesByName(String name) {
		return repository.getMoviesByName(name);
	}

}
