package com.wankys.www.swadeshurja.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.paynimo.android.payment.PaymentActivity;
import com.paynimo.android.payment.PaymentModesActivity;
import com.paynimo.android.payment.model.Checkout;
import com.wankys.www.swadeshurja.R;

public class CheckoutActivity extends Activity {
    private static final String TAG = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant_activity_checkout);
        startpayment();
        }

    private void startpayment() {
        Checkout checkout = new Checkout();
        // setting Merchant fields values
        checkout.setMerchantIdentifier("T1234");  //where T1234 is the MERCHANT CODE, update it with Merchant Code provided by TPSL
        checkout.setTransactionIdentifier("TXN001"); //where TXN001 is the Merchant Transaction Identifier, it should be different for each transaction (alphanumeric value, no special character allowed)
        checkout.setTransactionReference ("ORD0001"); //where ORD0001 is the Merchant Transaction Reference number
        checkout.setTransactionType (PaymentActivity.TRANSACTION_TYPE_SALE); //Transaction Type
        checkout.setTransactionSubType (PaymentActivity.TRANSACTION_SUBTYPE_DEBIT); //Transaction Subtype
        checkout.setTransactionCurrency ("INR"); //Currency Type
        checkout.setTransactionAmount ("1.00"); //Transaction Amount
        checkout.setTransactionDateTime ("27-06-2017"); //Transaction Date
// setting Consumer fields values
        checkout.setConsumerIdentifier (""); //Consumer Identifier, default value "", set this value as application user name if you want Instrument Vaulting, SI on Cards. Consumer ID should be alpha-numeric value with no space
        checkout.setConsumerEmailID ("test@gmail.com"); //Consumer Email ID
        checkout.setConsumerMobileNumber ("7620656789"); //Consumer Mobile Number
        checkout.setConsumerAccountNo ("");//Account Number, default value "". For eMandate, you can set this value here or can be set later in SDK.
        // setting Consumer Cart Item
        checkout.addCartItem("deac456ad","1.00","0.0","0.0", "SMSG2015-01-12345","Mobile","HTC Desire","www.flipkart.com");
        // setting Consumer Cart Item2
        checkout.addCartItem("deac456ad","1.00","0.0","0.0", "SMSG2015-01-1252","Watch","Fastrack","www.flipkart.com");
        checkout.setTransactionAmount ("2.00");
        checkout.setPaymentInstructionAction("Y");
// Amount type -> Fixed - F, Maximum - M
        checkout.setPaymentInstructionType("F");
// Max amount
        checkout.setPaymentInstructionLimit("1000.00");
// Payment Frequency
// DAIL - Daily, Week - Weekly, MNTH - Monthly,QURT - Quarterly, MIAN - Semi annually, YEAR - Yearly, BIMN - Bi-monthly,
        //ADHO - As and when presented
        checkout.setPaymentInstructionFrequency("ADHO");
// Debit start date, format -> 'DD-MM-YYYY'
        checkout.setPaymentInstructionStartDateTime("07-12-2016");
// Debit end date, format -> 'DD-MM-YYYY'
        checkout.setPaymentInstructionEndDateTime("07-12-2036");
        checkout.setTransactionMerchantInitiated("Y");
        checkout.setPaymentInstrumentIdentifier("4111111111111111");
        checkout.setPaymentInstrumentExpiryMonth("02");
        checkout.setPaymentInstrumentExpiryYear("2019");
        checkout.setPaymentInstrumentVerificationCode("123");
        checkout.setPaymentInstrumentHolderName("Sumit Sharma");
        checkout.setTransactionIsRegistration("Y");
        checkout.setTransactionMerchantInitiated("Y");
        checkout.setPaymentInstrumentToken("123234");
        checkout.setPaymentInstrumentVerificationCode("123");
        checkout.setTransactionMerchantInitiated("Y");
        checkout.setPaymentMethodToken("123234");
        Intent authIntent = new Intent(this, PaymentModesActivity.class);
// Checkout Object
        Log.d("Checkout Request Object",
                checkout.getMerchantRequestPayload().toString());authIntent.putExtra(PaymentActivity.ARGUMENT_DATA_CHECKOUT, checkout);
// Public Key
        authIntent.putExtra(PaymentActivity.EXTRA_PUBLIC_KEY, "1234-6666-6789-56");
// Requested Payment Mode
        authIntent.putExtra(PaymentActivity.EXTRA_REQUESTED_PAYMENT_MODE, PaymentActivity.PAYMENT_METHOD_DEFAULT);
        startActivityForResult(authIntent, PaymentActivity.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PaymentActivity.REQUEST_CODE) {
            // Make sure the request was successful
          if (resultCode == PaymentActivity.RESULT_OK) {
            Log.d(TAG, "Result Code :" + RESULT_OK);
            if (data != null) {
            try {
                Checkout checkout_res = (Checkout) data.getSerializableExtra(PaymentActivity.ARGUMENT_DATA_CHECKOUT);
                Log.d("Checkout Response Obj", checkout_res.getMerchantResponsePayload().toString());
                String transactionType = checkout_res.getMerchantRequestPayload().getTransaction().getType();
                String transactionSubType = checkout_res.getMerchantRequestPayload().getTransaction().getSubType();
                if (transactionType != null && transactionType.equalsIgnoreCase(PaymentActivity.TRANSACTION_TYPE_PREAUTH)
                     && transactionSubType != null && transactionSubType.equalsIgnoreCase(PaymentActivity.TRANSACTION_SUBTYPE_RESERVE)){
                    // Transaction Completed and Got SUCCESS
                    if (checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getStatusCode().equalsIgnoreCase(PaymentActivity.TRANSACTION_STATUS_PREAUTH_RESERVE_SUCCESS)) {
                       Toast.makeText(getApplicationContext(), "Transaction Status - Success", Toast.LENGTH_SHORT).show();
                       Log.v("TRANSACTION STATUS=>", "SUCCESS");

                                /**
                                 * TRANSACTION STATUS - SUCCESS (status code
                                 * 0200 means success), NOW MERCHANT CAN PERFORM
                                 * ANY OPERATION OVER SUCCESS RESULT
                                 */

                     if (checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getInstruction().getStatusCode().equalsIgnoreCase("")) {
                                    /**
                                     * SI TRANSACTION STATUS - SUCCESS (status
                                     * code 0200 means success)
                                     */
                      Log.v("TRANSACTION SI STATUS=>", "SI Transaction Not Initiated");
                                }

                            } // Transaction Completed and Got FAILURE

                    else {// some error from bank side
                        Log.v("TRANSACTION STATUS=>", "FAILURE");
                        Toast.makeText(getApplicationContext(), "Transaction Status - Failure", Toast.LENGTH_SHORT).show();
                        }
                } else {
                    // Transaction Completed and Got SUCCESS
                    if (checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getStatusCode().equalsIgnoreCase(PaymentActivity.TRANSACTION_STATUS_SALES_DEBIT_SUCCESS)) {
                      Toast.makeText(getApplicationContext(), "Transaction Status - Success", Toast.LENGTH_SHORT).show();
                       Log.v("TRANSACTION STATUS=>", "SUCCESS");
                                  /**
                                 * TRANSACTION STATUS - SUCCESS (status code
                                 * 0300 means success), NOW MERCHANT CAN PERFORM
                                 * ANY OPERATION OVER SUCCESS RESULT
                                 */

                     if (checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getInstruction().getStatusCode().equalsIgnoreCase("")) {
                                    /**
                                     * SI TRANSACTION STATUS - SUCCESS (status
                                     * code 0300 means success)
                                     */
                       Log.v("TRANSACTION SI STATUS=>", "SI Transaction Not Initiated");

                     }
                     else if (checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getInstruction().getStatusCode().equalsIgnoreCase(PaymentActivity.TRANSACTION_STATUS_SALES_DEBIT_SUCCESS)) {
                                      /**
                                     * SI TRANSACTION STATUS - SUCCESS (status
                                     * code 0300 means success)
                                     */Log.v("TRANSACTION SI STATUS=>", "SUCCESS");
                } else {/*** SI TRANSACTION STATUS - Failure (status* code OTHER THAN 0300 means failure)*/
                   Log.v("TRANSACTION SI STATUS=>", "FAILURE");
                   }
            } // Transaction Completed and Got FAILURE
            else {
                  // some error from bank side
                  Log.v("TRANSACTION STATUS=>", "FAILURE");Toast.makeText(getApplicationContext(), "Transaction Status - Failure", Toast.LENGTH_SHORT).show();
                  }

             }
    String result = "StatusCode : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getStatusCode()
            + "\nStatusMessage : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getStatusMessage()
            + "\nErrorMessage : "+ checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getErrorMessage()
            + "\nAmount : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getAmount()
            + "\nDateTime : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getDateTime()
            + "\nMerchantTransactionIdentifier : " + checkout_res.getMerchantResponsePayload().getMerchantTransactionIdentifier()
            + "\nIdentifier : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getIdentifier()
            + "\nBankSelectionCode : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getBankSelectionCode()
            + "\nBankReferenceIdentifier : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getBankReferenceIdentifier()
            + "\nRefundIdentifier : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getRefundIdentifier()
            + "\nBalanceAmount : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getBalanceAmount()
            + "\nInstrumentAliasName : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getInstrumentAliasName()
            + "\nSI Mandate Id : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getInstruction().getId()
            + "\nSI Mandate Status : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getInstruction().getStatusCode()
            + "\nSI Mandate Error Code : " + checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getInstruction().getErrorcode();
        System.out.println(result);
    } catch (Exception e) { e.printStackTrace(); }

   }
   }
            else if (resultCode == PaymentActivity.RESULT_ERROR) {
                Log.d(TAG, "got an error");
                if (data.hasExtra(PaymentActivity.RETURN_ERROR_CODE) && data.hasExtra(PaymentActivity.RETURN_ERROR_DESCRIPTION)) {
                    String error_code = (String) data.getStringExtra(PaymentActivity.RETURN_ERROR_CODE);
                    String error_desc = (String) data.getStringExtra(PaymentActivity.RETURN_ERROR_DESCRIPTION);
                    Toast.makeText(getApplicationContext(), " Got error :" + error_code + "--- " + error_desc, Toast.LENGTH_SHORT).show();
                    Log.d(TAG + " Code=>", error_code);
                    Log.d(TAG + " Desc=>", error_desc);

                }
            }
            else if (resultCode == PaymentActivity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Transaction Aborted by User", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "User pressed back button");

            }
        }
    }


}
