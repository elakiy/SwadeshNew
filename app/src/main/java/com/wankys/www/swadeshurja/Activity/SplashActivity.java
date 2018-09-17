package com.wankys.www.swadeshurja.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wankys.www.swadeshurja.Adapters.ProductRecyclerViewAdapter;
import com.wankys.www.swadeshurja.Models.Product;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.ProductResponse;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Wankys on 3/16/2018.
 */

public class SplashActivity extends AppCompatActivity {
    TextView imageView;
    String catid = "5b2b45a61976d";
    SharedPreferences sharedPreference, sharedPreferencesCache;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        imageView= (TextView) findViewById(R.id.imageView);
        final TextView imageView = (TextView) findViewById(R.id.imageView);
        final Animation animation_1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.zoom_in);
        final Animation animation_2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.zoom_out);
        final Animation animation_3 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

       // getProductList(catid);

        imageView.startAnimation(animation_1);
        animation_2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation_2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation_3);
                finish();
                Intent i = new Intent(getBaseContext(),Login.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void getProductList(String catid) {
            ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
            Call<ProductResponse> call = apiInterface.getProductList(catid);
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if(response.isSuccessful()) {
                        ProductResponse listResponse = response.body();
                        System.out.println(listResponse.getStatus_response());
                        System.out.println(listResponse.getProducts_data());
                        if(listResponse.getStatus().equals("1"))
                        {
                            List<Product> ItemList = listResponse.getProducts_data();
                            Gson gson = new Gson();
                            String json = gson.toJson(ItemList);
                            editor.putString("newslist", json);
                            editor.commit();
                            //moveNext();
                        }
                        else if(listResponse.getStatus().equals("0")) {
                            Toast.makeText(SplashActivity.this,"No category found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Toast.makeText(SplashActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    }

    @Override
    protected void onRestart() {
        finish();
        startActivity(getIntent());
        super.onRestart();
    }
}
