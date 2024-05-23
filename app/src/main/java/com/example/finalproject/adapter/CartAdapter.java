package com.example.finalproject.adapter;

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

import com.bumptech.glide.Glide;
import com.example.finalproject.CartItem.CartItem;
import com.example.finalproject.R;

import java.time.Instant;
import java.time.temporal.TemporalAdjuster;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItemList;
    private OnItemClickListener onItemClickListener;
    private Instant Glide;

    public CartAdapter(Context context, List<CartItem> cartItemList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.cartItemList = cartItemList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.itemName.setText(cartItem.getName());
        holder.itemPrice.setText("$" + cartItem.getPrice());
        holder.itemQuantity.setText(String.valueOf(cartItem.getQuantity()));

        Glide.with((TemporalAdjuster) context).clone(cartItem.getImageUrl()).into(holder.itemImage);

        holder.buttonIncrement.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onIncrementClick(cartItem, position);
            }
        });

        holder.buttonDecrement.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDecrementClick(cartItem, position);
            }
        });

        holder.buttonDelete.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDeleteClick(cartItem, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public interface OnItemClickListener {
        void onIncrementClick(CartItem cartItem, int position);
        void onDecrementClick(CartItem cartItem, int position);
        void onDeleteClick(CartItem cartItem, int position);
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
