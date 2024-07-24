package com.flowerorder.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.flowerorder.model.Products;
import com.flowerorder.util.DBConnection;

public class ProductDaoImpl implements ProductDao {
	@Override
	public List<Products> getLatestProducts() {
	    List<Products> products = new ArrayList<>();
	    try (Connection conn = DBConnection.getConnection()) {
	        String sql = "SELECT * FROM products";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	            Products product = new Products();
	            product.setProduct_id(rs.getInt("product_id"));
	            product.setName(rs.getString("name"));
	            product.setDescription(rs.getString("description"));
	            product.setPrice(rs.getDouble("price"));
	            product.setCategory(rs.getString("category"));
	            product.setImage_url(rs.getString("image_url"));
	            product.setStock(rs.getInt("stock"));
	            product.setCreated_at(rs.getTimestamp("created_at"));
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
            String sql = "INSERT INTO wishlist (user_id, product_id, name, price, image) VALUES (?, ?, ?, ?, ?)";
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