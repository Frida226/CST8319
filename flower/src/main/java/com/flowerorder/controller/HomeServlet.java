package com.flowerorder.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flowerorder.dao.ProductDao;
import com.flowerorder.dao.ProductDaoImpl;
import com.flowerorder.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize ProductDao using dependency injection or factory method
        productDao = new ProductDaoImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDao.getLatestProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

