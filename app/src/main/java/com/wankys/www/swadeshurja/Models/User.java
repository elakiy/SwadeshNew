package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Elakiya on 5/10/2018.
 */

public class User {
    @SerializedName("swa_user_unique_id")
    private String swa_user_unique_id;
    @SerializedName("swa_user_name")
    private String swa_user_name;
    @SerializedName("swa_email")
    private String swa_email;
    @SerializedName("swa_mobile")
    private String swa_mobile;
    @SerializedName("swa_password")
    private String swa_password;
    @SerializedName("status")
    private String status;
    @SerializedName("status_response")
    private  String status_response;


    public User(String swa_user_unique_id, String swa_user_name, String swa_email, String swa_mobile, String swa_password, String status, String status_response) {
        this.swa_user_unique_id = swa_user_unique_id;
        this.swa_user_name = swa_user_name;
        this.swa_email = swa_email;
        this.swa_mobile = swa_mobile;
        this.swa_password = swa_password;
        this.status = status;
        this.status_response = status_response;
    }

    public String getSwa_user_unique_id() {
        return swa_user_unique_id;
    }

    public void setSwa_user_unique_id(String swa_user_unique_id) {
        this.swa_user_unique_id = swa_user_unique_id;
    }

    public String getSwa_user_name() {
        return swa_user_name;
    }

    public void setSwa_user_name(String swa_user_name) {
        this.swa_user_name = swa_user_name;
    }

    public String getSwa_email() {
        return swa_email;
    }

    public void setSwa_email(String swa_email) {
        this.swa_email = swa_email;
    }

    public String getSwa_mobile() {
        return swa_mobile;
    }

    public void setSwa_mobile(String swa_mobile) {
        this.swa_mobile = swa_mobile;
    }

    public String getSwa_password() {
        return swa_password;
    }

    public void setSwa_password(String swa_password) {
        this.swa_password = swa_password;
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
