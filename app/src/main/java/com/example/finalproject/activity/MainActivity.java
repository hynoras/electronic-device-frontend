package com.example.finalproject.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.example.finalproject.adapter.ProductAdapter;
import com.example.finalproject.R;
import com.example.finalproject.adapter.ProductDetailAdapter;
import com.example.finalproject.api.ApiClient;
import com.example.finalproject.api.RetrieveAllProductApi;
import com.example.finalproject.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView productList;
    private ProductAdapter productAdapter;
    private RetrieveAllProductApi retrieveAllProductApi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = findViewById(R.id.productList);
        productList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        retrieveAllProductApi = ApiClient.getRetrieveAllProductApi();

        fetchProducts();
        startSignUp();
    }

    public void startSignUp() {
        ImageButton signUpButton = findViewById(R.id.btnUser);
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        });
    }

    private void fetchProducts() {
        Call<List<Product>> call = retrieveAllProductApi.retrieveAllProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();
                    Log.d("Response", "Products: " + products);
                    productAdapter = new ProductAdapter(MainActivity.this, products);
                    productList.setAdapter(productAdapter);
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
