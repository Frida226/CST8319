package com.flowerorder.dao;

import java.sql.SQLException;

import com.flowerorder.model.Users;

public interface UsersDao {
	
	boolean registerUser(Users user) throws SQLException;
	Users login(String username, String password) throws SQLException;

}
