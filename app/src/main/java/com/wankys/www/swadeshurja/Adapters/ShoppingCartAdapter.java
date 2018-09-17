package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Fragments.Shoppingcart;
import com.wankys.www.swadeshurja.Models.Cart;
import com.wankys.www.swadeshurja.Models.ProductSpecification;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Utils.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by Appi on 2/12/2018.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private Context mContext;
    private  List<Cart> mcategoryLists;
    int difference,count=0;

    public ShoppingCartAdapter(Context mContext, List<Cart> mcategoryLists){
        this.mContext = mContext;
        this.mcategoryLists=mcategoryLists;
    }

    @Override
    public ShoppingCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.adapter_shoppingcart,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final ShoppingCartAdapter.ViewHolder holder, final int position) {
        holder.cat_number.setText(mcategoryLists.get(position).getProduct_catno());
        holder.mrp.setText(mcategoryLists.get(position).getMrp());
        holder.discount.setText(mcategoryLists.get(position).getDiscount());
        holder.name.setText(mcategoryLists.get(position).getName());
        holder.qty.setText(mcategoryLists.get(position).getQuantity());

        int int_price = Integer.parseInt(mcategoryLists.get(position).getDiscount());
        int qty = Integer.parseInt(mcategoryLists.get(position).getQuantity());
        holder.total.setText(String.valueOf(qty * int_price));
        Shoppingcart.list.add(new ProductSpecification(
                mcategoryLists.get(position).getDiscount(),
                mcategoryLists.get(position).getMrp(),
                mcategoryLists.get(position).getProduct_catno(),
                mcategoryLists.get(position).getRecord_id(),
                mcategoryLists.get(position).getImage(),
                mcategoryLists.get(position).getName(),
                holder.qty.getText().toString(),holder.total.getText().toString()));

        Shoppingcart.grandtotal += qty * int_price;
        Shoppingcart.totalcost.setText(String.valueOf(Shoppingcart.grandtotal));
        System.out.println("Grand Total"+ Shoppingcart.grandtotal);
        String url = "http://Swadeshurja.com//swadeshseller/";
        String image=mcategoryLists.get(position).getImage();
        String imagePath = url + image;
        Glide.with(mContext).load(imagePath).placeholder(R.drawable.loading).into(holder.image);

        int mrp =Integer.parseInt(mcategoryLists.get(position).getMrp());
        int discnt = Integer.parseInt(mcategoryLists.get(position).getDiscount());
        difference = mrp - discnt;
        holder.difference.setText(String.valueOf(difference));

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = Integer.valueOf(holder.qty.getText().toString());
                count++;
                //holder.qty.setText(String.valueOf(count));
                updateqty(mcategoryLists.get(position).getCart_id().toString(),count);
//                System.out.println(count);
//                Toast.makeText(mContext, String.valueOf(count), Toast.LENGTH_SHORT).show();
//                String str_price = holder.discount.getText().toString();
//                int int_price = Integer.parseInt(str_price);
//                int sum = count * int_price;
//                holder.total.setText(String.valueOf(sum));
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = Integer.valueOf(holder.qty.getText().toString());
                if (count!=1) {
                    count--;
                    //holder.qty.setText(String.valueOf(count));
                    updateqty(mcategoryLists.get(position).getCart_id().toString(),count);
//                    String str_price = holder.discount.getText().toString();
//                    int int_price = Integer.parseInt(str_price);
//                    int sum = count * int_price;
//                    holder.total.setText(String.valueOf(sum));
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletecartItem(mcategoryLists.get(position).getCart_id());
            }
        });
    }

    private void deletecartItem(String cart_id) {
        Api.getClient().deleteitem(cart_id,
                new retrofit.Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                        BufferedReader reader = null;
                        String output = "";
                        Toast.makeText(mContext, "inside success", Toast.LENGTH_SHORT).show();
                        try {
                            System.out.println(response2);
                            System.out.println(response);
                            reader = new BufferedReader(new InputStreamReader(response2.getBody().in()));
                            output = reader.readLine();
                            System.out.println(output);
                                if (output.contains("1")) {
                                System.out.println(output);
                              ((MainActivity) mContext).removeCurrentFragmentAndMoveBack();
                                Shoppingcart shoppingcart = new Shoppingcart();
                                ((MainActivity) mContext).loadFragment(shoppingcart, true);
                                }
                                else if (output.contains("0")) {
                                    Toast.makeText(mContext, "Error in Deleting", Toast.LENGTH_SHORT).show();

                                }

                        } catch (IOException e) {
                            e.getStackTrace();
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        System.out.println(error.toString());
                        Toast.makeText(mContext, "inside failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateqty(String cart_id,int count) {
        System.out.println(String.valueOf(count));
        Api.getClient().updateqty(cart_id,String.valueOf(count),
                new retrofit.Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                        BufferedReader reader = null;
                        String output = "";
                        Toast.makeText(mContext, "inside success", Toast.LENGTH_SHORT).show();
                        try {
                            System.out.println(response2);
                            System.out.println(response);
                            reader = new BufferedReader(new InputStreamReader(response2.getBody().in()));
                            output = reader.readLine();
                            System.out.println(output);
                                if (output.contains("1")) {
                                    System.out.println(output);
                                    ((MainActivity) mContext).removeCurrentFragmentAndMoveBack();
                                    Shoppingcart shoppingcart = new Shoppingcart();
                                    ((MainActivity) mContext).loadFragment(shoppingcart, true);

                                } else if (output.contains("0")) {
                                    Toast.makeText(mContext, "Error in Updating", Toast.LENGTH_SHORT).show();

                                }

                        } catch (IOException e) {
                            e.getStackTrace();
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        System.out.println(error.toString());
                        Toast.makeText(mContext, "inside failure", Toast.LENGTH_SHORT).show();
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
        Button delete;

        public ViewHolder(final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            image = itemView.findViewById(R.id.image_id);
            cat_number = itemView.findViewById(R.id.catno_id);
            mrp = (TextView) itemView.findViewById(R.id.mrp_id);
            mrp.setPaintFlags(mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            discount = (TextView) itemView.findViewById(R.id.discount_id);
            difference=itemView.findViewById(R.id.difference_id);
            plus =itemView.findViewById(R.id.plus_id);
            minus=itemView.findViewById(R.id.minus_id);
            qty=itemView.findViewById(R.id.quantity_id);
            total=itemView.findViewById(R.id.total_id);
            delete = itemView.findViewById(R.id.delete_id);
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
