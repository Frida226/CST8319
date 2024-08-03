package com.flowerorder.dao;

import java.util.List;
import com.flowerorder.model.Products;

public interface ProductsDao {
//    List<Products> getLatestProducts();
    void addProduct(Products product);
    void updateProduct(Products product);
    void deleteProduct(int productId);
    void addToWishlist(int userId, int productId, String productName, double productPrice, String productImage);
	List<Products> listAllFoodItemsByUser( String userRole);
	Products getProductById(int productId);

}

