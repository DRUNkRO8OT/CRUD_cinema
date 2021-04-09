package com.cinema.spring.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hall")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(unique = true, name = "hall_number")
    @Min(value = 0, message = "Can't be negative!")
    private int hallNumber;

    @Column(name = "hall_name")
    private String hallName;

    @Column(name = "capacity")
    @Min(value = 1, message = "Can't be less than 1!")
    private int capacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hall")
    private List<MovieSession> sessions;

    public Hall() {
    }

    public Hall(int hallNumber, String hallName, int capacity) {
        this.hallNumber = hallNumber;
        this.hallName = hallName;
        this.capacity = capacity;
    }

    public void addSession(MovieSession movieSession) {
        if (sessions == null) {
            sessions = new ArrayList<>();
        }

        sessions.add(movieSession);
        movieSession.setHall(this);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getHallNumber() {
        return hallNumber;
    }
    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    public String getHallName() {
        return hallName;
    }
    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<MovieSession> getSessions() {
        return sessions;
    }
    public void setSessions(List<MovieSession> sessions) {
        this.sessions = sessions;
    }
}
