package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product_DetailModel{
    @SerializedName("sproduct_id")
    private String sproduct_id;
    @SerializedName("product_id")
    private String product_id;
    @SerializedName("category_id")
    private  String category_id;
    @SerializedName("product_name")
    private String product_name;
    @SerializedName("product_desc")
    private String product_desc;
    @SerializedName("image_array")
    private ArrayList<String> image_array;
    @SerializedName("specification_url")
    private String specification_url;
    @SerializedName("product_mrp")
    private String product_mrp;
    @SerializedName("product_catno")
    private String product_catno;

    public Product_DetailModel(String sproduct_id, String product_id, String category_id, String product_name, String product_desc, ArrayList<String> image_array, String specification_url, String product_mrp, String product_catno) {
        this.sproduct_id = sproduct_id;
        this.product_id = product_id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.image_array = image_array;
        this.specification_url = specification_url;
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

    public ArrayList<String>getProduct_img() {
        return image_array;
    }

    public void setProduct_img(ArrayList<String> product_img) {
        this.image_array = product_img;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
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

    public String getSpecification_url() {
        return specification_url;
    }

    public void setSpecification_url(String specification_url) {
        this.specification_url = specification_url;
    }
}
