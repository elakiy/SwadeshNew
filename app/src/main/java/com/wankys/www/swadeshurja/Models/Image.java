package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

public class Image {
    private String product_img;

    public Image(String product_img) {
        this.product_img = product_img;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }
}
