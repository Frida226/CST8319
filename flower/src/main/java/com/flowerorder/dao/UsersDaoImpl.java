package com.flowerorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flowerorder.model.Role;
import com.flowerorder.model.Users;
import com.flowerorder.util.DBConnection;

public class UsersDaoImpl implements UsersDao {
    private static final Logger logger = Logger.getLogger(UsersDaoImpl.class.getName());

    @Override
    public boolean registerUser(Users user) throws SQLException {
        String query = "INSERT INTO users (username, password_hash, email, phone_number, first_name, last_name, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPasswordHash());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPhoneNumber());
            pst.setString(5, user.getFirstName());
            pst.setString(6, user.getLastName());
            pst.setString(7, user.getAddress());

            logger.log(Level.INFO, "Executing query: {0}", pst);
            int rowCount = pst.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error registering user", e);
            throw e;
        }
    }

    @Override
    public boolean registerAdmin(Users admin) throws SQLException {
        String query = "INSERT INTO users (username, password_hash, email, role ) VALUES (?, ?, ?, ?)";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, admin.getUsername());
            pst.setString(2, admin.getPasswordHash());
            pst.setString(3, admin.getEmail());
            pst.setString(4, admin.getRole().toString());
            
            logger.log(Level.INFO, "Executing query: {0}", pst);
            int rowCount = pst.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error registering user", e);
            throw e;
        }
    }
    @Override
    public Users login(String username) throws SQLException {
        String query = "SELECT user_id,username, password_hash, role FROM users WHERE username = ?";//add user_id attribute in the loginUser object
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Users(
                	rs.getInt("user_id"),  // Add this line to retrieve user_id
                    rs.getString("username"), 
                    rs.getString("password_hash"),
                    Role.fromString(rs.getString("role"))// Convert string to Role enum here！！
                );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during login", e);
            throw e;
        }
        return null;
    }
    
    // added methods for handling cart&.. function
    @Override
    public int getUserIdFromUserName(String username) {
        String query = "SELECT user_id FROM users WHERE username = ?";
        int user_id = -1;
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            	user_id= rs.getInt("user_id");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during login", e);
        }
        return user_id;

    
    }
    
    @Override
    public Users getUserProfile(String username) throws SQLException{
    	
        String query = "SELECT username, password_hash, email, phone_number, first_name, last_name, address FROM users WHERE username = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Users(
                    rs.getString("username"), 
                    rs.getString("password_hash"), 
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("address")
                );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during login", e);
            throw e;
        }
        return null;
    }
    
    @Override
    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT user_id, username, role FROM users WHERE role = 'USER'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Users user = new Users(
                    rs.getInt("user_id"), 
                    rs.getString("username"), 
                    Role.valueOf(rs.getString("role").toUpperCase())	// transfer to UPPERCASE
                );
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }
}



