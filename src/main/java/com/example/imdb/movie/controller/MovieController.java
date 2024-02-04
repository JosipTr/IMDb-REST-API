package com.example.imdb.movie.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.movie.entity.MovieEntity;
import com.example.imdb.movie.repository.MovieRepository;

@RestController
public class MovieController {

	private final MovieRepository repository;

	public MovieController(MovieRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping("/movie/{id}")
	public MovieEntity getMovie(@PathVariable int id) {
			return repository.getMovieById(id);
	}

	@GetMapping("/movies")
	public List<MovieEntity> getMovies() {
		return repository.getAllMovies();
	}

	@GetMapping("/movies/{name}")
	public List<MovieEntity> getMovieByName(@PathVariable String name) {
		return repository.getMoviesByName(name);
	}
}
