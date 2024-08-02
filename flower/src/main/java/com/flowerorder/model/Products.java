package com.flowerorder.model;

import java.sql.Timestamp;

public class Products {
    private int product_id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String image_url;
    private int stock;
    private Timestamp created_at;

	public Products() {	}
	
    public Products(String name, String description, double price, String category, String image_url, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image_url = image_url;
        this.stock = stock;
    }

    public Products(int product_id, String name, String description, double price, String category, String image_url, int stock) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image_url = image_url;
        this.stock = stock;
    }

	// Getters and setters
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}


