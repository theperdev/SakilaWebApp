package edu.utp.dwi.sakilawebapp.controller;

import edu.utp.dwi.sakilawebapp.model.Film;
import edu.utp.dwi.sakilawebapp.model.dao.CategoryDAO;
import edu.utp.dwi.sakilawebapp.model.dao.FilmDAO;
import edu.utp.dwi.sakilawebapp.model.dao.LanguageDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "FilmController", value = "/FilmController")
public class FilmController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest("get", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest("post", request, response);
    }

    protected void processRequest(String method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "list";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        String initial = "A";
        if (request.getParameter("initial") != null) {
            initial = request.getParameter("initial");
        }

        int id = 0;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        FilmDAO filmDAO = new FilmDAO();

        try {
            if (method == "get") {
                switch (action) {
                    case "list":
                        goToCatalog(request, response, filmDAO.getFilms(initial));
                        break;
                    case "edit":
                        setViewContent(request, id > 0 ? filmDAO.getFilm(id) : new Film());
                        request.getRequestDispatcher("view/film.jsp").forward(request, response);
                        break;
                    case "delete":
                        filmDAO.deleteFilm(id);
                        goToCatalog(request, response, filmDAO.getFilms(initial));
                        break;
                    case "view":
                        setViewContent(request, filmDAO.getFilm(id));
                        request.getRequestDispatcher("view/view.jsp").forward(request, response);
                        break;
                }
            } else {
                switch (action) {
                    case "save":
                        if (filmDAO.addFilm(getFilmFromForm(request))) {
                            goToCatalog(request, response, filmDAO.getFilms("A"));
                        } else {
                            setViewContent(request, filmDAO.getFilm(id));
                            request.getRequestDispatcher("view/film.jsp").forward(request, response);
                        }
                        break;
                    case "update":
                        if (filmDAO.updateFilm(getFilmFromForm(request))) {
                            goToCatalog(request, response, filmDAO.getFilms("A"));
                            request.getRequestDispatcher("view/listFilms.jsp").forward(request, response);
                        } else {
                            setViewContent(request, filmDAO.getFilm(id));
                            request.getRequestDispatcher("view/film.jsp").forward(request, response);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViewContent(HttpServletRequest request, Film film) throws SQLException {
        request.setAttribute("film", film);
        request.setAttribute("languages", new LanguageDAO().getLanguages());
        request.setAttribute("categories", new CategoryDAO().getCategories());
    }

    private void goToCatalog(HttpServletRequest request, HttpServletResponse response, ArrayList<Film> films) throws ServletException, IOException {
        request.setAttribute("films", films);
        request.getRequestDispatcher("view/listFilms.jsp").forward(request, response);
    }

    private Film getFilmFromForm(HttpServletRequest request) {
        Film film = new Film();

        film.setId(Integer.parseInt(request.getParameter("id")));
        film.setTitle(request.getParameter("txtTitle"));
        film.setDescription(request.getParameter("txtDescription"));
        film.setReleaseYear(Integer.parseInt(request.getParameter("txtReleaseYear")));
        film.setLanguageId(Integer.parseInt(request.getParameter("selLanguage")));
        if (request.getParameter("selOriginalLanguage") != "0") {
            film.setOriginalLanguageId(Integer.parseInt(request.getParameter("selOriginalLanguage")));
        }
        film.setRentalDuration(Integer.parseInt(request.getParameter("txtRentalDuration")));
        film.setRentalRate(Double.parseDouble(request.getParameter("txtRentalRate")));
        film.setLength(Integer.parseInt(request.getParameter("txtLength")));
        film.setReplacementCost(Double.parseDouble(request.getParameter("txtReplacementCost")));
        film.setRating(request.getParameter("selRating"));

        String[] features = request.getParameterValues("selFeatures");
        String allFeatures = features[0];
        for (int i = 1; i < features.length; i++) {
            allFeatures += "," + features[i];
        }
        film.setSpecialFeatures(allFeatures);
        film.setLastUpdate(new Timestamp(new Date().getTime()));

        return film;
    }
}
