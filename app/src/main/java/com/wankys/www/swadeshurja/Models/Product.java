package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("sproduct_id")
    private String sproduct_id;
    @SerializedName("product_id")
    private String product_id;
    @SerializedName("category_id")
    private  String category_id;
    @SerializedName("product_name")
    private String product_name;
    @SerializedName("product_img")
    private String product_img;
    @SerializedName("product_mrp")
    private String product_mrp;
    @SerializedName("product_catno")
    private String product_catno;

    public Product(String sproduct_id, String product_id, String category_id, String product_name, String product_img, String product_mrp, String product_catno) {
        this.sproduct_id = sproduct_id;
        this.product_id = product_id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.product_img = product_img;
        this.product_mrp = product_mrp;
        this.product_catno = product_catno;
    }

    public String getSproduct_id() {
        return sproduct_id;
    }

    public void setSproduct_id(String sproduct_id) {
        this.sproduct_id = sproduct_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getProduct_mrp() {
        return product_mrp;
    }

    public void setProduct_mrp(String product_mrp) {
        this.product_mrp = product_mrp;
    }

    public String getProduct_catno() {
        return product_catno;
    }

    public void setProduct_catno(String product_catno) {
        this.product_catno = product_catno;
    }
}
