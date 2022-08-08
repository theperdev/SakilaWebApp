package edu.utp.dwi.sakilawebapp.model.dao;

import edu.utp.dwi.sakilawebapp.model.Film;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IFilmDAO {
    boolean addFilm(Film film) throws SQLException;
    Film getFilm(int id) throws SQLException;
    boolean deleteFilm(int id) throws SQLException;
    boolean updateFilm(Film film) throws SQLException;
    ArrayList<Film> getFilms() throws SQLException;
    ArrayList<Film> getFilms(String initial) throws SQLException;
}
