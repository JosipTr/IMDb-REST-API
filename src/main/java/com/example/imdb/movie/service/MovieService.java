package com.example.imdb.movie.service;

import java.util.List;

import com.example.imdb.movie.entity.MovieEntity;

public interface MovieService {
	MovieEntity getMovieById(int id);
	List<MovieEntity> getAllMovies();
	List<MovieEntity> getMoviesByName(String name);
}
