package com.flowerorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flowerorder.model.Users;
import com.flowerorder.util.DBConnection;

public class UsersDAO {
    public boolean registerUser(Users user) throws SQLException {
        String query = "INSERT INTO users (username, password, email, phonenumber) VALUES (?, ?, ?, ?)";
        try (
        	Connection con = DBConnection.getConnection();
        	PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPhoneNumber());
            int rowCount = pst.executeUpdate();
            return rowCount > 0;
        }
    }
    
    public Users login(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Users(rs.getInt("id"),rs.getString("username"), 
                                   rs.getString("password"), rs.getString("email"), 
                                   rs.getString("phonenumber"));
            }
        }
        return null;
    }
}
