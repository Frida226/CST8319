package com.flowerorder.model;

import java.sql.Date;

public class SpecialOffers {
    private int special_offer_id;
    private int product_id;
    private double discount_percentage;
    private Date start_date;
    private Date end_date;

    // Getters and setters
    public int getSpecial_offer_id() {
        return special_offer_id;
    }

    public void setSpecial_offer_id(int special_offer_id) {
        this.special_offer_id = special_offer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
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

