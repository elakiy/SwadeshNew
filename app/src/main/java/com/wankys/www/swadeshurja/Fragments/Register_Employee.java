package com.wankys.www.swadeshurja.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Activity.Login;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Utils.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.RetrofitError;

/**
 * Created by Elakiya on 5/4/2018.
 */

public class Register_Employee extends Fragment {
    EditText employee_id,name,phone,email,password;
    TextView signup_id;
    AppCompatButton buttonConfirm;
    EditText editTextConfirmOtp;
    String Employee_id,Pancard_num,Name,Email,Phone,Password;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_employee,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        employee_id=getView().findViewById(R.id.employee_id);
        name= getView().findViewById(R.id.name_id);
        phone=getView().findViewById(R.id.phone_id);
        email=getView().findViewById(R.id.email_id);
        password=getView().findViewById(R.id.password_id);

        signup_id=getView().findViewById(R.id.signup_id);

        signup_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {
         Pancard_num=null;
         Employee_id = employee_id.getText().toString();
         Name = name.getText().toString();
         Phone = phone.getText().toString();
         Email = email.getText().toString();
         Password = password.getText().toString();

       if(Employee_id.isEmpty()) {employee_id.requestFocus(); employee_id.setError("Enter Employee Id");}
       else if(Name.isEmpty()){name.requestFocus();name.setError("Enter Name");}
       else if(Phone.isEmpty()){phone.requestFocus();phone.setError("Enter Phone");}
       else if(Email.isEmpty()){email.requestFocus();email.setError("Enter Email Id");}
       else if(Password.isEmpty()){password.requestFocus();password.setError("Enter Password");}
       else { OTP_generation(Employee_id,Pancard_num,Name,Email,Phone,Password);}
    }

    private void OTP_generation(final String Employee_id, final String Pancard_num, final String Name, final String Email, final String Phone, final String Password){
        Api.getClient().otpgeneration(Employee_id,Pancard_num,Name,Email,Phone,Password,
            new retrofit.Callback<retrofit.client.Response>()
            {
             @Override
             public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                 BufferedReader reader = null;
                 String output = "";
                 try {
                     reader = new BufferedReader(new InputStreamReader(response2.getBody().in()));
                     output = reader.readLine();
                     Toast.makeText(getActivity(), output, Toast.LENGTH_SHORT).show();
                     System.out.println(output);
                     System.out.println(response.getStatus());
                     if (output.contains("1"))
                     {
                         Toast.makeText(getActivity(), output, Toast.LENGTH_SHORT).show();
                         Toast.makeText(getActivity(), "Successfully sent otp", Toast.LENGTH_SHORT).show();
                         register();
                     }
                     else if (output.contains("0"))
                     {
                         Toast.makeText(getActivity(), "User Already Exists", Toast.LENGTH_SHORT).show();
                     }
                     else if(output.contains("2"))
                     {
                         Toast.makeText(getActivity(), "Resend Otp", Toast.LENGTH_SHORT).show();
                         register();
                     }
                 } catch (IOException e) {
                     e.getStackTrace();
                 }
             }
                @Override
                public void failure(RetrofitError error) {
                    System.out.println(error.toString());
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
    }
    private void register() {
        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(getActivity());
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm, null);
        //Initizliaing confirm button fo dialog box and edittext of dialog box
        buttonConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.buttonConfirm);
        editTextConfirmOtp = (EditText) confirmDialog.findViewById(R.id.editTextOtp);
        final String otp = editTextConfirmOtp.getText().toString();
        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //Hiding the alert dialog
             alertDialog.dismiss();
             final ProgressDialog loading = ProgressDialog.show(getActivity(),"Authenticating","Please wait while we check the entered code",false,false);
             System.out.println(Employee_id+"\n"+Pancard_num+"\n"+Name+"\n"+ Email+"\n"+Phone+"\n"+Password);
             Api.getClient().registration(Employee_id,Pancard_num,Name,Email,Phone,Password,otp,
               new retrofit.Callback<retrofit.client.Response>() {
                   @Override public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                       BufferedReader reader = null;
                       String output = "";
                       try {
                           loading.dismiss();
                           reader = new BufferedReader(new InputStreamReader(response2.getBody().in()));
                           output = reader.readLine();
                           Toast.makeText(getActivity(), output, Toast.LENGTH_SHORT).show();
                           System.out.println(output);
                           System.out.println(response.getStatus());
                           if (output.contains("1"))
                           {
                               Toast.makeText(getActivity(), "Registration is Successfull", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getActivity(), Login.class);
                               startActivity(intent);
                           }
                           else if (output.contains("0")) {
                               Toast.makeText(getActivity(), "User Already Exists", Toast.LENGTH_SHORT).show();
                           }
                           else if(output.contains("2"))
                           {
                               Toast.makeText(getActivity(), "Otp Updated", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getActivity(), Login.class);
                               startActivity(intent);
                           }
                       } catch (IOException e) {
                           e.getStackTrace();
                       }
                   }
                   @Override
                   public void failure(RetrofitError error) {
                       loading.dismiss();
                       System.out.println(error.toString());
                       Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                   }
               });
            }
        });
    }
}

