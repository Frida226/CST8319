package com.flowerorder.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.flowerorder.dao.UsersDao;
import com.flowerorder.dao.UsersDaoImpl;
import com.flowerorder.model.Role;
import com.flowerorder.model.Users;

@WebServlet("/addAdmin")
public class AdminRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(RegistrationServlet.class.getName());
    UsersDao usersDao = new UsersDaoImpl();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String hashedPassword = hashPassword(password);
        
        Users admin = new Users(username, hashedPassword, email, Role.ADMIN);  // Directly set as ADMIN

        try {
            boolean result = usersDao.registerAdmin(admin);
            if (result) {
            	response.sendRedirect("admin.jsp?status=success");
            } else {
            	response.sendRedirect("admin.jsp?status=failed");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Admin registration error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
