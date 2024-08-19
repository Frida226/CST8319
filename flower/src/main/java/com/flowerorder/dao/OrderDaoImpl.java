package com.flowerorder.dao;

import com.flowerorder.model.Orders;
import com.flowerorder.util.DBConnection;
import com.flowerorder.model.OrderItems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void createOrder(Orders orders) {
        String insertOrderSQL = "INSERT INTO orders (user_id, total_price, order_status, shipping_address, payment_method, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        String insertOrderItemSQL = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement itemStmt = conn.prepareStatement(insertOrderItemSQL)) {

            conn.setAutoCommit(false); // Open commitment

            // Insert orders
            orderStmt.setInt(1, orders.getUser_id());
            orderStmt.setDouble(2, orders.getTotal_price());
//            orderStmt.setInt(3, orders.getOrder_id());
            orderStmt.setString(3, orders.getOrder_status()); // modify setting as order_status
            orderStmt.setString(4, orders.getShipping_address());
            orderStmt.setString(5, orders.getPayment_method());
            orderStmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            orderStmt.executeUpdate();

            // Retreive generated order ID
            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                orders.setOrder_id(orderId);

                // Insert order items
                for (OrderItems item : orders.getOrderItems()) {
                    itemStmt.setInt(1, orderId);
                    itemStmt.setInt(2, item.getProduct_id());
                    itemStmt.setInt(3, item.getQuantity());
                    itemStmt.setDouble(4, item.getPrice());
                    itemStmt.addBatch();
                }
                itemStmt.executeBatch();
            }

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Orders> getOrdersByUserId(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        List<Orders> orders = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrder_id(rs.getInt("order_id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setTotal_price(rs.getDouble("total_price"));
                order.setOrder_status(rs.getString("order_status"));
                order.setShipping_address(rs.getString("shipping_address"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setCreated_at(rs.getTimestamp("created_at"));

                orders.add(order); // Place order into orders list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("Number of orders retrieved for user " + userId + ": " + orders.size()); //Debugging
        return orders;
    }

    public Orders getOrdersByOrderId(int orderId) {
        Orders order = null;
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order = new Orders();
                order.setOrder_id(rs.getInt("order_id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setTotal_price(rs.getDouble("total_price"));
                order.setOrder_status(rs.getString("order_status"));
                order.setShipping_address(rs.getString("shipping_address"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setCreated_at(rs.getTimestamp("created_at"));
                // Populate order with related items if needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<OrderItems> getOrderItemsByOrderId(int orderId) {
//        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.price, p.name " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id = ?";	// p.name NOT p.product_name!!
        List<OrderItems> orderItems = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItems item = new OrderItems();
                item.setOrder_item_id(rs.getInt("order_item_id"));
                item.setOrder_id(rs.getInt("order_id"));
                item.setProduct_id(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setProduct_name(rs.getString("name"));// set as 'product_name' NOT 'name' coz it's the attribute in orderItems
                orderItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }
    
    @Override
    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderById(int orderId) {
        String deleteOrderItemsSQL = "DELETE FROM order_items WHERE order_id = ?";
        String deleteOrderSQL = "DELETE FROM orders WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement deleteItemsStmt = conn.prepareStatement(deleteOrderItemsSQL);
                PreparedStatement deleteOrderStmt = conn.prepareStatement(deleteOrderSQL)) {
//            conn.setAutoCommit(false);  
        	
            // Delete order items first
            deleteItemsStmt.setInt(1, orderId);
            deleteItemsStmt.executeUpdate();

            // Delete the order
            deleteOrderStmt.setInt(1, orderId);
            deleteOrderStmt.executeUpdate();

//            conn.commit(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateOrder(int orderId, String orderStatus, String shippingAddress, String paymentMethod) {
        String sql = "UPDATE orders SET order_status = ?, shipping_address = ?, payment_method = ? WHERE order_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orderStatus);
            stmt.setString(2, shippingAddress);
            stmt.setString(3, paymentMethod);
            stmt.setInt(4, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

