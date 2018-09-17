package com.wankys.www.swadeshurja.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Elakiya on 5/8/2018.
 */

public class ApiClient {
    private static String BASE_URL="http://Swadeshurja.com/mobileapis/";

    private static Retrofit retrofit;
    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
