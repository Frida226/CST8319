package com.flowerorder.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.flowerorder.model.Products;
import com.flowerorder.util.DBConnection;


public class CartDaoImp implements CartDao{
	
	@Override
	public void addToCart(int user_id, int product_Id, int quantity) {
        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,user_id);
            pstmt.setInt(2, product_Id);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	@Override
	public List<Products> getAllCartItems(String username){
		List<Products> products = new ArrayList<>();
		String sql = "SELECT PRODUCTs.*,Sum(Cart.Quantity) as stock FROM products INNER JOIN CART ON Cart.product_id = products.product_id inner join users on users.user_id = cart.user_id where users.username like ? " + "  GROUP BY product_id,name,description,price,category,image_url";
		System.out.println("Executing SQL: " + sql); //add logging!
	    try (Connection con = DBConnection.getConnection();  
	            PreparedStatement pst = con.prepareStatement(sql)) {
	    		pst.setString(1, username);
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
		            products.add(product);// Adding each product to the list
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	       return products;
	}
}
