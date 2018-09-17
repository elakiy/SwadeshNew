package com.wankys.www.swadeshurja.Response;

import com.google.gson.annotations.SerializedName;
import com.wankys.www.swadeshurja.Models.AddressList;

import java.util.List;

public class AddressResponse {
    @SerializedName("address_data")
    private List<AddressList> address_data;
    @SerializedName("status")
    private String status;
    @SerializedName("status_response")
    private String status_response;

    public AddressResponse(List<AddressList> address_data, String status, String status_response) {
        this.address_data = address_data;
        this.status = status;
        this.status_response = status_response;
    }

    public List<AddressList> getAddress_data() {
        return address_data;
    }

    public void setAddress_data(List<AddressList> address_data) {
        this.address_data = address_data;
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
}
