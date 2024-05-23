package com.example.finalproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.activity.ProductDetail;
import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ProductDetailHolder> {
    private Context context;
    private List<Product> productList;
    private int maxQuantity;

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
            if (context instanceof ProductDetail) {
                ((ProductDetail) context).finish();
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
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductDetailHolder extends RecyclerView.ViewHolder {
        TextView name, price, description;
        ImageView image;
        ImageButton btnBack;
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
        }
    }
}
