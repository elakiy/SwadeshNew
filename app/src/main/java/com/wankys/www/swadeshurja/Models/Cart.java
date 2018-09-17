package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("sproduct_id")
    private String sproduct_id;
    @SerializedName("name")
    private String name;
    @SerializedName("cart_id")
    private String cart_id;
    @SerializedName("record_id")
    private String record_id;
    @SerializedName("product_catno")
    private String product_catno;
    @SerializedName("mrp")
    private String mrp;
    @SerializedName("discount")
    private String discount;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("image")
    private String image;

    public Cart(String user_id, String sproduct_id, String name, String cart_id, String record_id, String product_catno, String mrp, String discount, String quantity, String image) {
        this.user_id = user_id;
        this.sproduct_id = sproduct_id;
        this.name = name;
        this.cart_id = cart_id;
        this.record_id = record_id;
        this.product_catno = product_catno;
        this.mrp = mrp;
        this.discount = discount;
        this.quantity = quantity;
        this.image = image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSproduct_id() {
        return sproduct_id;
    }

    public void setSproduct_id(String sproduct_id) {
        this.sproduct_id = sproduct_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getProduct_catno() {
        return product_catno;
    }

    public void setProduct_catno(String product_catno) {
        this.product_catno = product_catno;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
