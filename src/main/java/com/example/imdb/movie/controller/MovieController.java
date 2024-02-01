package com.example.imdb.movie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
