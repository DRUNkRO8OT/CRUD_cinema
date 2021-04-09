package com.cinema.spring.dao;

import com.cinema.spring.entity.Movie;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface MovieDAO {
    List<Movie> getAllMovies();

    void saveMovie(Movie movie);

    Movie getMovie(int id);

    void deleteMovie(int id);
}
