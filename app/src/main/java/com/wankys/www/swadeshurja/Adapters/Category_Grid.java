package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wankys.www.swadeshurja.Models.CategoryList;
import com.wankys.www.swadeshurja.R;

import java.util.List;


public class Category_Grid extends BaseAdapter {
    private Context mContext;
    private final List<CategoryList> mcategoryLists;

    public Category_Grid(Context mContext, List<CategoryList> mcategoryLists) {
        this.mContext = mContext;
        this.mcategoryLists = mcategoryLists;
    }


    @Override
    public int getCount() {
        return mcategoryLists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.categorylist, null);
            TextView textView = (TextView) grid.findViewById(R.id.categoryname);
            textView.setText(mcategoryLists.get(position).getCategoryName());
        }
        else {
            grid = (View) convertView;
        }

        return grid;
    }
}



