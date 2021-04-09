package com.cinema.spring.service;

import com.cinema.spring.dao.MovieSessionDAO;
import com.cinema.spring.entity.MovieSession;
import com.cinema.spring.exceptions.SessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {

    @Autowired
    private MovieSessionDAO movieSessionDAO;

    @Override
    @Transactional
    public List<MovieSession> getAllSessions() {
        return movieSessionDAO.getAllSessions();
    }

    @Override
    @Transactional
    public void saveSession(MovieSession session) throws SessionException {
        movieSessionDAO.saveSession(session);
    }

    @Override
    @Transactional
    public MovieSession getSession(int id) {

        return movieSessionDAO.getSession(id);
    }

    @Override
    @Transactional
    public void deleteSession(int id) {
        movieSessionDAO.deleteSession(id);
    }
}
