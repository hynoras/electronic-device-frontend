package com.example.finalproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.model.CartDetail;
import com.example.finalproject.R;

import java.time.Instant;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final Context context;
    private final List<CartDetail> cartDetailList;
    private final OnItemClickListener onItemClickListener;
    private Instant Glide;

    public CartAdapter(Context context, List<CartDetail> cartDetailList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.cartDetailList = cartDetailList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartDetail cartDetail = cartDetailList.get(position);
        holder.itemPrice.setText("$" + cartDetail.getCurrPrice());
        holder.itemQuantity.setText(String.valueOf(cartDetail.getCurrQuan()));

        holder.buttonIncrement.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onIncrementClick(cartDetail, position);
            }
        });

        holder.buttonDecrement.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDecrementClick(cartDetail, position);
            }
        });

        holder.buttonDelete.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDeleteClick(cartDetail, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartDetailList.size();
    }

    public interface OnItemClickListener {
        void onIncrementClick(CartDetail cartDetail, int position);
        void onDecrementClick(CartDetail cartDetail, int position);
        void onDeleteClick(CartDetail cartDetail, int position);
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemPrice;
        TextView itemQuantity;
        Button buttonIncrement;
        Button buttonDecrement;
        ImageButton buttonDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            buttonIncrement = itemView.findViewById(R.id.button_increment);
            buttonDecrement = itemView.findViewById(R.id.button_decrement);
            buttonDelete = itemView.findViewById(R.id.button_delete);
        }
    }
}
