package com.example.imdb.movie.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.imdb.movie.entity.MovieEntity;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

@Component
public class JsonToMovieMapper {
	private final static String FILE_URL = "./src/main/resources/imdb_movie_data_2023.json";
	private final Gson gson = new Gson();
	private final FileReader reader;
	private final JsonReader jsonReader;
	
	public JsonToMovieMapper() throws FileNotFoundException {
		this.reader = new FileReader(FILE_URL);
	    this.jsonReader = new JsonReader(reader);
	}
	
	public List<MovieEntity> getMovieList() {
		MovieEntity[] movies = gson.fromJson(jsonReader, MovieEntity[].class);
	    List<MovieEntity> movieList = Arrays.asList(movies);
	    
	    return movieList;
	}
}
