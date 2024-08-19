package com.flowerorder.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import com.flowerorder.model.CartItem;
import com.flowerorder.model.Products;

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {
    ProductsDao productsDao = new ProductsDaoImpl();
    CartDao cartDao = new CartDaoImp();
    UsersDao usersDao = new UsersDaoImpl();
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            HttpSession session = request.getSession(false);
            Integer userId = (Integer) session.getAttribute("user_id");
            
            if (session == null || session.getAttribute("name") == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found.");
                return;
            }
            
            if (userId == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            List<CartItem> cartItems = cartDao.getCartItemsByUserId(userId);            
            request.setAttribute("cartItems", cartItems);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }

        switch (action) {
        case "addToCart":
            addToCart(request, response);
            break;
        case "updateCart":
            updateCartItem(request, response);
            break;
        case "removeFromCart":
            removeFromCart(request, response);
            break;
        default:
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            return;
        }
    }

    
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // retreive userName and check if exist
        String username = (String) session.getAttribute("name");
        if (username == null) {
            response.sendRedirect("login.jsp"); // if not login then redirect to login page
            return;
        }
        
        // retreive product id and quantity
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("product_quantity"));
        
        try {
            // get userName from userId
            int user_id = usersDao.getUserIdFromUserName(username);

            // check if the product under the user exist in db
            boolean itemExists = cartDao.checkIfItemExists(user_id, product_id);
            if (itemExists) {
                // if product exist, update its qty
                cartDao.addItemQty(user_id, product_id, quantity);
            } else {
                // if not exist,add the product
                cartDao.addToCart(user_id, product_id, quantity);
            }
            request.setAttribute("message", "Item was added to Cart successfully!");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error adding to Cart: " + e.toString());
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/Cart");
    }

    
    private void removeFromCart(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        int productId = Integer.parseInt(request.getParameter("product_id")); // from request to get product_id
      
        try {
//        	System.out.println("Attempting to remove product_id: " + productId + " for user_id: " + userId);
        // Remove item from database
        cartDao.removeFromCart(userId, productId); 
        
        // Reload the cart from the database after deletion
        List<CartItem> cartItems = cartDao.getCartItemsByUserId(userId);
        
        session.setAttribute("cartItems", cartItems); // Update the session
        
        response.sendRedirect("cart.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to remove item from cart.");
        }
    }
    
    private void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            // 更新数据库中的数量
            cartDao.updateCartItem(userId, productId, newQuantity);

            // 重新从数据库加载更新后的购物车
            List<CartItem> cartItems = cartDao.getCartItemsByUserId(userId);
            session.setAttribute("cartItems", cartItems);

            response.sendRedirect("cart.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update cart item.");
        }
    }
}
