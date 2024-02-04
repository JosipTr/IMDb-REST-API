package com.example.imdb.movie.repository;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.example.imdb.movie.entity.MovieEntity;
import com.example.imdb.movie.model.JsonToMovieMapper;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
	
	private final JsonToMovieMapper mapper;

	public MovieRepositoryImpl(JsonToMovieMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public MovieEntity getMovieById(int id) throws IOException, ParseException {
	    List<MovieEntity> movies = mapper.getMovieList();
	    MovieEntity movie = movies.stream().filter(t -> t.getId() == id).findFirst().get();
	    System.out.println(movie.toString());
	    return movie;
	}

	@Override
	public List<MovieEntity> getAllMovies() throws IOException, ParseException {
		return mapper.getMovieList();
	}

	public List<MovieEntity> getMoviesByName(String name) throws IOException, ParseException {
		List<MovieEntity> movies = mapper.getMovieList();

		return movies.stream()
				.filter(movie -> containsTitle(movie, name))
				.toList();
	}
	
	public List<MovieEntity> getMoviesByFilter(String filter) throws IOException, ParseException {
		List<MovieEntity> movies = mapper.getMovieList();

		return movies.stream()
				.filter(movie -> containsTitle(movie, filter))
				.toList();
	}


	private boolean containsTitle(MovieEntity movie, String name) {
		String title = movie.getName();
;		return title.contains(name);
	}

}
