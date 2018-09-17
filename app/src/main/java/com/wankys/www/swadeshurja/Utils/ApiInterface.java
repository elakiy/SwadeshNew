package com.wankys.www.swadeshurja.Utils;


import com.wankys.www.swadeshurja.Response.AddressResponse;
import com.wankys.www.swadeshurja.Response.BrandList_Response;
import com.wankys.www.swadeshurja.Response.CategoryList_Response;
import com.wankys.www.swadeshurja.Response.GetSearchProducts;
import com.wankys.www.swadeshurja.Response.ProductDetailsResponse;
import com.wankys.www.swadeshurja.Response.ProductResponse;
import com.wankys.www.swadeshurja.Response.ProductSpecificationResponse;
import com.wankys.www.swadeshurja.Response.ResObj;
import com.wankys.www.swadeshurja.Response.ShoppicartResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Elakiya on 5/8/2018.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/mobileapis/Registration/otpgeneration")
    void otpgeneration(@Field("Employee_id") String Employee_id,
                       @Field("pan_number") String pan_number,
                       @Field("name") String name,
                       @Field("email") String email,
                       @Field("mobile") String mobile,
                       @Field("password") String password, Callback<Response> callback);
    @FormUrlEncoded
    @POST("/mobileapis/Registration")
    void registration(@Field("Employee_id")String Employee_id,
                      @Field("pan_number")String pan_number,
                      @Field("name")String name,
                      @Field("email")String email,
                      @Field("mobile")String mobile,
                      @Field("password")String password,
                      @Field("otp")String otp, Callback<Response> callback);
    @FormUrlEncoded
    @POST("/mobileapis/Login")
    void login(@Field("loginemail") String loginemail ,
               @Field("loginpassword") String loginpassword,Callback<Response> callback);

    @FormUrlEncoded
    @POST("/mobileapis/Login/Forgotpassword")
    void forgotpassword(@Field("email") String loginemail,Callback<Response> callback);

    @GET("Categorylist/brandlist")
    Call<BrandList_Response>getBrandList();

    @GET("Categorylist/brandcatlist")
    Call<CategoryList_Response>getCategoryList(@Query("brand_id") String brand_id);

    @retrofit2.http.GET("Productdetails/productsList")
    Call<ProductDetailsResponse>getProductDetails(@Query("product_id") String productId);

    @GET("Categorylist/recentproductsList")
    Call<ProductResponse>getRecentproductsList();

    @GET("Categorylist/productsList")
    Call<ProductResponse>getProductList(@Query("category_id") String category_id);

    @GET("Categorylist/productspecifactionList")
    Call<ProductSpecificationResponse>getProductSpecification(@Query("product_id") String product_id);

    @FormUrlEncoded
    @retrofit.http.POST("/mobileapis/Login/Changepassword")
    void changepassword(@Field("password") String password,
                        @Field("swa_user_unique_id") String swa_user_unique_id,
                        Callback<Response> callback);

    @retrofit2.http.GET("Productsearch/ProductSearch?products_search")
    Call<GetSearchProducts>getSearchProducts(@Query("products_search") String string);

    @FormUrlEncoded
    @retrofit.http.POST("/mobileapis/Addtocart/addtocartList")
    void add(@Field("jarray") String body, Callback<Response> callback);

    @FormUrlEncoded
    @retrofit.http.POST("/mobileapis/Addtocart/updateCartQuantity")
    void updateqty(@Field("cart_id") String cart_id,
                        @Field("quantity") String quantity,
                        Callback<Response> callback);
    @FormUrlEncoded
    @retrofit.http.POST("/mobileapis/Addtocart/productDelete")
    void deleteitem(@Field("cart_id") String cart_id, Callback<Response> callback);

    @retrofit2.http.GET("AddtoCart/getcartList?user_id")
    Call<ShoppicartResponse>getShoppingCartItems(@Query("user_id") String string);

    @FormUrlEncoded
    @retrofit.http.POST("/mobileapis/Useraddressdetail/addaddress")
    void addaddress(@Field("user_id") String user_id,
                    @Field("name") String name,
                    @Field("address")String address,
                    @Field("locality") String locality,
                    @Field("landmark") String landmark ,
                    @Field("city") String city,
                    @Field("state") String state,
                    @Field("pincode") String pincode,
                    @Field("phone") String mobile,
                    @Field("alternatephone") String alternate_mobile,
                    @Field("email") String email, Callback<Response> callback);

    @retrofit2.http.GET("Useraddressdetail/addresslist?user_id")
    Call<AddressResponse> getAddressList(@Query("user_id") String userId);

    @retrofit2.http.GET("Useraddressdetail/addressDelete?address_id")
    Call<ResObj> DeleteAddress(@Query("address_id") String address_id);

    @FormUrlEncoded
    @retrofit.http.POST("/mobileapis/Useraddressdetail/addressUpdate")
    void updateaddress(@Field("address_id") String address_id,
                    @Field("user_id") String user_id,
                    @Field("name") String name,
                    @Field("address")String address,
                    @Field("locality") String locality,
                    @Field("landmark") String landmark ,
                    @Field("city") String city,
                    @Field("state") String state,
                    @Field("pincode") String pincode,
                    @Field("phone") String mobile,
                    @Field("alternatephone") String alternate_mobile,
                    @Field("email") String email, Callback<Response> callback);

}
