package com.cinema.spring.service;

import com.cinema.spring.entity.Hall;
import com.cinema.spring.exceptions.HallNumberException;

import java.util.List;

public interface HallService {
    List<Hall> getAllHalls();

    void saveHall(Hall hall) throws HallNumberException;

    Hall getHall(int id);

    void deleteHall(int id);
}