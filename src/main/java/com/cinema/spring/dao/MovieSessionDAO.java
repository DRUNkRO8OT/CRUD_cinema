package com.cinema.spring.dao;

import com.cinema.spring.entity.MovieSession;
import com.cinema.spring.exceptions.SessionException;

import java.util.List;

public interface MovieSessionDAO {
    List<MovieSession> getAllSessions();

    void saveSession(MovieSession movieSession) throws SessionException;

    MovieSession getSession(int id);

    void deleteSession(int id);
}