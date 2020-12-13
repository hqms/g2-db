package com.hqm.demongo.controller;

import com.hqm.demongo.model.Movie;
import com.hqm.demongo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping(value = "/")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @GetMapping(value = "/{movieId}")
    public Movie getMovieById(@PathVariable String movieID){
        return movieRepository.findMovieById(movieID);
    }

    @PostMapping(value = "/")
    public Movie addMovie(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @PutMapping(value = "/{movieId}")
    public Movie updateMovie(@PathVariable String movieId, @RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @DeleteMapping(value = "/{movieId}")
    public void deleteMovie(@PathVariable String movieId) {
        movieRepository.deleteById(movieId);
    }
}
