package com.wankys.www.swadeshurja.Response;

import com.google.gson.annotations.SerializedName;
import com.wankys.www.swadeshurja.Models.Product;

import java.util.List;

public class ProductResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("status_response")
    private String status_response;
    @SerializedName("products_data")
    private List<Product> products_data;

    public ProductResponse(String status, String status_response, List<Product> products_data) {
        this.status = status;
        this.status_response = status_response;
        this.products_data = products_data;
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

    public List<Product> getProducts_data() {
        return products_data;
    }

    public void setProducts_data(List<Product> products_data) {
        this.products_data = products_data;
    }
}
