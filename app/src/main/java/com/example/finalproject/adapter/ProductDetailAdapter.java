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

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ProductDetailHolder> {
    private Context context;
    private List<Product> productList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int productId);
    }

    public ProductDetailAdapter(OnItemClickListener listener, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
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

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(product.getProdId()); // Pass prodId to the listener
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

        public ProductDetailHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.prodImg);
            name = itemView.findViewById(R.id.prodName);
            price = itemView.findViewById(R.id.prodPrice);
            description = itemView.findViewById(R.id.prodDesc);
        }
    }
}
