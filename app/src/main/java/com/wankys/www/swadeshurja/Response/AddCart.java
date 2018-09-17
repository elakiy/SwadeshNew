package com.wankys.www.swadeshurja.Response;

import com.google.gson.annotations.SerializedName;
import com.wankys.www.swadeshurja.Models.Specif;

import java.util.ArrayList;

public class AddCart {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("product_id")
    private String product_id;
    @SerializedName("recarray")
    private ArrayList<Specif> specifs;

    public AddCart(String user_id, String product_id, ArrayList<Specif> specifs) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.specifs = specifs;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public ArrayList<Specif> getSpecifs() {
        return specifs;
    }

    public void setSpecifs(ArrayList<Specif> specifs) {
        this.specifs = specifs;
    }
}
