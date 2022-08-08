package edu.utp.dwi.sakilawebapp.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* Implementation of the SINGLETON design pattern for database connection.
* It makes possible to have only one object instance to manage the connection with the data source.
* */

public class DBConnectionSingleton {
    private static Connection connection = null;

    public static Connection getConnection() {
        return connection;
    }

    static {
        String url = "jdbc:mysql://localhost/sakila";
        String user = "root";
        String password = "mysql";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
