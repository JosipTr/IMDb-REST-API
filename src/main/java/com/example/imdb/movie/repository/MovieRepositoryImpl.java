package com.example.imdb.movie.repository;

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
public class MovieRepositoryImpl implements MovieRepository {

	private final static String FILE_URL = "./src/main/resources/imdb_movie_data_2023.json";
	private final JSONParser jsonParser = new JSONParser();

	@Override
	public MovieModel getMovieById(int id) throws IOException, ParseException {
		FileReader reader = new FileReader(FILE_URL);
		JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
		JSONObject jsonObject = (JSONObject) jsonArray.get(id);
		return MovieModel.fromJson(jsonObject);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieModel> getAllMovies() throws IOException, ParseException {
		FileReader reader = new FileReader(FILE_URL);
		JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
		return jsonArray.stream().map((Object jsonObject) -> MovieModel.fromJson((JSONObject) jsonObject)).toList();

	}

	public List<MovieModel> getMoviesByName(String name) throws IOException, ParseException {
		FileReader reader = new FileReader(FILE_URL);
		JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

		return jsonArray.stream()
				.filter(jsonObject -> containsTitle(jsonObject, name))
				.map(jsonObject -> MovieModel.fromJson((JSONObject) jsonObject))
				.toList();
	}

	private boolean containsTitle(Object jsonObject, String name) {
		String title = (String) ((JSONObject) jsonObject).get("Title");
		return title.contains(name);
	}

}
