package com.wankys.www.swadeshurja.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.rd.PageIndicatorView;
import com.santalu.autoviewpager.AutoViewPager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Adapters.SliderImagesAdapter;
import com.wankys.www.swadeshurja.Adapters.SpecificationList;
import com.wankys.www.swadeshurja.Models.ProductSpecification;
import com.wankys.www.swadeshurja.Models.Product_DetailModel;
import com.wankys.www.swadeshurja.Models.SaveArrayList;
import com.wankys.www.swadeshurja.Models.Specif;
import com.wankys.www.swadeshurja.Models.UserSessionManager;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.ProductDetailsResponse;
import com.wankys.www.swadeshurja.Response.ProductSpecificationResponse;
import com.wankys.www.swadeshurja.Utils.Api;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wankys.www.swadeshurja.Models.UserSessionManager.Key_UserId;
import static com.wankys.www.swadeshurja.Models.UserSessionManager.PREFER_NAMEUserdata;

/**
 * Created by Elakiya on 5/16/2018.
 */

public class ProductDetail extends Fragment implements View.OnClickListener {
    TextView name,mrp;
    ImageView image;
    String url;
    private static final String TAG = "DemoActivity";
    private SlidingUpPanelLayout mLayout;
    public static ArrayList<Specif> selectedStrings = new ArrayList<>();
    public static List<ProductSpecification> itemsforbuynow = new ArrayList<>();
    public String Swarajuser_id;
    RecyclerView table;
    String product_id;
    List<ProductSpecification> ItemList;
    RecyclerView.LayoutManager table_layout;
    Button buynow,addtocart;
    UserSessionManager sessionManager;
    Activity activity;
    View view;
    WebView webView;
    ProgressBar progressBar;
    SaveArrayList saveArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_detail, container, false);
        ButterKnife.bind(this, view);
        activity = (Activity) view.getContext();
        Bundle bundle = getArguments();
        MainActivity.tool.setVisibility(View.GONE);
        product_id = bundle.getString("product_id");
       // image= (ImageView)view.findViewById(R.id.itemimage_Id);
        name = (TextView)view.findViewById(R.id.itemname_tv);
        mrp = (TextView) view.findViewById(R.id.price_tv);
        table = (RecyclerView) view.findViewById(R.id.table_id);
        buynow = (Button) view.findViewById(R.id.submit_id);
        addtocart = view.findViewById(R.id.addtocart_id);
        progressBar= (ProgressBar)view.findViewById(R.id.progress_id);
        webView = (WebView) view.findViewById(R.id.web_view_id);
        saveArrayList = new SaveArrayList(getActivity());
        saveArrayList.clearArrayList();
        System.out.println("SavedArray"+saveArrayList.getFavorites(getActivity()));
        itemsforbuynow=new ArrayList<>();
        System.out.println("itemsforbuynow1"+itemsforbuynow);
        return view;
        }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addtocart.setOnClickListener(this);
        buynow.setOnClickListener(this);
        table_layout = new LinearLayoutManager(getActivity());
        table.setLayoutManager(table_layout);
        table.setNestedScrollingEnabled(false);
        sessionManager = new UserSessionManager(getActivity());
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFER_NAMEUserdata, Context.MODE_PRIVATE);
        Swarajuser_id = preferences.getString(Key_UserId, null);
        gettable(product_id);
        setProductDetail();
    }

    private void setProductDetail() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductDetailsResponse> call = apiInterface.getProductDetails(product_id);
        System.out.println(product_id);
        Toast.makeText(getActivity(), product_id, Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<ProductDetailsResponse>() {
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                System.out.println(response);
                if (response.isSuccessful())
                {
                    ProductDetailsResponse list_response = response.body();
                    System.out.println(list_response.getStatus());
                    System.out.println(list_response.getStatus_response());
                    System.out.println("Table List"+list_response.getProducts_data());
                    List<Product_DetailModel> l = list_response.getProducts_data();
                    if(list_response.getStatus().equals("1")) {
                        int i; ArrayList<String> imgl = null;
                        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                        for(i=0;i<l.size();i++) {
                            imgl = l.get(i).getProduct_img();
                            name.setText(l.get(i).getProduct_name());
                            mrp.setText(l.get(i).getProduct_mrp());
                            System.out.println(l.get(i).getProduct_img());
                            url = l.get(i).getSpecification_url();
                        }
                        if(imgl.size()!=0)
                        {
                            setViewPager(imgl);
                        }
                        setFragment(url);
                    }
                }
            }
            @Override
            public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setViewPager(ArrayList<String> imgl) {
        AutoViewPager viewPager = (AutoViewPager) getView().findViewById(R.id.viewpager_id);
        SliderImagesAdapter samplePagerAdapter  = new SliderImagesAdapter(getContext(), imgl);
        viewPager.setAdapter(samplePagerAdapter);
        PageIndicatorView pageIndicatorView = (PageIndicatorView)getView(). findViewById(R.id.indicator);
        pageIndicatorView.setViewPager(viewPager);
    }

    private void setFragment(String url) {
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;
        }
    }
    private void gettable(final String prod_id) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductSpecificationResponse> call = apiInterface.getProductSpecification(product_id);
        call.enqueue(new Callback<ProductSpecificationResponse>() {
            @Override
            public void onResponse(Call<ProductSpecificationResponse> call, Response<ProductSpecificationResponse> response) {
                if (response.isSuccessful()) {
                    ProductSpecificationResponse listResponse = response.body();
                    if (listResponse.getStatus().equals("1")) {
                       // Toast.makeText(getActivity(), "inside table", Toast.LENGTH_SHORT).show();
                        ItemList = listResponse.getProducts_data();
                        System.out.println("TABLE"+ItemList);
                        System.out.println(listResponse.getStatus_response());
                        System.out.println(prod_id);
                        SpecificationList adapter = new SpecificationList(getContext(), ItemList);
                        table.setAdapter(adapter);
                        table.setVisibility(View.VISIBLE);
                    } else if (listResponse.getStatus().equals("0")) {
                        Toast.makeText(getActivity(), "No category found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProductSpecificationResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Gson gson = new Gson();
        String js = gson.toJson(selectedStrings);
        switch (view.getId())
        { case R.id.addtocart_id:
                addtocart(js);
                break;
            case R.id.submit_id:
                buynow(js);
                break;
        } }

    private void buynow(String js) {
         saveArrayList.clearArrayList();
         System.out.println("itemsforbuynow2"+itemsforbuynow);
         Place_order place_order = new Place_order();
         if(itemsforbuynow.size()>0)
         { saveArrayList.saveFavorites(getActivity(),itemsforbuynow);
        ((MainActivity) getActivity()).loadFragment(place_order, true);}
    }

    private void addtocart(String js) {
        if(js!=null) {
            JSONObject paramObject = new JSONObject();
            try {
                JSONArray json = new JSONArray(js);
                paramObject = new JSONObject();
                paramObject.put("user_id", Swarajuser_id);
                paramObject.put("product_id", product_id);
                paramObject.put("data", json);
                System.out.println("JSONOBJECT" + paramObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(Swarajuser_id);
            System.out.println(product_id);
            Api.getClient().add(paramObject.toString(), new retrofit.Callback<retrofit.client.Response>() {
                @Override
                public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                    BufferedReader reader = null;
                    String output = "";
                   // Toast.makeText(getActivity(), "inside success", Toast.LENGTH_SHORT).show();
                    try {
                        System.out.println(response2);
                        System.out.println(response);
                        reader = new BufferedReader(new InputStreamReader(response2.getBody().in()));
                        output = reader.readLine();
                        System.out.println(output);
                        if(output.contains("1"))
                        {
                            Toast.makeText(getActivity(), "Added to Cart", Toast.LENGTH_SHORT).show();
                            Shoppingcart shoppingcart = new Shoppingcart();
                            ((MainActivity) getActivity()).loadFragment(shoppingcart, true);
                        }

                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    System.out.println(error.toString());
                    Toast.makeText(getActivity(), "inside failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
