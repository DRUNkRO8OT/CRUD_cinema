package com.cinema.spring.dao;

import com.cinema.spring.entity.Hall;
import com.cinema.spring.exceptions.HallNumberException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HallDAOImpl implements HallDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Hall> getAllHalls() {

        Session session = sessionFactory.getCurrentSession();
        List<Hall> allHall = session.createQuery("from Hall", Hall.class).getResultList();

        return allHall;
    }

    @Override
    public void saveHall(Hall hall) throws HallNumberException {

        Session session = sessionFactory.getCurrentSession();

        List<Integer> numbers = session.createQuery("select hallNumber from Hall", Integer.class).getResultList();
        List<Integer> ids = session.createQuery("select id from Hall", Integer.class).getResultList();
        System.out.println(numbers);
        for (int i = 0; i < numbers.size(); i++) {
            if (hall.getHallNumber() == numbers.get(i) && hall.getId() != ids.get(i)) {
                throw new HallNumberException("Hall with that number already exists!");
            }
        }
        session.saveOrUpdate(hall);
    }

    @Override
    public Hall getHall(int id) {
        Session session = sessionFactory.getCurrentSession();
        Hall hall = session.get(Hall.class, id);

        return hall;
    }

    @Override
    public void deleteHall(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Hall> query = session.createQuery("delete from Hall " +
                "where id =:hallId");
        query.setParameter("hallId", id);
        query.executeUpdate();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
