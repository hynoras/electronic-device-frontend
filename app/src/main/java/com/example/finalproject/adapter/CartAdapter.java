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

import com.example.finalproject.api.CartApi;
import com.example.finalproject.model.CartDetail;
import com.example.finalproject.R;
import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final Context context;
    private final List<CartDetail> cartDetailList;



    public CartAdapter(Context context, List<CartDetail> cartDetailList) {
        this.context = context;
        this.cartDetailList = cartDetailList;
//        this.cartDetailList = (cartDetailList != null) ? cartDetailList : new ArrayList<>();
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
        holder.currPrice.setText("$" + cartDetail.getCurrPrice());
        holder.currQuan.setText(String.valueOf(cartDetail.getCurrQuan()));
    }

    @Override
    public int getItemCount() {
        return cartDetailList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView prodImg;
        TextView prodName, currPrice, currQuan;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            prodImg = itemView.findViewById(R.id.prodImg);
            prodName = itemView.findViewById(R.id.prodName);
            currPrice = itemView.findViewById(R.id.currPrice);
            currQuan = itemView.findViewById(R.id.currQuan);
        }
    }
}
