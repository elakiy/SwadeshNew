package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Fragments.ProductDetail;
import com.wankys.www.swadeshurja.Models.ProductSpecification;
import com.wankys.www.swadeshurja.Models.Specif;
import com.wankys.www.swadeshurja.R;

import java.util.List;

/**
 * Created by Appi on 2/12/2018.
 */

public class SpecificationList extends RecyclerView.Adapter<SpecificationList.ViewHolder> {
    private Context mContext;
    private  List<ProductSpecification> mcategoryLists;
    int difference,count=0;

    public SpecificationList(Context mContext, List<ProductSpecification> mcategoryLists){
        this.mContext = mContext;
        this.mcategoryLists=mcategoryLists;
    }

    @Override
    public SpecificationList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.combinationlist,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SpecificationList.ViewHolder holder, final int position) {
        holder.cat_number.setText("Cat. No.- "+mcategoryLists.get(position).getProduct_catno());
        holder.mrp.setText(MainActivity.currency + mcategoryLists.get(position).getProduct_mrp());
        holder.discount.setText(MainActivity.currency + mcategoryLists.get(position).getProduct_price());
        holder.name.setText(mcategoryLists.get(position).getProdut_name());
        holder.total.setText(MainActivity.currency + mcategoryLists.get(position).getProduct_price());

        String url = "http://Swadeshurja.com//swadeshseller/";
        String im = mcategoryLists.get(position).getProduct_image();
        String imagePath = url + im;
        Glide.with(mContext).load(imagePath).placeholder(R.drawable.loading).into(holder.image);

        int mrp = Integer.parseInt(mcategoryLists.get(position).getProduct_mrp().toString());
        int discnt = Integer.parseInt(mcategoryLists.get(position).getProduct_price().toString());
        difference = mrp - discnt;
        holder.difference.setText(MainActivity.currency + String.valueOf(difference));

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                holder.qty.setText(String.valueOf(count));
                String str_price =  mcategoryLists.get(position).getProduct_price();
                int int_price = Integer.parseInt(str_price);
                int sum = count * int_price;
                holder.total.setText(MainActivity.currency + String.valueOf(sum));
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 1) {
                    count--;
                    holder.qty.setText(String.valueOf(count));
                    String str_price =  mcategoryLists.get(position).getProduct_price();
                    int int_price = Integer.parseInt(str_price);
                    int sum = count * int_price;
                    holder.total.setText(MainActivity.currency + String.valueOf(sum));
                }
            }
        });
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String qty = holder.qty.getText().toString();
                    System.out.println(qty);
                    String total = String.valueOf(Double.parseDouble(qty)*Double.parseDouble( mcategoryLists.get(position).getProduct_mrp()));
                    String recid =  mcategoryLists.get(position).getProduct_record_id();
                    ProductDetail.selectedStrings.add(new Specif(recid,qty));
                    ProductDetail.itemsforbuynow.add(new ProductSpecification(
                            mcategoryLists.get(position).getProduct_price(),
                            mcategoryLists.get(position).getProduct_mrp(),
                            mcategoryLists.get(position).getProduct_catno(),
                            mcategoryLists.get(position).getProduct_record_id(),
                            mcategoryLists.get(position).getProduct_image(),
                            mcategoryLists.get(position).getProdut_name(),
                            holder.qty.getText().toString(),total));
                 }
                else if (!isChecked){
                    for(Specif specif : ProductDetail.selectedStrings)
                    {
                        System.out.println("specif"+specif.getProduct_record_id());
                        System.out.println("===============================");
                        if(specif.getProduct_record_id().equals(mcategoryLists.get(position).getProduct_record_id()))
                        {
                            ProductDetail.selectedStrings.remove(specif);
                            break;
                        }
                    }
                    for (ProductSpecification productSpecification:ProductDetail.itemsforbuynow)
                    {
                        if(productSpecification.getProduct_record_id().equals(mcategoryLists.get(position).getProduct_record_id()))
                        {
                            ProductDetail.itemsforbuynow.remove(productSpecification);
                            break;
                        }
                    }
                }
                for(int i=0;i<ProductDetail.selectedStrings.size();i++){
                    System.out.println("rec_id "+ProductDetail.selectedStrings.get(i).getProduct_record_id());
                    System.out.println("qty "+ProductDetail.selectedStrings.get(i).getQty());}
            }

        });
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
        CheckBox cb ;

        public ViewHolder(final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            image = itemView.findViewById(R.id.image_id);
            cat_number = itemView.findViewById(R.id.catno_id);
            mrp = (TextView) itemView.findViewById(R.id.mrp_id);
            mrp.setPaintFlags(mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            discount = (TextView) itemView.findViewById(R.id.discount_id);
            difference=itemView.findViewById(R.id.difference_id);
            cb = itemView.findViewById(R.id.checkbox_id);
            plus =itemView.findViewById(R.id.plus_id);
            minus=itemView.findViewById(R.id.minus_id);
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
