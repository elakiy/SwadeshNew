package com.wankys.www.swadeshurja.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Adapters.AddressAdapter;
import com.wankys.www.swadeshurja.Models.AddressList;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.AddressResponse;
import com.wankys.www.swadeshurja.Response.ResObj;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wankys.www.swadeshurja.Models.UserSessionManager.Key_UserId;
import static com.wankys.www.swadeshurja.Models.UserSessionManager.PREFER_NAMEUserdata;

public class Address extends Fragment implements View.OnClickListener {
    RecyclerView gridView;
    RecyclerView.LayoutManager layoutManager;
    TextView addaddress ,add;
    RelativeLayout addnow,progressBar;
    LinearLayout addresslist;
    String UserId;
    View view;
    public static int position;
    Activity activity;
    Button delete,edit,delivertothis;
    LinearLayout linearLayout;
    List<AddressList> l = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.selectbillingaddress, container, false);
        ButterKnife.bind(this, view);
        activity = (Activity) view.getContext();
        progressBar= (RelativeLayout)view. findViewById(R.id.progressd);
        gridView= view.findViewById(R.id.gridview);
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFER_NAMEUserdata, Context.MODE_PRIVATE);
        UserId = preferences.getString(Key_UserId,null);
        addresslist= (LinearLayout)view. findViewById(R.id.addresslist_id);
        addaddress=view.findViewById(R.id.add_address_btn);
        addaddress.setOnClickListener(this);
        delete = view.findViewById(R.id.delete_icon);
        edit = view.findViewById(R.id.edit_icon);
        delivertothis = view.findViewById(R.id.deliver_id);
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);
        delivertothis.setOnClickListener(this);
        linearLayout = view.findViewById(R.id.editdelete_ll);
        layoutManager= new LinearLayoutManager(getActivity());
        gridView.setLayoutManager(layoutManager);
        gridView.setNestedScrollingEnabled(false);
        Bundle bundle = getArguments();
        if (bundle!=null)
        {String permission = bundle.getString("nodeliver");
        if(permission.equalsIgnoreCase("nodeliver"))
        {delivertothis.setVisibility(View.GONE);}
        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setGridview(UserId);
    }

    @Override
    public void onResume() {
        super.onResume();
        setGridview(UserId);
    }

    public void resume()
    {
        onResume();
    }

    private void setGridview(String userId) {
       // progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AddressResponse> call = apiInterface.getAddressList(userId);
        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                System.out.println(response);
                if (response.isSuccessful())
                {
                   // progressBar.setVisibility(View.INVISIBLE);
                    AddressResponse list_response = response.body();
                    System.out.println(list_response.getStatus());
                    System.out.println(list_response.getStatus_response());
                    System.out.println(list_response.getAddress_data());
                    Toast.makeText(getActivity(), list_response.getStatus_response(), Toast.LENGTH_SHORT).show();
                    if(list_response.getStatus().equals("1")) {
                        addresslist.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(),"Success", Toast.LENGTH_SHORT).show();
                        l = list_response.getAddress_data();
                        for(int i=0;i<l.size();i++) {
                            System.out.println(l.get(i).getAddress());
                            System.out.println(l.get(i).getAddress_id());
                            System.out.println(l.get(i).getName());
                            System.out.println(l.get(i).getState());
                            System.out.println(l.get(i).getCity());
                        }
                        AddressAdapter adapter=new AddressAdapter(getActivity(),l);
                        gridView.setAdapter(adapter);
                    }
                    else if(list_response.getStatus().equals("0"))
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        addnow.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_btn:
            NewAddress newAddress = new NewAddress();
            ((MainActivity) getActivity()).loadFragment(newAddress, true);
            break;
            case R.id.delete_icon:
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(view.getRootView().getContext());
                    alertbox.setMessage("Are you sure you want to delete this Address?");
                    alertbox.setTitle("Delete");
                    alertbox.setCancelable(false);
                    alertbox.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    deletecart(position);
                                }
                            });
                    alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    alertbox.show();
                    break;
            case R.id.edit_icon:
                Bundle bundle = new Bundle();
                bundle.putString("address_id",l.get(position).getAddress_id());
                bundle.putString("name",l.get(position).getName());
                bundle.putString("address",l.get(position).getAddress());
                bundle.putString("locality",l.get(position).getLocality());
                bundle.putString("landmark",l.get(position).getLandmark());
                bundle.putString("city",l.get(position).getCity());
                bundle.putString("state",l.get(position).getState());
                bundle.putString("pincode",l.get(position).getPincode());
                bundle.putString("mobile",l.get(position).getPhone());
                bundle.putString("alternate_number",l.get(position).getAlternate_number());
                bundle.putString("email",l.get(position).getEmail());
                EditAddress editaddress = new EditAddress();
                editaddress.setArguments(bundle);
                ((MainActivity) getActivity()).loadFragment(editaddress, true);
                break;
            case R.id.deliver_id:
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("position",position);
                    Place_order place_order = new Place_order();
                    place_order.setArguments(bundle1);
                    ((MainActivity) getActivity()).loadFragment(place_order, true);
                break;
        }
    }

    private void deletecart(int position) {
        System.out.println(position);
        ApiInterface deletecartItem = ApiClient.getClient().create(ApiInterface.class);
        Call<ResObj> calldelete = deletecartItem.DeleteAddress(l.get(position).getAddress_id());
        calldelete.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response)
            {
                if (response.isSuccessful())
                {
                    ResObj resObj=response.body();
                    System.out.println(resObj.getStatus_message());
                    if (resObj.getStatus().equals("1"))
                    {
                        Toast.makeText(getContext(), "Address deleted", Toast.LENGTH_SHORT).show();
                        onResume();
                    }
                    else if (resObj.getStatus().equals("0"))
                    {
                        System.out.println(resObj.getStatus_message());
                        Toast.makeText(getContext(), "No Address found to delete", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Error! please try again!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
