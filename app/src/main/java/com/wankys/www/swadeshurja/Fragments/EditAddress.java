package com.wankys.www.swadeshurja.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Models.UserSessionManager;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Utils.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import retrofit.RetrofitError;

import static com.wankys.www.swadeshurja.Activity.MainActivity.progressBar;
import static com.wankys.www.swadeshurja.Models.UserSessionManager.Key_UserId;
import static com.wankys.www.swadeshurja.Models.UserSessionManager.PREFER_NAMEUserdata;

public class EditAddress extends Fragment implements View.OnClickListener {
    View view;
    Activity activity;
    int position;
    UserSessionManager userSessionManager;
    String Swarajuser_id;
    Button update_btn;
    EditText name,address,locality,landmark,city,state,pincode,mobile,alternate_mobile,email;
    String s_name,s_address,s_locality,s_landmark,s_city,s_state,s_pincode,s_mobile,s_alternate_mobile,s_email,s_addressid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_editaddress, container, false);
        ButterKnife.bind(this, view);
        activity = (Activity) view.getContext();
        Bundle bundle = getArguments();
        if (bundle!=null)
        {position = bundle.getInt("position");
         s_addressid = bundle.getString("address_id");
         s_name = bundle.getString("name");
         s_address =bundle.getString("address");
         s_locality= bundle.getString("locality");
         s_landmark =  bundle.getString("landmark");
         s_city = bundle.getString("city");
         s_state = bundle.getString("state");
         s_pincode = bundle.getString("pincode");
         s_mobile = bundle.getString("mobile");
         s_alternate_mobile = bundle.getString("alternate_number");
         s_email=bundle.getString("email");
        }
        userSessionManager=new UserSessionManager(getActivity());
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFER_NAMEUserdata, Context.MODE_PRIVATE);
        Swarajuser_id = preferences.getString(Key_UserId, null);
        name= (EditText)view.findViewById(R.id.name_et);
        address = (EditText)view.findViewById(R.id.address_et);
        locality = (EditText)view.findViewById(R.id.locality_et);
        landmark = (EditText)view.findViewById(R.id.landmark_et);
        city = (EditText)view.findViewById(R.id.city_et);
        state = (EditText)view.findViewById(R.id.state_et);
        pincode = (EditText)view.findViewById(R.id.pincode_et);
        mobile = (EditText)view.findViewById(R.id.phone_et);
        alternate_mobile = (EditText)view.findViewById(R.id.alternatephone_et);
        email = view.findViewById(R.id.email_et);
        update_btn=view.findViewById(R.id.update_address_btn);
        name.setText(s_name);
        address.setText(s_address);
        locality.setText(s_locality);
        landmark.setText(s_landmark);
        city.setText(s_city);
        state.setText(s_state);
        pincode.setText(s_pincode);
        mobile.setText(s_mobile);
        alternate_mobile.setText(s_alternate_mobile);
        email.setText(s_email);
        update_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String Name = name.getText().toString();
        String Address = address.getText().toString();
        String Locality = locality.getText().toString();
        String Landmark = landmark.getText().toString();
        String City = city.getText().toString();
        String State = state.getText().toString();
        String Pincode = pincode.getText().toString();
        String Mobile = mobile.getText().toString();
        String Alternate_mobile = alternate_mobile.getText().toString();
        String Email = email.getText().toString();
        if (Name.equalsIgnoreCase(null)) {
            name.requestFocus();
            name.setError("Enter name");
        }
        else if (Address.equalsIgnoreCase(null)) {
            address.requestFocus();
            address.setError("Enter address");
        }
        else if (Locality.equalsIgnoreCase(null)) {
            locality.requestFocus();
            locality.setError("Enter locality");
        }
        else if (Landmark.equalsIgnoreCase(null)) {
            landmark.requestFocus();
            landmark.setError("Enter landmark");
        }
        else if (City.equalsIgnoreCase(null)) {
            city.requestFocus();
            city.setError("Enter city");
        }
        else if (State.equalsIgnoreCase(null)) {
            state.requestFocus();
            state.setError("Enter state");
        }
        else if (Pincode.equalsIgnoreCase(null)) {
            pincode.requestFocus();
            pincode.setError("Enter pincode");
        }
        else if (Mobile.equalsIgnoreCase(null)) {
            mobile.requestFocus();
            mobile.setError("Enter mobile");
        }
        else if (Alternate_mobile.equalsIgnoreCase(null)) {
            alternate_mobile.requestFocus();
            alternate_mobile.setError("Enter alternate mobile");
        }
        else if (Email.equalsIgnoreCase(null)) {
            email.requestFocus();
            email.setError("Enter email");
        }
        else
        {update(s_addressid,Name,Address,Locality,Landmark,City,State,Pincode,Mobile,Alternate_mobile,Email);}

    }

    private void update(String s_addressid, String name, String address, String locality, String landmark, String city, String state, String pincode, String mobile, String alternate_mobile,String email) {
        Api.getClient().updateaddress(s_addressid,Swarajuser_id,name,address,locality,landmark,city,state,pincode,mobile,alternate_mobile,email,
                new retrofit.Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                        BufferedReader reader = null;
                        String output = "";
                        Toast.makeText(getActivity(), "inside success", Toast.LENGTH_SHORT).show();
                        try {
                            System.out.println(response2);
                            System.out.println(response);
                            reader = new BufferedReader(new InputStreamReader(response2.getBody().in()));
                            output = reader.readLine();
                            System.out.println(output);
                            if (output.contains("1")) {
                                progressBar.setVisibility(View.INVISIBLE);
                                ((MainActivity) getActivity()).removeCurrentFragmentAndMoveBack();
                                System.out.println(output);

                            } else if (output.contains("0")) {
                                progressBar.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                                builder.setTitle("Error");
                                builder.setIcon(R.drawable.error);
                                builder.setMessage("Please try updating it later");
                                builder.setCancelable(false);
                                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ((MainActivity) getActivity()).removeCurrentFragmentAndMoveBack();
                                    }
                                });
                                AlertDialog alert=builder.create();
                                alert.show();

                            }

                        } catch (IOException e) {
                            e.getStackTrace();
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        System.out.println(error.toString());
                        Toast.makeText(getActivity(), "inside failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
