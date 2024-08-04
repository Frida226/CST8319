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
        HttpSession session = request.getSession(false); // use false to avoid create new session
        if (session == null || session.getAttribute("role") == null) {
        	System.out.println("Redirecting to login page: No session or role found.");
            response.sendRedirect("login.jsp"); // if not meet demand, redirect to login page
        }

//        System.out.println("Current Role deliver from previous seession is:" + session.getAttribute("role"));
        String userRole = (String) session.getAttribute("role");
//        String userRole = ((Role) session.getAttribute("role")).name();

        System.out.println("User Role from Session: " + userRole);// verify the Role get from the previous session!
    	List<Products> products = productsDao.listAllProductItemsByUser(userRole);
    	// Set the products as a request attribute to be accessible in the JSP
        request.setAttribute("products", products);
        // Forward the request to the "index.jsp" page which will display the products
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

