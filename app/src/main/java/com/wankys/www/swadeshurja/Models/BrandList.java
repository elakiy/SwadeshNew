package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

public class BrandList {
    @SerializedName("brand_id")
    private String brand_id;
    @SerializedName("sbrand_id")
    private String sbrand_id;
    @SerializedName("brand_name")
    private String brand_name;
    @SerializedName("brandlogo")
    private String brandlogo;

    public BrandList(String brand_id, String sbrand_id, String brand_name ,String brandlogo) {
        this.brand_id = brand_id;
        this.sbrand_id = sbrand_id;
        this.brand_name = brand_name;
        this.brandlogo= brandlogo;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getSbrand_id() {
        return sbrand_id;
    }

    public void setSbrand_id(String sbrand_id) {
        this.sbrand_id = sbrand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrandlogo() {
        return brandlogo;
    }

    public void setBrandlogo(String brandlogo) {
        this.brandlogo = brandlogo;
    }
}
