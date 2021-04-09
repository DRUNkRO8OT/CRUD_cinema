package com.cinema.spring.service;

import com.cinema.spring.dao.HallDAO;
import com.cinema.spring.entity.Hall;
import com.cinema.spring.exceptions.HallNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallDAO hallDAO;

    @Override
    @Transactional
    public List<Hall> getAllHalls() {

        return hallDAO.getAllHalls();
    }

    @Override
    @Transactional
    public void saveHall(Hall hall) throws HallNumberException {

        hallDAO.saveHall(hall);
    }

    @Override
    @Transactional
    public Hall getHall(int id) {

        return hallDAO.getHall(id);
    }

    @Override
    @Transactional
    public void deleteHall(int id) {

        hallDAO.deleteHall(id);
    }
}
