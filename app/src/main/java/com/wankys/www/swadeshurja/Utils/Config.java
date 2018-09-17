package com.wankys.www.swadeshurja.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Activity.Login;
import com.wankys.www.swadeshurja.Activity.MainActivity;
import com.wankys.www.swadeshurja.Activity.Register;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Response.ShoppicartResponse;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;

public class Config {
    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    // id to handle the notification in the notification tray
    public static final String SHARED_PREF = "ah_firebase";

    public static void moveTo(Context context, Class targetClass) {
        Intent intent = new Intent(context, targetClass);
        context.startActivity(intent);
    }
    public static boolean validateEmail(EditText editText, Context context) {
        String email = editText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            editText.setError(context.getString(R.string.err_msg_email));
            editText.requestFocus();
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static void showCustomAlertDialog(Context context, String title, String msg, int type) {
        SweetAlertDialog alertDialog = new SweetAlertDialog(context, type);
        alertDialog.setTitleText(title);

        if (msg.length() > 0)
            alertDialog.setContentText(msg);
        alertDialog.show();
        Button btn = (Button) alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    public static void showLoginCustomAlertDialog(final Context context, String title, String msg, int type) {
        SweetAlertDialog alertDialog = new SweetAlertDialog(context, type);
        alertDialog.setTitleText(title);
        alertDialog.setCancelText("Login");
        alertDialog.setCancelClickListener( new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Config.moveTo(context, Login.class);

            }
        });
        alertDialog.setConfirmText("Signup");
        alertDialog.setConfirmClickListener( new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Config.moveTo(context, Register.class);

            }
        });
        if (msg.length() > 0)
            alertDialog.setContentText(msg);
        alertDialog.show();
        Button btn = (Button) alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        Button btn1 = (Button) alertDialog.findViewById(R.id.cancel_button);
        btn1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

    }

    public static void getCartList(final Context context, final boolean b) {
        if (b)
       MainActivity.progressBar.setVisibility(View.VISIBLE);
        MainActivity.cartCount.setVisibility(View.GONE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ShoppicartResponse> call = apiInterface.getShoppingCartItems(MainActivity.userId);
        call.enqueue(new retrofit2.Callback<ShoppicartResponse>() {
            @Override
            public void onResponse(Call<ShoppicartResponse> call, retrofit2.Response<ShoppicartResponse> response) {
                MainActivity.progressBar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        ShoppicartResponse listResponse = response.body();
                        if (listResponse.getShoppingcarts().size() <= 0) {
                            MainActivity.cartCount.setVisibility(View.GONE);
                            Toast.makeText(context, "listResponse.getShoppingcarts().size()", Toast.LENGTH_SHORT).show();
                            System.out.println(listResponse.getShoppingcarts().size());
                        }else {
                            MainActivity.cartCount.setText(listResponse.getShoppingcarts().size() + "");
                            if (!b) {
                                Log.d("equals", "equals");
                                MainActivity.cartCount.setVisibility(View.GONE);
                            } else {
                                MainActivity.cartCount.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }catch (Exception e) {
                    MainActivity.cartCount.setVisibility(View.GONE);
                    }
            }

            @Override
            public void onFailure(Call<ShoppicartResponse> call, Throwable t) {
                MainActivity.progressBar.setVisibility(View.GONE);
            }
        });
    }

//    public static void addOrder(final Context context, String transactionId, String paymentMode) {
//        final ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Please Wait");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//
//        Api.getClient().addOrder(Test1.userId,
//                MyCartList.cartistResponseData.getCartid(),
//                ChoosePaymentMethod.address,
//                ChoosePaymentMethod.mobileNo,
//                transactionId,
//                "succeeded",
//                CartListAdapter.totalAmountPayable,
//                paymentMode,
//                new Callback<SignUpResponse>() {
//                    @Override
//                    public void success(SignUpResponse signUpResponse, Response response) {
//                        progressDialog.dismiss();
//                        Intent intent = new Intent(context, OrderConfirmed.class);
//                        context.startActivity(intent);
//                        ((Activity) context).finishAffinity();
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        progressDialog.dismiss();
//                        ((Activity) context).finish();
//                    }
//                });
//    }
}