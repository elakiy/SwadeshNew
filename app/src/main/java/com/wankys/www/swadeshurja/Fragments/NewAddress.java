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
import android.widget.EditText;
import android.widget.TextView;
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

/**
 * Created by Wankys on 8/6/2018.
 */

public class NewAddress extends Fragment implements View.OnClickListener {
    UserSessionManager userSessionManager;
    String Swarajuser_id;
    EditText name,address,locality,landmark,city,state,pincode,mobile,alternate_mobile,email;
    String s_name,s_address,s_locality,s_landmark,s_city,s_state,s_pincode,s_mobile,s_alternate_mobile,s_email;
    TextView add;
    View view;
    Activity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_address, container, false);
        ButterKnife.bind(this, view);
        activity = (Activity) view.getContext();
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
        email = (EditText)view.findViewById(R.id.email_et);
        add= view.findViewById(R.id.add_address_btn);
        add.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        s_name = name.getText().toString();
        s_address = address.getText().toString();
        s_locality = locality.getText().toString();
        s_landmark = landmark.getText().toString();
        s_city = city.getText().toString();
        s_state = state.getText().toString();
        s_pincode=pincode.getText().toString();
        s_mobile = mobile.getText().toString();
        s_alternate_mobile = alternate_mobile.getText().toString();
        s_email = email.getText().toString();
        if (s_name==null){name.requestFocus();name.setError("Enter Name");}
        else  if (s_address==null){address.requestFocus();address.setError("Enter Address");}
        else  if (s_locality==null){locality.requestFocus();locality.setError("Enter Address");}
        else  if (s_landmark==null){landmark.requestFocus();landmark.setError("Enter landmark");}
        else  if (s_city==null){city.requestFocus();city.setError("Enter city");}
        else  if (s_state==null){state.requestFocus();state.setError("Enter state");}
        else  if (s_pincode==null){pincode.requestFocus();pincode.setError("Enter pincode");}
        else  if (s_mobile==null){mobile.requestFocus();mobile.setError("Enter mobile");}
        else  if (s_alternate_mobile==null){alternate_mobile.requestFocus();alternate_mobile.setError("Enter alternate mobile number");}
        else  if (s_email==null){email.requestFocus();email.setError("Enter email");}
        else
        { addaddress(Swarajuser_id,s_name,s_address,s_locality,s_landmark,s_city,s_state,s_pincode,s_mobile,s_alternate_mobile,s_email);
        }
    }

    private void addaddress(String swarajuser_id, String s_name, String s_address, String s_locality, String s_landmark, String s_city, String s_state, String s_pincode, String s_mobile, String s_alternate_mobile, String s_email) {
        Api.getClient().addaddress(Swarajuser_id,s_name,s_address,s_locality,s_landmark,s_city,s_state,s_pincode,s_mobile,s_alternate_mobile,s_email,
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
                                    builder.setMessage("Please try adding it later");
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
