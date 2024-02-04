package com.example.imdb.movie.repository;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.example.imdb.movie.entity.MovieEntity;

public interface MovieRepository {
	MovieEntity getMovieById(int id) throws IOException, ParseException;
	List<MovieEntity> getAllMovies() throws IOException, ParseException;
	List<MovieEntity> getMoviesByName(String name) throws IOException, ParseException;
}
