package com.example.imdb.movie.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.example.imdb.movie.model.MovieModel;

@Repository
public class MovieRepositoryImpl implements MovieRepository{

	@Override
	public MovieModel getMovieById(int id) throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader("./src/main/resources/imdb_movie_data_2023.json");
			var array= jsonParser.parse(reader);
			JSONArray jsonArray = (JSONArray) array;
			JSONObject jsonObject = (JSONObject) jsonArray.get(0);
			return MovieModel.fromJson(jsonObject);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return null;
	}
	

	

	
}
