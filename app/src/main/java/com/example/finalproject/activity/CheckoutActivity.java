package com.example.finalproject.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.activity.MainActivity;
import com.example.finalproject.R;
import com.example.finalproject.api.ApiClient;
import com.example.finalproject.api.OrderApi;
import com.example.finalproject.model.Order;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    private EditText custNameEditText;
    private EditText custPhoneNoEditText;
    private EditText shippingMethodEditText;
    private EditText shippingAddrEditText;
    private Button btnPlaceOrder;
    private OrderApi orderApi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proceed_to_checkout);

        custNameEditText = findViewById(R.id.custName);
        custPhoneNoEditText = findViewById(R.id.custPhoneNo);
        shippingMethodEditText = findViewById(R.id.shippingMethod);
        shippingAddrEditText = findViewById(R.id.shippingAddr);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        orderApi = ApiClient.getOrderApi();

        btnPlaceOrder.setOnClickListener( v -> {
                generateOrder();
        });

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            backToPreviousActivity(this);
        });
    }

    private int getCartIdFromSession() {
        SharedPreferences sharedPreferences = CheckoutActivity.this.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("cart_id", -1); // Default value -1 if cartId is not found
    }

    private int getUserIdFromSession() {
        SharedPreferences sharedPreferences = CheckoutActivity.this.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("user_id", -1);
    }

    public void backToPreviousActivity(Context context) {
        if (context instanceof ProductDetailActivity) {
            ((ProductDetailActivity) context).finish();
        }
    }

    public void startActivity(Context context, Class<?> newActivity) {
        Intent Intent = new Intent(context, newActivity);
        startActivity(Intent);
    }

    private void generateOrder() {
        String custName = custNameEditText.getText().toString();
        String custPhoneNo = custPhoneNoEditText.getText().toString();
        String shippingMethod = shippingMethodEditText.getText().toString();
        String shippingAddr = shippingAddrEditText.getText().toString();
        int userId = getUserIdFromSession();
        int cartId = getCartIdFromSession();

        if (custName.isEmpty() || custPhoneNo.isEmpty() || shippingMethod.isEmpty() || shippingAddr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setCustName(custName);
        order.setCustPhoneNo(custPhoneNo);
        order.setShippingMethod(shippingMethod);
        order.setShippingAddr(shippingAddr);

        Call<ResponseBody> call = orderApi.generateOrder(cartId, order);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CheckoutActivity.this, "Order created successfully", Toast.LENGTH_SHORT).show();
                    startActivity(CheckoutActivity.this, MainActivity.class);
                } else {
                    Toast.makeText(CheckoutActivity.this, "Failed to create order", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("CheckoutActivity", "Error creating order", t);
                Toast.makeText(CheckoutActivity.this, "Error creating order", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
