package com.flowerorder.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flowerorder.dao.ProductsDao;
import com.flowerorder.dao.ProductsDaoImpl;
import com.flowerorder.model.Products;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


	@WebServlet("/manageInventory")
	public class ProductServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    ProductsDao productsDao = new ProductsDaoImpl(); // Initialize the DAO
	    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if ("view".equals(action)) {
				listProduct(request, response);
			} else if ("edit".equals(action)) {
                showEditForm(request, response);
            } else { 
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
    
    private void listProduct(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {

        HttpSession session = request.getSession(false); //session == null || 
        if (session.getAttribute("role").toString() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Role parameter is missing.");
            return;
        }

        String userRole = session.getAttribute("role").toString().trim().toUpperCase();
        //Calls the listFoodItems method of foodItemService to retrieve a list of all food items
    	List<Products> listProducts = productsDao.listAllProductItemsByUser(userRole); 	    	
    	//Attaches the list of food items to the request object. 
        request.setAttribute("products",listProducts);
        //Uses the request dispatcher to forward the request and response objects to the JSP page     
         if(userRole.equals("ADMIN")) {  	
               RequestDispatcher dispatcher = request.getRequestDispatcher("/manageInventory.jsp");
               dispatcher.forward(request, response);
         }else if(userRole.equals("USER")){
        	 RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        	 dispatcher.forward(request, response);
         }
        
    }

    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        Products existingProduct = productsDao.getProductById(productId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editProduct.jsp");
        request.setAttribute("product", existingProduct);
        dispatcher.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        String imageUrl = request.getParameter("image_url");
        int stock = Integer.parseInt(request.getParameter("stock"));
        Products product = new Products(name, description, price, category, imageUrl, stock);
        try {
            productsDao.addProduct(product);
            request.setAttribute("message", "Product added successfully!");
        } catch (Exception e) { // Catching general Exception instead of SQLException
            request.setAttribute("errorMessage", "Error adding product: " + e.toString());
            e.printStackTrace();
        }
//        response.sendRedirect("manageInventory.jsp"); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/manageInventory.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Similar to addProduct, but with existing product_id
        int productId = Integer.parseInt(request.getParameter("product_id"));
        Products product = new Products(productId, request.getParameter("name"), request.getParameter("description"), 
            Double.parseDouble(request.getParameter("price")), request.getParameter("category"), request.getParameter("image_url"), 
            Integer.parseInt(request.getParameter("stock")));
        try {
            productsDao.updateProduct(product);
            request.setAttribute("message", "Product updated successfully!");
        } catch (Exception e) { // Catching general Exception instead of SQLException
            request.setAttribute("errorMessage", "Error updating product: " + e.toString());
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/manageInventory.jsp");
        dispatcher.forward(request, response);
//        response.sendRedirect("manageInventory?action=view");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        try {
            productsDao.deleteProduct(productId);
            request.setAttribute("message", "Product deleted successfully!");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error deleting product: " + e.toString());
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/manageInventory.jsp");
        dispatcher.forward(request, response);
//        response.sendRedirect("manageInventory.jsp");
    }
}