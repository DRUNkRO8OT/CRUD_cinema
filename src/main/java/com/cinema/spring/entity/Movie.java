package com.cinema.spring.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotBlank(message = "REQUIRED FIELD")
    private String title;

    @Column(name = "country")
    private String country;

    @Column(name = "director")
    private String director;

    @Column(name = "genre")
    private String genre;

    @Column(name = "duration")
    @Min(value = 0, message = "Can't be negative!")
    private int duration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private List<MovieSession> sessions;

    public Movie() {
    }

    public Movie(String title, String country, String director, String genre, int duration) {
        this.title = title;
        this.country = country;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
    }

    public void addSession(MovieSession movieSession) {
        if (sessions == null) {
            sessions = new ArrayList<>();
        }

        sessions.add(movieSession);
        movieSession.setMovie(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<MovieSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<MovieSession> sessions) {
        this.sessions = sessions;
    }
}
