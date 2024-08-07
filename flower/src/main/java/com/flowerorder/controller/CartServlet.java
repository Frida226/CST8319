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

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/Cart")
public class CartServlet extends HttpServlet {
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

            List<Products> cartItems = cartDao.getAllCartItems(session.getAttribute("name").toString());
            request.setAttribute("cartItems", cartItems);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addToCart = request.getParameter("addToCart");

        if (addToCart == null) {
            addToCart = "addToCart";
        }

        switch (addToCart) {
            case "addToCart":
                addToCart(request, response);
                break;
            case "updateCart":
                updateCart(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                return;
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("product_quantity"));
        String username = request.getSession().getAttribute("name").toString();

        try {
            cartDao.addToCart(usersDao.getUserIdFromUserName(username), product_id, quantity);
            request.setAttribute("message", "Item was added to Cart successfully!");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error adding to Cart: " + e.toString());
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/Cart");
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Your updateCart logic here
    }
}
