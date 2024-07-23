package com.flowerorder.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.flowerorder.model.Product;
import com.flowerorder.util.DBConnection;

public class ProductDaoImpl implements ProductDao {
	@Override
    public List<Product> getLatestProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM products LIMIT 6";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
	
	@Override
    public void addToWishlist(int userId, int productId, String productName, double productPrice, String productImage) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO wishlist (user_id, pid, name, price, image) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.setString(3, productName);
            pstmt.setDouble(4, productPrice);
            pstmt.setString(5, productImage);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Other DAO methods...
}