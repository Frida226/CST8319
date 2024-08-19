package com.flowerorder.dao;

import java.util.List;
import com.flowerorder.model.Products;

import com.flowerorder.model.Products;

public interface CartDao {
	  void addToCart(int user_id, int product_Id, int quantity);
	  List<Products> getAllCartItems( String username);
	  
}
