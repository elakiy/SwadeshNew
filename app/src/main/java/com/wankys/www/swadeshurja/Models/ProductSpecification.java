package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

public class ProductSpecification {
    @SerializedName("product_price")
    private String product_price;
    @SerializedName("product_mrp")
    private String product_mrp;
    @SerializedName("product_catno")
    private String product_catno;
    @SerializedName("product_record_id")
    private String product_record_id;
    @SerializedName("product_image")
    private String product_image;
    @SerializedName("produt_name")
    private String produt_name;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("total")
    private String total;
    public ProductSpecification(String product_price, String product_mrp, String product_catno, String product_record_id, String product_image, String produt_name, String quantity, String total) {
        this.product_price = product_price;
        this.product_mrp = product_mrp;
        this.product_catno = product_catno;
        this.product_record_id = product_record_id;
        this.product_image = product_image;
        this.produt_name = produt_name;
        this.quantity = quantity;
        this.total=total;

    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
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

    public String getProduct_record_id() {
        return product_record_id;
    }

    public void setProduct_record_id(String product_record_id) {
        this.product_record_id = product_record_id;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProdut_name() {
        return produt_name;
    }

    public void setProdut_name(String produt_name) {
        this.produt_name = produt_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
