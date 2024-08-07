package com.flowerorder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flowerorder.dao.CartDao;
import com.flowerorder.dao.CartDaoImp;
import com.flowerorder.dao.ProductsDao;
import com.flowerorder.dao.ProductsDaoImpl;
import com.flowerorder.dao.UsersDao;
import com.flowerorder.dao.UsersDaoImpl;
import com.flowerorder.model.Cart;
import com.flowerorder.model.Products;
import com.flowerorder.model.Users;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/Profile")
public class UserServlet extends HttpServlet {
    ProductsDao productsDao = new ProductsDaoImpl();
    CartDao cartDao = new CartDaoImp();
    UsersDao usersDao = new UsersDaoImpl();
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("name") == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found.");
                return;
            }

            Users user = usersDao.getUserProfile(session.getAttribute("name").toString());
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }


}
