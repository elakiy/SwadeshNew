package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wankys.www.swadeshurja.Fragments.Address;
import com.wankys.www.swadeshurja.Models.AddressList;
import com.wankys.www.swadeshurja.Models.UserSessionManager;
import com.wankys.www.swadeshurja.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private Context context;
    private List<AddressList> itemList;
    UserSessionManager sessionManager;
    RadioButton mCurrentlyCheckedRB=null;

    public AddressAdapter(Context context, List<AddressList> itemList){
        this.context=context;
        this.itemList=itemList;
    }

    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.adapter_gridaddress,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(AddressAdapter.ViewHolder holder, final int position) {
        holder.name.setText(itemList.get(position).getName());
        //phone.setText(itemList.get(position).getPhone());
        holder.pincode.setText(itemList.get(position).getPincode());
        holder.locality.setText(itemList.get(position).getLocality());
        holder.address.setText(itemList.get(position).getAddress());
        holder.city.setText(itemList.get(position).getCity());
        holder.state.setText(itemList.get(position).getState());
        holder.landmark.setText(itemList.get(position).getLocality());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// checks if we already have a checked radiobutton. If we don't, we can assume that
// the user clicked the current one to check it, so we can remember it.
                if(mCurrentlyCheckedRB == null){
                    mCurrentlyCheckedRB = (RadioButton) view;
                    mCurrentlyCheckedRB.setChecked(true);
                    Address.position=position;
                }

// If the user clicks on a RadioButton that we've already stored as being checked, we
// don't want to do anything.
                if(mCurrentlyCheckedRB == view)
                    return;
// Otherwise, uncheck the currently checked RadioButton, check the newly checked
// RadioButton, and store it for future reference.
                mCurrentlyCheckedRB.setChecked(false);
                ((RadioButton)view).setChecked(true);
                mCurrentlyCheckedRB = (RadioButton)view;
                Address.position=position;
            }
        });
//                    if(isEnabled(position))
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
        RadioButton radioButton;
        public TextView name,phone,pincode,locality,address,city,state,landmark;

        public ViewHolder(final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_id);
            //phone=grid.findViewById(R.id.phone_id);
            pincode=itemView.findViewById(R.id.pincode_id);
            locality=itemView.findViewById(R.id.locality_id);
            address=itemView.findViewById(R.id.address_id);
            city=itemView.findViewById(R.id.city_id);
            state=itemView.findViewById(R.id.state_id);
            landmark=itemView.findViewById(R.id.landmark_id);
            radioButton= itemView.findViewById(R.id.radiobtn_id);
        }
    }

    }
