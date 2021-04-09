package com.cinema.spring.dao;

import com.cinema.spring.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDAOImpl implements MovieDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Movie> getAllMovies() {

        Session session = sessionFactory.getCurrentSession();
        List<Movie> allMovies = session.createQuery("from Movie", Movie.class).getResultList();

        return allMovies;
    }

    @Override
    public void saveMovie(Movie movie) {


        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(movie);
    }

    @Override
    public Movie getMovie(int id) {
        Session session = sessionFactory.getCurrentSession();
        Movie movie = session.get(Movie.class, id);

        return movie;
    }

    @Override
    public void deleteMovie(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Movie> query = session.createQuery("delete from Movie " +
                "where id =:movieId");
        query.setParameter("movieId", id);
        query.executeUpdate();
    }
}
