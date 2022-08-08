package edu.utp.dwi.sakilawebapp.model.dao;

import edu.utp.dwi.sakilawebapp.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO implements ICategoryDAO {
    static Connection connection = DBConnectionSingleton.getConnection();

    @Override
    public ArrayList<Category> getCategories() throws SQLException {
        String query = "select * from category;";
        PreparedStatement pStatement = connection.prepareStatement(query);
        ResultSet resultSet = pStatement.executeQuery();

        ArrayList<Category> categories = new ArrayList<>();

        while(resultSet.next()) {
            categories.add(new Category(
                    resultSet.getInt("category_id"),
                    resultSet.getString("name"),
                    resultSet.getDate("last_update")
            ));
        }

        return categories;
    }
}
