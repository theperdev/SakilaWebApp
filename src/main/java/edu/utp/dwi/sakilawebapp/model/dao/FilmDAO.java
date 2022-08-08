package edu.utp.dwi.sakilawebapp.model.dao;

import edu.utp.dwi.sakilawebapp.model.Film;

import java.sql.*;
import java.util.ArrayList;

public class FilmDAO implements IFilmDAO {

    static Connection connection = DBConnectionSingleton.getConnection();

    @Override
    public boolean addFilm(Film film) throws SQLException {
        // We reuse the updateFilm method to save new films.
        return updateFilm(film);
    }

    @Override
    public Film getFilm(int id) throws SQLException {
        Film film = null;
        String query = "select * from film where film_id=" + id;
        PreparedStatement pStatement = connection.prepareStatement(query);
        ResultSet resultSet = pStatement.executeQuery();

        while(resultSet.next()) {
            film = new Film(
                    resultSet.getInt("film_id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("release_year"),
                    resultSet.getInt("language_id"),
                    resultSet.getInt("original_language_id"),
                    resultSet.getInt("rental_duration"),
                    resultSet.getDouble("rental_rate"),
                    resultSet.getInt("length"),
                    resultSet.getDouble("replacement_cost"),
                    resultSet.getString("rating"),
                    resultSet.getString("special_features"),
                    resultSet.getTimestamp("last_update")
            );
        }

        return film;
    }

    @Override
    public boolean deleteFilm(int id) throws SQLException {
        String query = "{ CALL usp_DeleteFilm(?) }";

        CallableStatement statement = connection.prepareCall(query);
        statement.setInt(1, id);

        statement.execute();
        int rowsAffected = statement.getUpdateCount();

        return rowsAffected > 0;
    }

    @Override
    public boolean updateFilm(Film film) throws SQLException {
        String query = "{ CALL usp_UpdateFilm(?,?,?,?,?,?,?,?,?,?,?,?,?) }";

        CallableStatement statement = connection.prepareCall(query);

        statement.setInt(1, film.getId());
        statement.setString(2, film.getTitle());
        statement.setString(3, film.getDescription());
        statement.setInt(4, film.getReleaseYear());
        statement.setInt(5, film.getLanguageId());

        if (film.getOriginalLanguageId() > 0) {
            statement.setInt(6, film.getOriginalLanguageId());
        } else {
            statement.setNull(6, Types.INTEGER);
        }

        statement.setInt(7, film.getRentalDuration());
        statement.setDouble(8, film.getRentalRate());
        statement.setInt(9, film.getLength());
        statement.setDouble(10, film.getReplacementCost());
        statement.setString(11, film.getRating());
        statement.setString(12, film.getSpecialFeatures());
        statement.setTimestamp(13, film.getLastUpdate());

        statement.execute();

        int rowsAffected = statement.getUpdateCount();

        return rowsAffected > 0;
    }

    @Override
    public ArrayList<Film> getFilms() throws SQLException {
        // Method not implemented yet
        return null;
    }

    @Override
    public ArrayList<Film> getFilms(String initial) throws SQLException {
        String query = "select * from film where substr(title, 1, 1) = \"" + initial + "\" ORDER BY title;";
        PreparedStatement pStatement = connection.prepareStatement(query);
        ResultSet resultSet = pStatement.executeQuery();

        ArrayList<Film> films = new ArrayList<>();

        while(resultSet.next()) {
            films.add(new Film(
                    resultSet.getInt("film_id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("release_year"),
                    resultSet.getInt("language_id"),
                    resultSet.getInt("original_language_id"),
                    resultSet.getInt("rental_duration"),
                    resultSet.getDouble("rental_rate"),
                    resultSet.getInt("length"),
                    resultSet.getDouble("replacement_cost"),
                    resultSet.getString("rating"),
                    resultSet.getString("special_features"),
                    resultSet.getTimestamp("last_update")
            ));
        }

        return films;
    }
}
