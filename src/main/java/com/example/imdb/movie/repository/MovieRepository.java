package com.example.imdb.movie.repository;

import java.util.List;

import com.example.imdb.movie.entity.MovieEntity;

public interface MovieRepository {
	MovieEntity getMovieById(int id);
	List<MovieEntity> getAllMovies();
	List<MovieEntity> getMoviesByName(String name);
}
