package com.flowerorder.dao;

import java.util.List;
import com.flowerorder.model.Product;

public interface ProductDao {
    List<Product> getLatestProducts();
    void addToWishlist(int userId, int productId, String productName, double productPrice, String productImage);

}

