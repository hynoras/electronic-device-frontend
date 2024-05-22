package com.example.finalproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.activity.ProductDetail;
import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductImageHolder> {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);
        return new ProductImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductImageHolder holder, int position) {
        Product product = productList.get(position);
        Picasso.get().load(product.getProdImg()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductImageHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ProductImageHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImage);
        }
    }
}


