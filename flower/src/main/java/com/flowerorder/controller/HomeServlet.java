package com.flowerorder.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flowerorder.dao.ProductsDao;
import com.flowerorder.dao.ProductsDaoImpl;
import com.flowerorder.model.Products;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	// the serialVersionUID serves as an explicit version control mechanism 
	// to handle the serialization and deserialization of classes,
    private static final long serialVersionUID = 1L;
    // DAO for handling product data operations
    private ProductsDao productsDao;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize ProductDao using a factory method or direct instantiation
        productsDao = new ProductsDaoImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        Integer userId = (Integer) session.getAttribute("userId");
        String userRole = (String) session.getAttribute("userRole");
    	// Fetch the latest products from the database
    	List<Products> products = productsDao.listAllFoodItemsByUser(userRole);
    	// Set the products as a request attribute to be accessible in the JSP
        request.setAttribute("products", products);
        // Forward the request to the "index.jsp" page which will display the products
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

