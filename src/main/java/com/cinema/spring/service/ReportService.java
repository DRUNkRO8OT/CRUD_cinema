package com.cinema.spring.service;

import com.cinema.spring.dao.MovieDAO;
import com.cinema.spring.entity.Movie;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private MovieDAO movieDAO;

    private final String path = "D:\\Study\\Programming\\3course\\reports";

    @Transactional
    public void exportReport(String reportFormat) throws JRException, FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:report.jrxml");

        List<Movie> movies;
        synchronized (movieDAO) {
            movies = movieDAO.getAllMovies();
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(movies);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint,
                    path + "\\movies.pdf");
        }
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,
                    path + "\\movies.html");
        }
    }

    public String getPath() {
        return "Reports generated in path: " + path;
    }
}
