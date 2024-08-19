package com.flowerorder;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import com.flowerorder.dao.UsersDaoImpl;
import com.flowerorder.model.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import static org.junit.Assert.*;

public class UsersDaoImplTest {

    private Connection connection;
    private UsersDaoImpl usersDao;

    @Before
    public void setUp() throws SQLException {
        // Initialize H2 database connection
        String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        connection = DriverManager.getConnection(url, "sa", "");
        
        // Create table and insert test data
        String createTableSQL = "CREATE TABLE users (" +
                "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50) NOT NULL, " +
                "password_hash VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100), " +
                "phone_number VARCHAR(20), " +
                "first_name VARCHAR(50), " +
                "last_name VARCHAR(50), " +
                "address VARCHAR(255)" +
                ")";
        try (PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.execute();
        }

        String insertSQL = "INSERT INTO users (username, password_hash, email, phone_number, first_name, last_name, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, "testuser");
            pstmt.setString(2, "testpasswordhash");
            pstmt.setString(3, "test@example.com");
            pstmt.setString(4, "1234567890");
            pstmt.setString(5, "John");
            pstmt.setString(6, "Doe");
            pstmt.setString(7, "123 Test Street");
            pstmt.executeUpdate();
        }

        usersDao = new UsersDaoImpl();
    }

    @Test
    public void testRegisterUser() throws SQLException {
        // Call the method to test
        Users user = usersDao.login("testUser");
        assertNotNull("User should not be null", user);
        assertEquals("testUser", user.getUsername());
    }

    @After
    public void tearDown() throws SQLException {
        // Clean up test data
        String deleteSQL = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setString(1, "testuser");
            pstmt.executeUpdate();
        }
        connection.close();
    }
}

