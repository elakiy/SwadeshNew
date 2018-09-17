package com.wankys.www.swadeshurja.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

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

public class CategoryWise extends Activity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String category_id, category_name;
    TextView Categoryname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorywiseproducts);
        recyclerView=findViewById(R.id.grid_id);
        Categoryname = findViewById(R.id.categoryname);
        layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = getIntent().getExtras();
        category_id =bundle.getString("category_id");
        category_name = bundle.getString("category_name");
        Categoryname.setText(category_name);
        setData(category_id);
        System.out.println("category_id"+category_id);
    }

    private void setData(String category_id) {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ProductResponse> call = apiInterface.getProductList(category_id);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()) {
                    ProductResponse listResponse = response.body();
                    System.out.println("response "+listResponse.getStatus_response());
                    System.out.println("product data "+listResponse.getProducts_data());
                    if(listResponse.getStatus().equals("1"))
                    {
                        Toast.makeText(CategoryWise.this, "inside category wise", Toast.LENGTH_SHORT).show();
                        List<Product> ItemList = listResponse.getProducts_data();
                        System.out.println(ItemList);
                        ProductRecyclerViewAdapter Adapter = new ProductRecyclerViewAdapter(getApplicationContext(),ItemList);
                        recyclerView.setAdapter(Adapter);
                    }
                    else if(listResponse.getStatus().equals("0")) {
                        Toast.makeText(getApplicationContext(),"No category found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
