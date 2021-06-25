package com.example.razorpayintegrationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button btn;
    TextView view;
    CoordinatorLayout layout;
    private static final String KEY_ID="rzp_test_29Ijx9eZiehu0k";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view=findViewById(R.id.textView);
    //   checkout.setKeyID("rzp_test_29Ijx9eZiehu0k");
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        Checkout.preload(getApplicationContext());



    }

    public void startPayment()
    {
    /**   * Instantiate Checkout   */
    Checkout checkout = new Checkout();
        checkout.setKeyID(KEY_ID);
    /**   * Set your logo here   */	checkout.setImage(R.drawable.logo);
      final Activity activity = this;
    /**   * Pass your payment options to the Razorpay Checkout as a JSONObject   */
    try {
        JSONObject options = new JSONObject();
        options.put("name", "Anish kumar");
        options.put("description", "Reference No. #123456");
        options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
       // options.put("order_id", "order_DBJOWzybf0sJbb");
        //from response of step 3.
         options.put("theme.color", "#3399cc");
         options.put("currency", "INR");
         options.put("amount", "50000");
        // pass amount in currency subunits
         options.put("prefill.email", "anishkumar2096@gmail.com");
        options.put("prefill.contact","7903971548");
        //
        JSONObject retryObj = new JSONObject();
        retryObj.put("enabled", true);
         retryObj.put("max_count", 4);
        options.put("retry", retryObj);
        checkout.open(activity, options);
        } catch(Exception e)
    {
        Log.e("Main", "Error in starting Razorpay Checkout", e);	}}

    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(this,"payment done successfully",Toast.LENGTH_LONG).show();
        view.setText("payment done successfully");

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"payment error"+s.toString(),Toast.LENGTH_LONG).show();
        view.setText(s);
    }
}