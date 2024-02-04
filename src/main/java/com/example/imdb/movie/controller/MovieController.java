package com.example.imdb.movie.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.movie.entity.MovieEntity;
import com.example.imdb.movie.service.MovieService;

@RestController
public class MovieController {

	private final MovieService service;

	public MovieController(MovieService service) {
		super();
		this.service = service;
	}

	@GetMapping("/movie/{id}")
	public MovieEntity getMovie(@PathVariable int id) {
			return service.getMovieById(id);
	}

	@GetMapping("/movies")
	public List<MovieEntity> getMovies() {
		return service.getAllMovies();
	}

	@GetMapping("/movies/{name}")
	public List<MovieEntity> getMovieByName(@PathVariable String name) {
		return service.getMoviesByName(name);
	}
}
