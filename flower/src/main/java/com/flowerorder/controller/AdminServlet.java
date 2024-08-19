package com.flowerorder.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.flowerorder.dao.*;
import com.flowerorder.model.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDao orderDao = new OrderDaoImpl();
    private UsersDao usersDao = new UsersDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("processOrders".equals(action)) {
            viewProcessOrders(request, response);
        } else {
            // 处理其他的操作
        }
    }

    private void viewProcessOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> users = usersDao.getAllUsers(); // 获取所有用户
        Map<Integer, List<Orders>> userOrdersMap = new HashMap<>();

        for (Users user : users) {
            List<Orders> orders = orderDao.getOrdersByUserId(user.getId());
            userOrdersMap.put(user.getId(), orders);
        }

        request.setAttribute("userOrdersMap", userOrdersMap);
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("processOrders.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("updateOrder".equals(action)) {
            int orderId = Integer.parseInt(request.getParameter("order_id"));
            String orderStatus = request.getParameter("order_status_" + orderId);
            String shippingAddress = request.getParameter("shipping_address_" + orderId);
            String paymentMethod = request.getParameter("payment_method_" + orderId);

            orderDao.updateOrder(orderId, orderStatus, shippingAddress, paymentMethod);
            response.sendRedirect("admin?action=processOrders"); // After update,redirect to processOrders.jsp

        } else if ("deleteOrder".equals(action)) {
            int orderId = Integer.parseInt(request.getParameter("order_id"));
            orderDao.deleteOrderById(orderId);
            response.sendRedirect("admin?action=processOrders"); // After delete,redirect to processOrders.jsp
        }
    }
    
}

