package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Elakiya on 5/10/2018.
 */

public class Login {
    @SerializedName("loginemail")
    private String loginemail;
    @SerializedName("loginpassword")
    private String loginpassword;

    public Login(String loginemail, String loginpassword) {
        this.loginemail = loginemail;
        this.loginpassword = loginpassword;
    }

    public String getLoginpassword() {
        return loginpassword;
    }

    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword;
    }

    public String getLoginemail() {
        return loginemail;
    }

    public void setLoginemail(String loginemail) {
        this.loginemail = loginemail;
    }
}
