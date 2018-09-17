package com.wankys.www.swadeshurja.Fragments;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rd.PageIndicatorView;
import com.santalu.autoviewpager.AutoViewPager;
import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Adapters.ProductRecyclerViewAdapter;
import com.wankys.www.swadeshurja.Adapters.RecentProductRecyclerViewAdapter;
import com.wankys.www.swadeshurja.Adapters.SliderPagerAdapter;
import com.wankys.www.swadeshurja.Models.Product;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.ProductResponse;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;
import com.wankys.www.swadeshurja.Utils.Config;
import com.wankys.www.swadeshurja.Utils.DetectConnection;

import java.util.List;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Elakiya on 5/3/2018.
 */

public class Home extends Fragment {
    private int[] slide_images = {R.drawable.controlgear,R.drawable.starters,R.drawable.powergear,R.drawable.energy_management,R.drawable.automation_pic,R.drawable.finaldistribution};
    private RecyclerView contactors_recy;
    private RecyclerView thermal_recy;
    private RecyclerView motor_recy;
    private RecyclerView gridView;
    private RecyclerView.LayoutManager contactors_layoutmanager;
    private RecyclerView.LayoutManager thermal_layoutmanager;
    private RecyclerView.LayoutManager motor_layoutmanager;
    private RecyclerView.LayoutManager gridlayoutmanager;
    public static SwipeRefreshLayout swipeRefreshLayout;
    View view;
    Activity activity;
    AutoViewPager viewPager1;
    PageIndicatorView pageIndicatorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.controlgears,container,false);
        ButterKnife.bind(this, view);
        activity = (Activity) view.getContext();
        contactors_recy=view.findViewById(R.id.contactors_id);
        thermal_recy=view.findViewById(R.id.thermal_id);
        motor_recy=view.findViewById(R.id.motorprotect_id);
        viewPager1 = view.findViewById(R.id.viewpager_id);
        pageIndicatorView= view.findViewById(R.id.indicator);
        gridView = view.findViewById(R.id.grid_id);

        MainActivity.tool.setVisibility(View.VISIBLE);
        contactors_recy.setNestedScrollingEnabled(false);
        thermal_recy.setNestedScrollingEnabled(false);
        motor_recy.setNestedScrollingEnabled(false);
        gridView.setNestedScrollingEnabled(false);
        getSliderList();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.simpleSwipeRefreshLayout);
        // implement setOnRefreshListener event on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (DetectConnection.checkInternetConnection(getActivity())) {
                   // Test1.searchLayout.setVisibility(View.GONE);
                    Config.getCartList(getActivity(), true);
                    getSliderList();
                    swipeRefreshLayout.setRefreshing(false);
                }
                else {
                    MainActivity.tool.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Internet Not Available", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        return view;
    }
    private void getSliderList() {
        SliderPagerAdapter samplePagerAdapter  = new SliderPagerAdapter(getActivity(), slide_images);
        viewPager1.setAdapter(samplePagerAdapter);
        pageIndicatorView.setViewPager(viewPager1);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridlayoutmanager = new GridLayoutManager(getActivity(), 2);
        gridView.setLayoutManager(gridlayoutmanager);

        contactors_layoutmanager = new LinearLayoutManager(getActivity(),LinearLayout.HORIZONTAL,false);
        thermal_layoutmanager= new LinearLayoutManager(getActivity(),LinearLayout.HORIZONTAL,false);
        motor_layoutmanager = new LinearLayoutManager(getActivity(),LinearLayout.HORIZONTAL,false);

        contactors_recy.setLayoutManager(contactors_layoutmanager);
        thermal_recy.setLayoutManager(thermal_layoutmanager);
        motor_recy.setLayoutManager(motor_layoutmanager);
        String catid = "5b44335d7c120";
        String catid2 = "5b44335d7c120";
        String catid3 = "5b44335d7c120";
        setGrid(catid,contactors_recy);
        setGrid(catid2,thermal_recy);
        setGrid(catid3,motor_recy);
        setrecentProducts();

    }

    private void setGrid(String catid, final RecyclerView recyclerView) {
        final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ProductResponse> call = apiInterface.getProductList(catid);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()) {
                    pDialog.cancel();
                    ProductResponse listResponse = response.body();
                    System.out.println("contactors"+listResponse.getStatus_response());
                    System.out.println(listResponse.getProducts_data());
                    if(listResponse.getStatus().equals("1"))
                    {
                        List<Product> ItemList = listResponse.getProducts_data();
                        ProductRecyclerViewAdapter Adapter = new ProductRecyclerViewAdapter(getContext(),ItemList);
                        recyclerView.setAdapter(Adapter);
                    }
                    else if(listResponse.getStatus().equals("0")) {
                        Toast.makeText(getActivity(),"No category found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                pDialog.cancel();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setrecentProducts() {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ProductResponse> call = apiInterface.getRecentproductsList();
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
                        RecentProductRecyclerViewAdapter Adapter = new RecentProductRecyclerViewAdapter(getContext(),ItemList);
                        gridView.setAdapter(Adapter);
                    }
                    else if(listResponse.getStatus().equals("0")) {
                        Toast.makeText(getActivity(),"No category found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
