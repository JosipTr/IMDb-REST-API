package com.example.imdb.movie.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.imdb.movie.entity.MovieEntity;
import com.example.imdb.movie.model.JsonToMovieMapper;

@Repository
public class MovieRepositoryImpl implements MovieRepository {


	private final List<MovieEntity> movieList;

	public MovieRepositoryImpl(JsonToMovieMapper mapper) {
		super();
		this.movieList = mapper.getMovieList();
	}

	@Override
	public MovieEntity getMovieById(int id) {
		MovieEntity movie = movieList.stream().filter(t -> t.getId() == id).findFirst().get();
		return movie;
	}

	@Override
	public List<MovieEntity> getAllMovies() {
		return movieList;
	}

	public List<MovieEntity> getMoviesByName(String name) {
		name.toLowerCase();
		return movieList.stream().filter(t -> containsTitle(t, name)).toList(); 
	}

	public List<MovieEntity> getMoviesByFilter(String filter) {

		return movieList.stream().filter(movie -> containsTitle(movie, filter)).toList();
	}

	private boolean containsTitle(MovieEntity movie, String name) {
		String title = movie.getName().toLowerCase();
		return title.contains(name);
	}

}
