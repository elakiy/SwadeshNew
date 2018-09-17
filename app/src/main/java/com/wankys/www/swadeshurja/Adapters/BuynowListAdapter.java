package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wankys.www.swadeshurja.Fragments.Place_order;
import com.wankys.www.swadeshurja.Models.ProductSpecification;
import com.wankys.www.swadeshurja.R;

import java.util.List;

/**
 * Created by Appi on 2/12/2018.
 */

public class BuynowListAdapter extends RecyclerView.Adapter<BuynowListAdapter.ViewHolder> {
    private Context mContext;
    private  List<ProductSpecification> mcategoryLists;
    int count=0;

    public BuynowListAdapter(Context mContext, List<ProductSpecification> mcategoryLists){
        this.mContext = mContext;
        this.mcategoryLists=mcategoryLists;
    }

    @Override
    public BuynowListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.adapter_buynowlist,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final BuynowListAdapter.ViewHolder holder, final int position) {
        holder.cat_number.setText(mcategoryLists.get(position).getProduct_catno());
        holder.mrp.setText(mcategoryLists.get(position).getProduct_mrp());
        holder.discount.setText(mcategoryLists.get(position).getProduct_price());
        holder.name.setText(mcategoryLists.get(position).getProdut_name());
        holder.qty.setText(mcategoryLists.get(position).getQuantity());
        holder.total.setText(mcategoryLists.get(position).getTotal());
        String url = "http://Swadeshurja.com//swadeshseller/";
        String image=mcategoryLists.get(position).getProduct_image();
        String imagePath = url + image;
        Glide.with(mContext).load(imagePath).placeholder(R.drawable.loading).into(holder.image);

        Double mrp =Double.parseDouble(mcategoryLists.get(position).getProduct_mrp());
        Double discnt = Double.parseDouble(mcategoryLists.get(position).getProduct_price());
        // difference = mrp - discnt;
        holder.difference.setText(String.valueOf(mrp-discnt));

        int int_price = Integer.parseInt(mcategoryLists.get(position).getProduct_price());
        int qty = Integer.parseInt(mcategoryLists.get(position).getQuantity());
        Place_order.itemstotal += Double.parseDouble(holder.total.getText().toString());
        System.out.println(Place_order.itemstotal);
        Place_order.items.setText(String.valueOf(Place_order.itemstotal));
        Place_order.order_total.setText(String.valueOf(Place_order.itemstotal));

//        holder.plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                count++;
//                holder.qty.setText(String.valueOf(count));
//                String str_price = holder.discount.getText().toString();
//                int int_price = Integer.parseInt(str_price);
//                int sum = count * int_price;
//                holder.total.setText(String.valueOf(sum));
//                settotal(position);
//            }
//        });
//        holder.minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (count > 1) {
//                    count--;
//                    holder.qty.setText(String.valueOf(count));
//                    String str_price = holder.discount.getText().toString();
//                    int int_price = Integer.parseInt(str_price);
//                    int sum = count * int_price;
//                    holder.total.setText(String.valueOf(sum));
//                }
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return mcategoryLists.size();
    }

    public Context getContext() {
        return mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,cat_number,mrp,discount,difference,qty,total;
        ImageView plus,minus,image;

        public ViewHolder(final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            image = itemView.findViewById(R.id.image_id);
            cat_number = itemView.findViewById(R.id.catno_id);
            mrp = (TextView) itemView.findViewById(R.id.mrp_id);
            mrp.setPaintFlags(mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            discount = (TextView) itemView.findViewById(R.id.discount_id);
            difference=itemView.findViewById(R.id.difference_id);
//            plus =itemView.findViewById(R.id.plus_id);
//            minus=itemView.findViewById(R.id.minus_id);
            qty=itemView.findViewById(R.id.quantity_id);
            total=itemView.findViewById(R.id.total_id);
            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Fragment fr=null;
//                    int pos=getAdapterPosition();
//                    Intent intent = new Intent(mContext, ProductDetail.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    intent.putExtra("product_id",itemList.get(pos).getSproduct_id());
////                    Toast.makeText(mContext, mcategoryLists.get(pos).getSproduct_id(), Toast.LENGTH_SHORT).show();
////                    mContext.startActivity(intent);
               }
            });
        }
    }

    }
