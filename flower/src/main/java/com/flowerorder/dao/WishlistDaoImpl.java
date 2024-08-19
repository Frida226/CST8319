package com.flowerorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flowerorder.model.WishlistItem;
import com.flowerorder.util.DBConnection;

public class WishlistDaoImpl implements WishlistDao {
	

    @Override
    public void addToWishlist(int userId, int productId) throws SQLException {
        String sql = "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void removeFromWishlist(int userId, int productId) throws SQLException {
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<WishlistItem> getWishlistItemsByUserId(Integer userId) {
        String sql = "SELECT w.product_id, p.stock, p.price, p.name AS product_name, p.image_url AS product_image " +
                     "FROM wishlist w INNER JOIN products p ON w.product_id = p.product_id " +
                     "WHERE w.user_id = ?";
        List<WishlistItem> wishlistItems = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                WishlistItem item = new WishlistItem();
                item.setProduct_id(rs.getInt("product_id"));
                item.setStock(rs.getInt("stock"));
                item.setPrice(rs.getDouble("price"));
                item.setProduct_name(rs.getString("product_name"));
                item.setProduct_image(rs.getString("product_image"));
                wishlistItems.add(item);
            }
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        return wishlistItems;
    }
    
    @Override
    public boolean isProductInWishlist(int userId, int productId) {
        String sql = "SELECT COUNT(*) FROM wishlist WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // if reslut > 0 that show prodcut has listed in the wishlist
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
