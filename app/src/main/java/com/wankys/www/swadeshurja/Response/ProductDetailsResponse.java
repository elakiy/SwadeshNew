package com.wankys.www.swadeshurja.Response;

import com.google.gson.annotations.SerializedName;
import com.wankys.www.swadeshurja.Models.Product;
import com.wankys.www.swadeshurja.Models.Product_DetailModel;

import java.util.List;

public class ProductDetailsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("status_response")
    private String status_response;
    @SerializedName("products_data")
    private List<Product_DetailModel> products_data;

    public ProductDetailsResponse(String status, String status_response, List<Product_DetailModel> products_data) {
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

    public List<Product_DetailModel> getProducts_data() {
        return products_data;
    }

    public void setProducts_data(List<Product_DetailModel> products_data) {
        this.products_data = products_data;
    }
}
