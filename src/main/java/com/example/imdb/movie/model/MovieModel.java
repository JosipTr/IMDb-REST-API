package com.example.imdb.movie.model;

import java.util.Objects;

import org.json.simple.JSONObject;

public class MovieModel {
	private final Long id;
	private final String name;
	private final String rating;
	private final String genre;
	private final Long year;
	private final String duration;
	private final String cast;
	private final String director;
	
	public MovieModel(Long id, String name, String rating, String genre, Long year, String duration, String cast,
			String director) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.genre = genre;
		this.year = year;
		this.duration = duration;
		this.cast = cast;
		this.director = director;
	}
	
	public static MovieModel fromJson(JSONObject jsonObject) {
		return new MovieModel((Long) jsonObject.get("id"), (String) jsonObject.get("Title"), (String) jsonObject.get("Rating"),(String) jsonObject.get("Genre"),(Long) jsonObject.get("Year"),(String) jsonObject.get("Duration"),(String) jsonObject.get("Cast"),(String) jsonObject.get("Director"));
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRating() {
		return rating;
	}

	public String getGenre() {
		return genre;
	}

	public Long getYear() {
		return year;
	}

	public String getDuration() {
		return duration;
	}

	public String getCast() {
		return cast;
	}

	public String getDirector() {
		return director;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cast, director, duration, genre, id, name, rating, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieModel other = (MovieModel) obj;
		return Objects.equals(cast, other.cast) && Objects.equals(director, other.director)
				&& Objects.equals(duration, other.duration) && Objects.equals(genre, other.genre) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(rating, other.rating) && year == other.year;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", rating=" + rating + ", genre=" + genre + ", year=" + year
				+ ", duration=" + duration + ", cast=" + cast + ", director=" + director + "]";
	}
	
	
}

//"FIELD1": 0,
//"Moive Name": " Leave the World Behind",
//"Rating": "6.5",
//"Votes": "90000.0",
//"Meta Score": "67",
//"Genre": "Drama, Mystery, Thriller",
//"PG Rating": "R",
//"Year": 2023,
//"Duration": "2h 18m",
//"Cast": "Julia Roberts, Mahershala Ali, Ethan Hawke, Myha'la",
//"Director": "Sam Esmail"