package com.flowerorder.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
//    private static final String URL = "jdbc:mysql://localhost:3306/flowershop?useSSL=false";
	private static final String URL = "jdbc:mysql://localhost:3305/flowershop";
    // Change this to your database username
    private static final String USER = "root";
    // Change this to your database password
//    private static final String PASSWORD = "Zxf980226!";
    private static final String PASSWORD = "8011";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

