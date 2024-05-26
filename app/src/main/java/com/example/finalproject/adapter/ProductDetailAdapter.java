package com.example.finalproject.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.activity.ProductDetailActivity;
import com.example.finalproject.activity.LoginActivity;
import com.example.finalproject.activity.CartActivity;
import com.example.finalproject.api.ApiClient;
import com.example.finalproject.api.CartApi;
import com.example.finalproject.model.CartDetail;
import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ProductDetailHolder> {
    private final Context context;
    private final List<Product> productList;
    private final int maxQuantity;
    private CartApi cartApi;

    public ProductDetailAdapter(Context context ,List<Product> productList, int maxQuantity) {
        this.context = context;
        this.productList = productList;
        this.maxQuantity = maxQuantity;
    }

    @NonNull
    @Override
    public ProductDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_detail, parent, false);
        return new ProductDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getProdName());
        holder.price.setText(String.valueOf(product.getProdPrice()));
        holder.description.setText(product.getProdDesc());
        Picasso.get().load(product.getProdImg()).into(holder.image);

        holder.btnBack.setOnClickListener(v -> {
            if (context instanceof ProductDetailActivity) {
                ((ProductDetailActivity) context).finish();
            }
        });

        holder.btnIncrease.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(holder.quantity.getText().toString());
            if (currentQuantity < maxQuantity) {
                holder.quantity.setText(String.valueOf(currentQuantity + 1));
            } else {
                Toast.makeText(context, "Maximum quantity reached", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnDecrease.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(holder.quantity.getText().toString());
            if (currentQuantity > 0) {
                holder.quantity.setText(String.valueOf(currentQuantity - 1));
            } else {
                Toast.makeText(context, "Quantity cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnCart.setOnClickListener(v -> {
            if (isUserLoggedIn()) {
                // User is logged in, redirect to CartActivity
                Intent intent = new Intent(context, CartActivity.class);
                context.startActivity(intent);
            } else {
                // User is not logged in, prompt to login
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });

        holder.btnAddToCart.setOnClickListener(v -> {
            if (isUserLoggedIn()) {
                int cartId = getCartIdFromSession();
                int prodId = product.getProdId();
                int currQuan = Integer.parseInt(holder.quantity.getText().toString());

                addToCart(cartId, prodId, currQuan);
            } else {
                // User is not logged in, prompt to login
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("is_logged_in", false);
    }

    private int getCartIdFromSession() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("cart_id", -1); // Default value -1 if cartId is not found
    }

    private void addToCart(int cartId, int prodId, int currQuan) {
        cartApi = ApiClient.getCartApi();

        CartApi.QuantityRequest quantityRequest = new CartApi.QuantityRequest(currQuan);

        Call<CartDetail> call = cartApi.addToCart(cartId, prodId, quantityRequest);
        call.enqueue(new Callback<CartDetail>() {
            @Override
            public void onResponse(Call<CartDetail> call, Response<CartDetail> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Product added to cart successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to add product to cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartDetail> call, Throwable t) {
                Toast.makeText(context, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class ProductDetailHolder extends RecyclerView.ViewHolder {
        TextView name, price, description;
        ImageView image;
        ImageButton btnBack, btnCart, btnAddToCart;
        Button btnIncrease, btnDecrease;
        EditText quantity;

        public ProductDetailHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.prodImg);
            name = itemView.findViewById(R.id.prodName);
            price = itemView.findViewById(R.id.prodPrice);
            description = itemView.findViewById(R.id.prodDesc);
            btnBack = itemView.findViewById(R.id.btnBack);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            quantity = itemView.findViewById(R.id.prodQuan);
            btnCart = itemView.findViewById(R.id.btnCart);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }


}
