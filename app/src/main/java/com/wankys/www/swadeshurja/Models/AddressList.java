package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Elakiya on 3/27/2018.
 */

public class AddressList {
    @SerializedName("address_id")
    private String address_id;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("name")
    private String name ;
    @SerializedName("phone")
    private String phone ;
    @SerializedName("pincode")
    private String pincode ;
    @SerializedName("locality")
    private String locality ;
    @SerializedName("address")
    private String address ;
    @SerializedName("city")
    private String city ;
    @SerializedName("state")
    private String state ;
    @SerializedName("landmark")
    private String landmark ;
    @SerializedName("alternata_number")
    private String alternate_number ;
    @SerializedName("email")
    private String email;

    public AddressList(String address_id, String user_id, String name, String phone, String pincode, String locality, String address, String city, String state, String landmark, String alternate_number, String email) {
        this.address_id = address_id;
        this.user_id = user_id;
        this.name = name;
        this.phone = phone;
        this.pincode = pincode;
        this.locality = locality;
        this.address = address;
        this.city = city;
        this.state = state;
        this.landmark = landmark;
        this.alternate_number = alternate_number;
        this.email = email;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAlternate_number() {
        return alternate_number;
    }

    public void setAlternate_number(String alternate_number) {
        this.alternate_number = alternate_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
