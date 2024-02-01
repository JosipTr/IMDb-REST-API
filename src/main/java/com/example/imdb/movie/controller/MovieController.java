package com.example.imdb.movie.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.movie.model.MovieModel;
import com.example.imdb.movie.repository.MovieRepository;

@RestController
public class MovieController {
	
	private final MovieRepository repository;
	
	public MovieController(MovieRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping("/movie")
	public String getMovie() {
		try {
		var movie = repository.getMovieById(1);
		System.out.println(movie.toString());
		return "Success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "Movie";
	}
	
	@GetMapping("/movies")
	public List<MovieModel> getMovies() {
		List<MovieModel> movies;
		try {
			movies = repository.getAllMovies();
			return movies;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return List.of();
		
	}
}
