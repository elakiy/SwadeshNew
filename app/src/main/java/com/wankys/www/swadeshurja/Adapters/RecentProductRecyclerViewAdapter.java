package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Fragments.ProductDetail;
import com.wankys.www.swadeshurja.Models.Product;
import com.wankys.www.swadeshurja.R;

import java.util.List;

/**
 * Created by Appi on 2/12/2018.
 */

public class RecentProductRecyclerViewAdapter extends RecyclerView.Adapter<RecentProductRecyclerViewAdapter.ViewHolder> {
    private List<Product> itemList;
    Context context;

    public RecentProductRecyclerViewAdapter(Context context, List<Product> itemList){
        this.context=context;
        this.itemList=itemList;
    }

    @Override
    public RecentProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.grid_input,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecentProductRecyclerViewAdapter.ViewHolder holder, int position) {

        String s_Name= itemList.get(position).getProduct_name();
        holder.Name.setText(s_Name);
        String s_Catid=itemList.get(position).getProduct_catno();
        System.out.println("Cat Number"+itemList.get(position).getProduct_catno());
        //holder.Catno.setText(s_Catid);
        String url = "http://Swadeshurja.com//swadeshseller/";
        String image=itemList.get(position).getProduct_img();
        String imagePath = url + image;
        Glide.with(context).load(imagePath).placeholder(R.drawable.loading).into(holder.Image1);
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public Context getContext() {
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView Image1,rupee,dollor;
        public TextView Name,Catno;

        public ViewHolder(final View itemView) {
            super(itemView);
            Image1= (ImageView) itemView.findViewById(R.id.item_image);
            Name= (TextView) itemView.findViewById(R.id.itemname_tv);
            Catno = itemView.findViewById(R.id.cat_no);
            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Bundle bundle = new Bundle();
                    bundle.putString("product_id",itemList.get(pos).getSproduct_id());
                    ProductDetail productDetail = new ProductDetail();
                    productDetail.setArguments(bundle);
                    ((MainActivity) context).loadFragment(productDetail, true);

               }
            });
        }
    }

    }
