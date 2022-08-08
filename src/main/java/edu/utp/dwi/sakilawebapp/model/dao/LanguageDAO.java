package edu.utp.dwi.sakilawebapp.model.dao;

import edu.utp.dwi.sakilawebapp.model.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LanguageDAO implements ILanguageDAO {

    static Connection connection = DBConnectionSingleton.getConnection();

    @Override
    public ArrayList<Language> getLanguages() throws SQLException {
        String query = "select * from language;";
        PreparedStatement pStatement = connection.prepareStatement(query);
        ResultSet resultSet = pStatement.executeQuery();

        ArrayList<Language> languages = new ArrayList<>();

        while(resultSet.next()) {
            languages.add(new Language(
                    resultSet.getInt("language_id"),
                    resultSet.getString("name"),
                    resultSet.getDate("last_update")
            ));
        }

        return languages;
    }
}
