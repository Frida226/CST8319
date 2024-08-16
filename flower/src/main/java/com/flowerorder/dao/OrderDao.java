package com.flowerorder.dao;

import com.flowerorder.model.OrderItems;
import com.flowerorder.model.Orders;

import java.util.List;

public interface OrderDao {
    void createOrder(Orders orders);
    List<Orders> getOrdersByUserId(int userId);
    void updateOrderStatus(int orderId, String status);
    void deleteOrderById(int orderId);
	Orders getOrdersByOrderId(int orderId);
	List<OrderItems> getOrderItemsByOrderId(int orderId);
}

