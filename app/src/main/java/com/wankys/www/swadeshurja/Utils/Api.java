package com.wankys.www.swadeshurja.Utils;

import retrofit.RestAdapter;

/**
 * Created by Elakiya on 5/9/2018.
 */

public class Api {

    public static ApiInterface getClient() {
        // change your base URL
     RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://Swadeshurja.com/") //Set the Root URL
                .build(); //Finally building the adapter
        //Creating object for our interface
        return adapter.create(ApiInterface.class);
    }
}

