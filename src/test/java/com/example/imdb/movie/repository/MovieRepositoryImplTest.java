package com.example.imdb.movie.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class MovieRepositoryImplTest {
	
	@Autowired
	private MovieRepository repository;

	@Test
	@DisplayName("Should return movie by id")
	void testGetMovieById() {
		var actual = repository.getMovieById(2);
		assertEquals(actual.getId(), 2);
	}

}
