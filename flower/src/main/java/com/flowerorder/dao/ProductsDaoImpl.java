package com.flowerorder.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.flowerorder.model.Products;
import com.flowerorder.util.DBConnection;

public class ProductsDaoImpl implements ProductsDao {

	@Override
	public List<Products> listAllFoodItemsByUser(String userRole){
		List<Products> products = new ArrayList<>();
		String sql = buildQueryBasedOnUserType(userRole);
		System.out.println("Executing SQL: " + sql); //add logging!
	    try (Connection con = DBConnection.getConnection();   		
	            PreparedStatement pst = con.prepareStatement(sql)) {	           
	           ResultSet rs = pst.executeQuery();
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
		            products.add(product);// Adding each product to the list
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	       return products;
	};

	private String buildQueryBasedOnUserType(String userRole) {
		if ("ADMIN".equals(userRole)) {	
	        return "SELECT * FROM products";
	    } else if ("USER".equals(userRole)) {
	        return "SELECT * FROM products WHERE stock > 3 "; //set low-Qty stock products cannot be seen by User 
	    } else {
	        return "SELECT * FROM products"; 
	    }
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
            pstmt.executeUpdate();// Execute the insert operation
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct(Products product) {
        String sql = "INSERT INTO products (name, description, price, category, image_url, stock, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getCategory());
            pstmt.setString(5, product.getImage_url());
            pstmt.setInt(6, product.getStock());
            pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // Setting the current time as created_at
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Products product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, category = ?, image_url = ?, stock = ? WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getCategory());
            pstmt.setString(5, product.getImage_url());
            pstmt.setInt(6, product.getStock());
            pstmt.setInt(7, product.getProduct_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Products getProductById(int productId) {
        Products product = null;
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, productId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                product = new Products();
                product.setProduct_id(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setCategory(rs.getString("category"));
                product.setImage_url(rs.getString("image_url"));
                product.setStock(rs.getInt("stock"));
                product.setCreated_at(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    
    @Override
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}