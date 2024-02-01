package com.example.imdb.movie.repository;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.imdb.movie.model.MovieModel;

public interface MovieRepository {
	MovieModel getMovieById(int id) throws IOException, ParseException;
}
