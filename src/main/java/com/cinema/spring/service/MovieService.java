package com.cinema.spring.service;

import com.cinema.spring.entity.Movie;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    void saveMovie(Movie movie);

    Movie getMovie(int id);

    void deleteMovie(int id);
}
