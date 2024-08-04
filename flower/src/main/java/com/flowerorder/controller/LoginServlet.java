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

        HttpSession session = request.getSession();        
        RequestDispatcher dispatcher = null;

        try {
            Users loginUser = usersDao.login(uname);//take a special name show its login function!
            if(loginUser != null && checkPassword(upwd, loginUser.getPasswordHash())){
                String roleStr = loginUser.getRole().name(); // retrieve Enum type name
                System.out.println("Role from loginUser: " + roleStr);
            	
                session.setAttribute("name", loginUser.getUsername());
//                session.setAttribute("role", loginUser.getRole()); // Store Role enum directly which is INCORRECT action!!
 

                if ("ADMIN".equals(roleStr)){// remove getParameter("role")
                    System.out.println("Attempting to dispatch to admin.jsp");
                    dispatcher = request.getRequestDispatcher("admin.jsp");
                } else if ("USER".equals(roleStr)) {
                	System.out.println("Attempting to dispatch to index.jsp");
                    dispatcher = request.getRequestDispatcher("index.jsp");
                }
                
                // Check if dispatcher is null before forwarding
                if (dispatcher != null) {
                    dispatcher.forward(request, response);
                } else {
                    System.out.println("Error: No dispatcher found. Check if JSP files are correctly named and located.");
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
                }
                
            } else {
                request.setAttribute("loginStatus", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
           
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

