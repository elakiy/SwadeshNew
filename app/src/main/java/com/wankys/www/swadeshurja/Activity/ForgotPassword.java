package com.wankys.www.swadeshurja.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Models.UserSessionManager;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Utils.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Elakiya on 5/11/2018.
 */

public class ForgotPassword extends Activity {
    private EditText email_id;
    private Button submit;
    private String Email;
    private UserSessionManager sessionManager;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        sessionManager = new UserSessionManager(this);
        email_id=findViewById(R.id.email_id);
        submit = findViewById(R.id.loginbtn_id);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = email_id.getText().toString();
                forgotpassword(Email);
            }
        });
    }
    private void forgotpassword(String email) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait while sending you mail.....");
        progressDialog.show();
        Api.getClient().forgotpassword(email,
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        BufferedReader reader = null;
                        String output = "";
                        try {
                            reader = new BufferedReader(new InputStreamReader(response2.getBody().in()));
                            output = reader.readLine();
                            //Toast.makeText(ForgotPassword.this, output, Toast.LENGTH_SHORT).show();
                            //System.out.println(output);
                            JSONObject jsonObj = new JSONObject(output.toString());
                            // System.out.println(jsonObj.getJSONObject("response"));
                            JSONObject resp = jsonObj.getJSONObject("response");
                            //System.out.println(resp.getString("swa_user_unique_id"));
                            if (output.contains("1")) {
                                progressDialog.dismiss();
                                AlertDialog.Builder builder=new AlertDialog.Builder(ForgotPassword.this);
                                builder.setTitle("Sent");
                                builder.setIcon(R.drawable.check);
                                builder.setMessage("Check your mail to login with new password");
                                builder.setCancelable(false);
                                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(ForgotPassword.this, Login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                });
                                AlertDialog alert=builder.create();
                                alert.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        System.out.println(error.toString());
                        Toast.makeText(ForgotPassword.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}
