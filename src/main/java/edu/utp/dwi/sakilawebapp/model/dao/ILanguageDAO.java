package edu.utp.dwi.sakilawebapp.model.dao;

import edu.utp.dwi.sakilawebapp.model.Language;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ILanguageDAO {
    ArrayList<Language> getLanguages() throws SQLException;
}
