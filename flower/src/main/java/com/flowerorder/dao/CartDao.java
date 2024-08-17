
package com.flowerorder.dao;

import java.sql.SQLException;
import java.util.List;

import com.flowerorder.model.CartItem;
import com.flowerorder.model.Products;


public interface CartDao {
	  void addToCart(int user_id, int product_Id, int quantity);
//	  List<Products> getAllCartItems( String username);
	  List<CartItem> getCartByUserId(Integer userId);
	  void removeFromCart(int userId, int productId) throws SQLException;
	boolean checkIfItemExists(int user_id, int product_id);
	void addItemQty(int user_id, int product_id, int quantity);
	List<CartItem> getCartItemsByUserId(Integer userId);
	void updateCartItem(Integer userId, int productId, int newQuantity) throws SQLException;
}
