package com.wankys.www.swadeshurja.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Adapters.ShoppingCartAdapter;
import com.wankys.www.swadeshurja.Models.Cart;
import com.wankys.www.swadeshurja.Models.ProductSpecification;
import com.wankys.www.swadeshurja.Models.SaveArrayList;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.ShoppicartResponse;
import com.wankys.www.swadeshurja.Utils.ApiClient;
import com.wankys.www.swadeshurja.Utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wankys.www.swadeshurja.Models.UserSessionManager.Key_UserId;
import static com.wankys.www.swadeshurja.Models.UserSessionManager.PREFER_NAMEUserdata;

public class Shoppingcart extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Cart> ItemList;
    public static Double grandtotal =0.0;
    public static TextView totalcost;
    View view;
    Activity activity;
    Button placeorder_button;
    SaveArrayList saveArrayList;
    LinearLayout cartempty,totallayout,placeorderlayout;
    NestedScrollView nestedScrollView;

    public static List<ProductSpecification> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.shopping_cart, container, false);
        ButterKnife.bind(this, view);
        activity = (Activity) view.getContext();
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFER_NAMEUserdata, Context.MODE_PRIVATE);
        String user_id = preferences.getString(Key_UserId, null);
        getCartData(user_id);
        System.out.println("cart" +user_id);
        recyclerView = view.findViewById(R.id.recyclerView_Id);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
        totalcost = view. findViewById(R.id.final_cost_id);
        placeorder_button = view.findViewById(R.id.placeorder_button);
        placeorder_button.setOnClickListener(this);
        list = new ArrayList<>();
        grandtotal = 0.0;
        cartempty = view.findViewById(R.id.emptycart_layout);
        totallayout = view.findViewById(R.id.total_layout);
        placeorderlayout=view.findViewById(R.id.placeorder_layout);
        nestedScrollView = view.findViewById(R.id.scroll);
        return view;
    }

    private void getCartData(String swarajuser_id) {
        final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ShoppicartResponse> call = apiInterface.getShoppingCartItems(swarajuser_id);
        call.enqueue(new Callback<ShoppicartResponse>() {
            @Override
            public void onResponse(Call<ShoppicartResponse> call, Response<ShoppicartResponse> response)
            {
                if (response.isSuccessful()) {
                    pDialog.cancel();
                    ShoppicartResponse listResponse = response.body();
                    System.out.println(listResponse.getStatus_repsonse());
                    System.out.println(listResponse.getStatus());
                    if (listResponse.getStatus().equals("1"))
                    {
                        nestedScrollView.setVisibility(View.VISIBLE);
                        placeorderlayout.setVisibility(View.VISIBLE);
                        totallayout.setVisibility(View.VISIBLE);
                        ItemList = listResponse.getShoppingcarts();
                        System.out.println(ItemList);
                        ShoppingCartAdapter adapter = new ShoppingCartAdapter(getContext(), ItemList);
                        recyclerView.setAdapter(adapter);
                    }
                    else if (listResponse.getStatus().equals("0"))
                    {
                        nestedScrollView.setVisibility(View.GONE);
                        placeorderlayout.setVisibility(View.GONE);
                        totallayout.setVisibility(View.GONE);
                        cartempty.setVisibility(View.VISIBLE);
                        Toast.makeText(activity, "No Items found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ShoppicartResponse> call, Throwable t)
            {
                pDialog.cancel();
                Toast.makeText(activity,"error in cartlistadapter", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        saveArrayList = new SaveArrayList(getContext());
        saveArrayList.clearArrayList();
        Place_order place_order = new Place_order();
        saveArrayList.saveFavorites(getActivity(),list);
        ((MainActivity) getActivity()).loadFragment(place_order, true);
    }
}
