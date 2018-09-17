package com.wankys.www.swadeshurja.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Activity.CheckoutActivity;
import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Adapters.BuynowListAdapter;
import com.wankys.www.swadeshurja.Models.AddressList;
import com.wankys.www.swadeshurja.Models.ProductSpecification;
import com.wankys.www.swadeshurja.Models.SaveArrayList;
import com.wankys.www.swadeshurja.Models.UserSessionManager;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.AddressResponse;
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

public class Place_order extends Fragment implements View.OnClickListener {
    View view;
    Activity activity;
    String user_id;
    String addressid;
    UserSessionManager sessionManager;
    int address_id=0;
    TextView address,landmark_id,locality_id,name_id;
    public static List<ProductSpecification> lt = new ArrayList<>();
    RecyclerView itemsforbuy_id;
    RecyclerView.LayoutManager layoutManager;
    SaveArrayList saveArrayList;
    ImageView viewmore_address;
    public static TextView items,delivery,order_total;
    public static Double itemstotal=0.0;
    Button placeorder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.place_order,container,false);
        ButterKnife.bind(this, view);
        activity = (Activity) view.getContext();
        address = view.findViewById(R.id.address_id);
        landmark_id = view.findViewById(R.id.landmark_id);
        locality_id = view.findViewById(R.id.locality_id);
        name_id = view.findViewById(R.id.name_id);
        itemsforbuy_id = view.findViewById(R.id.itemsforbuy_id);
        viewmore_address = view.findViewById(R.id.viewmore_address);
        items = view.findViewById(R.id.items_id);
        delivery  = view.findViewById(R.id.delivery_id);
        order_total = view.findViewById(R.id.ordertotal_id);
        placeorder = view.findViewById(R.id.submit_id);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             Intent intent = new Intent(activity, CheckoutActivity.class);
//             startActivity(intent);
            }
        });
        layoutManager = new LinearLayoutManager(getActivity());
        itemsforbuy_id.setLayoutManager(layoutManager);
        itemsforbuy_id.setNestedScrollingEnabled(false);
        sessionManager = new UserSessionManager(getActivity());
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFER_NAMEUserdata, Context.MODE_PRIVATE);
        user_id = preferences.getString(Key_UserId, null);
        itemstotal=0.0;
        saveArrayList = new SaveArrayList(getActivity());
        lt = saveArrayList.getFavorites(getActivity());
        getproductlist(lt);
        getaddress(user_id);
        viewmore_address.setOnClickListener(this);
        Bundle bundle = getArguments();
        if (bundle!=null)
        {address_id = bundle.getInt("position");}
        return view;
    }
    public void resume()
    {
        super.onResume();
    }

    private void getproductlist(List<ProductSpecification> lt) {
        BuynowListAdapter adapter = new BuynowListAdapter(getActivity(),lt);
        itemsforbuy_id.setAdapter(adapter);
    }

    private void getaddress(String user_id) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AddressResponse> call = apiInterface.getAddressList(user_id);
        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                System.out.println(response);
                if (response.isSuccessful())
                {
                    AddressResponse list_response = response.body();
                    System.out.println(list_response.getStatus());
                    System.out.println(list_response.getStatus_response());
                    System.out.println(list_response.getAddress_data());
                    Toast.makeText(getContext(), list_response.getStatus_response(), Toast.LENGTH_SHORT).show();
                    if(list_response.getStatus().equals("1")) {
                        Toast.makeText(getContext(),"Success", Toast.LENGTH_SHORT).show();
                        List<AddressList> l = list_response.getAddress_data();
                        for(int i=0;i<l.size();i++) {
                            System.out.println(l.get(i).getAddress());
                            System.out.println(l.get(i).getAddress_id());
                            System.out.println(l.get(i).getName());
                            System.out.println(l.get(i).getState());
                            System.out.println(l.get(i).getCity());
                        }
                        name_id.setText(l.get(address_id).getName());
                        address.setText(l.get(address_id).getAddress());
                        landmark_id.setText(l.get(address_id).getLandmark());
                        locality_id.setText(l.get(address_id).getLocality());
                        addressid = l.get(address_id).getAddress_id();
                    }
                    else if(list_response.getStatus().equals("0"))
                    {
                        Bundle bundle = new Bundle();
                        Address address = new Address();
                        address.setArguments(bundle);
                        ((MainActivity) getActivity()).loadFragment(address, true);
                    }
                }
            }
            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Address address = new Address();
        ((MainActivity) getActivity()).loadFragment(address, true);
    }
}
