package com.flowerorder.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import com.flowerorder.dao.UsersDao;
import com.flowerorder.dao.UsersDaoImpl;
import com.flowerorder.model.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UsersDao usersDao = new UsersDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String upwd = request.getParameter("password");
        String role = request.getParameter("role").trim().toUpperCase(); // Clean and standardize the role input

        HttpSession session = request.getSession();        
        RequestDispatcher dispatcher = null;

        try {
            Users loginUser = usersDao.login(uname);//take a special name show its login function!
            if(loginUser != null && checkPassword(upwd, loginUser.getPasswordHash())){
                session.setAttribute("name", loginUser.getUsername());
                session.setAttribute("role", loginUser.getRole()); // Store Role enum directly and will used at listAllProductByRole later!! 
                // get a NULL role because the users(loginUser) has no role attribute who was returned by usersDao.login(uname);!!
                
                if ("ADMIN".equals(role)){// this role is retrieved from above String variable role which get from input on jsp page! 
                    dispatcher = request.getRequestDispatcher("admin.jsp");
                } else if ("USER".equals(role)) {
                    dispatcher = request.getRequestDispatcher("index.jsp");
                }
            } else {
                request.setAttribute("loginStatus", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                    "An error occurred while processing your request.");
        }
    }

    private boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

