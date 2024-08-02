package com.flowerorder.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import com.flowerorder.dao.UsersDao;
import com.flowerorder.dao.UsersDaoImpl;
import com.flowerorder.model.Users;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(RegistrationServlet.class.getName());
    UsersDao usersDao = new UsersDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String phoneNumber = request.getParameter("contact");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String address = request.getParameter("address");
        boolean subscribe = request.getParameter("subscribe") != null; // Check if the subscribe checkbooks was checked
        RequestDispatcher dispatcher = null;

        String passwordHash = hashPassword(password);

        Users user = new Users(username, passwordHash, email, phoneNumber, firstName, lastName, address);

        try {
            boolean result = usersDao.registerUser(user);
            dispatcher = request.getRequestDispatcher("registration.jsp");
            if (result) {
                request.getSession().setAttribute("subscribe", subscribe); // Store subscription preference in the session
                request.setAttribute("status", "success");
                response.sendRedirect("login.jsp"); // Redirect to login page after successful registration
            } else {
                request.setAttribute("status", "failed");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }
//            dispatcher.forward(request, response);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Error during registration", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "General Error during registration", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}



