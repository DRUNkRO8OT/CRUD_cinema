package com.cinema.spring.controller;

import com.cinema.spring.entity.Hall;
import com.cinema.spring.entity.Movie;
import com.cinema.spring.entity.MovieSession;
import com.cinema.spring.exceptions.HallNumberException;
import com.cinema.spring.exceptions.SessionException;
import com.cinema.spring.service.HallService;
import com.cinema.spring.service.MovieService;
import com.cinema.spring.service.MovieSessionService;
import com.cinema.spring.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private HallService hallService;
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private ReportService reportService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping("/")
    public String showMenu() {
        return "menu";
    }

    @RequestMapping("/report")
    public String generateReport(Model model) {

        Runnable report1 = () -> {
            try {
                reportService.exportReport("pdf");
            } catch (JRException | FileNotFoundException e) {
                e.printStackTrace();
            }
        };

        Runnable report2 = () -> {
            try {
                reportService.exportReport("html");
            } catch (JRException | FileNotFoundException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(report1);
        Thread thread2 = new Thread(report2);
        thread1.start();
        thread2.start();

        String path = reportService.getPath();
        model.addAttribute("path", path);

        return "report";
    }

    @RequestMapping("/showAllMovies")
    public String showAllMovies(Model model) {

        List<Movie> allMovies = movieService.getAllMovies();
        model.addAttribute("allMovies", allMovies);

        return "all-movies";
    }

    @RequestMapping("/addNewMovie")
    public String addNewMovie(Model model) {

        Movie movie = new Movie();
        model.addAttribute("movie", movie);

        return "movie-info";
    }

    @RequestMapping("/saveMovie")
    public String saveMovie(@Valid @ModelAttribute("movie") Movie movie,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "movie-info";
        } else {
            movieService.saveMovie(movie);
            return "redirect:/showAllMovies";
        }
    }

    @RequestMapping("/updateMovie")
    public String updateMovie(@RequestParam("movieId") int id, Model model) {

        Movie movie = movieService.getMovie(id);
        model.addAttribute("movie", movie);

        return "movie-info";
    }

    @RequestMapping("/deleteMovie")
    public String deleteMovie(@RequestParam("movieId") int id) {

        movieService.deleteMovie(id);

        return "redirect:/showAllMovies";
    }

    @RequestMapping("/showAllHalls")
    public String showAllHalls(Model model) {

        List<Hall> allHalls = hallService.getAllHalls();
        model.addAttribute("allHalls", allHalls);

        return "all-halls";
    }

    @RequestMapping("/addNewHall")
    public String addNewHall(Model model) {

        Hall hall = new Hall();
        model.addAttribute("hall", hall);

        return "hall-info";
    }

    @RequestMapping("/saveHall")
    public String saveHall(@Valid @ModelAttribute("hall") Hall hall,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "hall-info";
        } else {
            try {
                hallService.saveHall(hall);
            } catch (HallNumberException e) {
                model.addAttribute("error", e);
                return "error";
            }
            return "redirect:/showAllHalls";
        }
    }

    @RequestMapping("/updateHall")
    public String updateHall(@RequestParam("hallId") int id, Model model) {

        Hall hall = hallService.getHall(id);
        model.addAttribute("hall", hall);

        return "hall-info";
    }

    @RequestMapping("/deleteHall")
    public String deleteHall(@RequestParam("hallId") int id) {

        hallService.deleteHall(id);

        return "redirect:/showAllHalls";
    }

    @RequestMapping("/showAllSessions")
    public String showAllSessions(Model model) {

        List<MovieSession> allSessions = movieSessionService.getAllSessions();
        model.addAttribute("allSessions", allSessions);

        return "all-sessions";
    }

    @RequestMapping("/addNewSession")
    public String addNewSession(Model model) {

        MovieSession session = new MovieSession();
        model.addAttribute("session", session);
        List<Movie> allMovies = movieService.getAllMovies();
        model.addAttribute("allMovies", allMovies);
        List<Hall> allHalls = hallService.getAllHalls();
        model.addAttribute("allHalls", allHalls);

        return "session-info";
    }

    @RequestMapping("/saveSession")
    public String saveSession(@Valid @ModelAttribute("session") MovieSession session,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<Movie> allMovies = movieService.getAllMovies();
            model.addAttribute("allMovies", allMovies);
            List<Hall> allHalls = hallService.getAllHalls();
            model.addAttribute("allHalls", allHalls);
            return "session-info";
        } else {
            try {
                session.setHall(hallService.getHall(session.getHallId()));
                session.setMovie(movieService.getMovie(session.getMovieId()));
                movieSessionService.saveSession(session);
            } catch (SessionException e) {
                model.addAttribute("error", e);
                return "error";
            }
            return "redirect:/showAllSessions";
        }
    }

    @RequestMapping("/updateSession")
    public String updateSession(@RequestParam("sessionId") int id, Model model) {

        MovieSession session = movieSessionService.getSession(id);
        model.addAttribute("session", session);

        List<Movie> allMovies = movieService.getAllMovies();
        model.addAttribute("allMovies", allMovies);
        List<Hall> allHalls = hallService.getAllHalls();
        model.addAttribute("allHalls", allHalls);

        return "session-info";
    }

    @RequestMapping("/deleteSession")
    public String deleteSession(@RequestParam("sessionId") int id) {

        movieSessionService.deleteSession(id);

        return "redirect:/showAllSessions";
    }
}