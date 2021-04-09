package com.cinema.spring.dao;

import com.cinema.spring.entity.Hall;
import com.cinema.spring.exceptions.HallNumberException;

import java.util.List;

public interface HallDAO {
    List<Hall> getAllHalls();

    void saveHall(Hall hall) throws HallNumberException;

    Hall getHall(int id);

    void deleteHall(int id);
}
