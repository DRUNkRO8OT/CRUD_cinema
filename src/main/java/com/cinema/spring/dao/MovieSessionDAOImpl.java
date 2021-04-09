package com.cinema.spring.dao;

import com.cinema.spring.entity.MovieSession;
import com.cinema.spring.exceptions.SessionException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MovieSessionDAOImpl implements MovieSessionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<MovieSession> getAllSessions() {
        Session session = sessionFactory.getCurrentSession();
        List<MovieSession> allSessions = session.createQuery("from MovieSession " +
                "order by startTime", MovieSession.class).getResultList();

        return allSessions;
    }

    @Override
    public void saveSession(MovieSession movieSession) throws SessionException {

        Session session = sessionFactory.getCurrentSession();

        List<MovieSession> allSessions = session.createQuery("from MovieSession " +
                "where id !=:sessionId", MovieSession.class).setParameter("sessionId", movieSession.getId())
                .getResultList();
        for (MovieSession otherSession : allSessions) {
            if (otherSession.getHall().getHallNumber() == movieSession.getHall().getHallNumber()) {
                long millis = Math.abs(otherSession.getStartTime().getTime() - movieSession.getStartTime().getTime());
                if (millis / 1000 / 60 < 180)
                    throw new SessionException("The hall is already busy!");
            }
        }

        session.saveOrUpdate(movieSession);
    }

    @Override
    public MovieSession getSession(int id) {
        Session session = sessionFactory.getCurrentSession();
        MovieSession movieSession = session.get(MovieSession.class, id);

        return movieSession;
    }

    @Override
    public void deleteSession(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<MovieSession> query = session.createQuery("delete from MovieSession " +
                "where id =:sessionId");
        query.setParameter("sessionId", id);
        query.executeUpdate();
    }
}
