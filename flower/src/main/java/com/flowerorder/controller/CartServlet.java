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
import com.flowerorder.dao.ProductsDao;
import com.flowerorder.dao.ProductsDaoImpl;
import com.flowerorder.dao.UsersDao;
import com.flowerorder.dao.UsersDaoImpl;
import com.flowerorder.model.Cart;
import com.flowerorder.model.Products;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UsersDao usersDao = new UsersDaoImpl();
    ProductsDao productsDao = new ProductsDaoImpl(); 
    
    public CartServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); //session == null || 
        if (session.getAttribute("name").toString() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Role parameter is missing.");
            return;
        }
		String userName = session.getAttribute("name").toString().trim().toUpperCase();
		List<Products> cartItems  = productsDao.listAllProductItemsforCart(userName);
		request.setAttribute("cartItems",cartItems);
	   	 RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
	   	 dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String addToCart = request.getParameter("addToCart");

	        if (addToCart == null) {
	        	addToCart = "addToCart"; // Default action
	        }

	        switch (addToCart) {
            case "addToCart":
            	try {
					addToCart(request, response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            case "updateCart":
            	try {
					addToCart(request, response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;   
        }       
	}
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
		
        HttpSession session = request.getSession(false); //session == null || 
        if (session.getAttribute("name").toString() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Role parameter is missing.");
            return;
        }

        String userName = session.getAttribute("name").toString().trim().toUpperCase();
				
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int quantity = 1;
		int user_id = 0;
		try {
			user_id = usersDao.getUserIdByUserName(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		Cart cart = new Cart(user_id,product_id ,quantity);
        try {
            productsDao.addToCart(cart);
            request.setAttribute("message", "Item was added to cart successfully!");
        } catch (Exception e) { // Catching general Exception instead of SQLException
            request.setAttribute("errorMessage", "Error adding to cartt: " + e.toString());
            e.printStackTrace();
        }
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        //dispatcher.forward(request, response);
        response.sendRedirect(request.getContextPath() + "/CartServlet");
    }

}
