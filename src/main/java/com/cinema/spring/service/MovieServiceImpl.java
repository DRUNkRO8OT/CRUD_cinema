package com.cinema.spring.service;

import com.cinema.spring.dao.MovieDAO;
import com.cinema.spring.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDAO movieDAO;

    @Override
    @Transactional
    public List<Movie> getAllMovies() {

        return movieDAO.getAllMovies();
    }

    @Override
    @Transactional
    public void saveMovie(Movie movie) {

        movieDAO.saveMovie(movie);
    }

    @Override
    @Transactional
    public Movie getMovie(int id) {

        return movieDAO.getMovie(id);
    }

    @Override
    @Transactional
    public void deleteMovie(int id) {

        movieDAO.deleteMovie(id);
    }
}
