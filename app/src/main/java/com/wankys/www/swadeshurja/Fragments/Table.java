package com.wankys.www.swadeshurja.Fragments;

/**
 * Created by Elakiya on 5/16/2018.
 */

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


import com.wankys.www.swadeshurja.Adapters.Spinner_Onlytext;
import com.wankys.www.swadeshurja.Models.ProductSpecification;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.ProductSpecificationResponse;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Table extends Fragment {
    String product_id = "5b2b45a61976d";
    ArrayList<String> c = new ArrayList<>();
    ArrayList<String> m = new ArrayList<>();
    ArrayList<String> cno = new ArrayList<>();
    ArrayList<String> rno = new ArrayList<>();
    CheckBox cb = new CheckBox(getActivity());
    Spinner sp = new Spinner(getActivity());
    public static Table newInstance() {
        return new Table();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.table, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getdata(product_id);
        printrec();
    }

    private void printrec() {
        for(int i=0;i<c.size();i++)
        {
            if(cb.isChecked())
            {
                
            }
        }
    }

    private void getdata(String product_id) {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ProductSpecificationResponse> call = apiInterface.getProductSpecification(product_id);
        call.enqueue(new Callback<ProductSpecificationResponse>() {
            @Override
            public void onResponse(Call<ProductSpecificationResponse> call, Response<ProductSpecificationResponse> response) {
                if(response.isSuccessful()) {
                    ProductSpecificationResponse listResponse = response.body();
                    System.out.println(listResponse.getStatus_response());
                    System.out.println(listResponse.getProducts_data());
                    Toast.makeText(getActivity(), "inside table", Toast.LENGTH_SHORT).show();
                    if(listResponse.getStatus().equals("1"))
                    {
                        List<ProductSpecification> ItemList = listResponse.getProducts_data();
                        for(int i=0;i<ItemList.size();i++)
                        {
                            String cost  = ItemList.get(i).getProduct_price();
                            c.add(cost);
                            String mrp = ItemList.get(i).getProduct_mrp();
                            m.add(mrp);
                            String catnpo = ItemList.get(i).getProduct_catno();
                            cno.add(catnpo);
                            String recno =ItemList.get(i).getProduct_record_id();
                            rno.add(recno);
                        }
                        System.out.println(c);
                        System.out.println(m);
                        System.out.println(cno);
                        System.out.println(rno);
                        addHeaders();
                        addData();
                    }
                    else if(listResponse.getStatus().equals("0")) {
                        Toast.makeText(getActivity(),"No category found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductSpecificationResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(getActivity());
        tv.setId(id);
        tv.setText(title);
        tv.setTextSize(12);
        tv.setTextColor(color);
        tv.setPadding(10, 10, 10, 10);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        return tv;
    }


    @NonNull
    private LayoutParams getLayoutParams() {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 1, (int) 1.5);
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    /**
     * This function add the headers to the table
     **/
    public void addHeaders() {
        TableLayout tl = getView().findViewById(R.id.table);
        TableRow tr = new TableRow(getActivity());
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "Cat Number \n", Color.WHITE, Typeface.NORMAL, R.color.colorPrimary));
        tr.addView(getTextView(0, "MRP \nRs", Color.WHITE, Typeface.NORMAL, R.color.colorPrimary));
        tr.addView(getTextView(0, "Discount \nRs.", Color.WHITE, Typeface.NORMAL, R.color.colorPrimary));
        tr.addView(getTextView(0, "Quantity \n", Color.WHITE, Typeface.NORMAL, R.color.colorPrimary));
        tr.addView(getTextView(0,"\n",Color.WHITE, Typeface.NORMAL, R.color.colorPrimary));
        tl.addView(tr, getTblLayoutParams());
    }

    /**
     * This function add the data to the table
     **/
    public void addData() {
        System.out.println("Catno"+ c.get(0).toString());
        int numCompanies = cno.size();
        TableLayout tl = getView().findViewById(R.id.table);
        for (int i = 0; i < numCompanies; i++) {
            TableRow tr = new TableRow(getActivity());
            tr.setLayoutParams(getLayoutParams());
            tr.addView(getTextView(i + 1, cno.get(i), Color.BLACK, Typeface.ITALIC, ContextCompat.getColor(getActivity(), R.color.colorWhite)));
            tr.addView(getTextView(i + numCompanies, m.get(i), Color.BLACK, Typeface.ITALIC, ContextCompat.getColor(getActivity(), R.color.colorWhite)));
            tr.addView(getTextView(i + numCompanies + 1, c.get(i), Color.BLACK, Typeface.ITALIC, ContextCompat.getColor(getActivity(), R.color.colorWhite)));
            tr.addView(getSpinnerView(i + numCompanies + 2));
            tr.addView(getCheckView(i+ numCompanies + 3));
            tl.addView(tr, getTblLayoutParams());
        }
    }

    @SuppressLint("ResourceAsColor")
    private CheckBox getCheckView(int id)
    {
        cb.setId(id);
        cb.setBackgroundColor(R.color.colorWhite);
        cb.setHighlightColor(Color.BLACK);
        cb.setLayoutParams(getLayoutParams());
        return cb;
    }
    @SuppressLint("ResourceAsColor")
    private Spinner getSpinnerView(int id)
    {
        sp.setId(id);
        sp.setLayoutParams(getLayoutParams());
        String[] Timing = {"1","2","3","4","5","6","7","8","9","10"};
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Timing);
        try {
            Field popup = Spinner.class.getDeclaredField( "mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(sp);

            // Set popupWindow height to 500px
            popupWindow.setHeight(70);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter1);
        return sp;
    }

}
