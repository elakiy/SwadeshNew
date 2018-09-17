package com.wankys.www.swadeshurja.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Models.UserSessionManager;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Utils.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Elakiya on 4/24/2018.
 */

public class Login extends Activity {
    private UserSessionManager sessionManager;
    private EditText emailorphone;
    private EditText password;
    private TextView login_btn;
    private TextView signup;
    private TextView forgotpass;
    private String Email;
    private String Password;
    Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new UserSessionManager(this);
        if(sessionManager.isUserLoggedIn()) {
            // Starting Test1
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
//        final ConstraintLayout root = findViewById(R.id.main_id);
//        root.post(new Runnable() {
//                      @Override
//                      public void run() {
//                          Blurry.with(Login.this)
//                                  .radius(1)
//                                  .sampling(2)
//                                  .async()
//                                  .animate(500)
//                                  .onto((ViewGroup) root);
//                      }
//                  });
        emailorphone = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);

        login_btn = findViewById(R.id.loginbtn_id);
        signup = findViewById(R.id.signupnow_id);

        forgotpass = findViewById(R.id.forgot_password_id);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = emailorphone.getText().toString();
                Password = password.getText().toString();
                if (Email.isEmpty()) {
                    emailorphone.requestFocus();
                    emailorphone.setError("Enter Email or Phone");
                } else if (Password.isEmpty()) {
                    password.requestFocus();
                    password.setError("Enter Password");
                } else {
                    login();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
       Api.getClient().login(Email, Password, new retrofit.Callback<Response>() {
                   @Override
                   public void success(Response response, Response response2) {
                      // System.out.println(response.getBody());
                       BufferedReader reader = null;
                       String output = "";
                       try {
                           reader = new BufferedReader(new InputStreamReader(response2.getBody().in()));
                           output = reader.readLine();
                           Toast.makeText(Login.this, output, Toast.LENGTH_SHORT).show();
                           //System.out.println(output);
                           JSONObject jsonObj = new JSONObject(output.toString());
                           // System.out.println(jsonObj.getJSONObject("response"));
                           JSONObject resp = jsonObj.getJSONObject("response");
                           //System.out.println(resp.getString("swa_user_unique_id"));
                           if (output.contains("1")) {
                               String u_id = resp.getString("swa_user_unique_id");
                               String u_name = resp.getString("swa_user_name");
                               String email = resp.getString("swa_email");
                               String mobile = resp.getString("swa_email");
                               String password = resp.getString("swa_password");
                               Toast.makeText(Login.this, "Login success", Toast.LENGTH_SHORT).show();
                               sessionManager.createUserLoginSession(u_id,u_name,email,mobile,password);

                               Intent i = new Intent(getApplicationContext(), MainActivity.class);
                               i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                               // Add new Flag to start new Activity
                               i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(i);
                               finish();
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
                       Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                   }
               });
    }
}
