package com.wankys.www.swadeshurja.Response;

import com.google.gson.annotations.SerializedName;
import com.wankys.www.swadeshurja.Models.CategoryList;

import java.util.List;

/**
 * Created by Elakiya on 5/14/2018.
 */

public class CategoryList_Response {
    @SerializedName("status")
    private String status;
    @SerializedName("status_response")
    private String status_response;
    @SerializedName("category_data")
    private List<CategoryList> category_data;

    public CategoryList_Response(String status, String status_response, List<CategoryList> category_data) {
        this.status = status;
        this.status_response = status_response;
        this.category_data = category_data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String cat_desc) {
        this.status = cat_desc;
    }

    public String getStatus_response() {
        return status_response;
    }

    public void setStatus_response(String status_response) {
        this.status_response = status_response;
    }

    public List<CategoryList> getCategory_data() {
        return category_data;
    }

    public void setCategory_data(List<CategoryList> category_data) {
        this.category_data = category_data;
    }
}
