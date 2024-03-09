package com.example.imdb.movie.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.imdb.movie.entity.MovieEntity;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

class JsonToMovieMapperTest {
	
	String FILE_URL;
	Gson gson;
	FileReader reader;
	JsonReader jsonReader;

	@BeforeEach
	void init() throws FileNotFoundException {
		FILE_URL = "./src/main/resources/imdb_movie_data_2023.json";
		gson = new Gson();
		reader = new FileReader(FILE_URL);
		jsonReader = new JsonReader(reader);
	}

	@Test
	@DisplayName("Should provide list of movies")
	void testGetMovieList() {
		var movies = gson.fromJson(reader, MovieEntity[].class);
		List<MovieEntity> movieList = Arrays.asList(movies);
		assertThat(movieList).isNotEmpty();
		assertThat(movieList).hasOnlyElementsOfType(MovieEntity.class);
	}

}