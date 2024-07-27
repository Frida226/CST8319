package com.flowerorder.model;

import java.sql.Date;

public class FeaturedProducts {
    private int featured_product_id;
    private int product_id;
    private Date start_date;
    private Date end_date;

    // Getters and setters
    public int getFeatured_product_id() {
        return featured_product_id;
    }

    public void setFeatured_product_id(int featured_product_id) {
        this.featured_product_id = featured_product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}

