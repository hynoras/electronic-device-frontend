package com.example.finalproject.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.adapter.ProductDetailAdapter;
import com.example.finalproject.api.ApiClient;
import com.example.finalproject.api.CartApi;
import com.example.finalproject.api.ProductApi;
import com.example.finalproject.model.CartDetail;
import com.example.finalproject.R;
import com.example.finalproject.adapter.CartAdapter;
import com.example.finalproject.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private RecyclerView cartDetail;
    private CartAdapter cartAdapter;
    private CartApi cartApi;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartDetail = findViewById(R.id.itemView);
        cartDetail.setLayoutManager(new LinearLayoutManager(this));
        cartApi = ApiClient.getCartApi();

        ImageButton btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(v -> {
            startActivity(this, CheckoutActivity.class);
        });

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            backToPreviousActivity(this);
        });

        int cartId = getCartIdFromSession();
        fetchCartDetail(cartId);

    }

    public void startActivity(Context context, Class<?> newActivity) {
        Intent Intent = new Intent(context, newActivity);
        startActivity(Intent);
    }

    public void backToPreviousActivity(Context context) {
        if (context instanceof ProductDetailActivity) {
            ((ProductDetailActivity) context).finish();
        }
    }

    private void fetchCartDetail(int cartId) {
        Call<CartApi.CartResponse> call = cartApi.retrieveCartInfoById(cartId);
        call.enqueue(new Callback<CartApi.CartResponse>() {
            @Override
            public void onResponse(Call<CartApi.CartResponse> call, Response<CartApi.CartResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CartDetail> cart = response.body().getCartDetails();
                    Log.d("Response", "Products: " + cart);
                    cartAdapter = new CartAdapter(CartActivity.this, cart);
                    cartDetail.setAdapter(cartAdapter);
                } else {
                    Toast.makeText(CartActivity.this, "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartApi.CartResponse> call, Throwable t) {
                Log.d("Response", "Code: " + t.getMessage());
                Toast.makeText(CartActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getCartIdFromSession() {
        SharedPreferences sharedPreferences = CartActivity.this.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("cart_id", -1); // Default value -1 if cartId is not found
    }


}
