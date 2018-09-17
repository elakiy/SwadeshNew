package com.wankys.www.swadeshurja.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Fragments.CategoryWiseProducts;
import com.wankys.www.swadeshurja.Models.CategoryList;
import com.wankys.www.swadeshurja.R;

import java.util.List;

/**
 * Created by Elakiya on 9/22/2017.
 */

public class Spinner_Onlytext extends ArrayAdapter<CategoryList> {
    int groupid;
    Activity context;
    List<CategoryList> list;

    public Spinner_Onlytext(Activity mContext, int groupid, int id, List<CategoryList> list) {
        super(mContext,id,list);
        this.list=list;
        this.groupid=groupid;
        context=mContext;
    }

    public View getView(final int position, View convertView, ViewGroup parent )
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(groupid,parent,false);
        TextView textView=(TextView)itemView.findViewById(R.id.spinner_text);
        textView.setText(list.get(position).getCategoryName());
        if(position==0)
        {
            textView.setTextSize(17);
            textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//          textView.setTypeface(Typeface.DEFAULT,Typeface.BOLD_ITALIC);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position!=0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("category_id", list.get(position).getScategory_id());
                    bundle.putString("category_name",list.get(position).getCategoryName());
                    CategoryWiseProducts categoryWiseProducts = new CategoryWiseProducts();
                    categoryWiseProducts.setArguments(bundle);
                    ((MainActivity)context).loadFragment(categoryWiseProducts, true);
                }
            }
        });
        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return getView(position,convertView,parent);}
}


