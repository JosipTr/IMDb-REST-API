package com.example.imdb.movie.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
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

	@GetMapping("/movie")
	public MovieEntity getMovie() {
		try {
		var movie = repository.getMovieById(1);
		System.out.println(movie.toString());
		return movie;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	@GetMapping("/movies")
	public List<MovieEntity> getMovies() {
		List<MovieEntity> movies;
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
	
	@GetMapping("/movies/{name}")
	public List<MovieEntity> getMovieByName(@PathVariable String name) {
				try {
			List<MovieEntity> movies = repository.getMoviesByName(name);
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
