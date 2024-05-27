package com.example.finalproject.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.finalproject.adapter.ProductImageAdapter;
import com.example.finalproject.R;
import com.example.finalproject.api.ApiClient;
import com.example.finalproject.api.ProductApi;
import com.example.finalproject.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView productList;
    private ProductImageAdapter productImageAdapter;
    private ProductApi productApi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = findViewById(R.id.productList);
        productList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        productApi = ApiClient.getRetrieveAllProductApi();

        fetchProducts();
        startSignUp();

        ImageButton cartButton = findViewById(R.id.btnCart);
        cartButton.setOnClickListener(v -> {
            if (isUserLoggedIn()) {
                startCartActivity();
            } else {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void startCartActivity() {
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        startActivity(intent);
    }

    public void startSignUp() {
        ImageButton sighUpButton = findViewById(R.id.btnUser);
        sighUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("is_logged_in", false);
    }

    private void fetchProducts() {
        Call<List<Product>> call = productApi.retrieveAllProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();
                    Log.d("Response", "Products: " + products);
                    productImageAdapter = new ProductImageAdapter(MainActivity.this, products);
                    productList.setAdapter(productImageAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
