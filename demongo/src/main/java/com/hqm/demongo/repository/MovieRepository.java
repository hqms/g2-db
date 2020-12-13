package com.hqm.demongo.repository;

import com.hqm.demongo.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findMovieById(String movieId);
}
