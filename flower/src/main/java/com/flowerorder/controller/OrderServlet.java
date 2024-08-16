package com.flowerorder.controller;

import com.flowerorder.dao.*;
import com.flowerorder.dao.OrderDaoImpl;
import com.flowerorder.model.Orders;
import com.flowerorder.model.CartItem;
import com.flowerorder.model.OrderItems;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDao orderDao = new OrderDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
		if (action != null) {
			switch (action) {
			case "history":
				viewOrderHistory(request, response);
				break;
			case "details":
				viewOrderDetail(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
		}
	}
    
    
    private void viewOrderHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);  //control to whther to create a new session or not
        int userId = (int) session.getAttribute("user_id");
        
//        System.out.println("User ID from session in doGet-viewOrderHistory: " + userId);//Debugging
        
        List<Orders> orders = orderDao.getOrdersByUserId(userId);
        
        // Automatically delete orders with Status and TotalPrice as 0
        for (Orders order : orders) {
            if ("0".equals(order.getOrder_status()) || order.getTotal_price() == 0.0) {
                orderDao.deleteOrderById(order.getOrder_id());
            }
        }
        //Debugging
//        System.out.println("Number of orders retrieved: " + orders.size());
//        for (Orders order : orders) {
//            System.out.println("Order ID: " + order.getOrder_id());
//        }
        
     // Reload the order list after deletion
        orders = orderDao.getOrdersByUserId(userId);
        request.setAttribute("orders", orders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("orderHistory.jsp");
        dispatcher.forward(request, response);
    }
 
    
    private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("order_id"));//Another transmit way compare above one
        Orders order = orderDao.getOrdersByOrderId(orderId); // Method similar with getOrdersByUserId(userId)
//        request.setAttribute("order", order);
        
        List<OrderItems> orderItems = orderDao.getOrderItemsByOrderId(orderId); // Retreive all orderItemss in this order
        if (order != null && orderItems != null) {
            order.setOrderItems(orderItems);
//            request.setAttribute("order", order);
            // deliver data to JSP page
            request.setAttribute("order_id", order.getOrder_id());
            request.setAttribute("totalPrice", order.getTotal_price());
            request.setAttribute("shippingAddress", order.getShipping_address());
            request.setAttribute("paymentMethod", order.getPayment_method());
            request.setAttribute("orderItems", orderItems);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderDetail.jsp");
        dispatcher.forward(request, response);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createOrder(request, response);
        } else if ("updateStatus".equals(action)) {
            updateOrderStatus(request, response);
        } else if ("delete".equals(action)) {
            deleteOrder(request, response);
        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id"); //Integer is a wrapper class for the primitive data type int
        System.out.println("User ID from session in doPost-createOrder: " + userId);
        
        if (userId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not logged in or session expired.");
            return;
        }
        
        Orders order = new Orders();
        order.setUser_id(userId);

//        order.setOrder_status("PENDING"); // default status when creating order
        order.setShipping_address(request.getParameter("shippingAddress"));
        order.setPayment_method(request.getParameter("paymentMethod"));

        List<OrderItems> orderItems = new ArrayList<>();
        
        CartDao cartDao = new CartDaoImp();
        ProductsDao productsDao = new ProductsDaoImpl();//create a instance of ProductsDao
        // retreive user's cart
        List<CartItem> cartItems = cartDao.getCartByUserId(userId);
        double totalPrice = 0.0;
      
        // Transfer each product in cart into orderItem  
        for (CartItem cartItem : cartItems) {
            OrderItems orderItem = new OrderItems();
            orderItem.setProduct_id(cartItem.getProduct_id());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            
			// Fetch and set the product name
            String productName = productsDao.getProductNameById(cartItem.getProduct_id());
            orderItem.setProduct_name(productName); // Set the product name in the order item

            orderItems.add(orderItem);
            //debugging
//            System.out.println("Product ID: " + cartItem.getProduct_id() + ", "+ "Quantity: " + cartItem.getQuantity() + ", Price: " + cartItem.getPrice());
           
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
//        System.out.println("Total Price Calculated: " + totalPrice);//debugging
        //two methods to retains 2 decimal places of totalPrice
//      DecimalFormat df = new DecimalFormat("#.00");
//      String formattedTotalPrice = df.format(totalPrice);
        String formattedTotalPrice = String.format("%.2f", totalPrice);   
        
     // Debug print the order items list
//        System.out.println("OrderItems size: " + orderItems.size());
//        for (OrderItems item : orderItems) {
//            System.out.println("Product Name: " + item.getProduct_name());
//        }
        order.setOrderItems(orderItems);
        order.setTotal_price(totalPrice);  // Add this line to set the total price
        order.setOrder_status("PENDING"); // default status when creating order
        
        System.out.println("Order Status before saving: " + order.getOrder_status());//Debugging

        try {
            orderDao.createOrder(order);
            // set request attributes
            request.setAttribute("order_id", order.getOrder_id());
            request.setAttribute("totalPrice", formattedTotalPrice); // use directly the totalPrice of calculated result
            request.setAttribute("shippingAddress", order.getShipping_address());
            request.setAttribute("paymentMethod", order.getPayment_method());
//            request.setAttribute("orderItems", order.getOrderItems());
            request.setAttribute("orderItems", orderItems);


            // forward request to orderDetail.jsp, not redirection
            RequestDispatcher dispatcher = request.getRequestDispatcher("orderDetail.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create order.");
        }
    }

    


    
    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("order_id"));
        String status = request.getParameter("status");

        orderDao.updateOrderStatus(orderId, status);

        response.sendRedirect("order?action=view");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("order_id"));

        try {
            orderDao.deleteOrderById(orderId);
            response.sendRedirect("order?action=history");  // Redirect to the order history page after deletion
        } catch (Exception e) {// Catch a more general exception if SQLException is not applicable
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete order.");
        }
    }
}
