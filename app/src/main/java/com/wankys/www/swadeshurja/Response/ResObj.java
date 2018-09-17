package com.wankys.www.swadeshurja.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Elakiya on 5/9/2018.
 */
public class ResObj  {
    @SerializedName("status")
    private String status;
    @SerializedName("status_message")
    private String status_message;
    @SerializedName("status_response")
    private  String status_response;

    public ResObj(String status, String status_message, String status_response) {
        this.status = status;
        this.status_message = status_message;
        this.status_response = status_response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String query_result) {
        this.status = query_result;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getStatus_response() {
        return status_response;
    }

    public void setStatus_response(String status_response) {
        this.status_response = status_response;
    }

}
