package com.cinema.spring.dao;

import com.cinema.spring.entity.Hall;
import com.cinema.spring.entity.Movie;
import com.cinema.spring.entity.MovieSession;
import com.cinema.spring.exceptions.HallNumberException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HallDAOImplTest {

    private HallDAOImpl hallDAO;
    private final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Hall.class)
            .addAnnotatedClass(Movie.class)
            .addAnnotatedClass(MovieSession.class)
            .buildSessionFactory();

    @Before
    public void setUp() {
        hallDAO = new HallDAOImpl();
        hallDAO.setSessionFactory(sessionFactory);
    }

    @Test
    public void getAllHalls_NOT_NULL() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Hall> expected = hallDAO.getAllHalls();

        session.getTransaction().commit();

        Assert.assertNotNull(expected);
    }

    @Test(expected = HallNumberException.class)
    public void saveHall() throws HallNumberException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Hall testHall = new Hall(2, "testHall", 33);
        hallDAO.saveHall(testHall);

        session.getTransaction().commit();
    }
}