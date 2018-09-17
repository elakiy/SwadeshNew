package com.wankys.www.swadeshurja.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Adapters.ProductRecyclerViewAdapter;
import com.wankys.www.swadeshurja.Adapters.RecyclerViewAdapter;
import com.wankys.www.swadeshurja.Models.Product;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.GetSearchProducts;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProducts extends Fragment {
    EditText searchview;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.searchproducts,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         recyclerView =getView().findViewById(R.id.recycleviewSearchProducts);

        //search view
        searchview = getView().findViewById(R.id.searchview_id);
        if (searchview != null) {
            searchview.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }
                @Override
                public void afterTextChanged(Editable editable) {
                    Log.d("text", editable.toString());
                    if (searchview.getText().toString().isEmpty()) {
                        //search.setVisibility(View.INVISIBLE);
                        //noproducts.setVisibility(View.INVISIBLE);
                    } else
                        searchProducts(editable.toString());
                }
            });
        }
    }

    private void searchProducts(String s) {
        if (s != null) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<GetSearchProducts> itemList = apiInterface.getSearchProducts(s);
            itemList.enqueue(new Callback<GetSearchProducts>() {
                @Override
                public void onResponse(Call<GetSearchProducts> call, Response<GetSearchProducts> response) {
                    GetSearchProducts listresponse = response.body();
                    System.out.println(listresponse.getDataList());
                    if (listresponse.getStatus().equalsIgnoreCase("1")) {
                        List<Product> searchproducts = listresponse.getDataList();
                        //Toast.makeText(MainActivity.this, listresponse.getStatus_message(), Toast.LENGTH_SHORT).show();
                        setProductList(searchproducts);
                    }
                    else if (listresponse.getStatus().equalsIgnoreCase("0")) {
                        System.out.println(listresponse.getStatus_message());
                        // Toast.makeText(MainActivity.this, listresponse.getStatus_message(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<GetSearchProducts> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setProductList(List<Product> searchproducts) {
        ProductRecyclerViewAdapter Adapter;
        GridLayoutManager gridLayoutManager;
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        Adapter = new ProductRecyclerViewAdapter(getActivity(), searchproducts);
        recyclerView.setAdapter(Adapter);
    }


}
