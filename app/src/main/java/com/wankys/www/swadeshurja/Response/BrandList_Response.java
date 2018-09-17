package com.wankys.www.swadeshurja.Response;

import com.google.gson.annotations.SerializedName;
import com.wankys.www.swadeshurja.Models.BrandList;
import com.wankys.www.swadeshurja.Models.CategoryList;

import java.util.List;

public class BrandList_Response {
    @SerializedName("status")
    private String status;
    @SerializedName("status_response")
    private String status_response;
    @SerializedName("brand_data")
    private List<BrandList> brand_data;

    public BrandList_Response(String status, String status_response, List<BrandList> brand_data) {
        this.status = status;
        this.status_response = status_response;
        this.brand_data = brand_data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_response() {
        return status_response;
    }

    public void setStatus_response(String status_response) {
        this.status_response = status_response;
    }

    public List<BrandList> getBrand_data() {
        return brand_data;
    }

    public void setBrand_data(List<BrandList> brand_data) {
        this.brand_data = brand_data;
    }
}
