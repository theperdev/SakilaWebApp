package edu.utp.dwi.sakilawebapp.model.dao;

import edu.utp.dwi.sakilawebapp.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICategoryDAO {
    ArrayList<Category> getCategories() throws SQLException;
}
