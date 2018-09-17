package com.wankys.www.swadeshurja.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Models.UserSessionManager;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Utils.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Elakiya on 5/3/2018.
 */

public class Profile extends Fragment implements View.OnClickListener {
    private static final String PREFER_NAMEUserdata = "StoreUserdata";
    private static final String Key_UserName="uname";
    private static final String Key_Email="email";
    private static final String Key_UserId="userId";
    private static final String Key_Phone="phone";
    private static final String Key_Password = "password";

    private EditText Currpass;
    private EditText Newpass;
    private EditText Confpass;
    private Button btn_change_pass;
    LinearLayout ll_change_password, Changepassword;
    ScrollView ll_profile;
    UserSessionManager sessionManager;
    View view;
    Activity activity;
    TextView name, email, phone;

    String Password, Swarajuser_id, newpass, confirmpass;

    public static Profile newInstance()
    {
        return new Profile();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile, container, false);
        ButterKnife.bind(this, view);
        activity = (Activity) view.getContext();
        sessionManager = new UserSessionManager(getActivity());
        ll_profile = (ScrollView) view.findViewById(R.id.textLayout);
        ll_change_password = (LinearLayout) view. findViewById(R.id.ll_change_pass);
        Currpass = (EditText) view. findViewById(R.id.et_curr_pass);
        Newpass = (EditText) view. findViewById(R.id.et_new_pass);
        Confpass = (EditText) view. findViewById(R.id.et_conf_password);
        Changepassword= (LinearLayout)  view.findViewById(R.id.changepass_id);
        btn_change_pass = (Button) view. findViewById(R.id.btn_change_pass);
        name = (TextView)view. findViewById(R.id.name_id);
        email = (TextView)view. findViewById(R.id.email_id);
        phone = (TextView)view. findViewById(R.id.mobile_id);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUserData();
        Changepassword.setOnClickListener(this);
        btn_change_pass.setOnClickListener(this);
    }
    private void getUserData() {
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFER_NAMEUserdata, Context.MODE_PRIVATE);
        String Name = preferences.getString(Key_UserName, null);
        String Email = preferences.getString(Key_Email, null);
        String Phone = preferences.getString(Key_Phone, null);
        Password = preferences.getString(Key_Password, null);
        Swarajuser_id = preferences.getString(Key_UserId, null);
        name.setText(Name);
        email.setText(Email);
        phone.setText(Phone);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.changepass_id:
                ll_profile.setVisibility(View.GONE);
                ll_change_password.setVisibility(View.VISIBLE);

            case R.id.btn_change_pass:
                newpass = Newpass.getText().toString();
                confirmpass = Confpass.getText().toString();
                if (!Password.equals(Currpass.getText().toString())) {
                    Currpass.setError("Enter your Current Password");
                } else if (Password.equals(Currpass.getText().toString())) {
                    if (!isValidPassword(Newpass.getText().toString())) {
                        Newpass.setError("Paswword should contain atleast 8 characters");
                    } else if (TextUtils.isEmpty(Newpass.getText().toString())) {
                        Newpass.setError("Enter your New Password");
                    } else if (!newpass.equals(confirmpass)) {
                        Confpass.setError("Passwords doesn't match");
                    } else {
                        ChangePassword();
                    }
                }
        }
    }
    public void ChangePassword() {
        Api.getClient().changepassword(newpass, Swarajuser_id, new retrofit.Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                BufferedReader reader = null;
                String output = "";
                System.out.println(Swarajuser_id);
                try {
                    reader = new BufferedReader(new InputStreamReader(response2.getBody().in()));
                    output = reader.readLine();
                    Toast.makeText(getActivity(), output, Toast.LENGTH_SHORT).show();
                    System.out.println(output);
                    if (output.contains("1")) {
                        System.out.println(output);
                        Toast.makeText(getActivity(), output, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                        sessionManager.logoutUser();
                    }
                    else if (output.contains("0")) {
                        Toast.makeText(getActivity(), "Failed to change the password", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean isValidPassword(String pass) {
        return pass != null && pass.length() >= 8;
    }

}
