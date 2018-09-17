package com.wankys.www.swadeshurja.Models;

/**
 * Created by Elakiya on 3/7/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveArrayList {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";
    SharedPreferences settings;
    Editor editor;
    Context context;


    public SaveArrayList(Context context) {
        this.context= context;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<ProductSpecification> favorites) {
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        editor.putString(FAVORITES, jsonFavorites);
        editor.commit();
    }

    public void addFavorite(Context context,ProductSpecification product) {
        List<ProductSpecification> favorites = getFavorites(context);
        if (favorites == null)
        favorites = new ArrayList<ProductSpecification>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public String Data(Context context) {
        SharedPreferences settings;
        List<ProductSpecification> favorites;
        String jsonFavorites;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            jsonFavorites = settings.getString(FAVORITES, null);
//            Gson gson = new Gson();
//            TestList[] favoriteItems = gson.fromJson(jsonFavorites,
//                    TestList[].class);
//
//            favorites = Arrays.asList(favoriteItems);
//            favorites = new ArrayList<TestList>(favorites);
        } else
            return null;

        return jsonFavorites;
    }

    public ArrayList<ProductSpecification> getFavorites(Context context) {
        SharedPreferences settings;
        List<ProductSpecification> favorites;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            ProductSpecification[] favoriteItems = gson.fromJson(jsonFavorites,
                    ProductSpecification[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<ProductSpecification>(favorites);
        } else
            return null;

        return (ArrayList<ProductSpecification>) favorites;
    }
    public void clearArrayList()
    {
        editor.clear();
        editor.commit();
    }
}
