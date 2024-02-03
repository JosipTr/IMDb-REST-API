package com.example.imdb.movie.repository;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.example.imdb.movie.model.MovieModel;

public interface MovieRepository {
	MovieModel getMovieById(int id) throws IOException, ParseException;
	List<MovieModel> getAllMovies() throws IOException, ParseException;
	List<MovieModel> getMoviesByName(String name) throws IOException, ParseException;
}
