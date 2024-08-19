package com.flowerorder.dao;

import java.sql.SQLException;
import java.util.List;
import com.flowerorder.model.WishlistItem;

public interface WishlistDao {
    void addToWishlist(int userId, int productId) throws SQLException;
    void removeFromWishlist(int userId, int productId) throws SQLException;
    List<WishlistItem> getWishlistItemsByUserId(Integer userId);
	boolean isProductInWishlist(int userId, int productId);
}
