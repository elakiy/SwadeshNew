package com.wankys.www.swadeshurja.Response;

/**
 * Created by Elakiya on 3/22/2018.
 */

import com.google.gson.annotations.SerializedName;
import com.wankys.www.swadeshurja.Models.Product;

import java.util.List;


/**
 * Created by Elakiya on 3/12/2018.
 */

public class GetSearchProducts {
    @SerializedName("status")
    private String status;
    @SerializedName("status_response")
    private String status_response;
    @SerializedName("search_results")
    private List<Product> search_results;

    public GetSearchProducts(String status, String status_response, List<Product> search_results)
    {
        this.status=status;
        this.status_response=status_response;
        this.search_results=search_results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getDataList() {
        return search_results;
    }

    public void setDataList(List<Product> search_results) {
        this.search_results = search_results;
    }

    public String getStatus_message() {
        return status_response;
    }
    public void setStatus_message(String status_message) {
        this.status_response = status_message;
    }
}

