package com.wankys.www.swadeshurja.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Models.BrandList;
import com.wankys.www.swadeshurja.Models.CategoryList;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.CategoryList_Response;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustumListview extends BaseAdapter {
    private final List<BrandList> mcategoryLists;
    private Activity context;
    List<CategoryList> list = new ArrayList<>();

    public CustumListview(Activity context, List<BrandList> categoryLists) {
        this.context=context;
        this.mcategoryLists = categoryLists;
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        System.out.println(mcategoryLists.size());
        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.listview_input, null);
            final CategoryList categoryList = new CategoryList("1111",mcategoryLists.get(position).getBrand_name(),"0","0",mcategoryLists.get(position).getBrandlogo());
            final Spinner spinner = grid.findViewById(R.id.spinner_text);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CategoryList_Response> call = apiInterface.getCategoryList( mcategoryLists.get(position).getBrand_id());
            call.enqueue(new Callback<CategoryList_Response>() {
                @Override
                public void onResponse(Call<CategoryList_Response> call, Response<CategoryList_Response> response) {
                    if(response.isSuccessful())
                    {
                        CategoryList_Response brandList_response = response.body();
                        if(brandList_response.getStatus().equals("1"))
                        {
                            //Toast.makeText(context, "categories found", Toast.LENGTH_SHORT).show();
                            list = brandList_response.getCategory_data();
                            list.add(0,categoryList);
                            System.out.println(mcategoryLists.get(position).getBrand_id());
                            for(int i =0;i<list.size();i++)
                            {System.out.println("category  "+list.get(i).getScategory_id());}
                            final Spinner_Onlytext sadapter = new Spinner_Onlytext(context,R.layout.spinner_onlytext,R.id.spinner_text, list);
                            sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(sadapter);

                        }
                        else if(brandList_response.getStatus().equals("0"))
                        {
                            list = brandList_response.getCategory_data();
                            list.add(0,categoryList);
                            final Spinner_Onlytext sadapter = new Spinner_Onlytext(context,R.layout.spinner_onlytext,R.id.spinner_text, list);
                            sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(sadapter);
                        }
                    }
                }
                @Override
                public void onFailure(Call<CategoryList_Response> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        else {
            grid = (View) convertView;
        }

        return grid;
    }

}
