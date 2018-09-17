package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Models.BrandList;
import com.wankys.www.swadeshurja.Models.CategoryList;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.CategoryList_Response;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Elakiya on 5/14/2018.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private final List<BrandList> mcategoryLists;

    public ListViewAdapter(Context c,List<BrandList> categoryLists) {
        mContext = c;
        mcategoryLists = categoryLists;
    }

    @Override
    public int getCount() { return mcategoryLists.size(); }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.listviewtest, null);
            TextView brandname = grid.findViewById(R.id.brand_name);
            ImageView dropdown = grid.findViewById(R.id.dropdown_id);
            brandname.setText(mcategoryLists.get(position).getBrand_name());
            String brandid = mcategoryLists.get(position).getBrand_id();
            final ListView listView = grid.findViewById(R.id.categorylist);
            final RelativeLayout layout = grid.findViewById(R.id.rel_id);

            dropdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (listView.getVisibility()==View.VISIBLE)
//                    {
//                        listView.setVisibility(View.INVISIBLE);
//                    }
                    layout.setVisibility(View.VISIBLE);
                }
            });

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CategoryList_Response> call = apiInterface.getCategoryList(brandid);
            call.enqueue(new Callback<CategoryList_Response>() {
                @Override
                public void onResponse(Call<CategoryList_Response> call, Response<CategoryList_Response> response) {
                    if(response.isSuccessful())
                    {
                        CategoryList_Response brandList_response = response.body();
                        System.out.println(brandList_response.getCategory_data());
                        if(brandList_response.getStatus().equals("1"))
                        {
                            Toast.makeText(mContext, "categories found", Toast.LENGTH_SHORT).show();
                            List<CategoryList> list = brandList_response.getCategory_data();
                            System.out.println(list);
                            Category_Grid customAdapter_grid = new Category_Grid(mContext,list);
                            listView.setAdapter(customAdapter_grid);
                        }
                    }
                }
                @Override
                public void onFailure(Call<CategoryList_Response> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
