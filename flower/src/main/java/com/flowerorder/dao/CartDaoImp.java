
package com.flowerorder.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.flowerorder.model.CartItem;
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
	
	
//	@Override		//useless?
//	public List<Products> getAllCartItems(String username){
//		List<Products> products = new ArrayList<>();
//		String sql = "SELECT PRODUCTs.*,Sum(Cart.Quantity) as stock FROM products INNER JOIN CART ON Cart.product_id = products.product_id inner join users on users.user_id = cart.user_id where users.username like ? " + "  GROUP BY product_id,name,description,price,category,image_url";
//		System.out.println("Executing SQL: " + sql); //add logging!
//	    try (Connection con = DBConnection.getConnection();  
//	            PreparedStatement pst = con.prepareStatement(sql)) {
//	    		pst.setString(1, username);
//	           ResultSet rs = pst.executeQuery();
//	           while (rs.next()) {
//		            Products product = new Products();
//		            product.setProduct_id(rs.getInt("product_id"));
//		            product.setName(rs.getString("name"));
//		            product.setDescription(rs.getString("description"));
//		            product.setPrice(rs.getDouble("price"));
//		            product.setCategory(rs.getString("category"));
//		            product.setImage_url(rs.getString("image_url"));
//		            product.setStock(rs.getInt("stock"));
//		            products.add(product);// Adding each product to the list
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			return products;
//		}


	@Override	// useless??
	public List<CartItem> getCartByUserId(Integer userId) {
		List<CartItem> cartItems = new ArrayList<>();
		String query = "SELECT c.product_id, c.quantity, p.price " +
	               "FROM cart c " +
	               "JOIN products p ON c.product_id = p.product_id " +
	               "WHERE c.user_id = ?";
		
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CartItem item = new CartItem();
				item.setProduct_id(rs.getInt("product_id"));
				item.setQuantity(rs.getInt("quantity"));
				item.setPrice(rs.getDouble("price"));
				//debugging
//				System.out.println("Product ID: " + item.getProduct_id() + ", Price: " + item.getPrice());

				cartItems.add(item);
			}
		}catch (SQLException e) {
	        e.printStackTrace();
	    }
		return cartItems;
    }
	

	public List<CartItem> getCartItemsByUserId(Integer userId) {
	    String sql = "SELECT c.product_id, c.quantity, p.price AS price, p.name AS product_name, p.image_url AS product_image " +
	                 "FROM cart c " + //lack of a space!!
	                 "INNER JOIN products p ON c.product_id = p.product_id " +
	                 "WHERE c.user_id = ?";
	    List<CartItem> cartItems = new ArrayList<>();
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, userId);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            CartItem item = new CartItem();
	            item.setProduct_id(rs.getInt("product_id"));
	            item.setQuantity(rs.getInt("quantity"));
	            item.setPrice(rs.getDouble("price"));
	            item.setProduct_name(rs.getString("product_name")); 
	            item.setProduct_image(rs.getString("product_image"));
	            cartItems.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cartItems;
	}
	
	
    @Override
    public void removeFromCart(int userId, int productId) throws SQLException{
        String sql = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
            
//            int affectedRows = stmt.executeUpdate(); // get rows affected          
//            if (affectedRows > 0) {
//                System.out.println("Successfully removed product with id " + productId + " from cart for user " + userId);
//            } else {
//                System.out.println("No product with id " + productId + " found in cart for user " + userId);
//            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


	@Override
	public boolean checkIfItemExists(int user_id, int product_id) {
	    String query = "SELECT COUNT(*) FROM cart WHERE user_id = ? AND product_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setInt(1, user_id);
	        pstmt.setInt(2, product_id);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	@Override
	public void addItemQty(int user_id, int product_id, int quantity) {
	    String sql = "UPDATE cart SET quantity = quantity + ? WHERE user_id = ? AND product_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, quantity);
	        pstmt.setInt(2, user_id);
	        pstmt.setInt(3, product_id);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public void updateCartItem(Integer userId, int productId, int newQuantity) throws SQLException {
	    String sql = "UPDATE cart SET quantity = ? WHERE user_id = ? AND product_id = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, newQuantity);
	        pstmt.setInt(2, userId);
	        pstmt.setInt(3, productId);

	        pstmt.executeUpdate();
	    }
	}


}
