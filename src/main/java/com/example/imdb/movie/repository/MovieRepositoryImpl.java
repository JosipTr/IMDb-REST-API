package com.example.imdb.movie.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.example.imdb.movie.model.MovieModel;

@Repository
public class MovieRepositoryImpl implements MovieRepository{
	private final static String FILE_URL = "./src/main/resources/imdb_movie_data_2023.json";
	@Override
	public MovieModel getMovieById(int id) throws IOException, ParseException {
			JSONParser jsonParser = new JSONParser();
			FileReader reader = new FileReader(FILE_URL);
			var array= jsonParser.parse(reader);
			JSONArray jsonArray = (JSONArray) array;
			JSONObject jsonObject = (JSONObject) jsonArray.get(0);
			return MovieModel.fromJson(jsonObject);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieModel> getAllMovies() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(FILE_URL);
		JSONArray jsonArray =(JSONArray) jsonParser.parse(reader);
		List<MovieModel> movieList = jsonArray.stream()
		        .map((Object jsonObject) -> MovieModel.fromJson((JSONObject) jsonObject))
		        .toList();
		return movieList;
	}
	
	
	

	

	
}
