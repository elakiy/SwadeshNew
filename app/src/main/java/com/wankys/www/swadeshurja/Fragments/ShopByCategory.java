package com.wankys.www.swadeshurja.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Adapters.CustumListview;
import com.wankys.www.swadeshurja.Models.BrandList;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.BrandList_Response;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;

import java.util.List;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Elakiya on 5/14/2018.
 */

public class ShopByCategory extends Fragment {
    ListView listView;
    List<BrandList> list;
    View view;
    Activity activity;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopbycategory,container,false);
        ButterKnife.bind(this, view);
        activity = (Activity) view.getContext();
        listView = view.findViewById(R.id.listview_id);
        progressBar = view.findViewById(R.id.progress_bar_id);
        getBrandList();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        MainActivity.tool.setVisibility(View.GONE);
    }
    private void getBrandList() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<BrandList_Response> call = apiInterface.getBrandList();
        call.enqueue(new Callback<BrandList_Response>() {
            @Override
            public void onResponse(Call<BrandList_Response> call, Response<BrandList_Response> response) {
                if(response.isSuccessful())
                {
                    BrandList_Response brandList_response = response.body();
                    //System.out.println(brandList_response.getBrand_data());
                    if(brandList_response.getStatus().equals("1"))
                    {
                        //Toast.makeText(getActivity(), "Brands found", Toast.LENGTH_SHORT).show();
                        list = brandList_response.getBrand_data();
                        CustumListview custumListview = new CustumListview(getActivity(),list);
                        listView.setAdapter(custumListview);
                        pDialog.cancel();
                    }
                }
            }
            @Override
            public void onFailure(Call<BrandList_Response> call, Throwable t) {
                pDialog.cancel();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
