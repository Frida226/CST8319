package com.flowerorder.dao;

import java.util.List;
import com.flowerorder.model.Products;

public interface ProductDao {
    List<Products> getLatestProducts();
    void addToWishlist(int userId, int productId, String productName, double productPrice, String productImage);

}

