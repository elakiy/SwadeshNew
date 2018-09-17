package com.wankys.www.swadeshurja.Models;

/**
 * Created by Appi on 3/1/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.wankys.www.swadeshurja.Activity.Login;



public class UserSessionManager {
    // Shared Preferences reference
    SharedPreferences pref_session1,pref_session2,pref_session3,pref_session4;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor_session1,editor_session2,editor_session3,editor_session4;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAMEProduct = "StoreProductData";
    public static final String PREFER_NAMEUserdata = "StoreUserdata";
    private static final String PREFER_NAMECartCount = "StoreCartCount";
    private static final String PREFER_NAMEOrderDetails = "StoreOrderDetails";

    public static final String Key_Cart="cartcount";
    private static final String IS_COUNT_SAVED = "IsCountSaved";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    //For UserDetails
    public static final String Key_UserName="uname";
    public static final String Key_Email="email";
    public static final String Key_UserId="userId";
    public static final String Key_Phone="phone";
    public static final String Key_Password = "password";


    public static final String Key_OrderId="orderId";
    public static final String Key_OrderNumber="orderNumber";
    public static final String Key_OrderDate="orderDate";
    public static final String Key_OrderTotal="orderTotal";
    public static final String Key_OrderAddress="orderAddress";
    public static final String Key_OrderStatus="orderStatus";

    public void createUserLoginSession(String userid, String uname, String email, String phone, String password){
        // Storing login value as TRUE
        editor_session1.putBoolean(IS_USER_LOGIN, true);
        editor_session1.putString(Key_UserId,userid);
        editor_session1.putString(Key_UserName,uname);
        editor_session1.putString(Key_Email,email);
        editor_session1.putString(Key_Phone,phone);
        editor_session1.putString(Key_Password, password);
        // commit changes
        editor_session1.commit();
    }
    public static String getSavedUserid(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(Key_UserId, 0);
        return pref.getString(key, "");
        }
    public void createCartCount(int count)
    {
        editor_session3.putBoolean(IS_COUNT_SAVED,true);
        editor_session3.putInt(Key_Cart,count);
        editor_session3.commit();
    }

    // Product details (make variable public to access from outside)
    public static final String Key_ProductId="ref_id";
    public static final String Key_Category_Id="category_id";
    public static final String Key_Name="product_name";
    public static final String Key_ShortName="product_shrtname";
    public static final String Key_Image1="product_img1";
    public static final String Key_Image2="product_img2";
    public static final String Key_MRP="product_mrp";
    public static final String Key_Size="product_size";
    public static final String Key_Description="product_desc";
    public static final String Key_PrescriptionFlag = "prescription_flag";
    public static final String Key_Composition_Url = "composition_url";
    public static final String Key_Efficacy_Url = "efficacy_url";
    public static final String Key_Currency = "currency";
    // Constructor
    public UserSessionManager(Context context){
        this._context = context;
        pref_session1 = _context.getSharedPreferences(PREFER_NAMEUserdata, PRIVATE_MODE);
        editor_session1 = pref_session1.edit();
        pref_session2 = _context.getSharedPreferences(PREFER_NAMEProduct, PRIVATE_MODE);
        editor_session2 = pref_session2.edit();
        pref_session3=_context.getSharedPreferences(PREFER_NAMECartCount,PRIVATE_MODE);
        editor_session3=pref_session3.edit();
        pref_session4=_context.getSharedPreferences(PREFER_NAMEOrderDetails,PRIVATE_MODE);
        editor_session4=pref_session4.edit();
    }

    //Create login session
    public void createProductData(String ref_id, String category_id, String product_name, String product_shrtname, String product_img1, String product_img2, String product_mrp, String product_size, String product_desc, String prescription_flag, String composition_url, String efficacy_url, String currency){
        // Storing login value as TRUE
        editor_session2.putString(Key_ProductId,ref_id);
        editor_session2.putString(Key_Category_Id,category_id);
        editor_session2.putString(Key_Name, product_name);
        editor_session2.putString(Key_ShortName,product_shrtname);
        editor_session2.putString(Key_Image1,product_img1);
        editor_session2.putString(Key_Image2,product_img2);
        editor_session2.putString(Key_MRP,product_mrp);
        editor_session2.putString(Key_Size,product_size);
        editor_session2.putString(Key_Description,product_desc);
        editor_session2.putString(Key_PrescriptionFlag,prescription_flag);
        editor_session2.putString(Key_Composition_Url,composition_url);
        editor_session2.putString(Key_Efficacy_Url,efficacy_url);
        editor_session2.putString(Key_Currency,currency);
        // commit changes
        editor_session2.commit();
    }
    public void createOrderDetails(String orderId, String orderNumber, String orderDate, String orderTotal, String orderAddress, String orderStatus){
        // Storing login value as TRUE
        editor_session4.putString(Key_OrderId,orderId);
        editor_session4.putString(Key_OrderNumber,orderNumber);
        editor_session4.putString(Key_OrderDate, orderDate);
        editor_session4.putString(Key_OrderTotal, orderTotal);
        editor_session4.putString(Key_OrderAddress,orderAddress);
        editor_session4.putString(Key_OrderStatus,orderStatus);
        // commit changes
        editor_session4.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);
            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            _context.startActivity(i);
            return true;
        }
        return false;
    }
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor_session1.clear();
        editor_session1.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, Login.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    // Check for login
    public boolean isUserLoggedIn(){
        return pref_session1.getBoolean(IS_USER_LOGIN, false);
    }

    public boolean isCartCountdone(){
        return pref_session3.getBoolean(IS_COUNT_SAVED, false);
    }

}


