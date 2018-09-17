package com.wankys.www.swadeshurja.Response;

import com.google.gson.annotations.SerializedName;
import com.wankys.www.swadeshurja.Models.User;

import java.util.List;

/**
 * Created by Elakiya on 5/10/2018.
 */

public class UserDetails {
    @SerializedName("response")
    private List<User> response;


    public UserDetails(List<User> response) {
        this.response = response;
    }

    public List<User> getUserList() {
        return response;
    }

    public void setUserList(List<User> response) {
        this.response = response;
    }
}
