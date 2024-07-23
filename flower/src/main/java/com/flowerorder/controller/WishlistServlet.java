package com.flowerorder.controller;

import com.flowerorder.dao.ProductDao;
import com.flowerorder.dao.ProductDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize ProductDao using dependency injection or factory method
        productDao = new ProductDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getSession().getAttribute("user_id").toString());
        int productId = Integer.parseInt(request.getParameter("product_id"));
        String productName = request.getParameter("product_name");
        double productPrice = Double.parseDouble(request.getParameter("product_price"));
        String productImage = request.getParameter("product_image");

        // Use ProductDao to handle wishlist addition
        productDao.addToWishlist(userId, productId, productName, productPrice, productImage);

        response.sendRedirect("home");
    }
}
