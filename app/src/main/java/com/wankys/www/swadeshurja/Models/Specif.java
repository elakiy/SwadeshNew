package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

public class Specif {
    @SerializedName("record_id")
    private String record_id;
    @SerializedName("quantity")
    private String quantity;

    public Specif(String product_record_id, String qty) {
        this.record_id = product_record_id;
        this.quantity = qty;
    }

    public String getProduct_record_id() {
        return record_id;
    }

    public void setProduct_record_id(String product_record_id) {
        this.record_id = product_record_id;
    }

    public String getQty() {
        return quantity;
    }

    public void setQty(String qty) {
        this.quantity = qty;
    }
}
