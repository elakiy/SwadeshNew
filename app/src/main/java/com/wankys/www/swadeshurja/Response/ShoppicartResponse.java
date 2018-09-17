package com.wankys.www.swadeshurja.Response;

import com.google.gson.annotations.SerializedName;
import com.wankys.www.swadeshurja.Models.Cart;

import java.util.List;

public class ShoppicartResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("status_repsonse")
    private String status_repsonse;
    @SerializedName("cart_data")
    private List<Cart> shoppingcarts;

    public ShoppicartResponse(String status, String status_repsonse, List<Cart> shoppingcarts) {
        this.status = status;
        this.status_repsonse = status_repsonse;
        this.shoppingcarts = shoppingcarts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_repsonse() {
        return status_repsonse;
    }

    public void setStatus_repsonse(String status_repsonse) {
        this.status_repsonse = status_repsonse;
    }

    public List<Cart> getShoppingcarts() {
        return shoppingcarts;
    }

    public void setShoppingcarts(List<Cart> shoppingcarts) {
        this.shoppingcarts = shoppingcarts;
    }
}
