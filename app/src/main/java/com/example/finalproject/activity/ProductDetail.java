package com.example.finalproject.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.ProductAdapter;
import com.example.finalproject.adapter.ProductDetailAdapter;
import com.example.finalproject.api.ApiClient;
import com.example.finalproject.api.RetrieveAllProductApi;
import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail extends AppCompatActivity {

    private RecyclerView productDetail;
    private ProductDetailAdapter productDetailAdapter;
    private RetrieveAllProductApi retrieveAllProductApi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);

        productDetail = findViewById(R.id.productView);
        productDetail.setLayoutManager(new LinearLayoutManager(this));
        retrieveAllProductApi = ApiClient.getRetrieveAllProductApi();
        int productId = getIntent().getIntExtra("productId", -1);
        if (productId != -1) {
            fetchProductDetails(productId);
        } else {
            Toast.makeText(this, "Invalid product ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchProductDetails(int productId) {
        Call<Product> call = retrieveAllProductApi.getProductById(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body();
                    Log.d("Response", "Products: " + product);
                    int maxQuantity = product.getProdQuan();
                    productDetailAdapter = new ProductDetailAdapter(ProductDetail.this, Collections.singletonList(product), maxQuantity);
                    productDetail.setAdapter(productDetailAdapter);
                } else {
                    Toast.makeText(ProductDetail.this, "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetail.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

