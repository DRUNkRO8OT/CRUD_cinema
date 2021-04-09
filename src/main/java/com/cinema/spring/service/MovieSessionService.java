package com.cinema.spring.service;

import com.cinema.spring.entity.MovieSession;
import com.cinema.spring.exceptions.SessionException;

import java.util.List;

public interface MovieSessionService {
    List<MovieSession> getAllSessions();

    void saveSession(MovieSession session) throws SessionException;

    MovieSession getSession(int id);

    void deleteSession(int id);
}
