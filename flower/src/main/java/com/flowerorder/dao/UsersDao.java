package com.flowerorder.dao;

import java.sql.SQLException;
import com.flowerorder.model.Users;

public interface UsersDao {
    boolean registerUser(Users user) throws SQLException;
    Users login(String username) throws SQLException;
	boolean registerAdmin(Users admin)throws SQLException;
	int getUserIdFromUserName(String username);
	
    Users getUserProfile(String username) throws SQLException;

}

