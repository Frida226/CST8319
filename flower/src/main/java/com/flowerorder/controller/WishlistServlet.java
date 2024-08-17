package com.flowerorder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flowerorder.dao.ProductsDao;
import com.flowerorder.dao.ProductsDaoImpl;
import com.flowerorder.dao.UsersDao;
import com.flowerorder.dao.UsersDaoImpl;
import com.flowerorder.dao.WishlistDao;
import com.flowerorder.dao.WishlistDaoImpl;
import com.flowerorder.model.Wishlist;
import com.flowerorder.model.WishlistItem;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    WishlistDao wishlistDao = new WishlistDaoImpl();
    UsersDao usersDao = new UsersDaoImpl();
  
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer userId = (Integer) request.getSession().getAttribute("user_id");
        List<WishlistItem> wishlistItems = wishlistDao.getWishlistItemsByUserId(userId);
        System.out.println("User ID in session: " + userId);//debugging
        System.out.println("Number of wishlist items retrieved for user " + userId + ": " + wishlistItems.size());//debugging
        request.setAttribute("wishlistItems", wishlistItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("wishlist.jsp");
        dispatcher.forward(request, response);

    }

    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }

        switch (action) {
            case "addToWishlist":
                addToWishlist(request, response);
                break;
            case "removeFromWishlist":
                removeFromWishlist(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                return;
        }
    }

    private void addToWishlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        String username = request.getSession().getAttribute("name").toString();
//        int quantity = Integer.parseInt(request.getParameter("product_quantity"));
        int userId = usersDao.getUserIdFromUserName(username);

		    try {
		        if (wishlistDao.isProductInWishlist(userId, productId)) {
		            request.setAttribute("message", "Product is already in your wishlist!");
		        } else {
					wishlistDao.addToWishlist(userId, productId);
		            request.setAttribute("message", "Item added to wishlist successfully!");
		        }
		    } catch (Exception e) {
		        request.setAttribute("errorMessage", "Error adding to wishlist: " + e.toString());
		        e.printStackTrace();
		    }
        response.sendRedirect(request.getContextPath() + "/wishlist");
    }

    private void removeFromWishlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int userId = (Integer) request.getSession().getAttribute("user_id");

        try {
            wishlistDao.removeFromWishlist(userId, productId);
            response.sendRedirect(request.getContextPath() + "/wishlist");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to remove item from wishlist.");
        }
    }


}

