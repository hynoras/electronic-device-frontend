package com.example.finalproject.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.CartItem.CartItem;
import com.example.finalproject.R;
import com.example.finalproject.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a list of CartItems (sample data)
        cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem("Product 1", "https://via.placeholder.com/150", 198.00, 1));
        cartItemList.add(new CartItem("Product 2", "https://via.placeholder.com/150", 245.00, 1));
        cartItemList.add(new CartItem("Product 3", "https://via.placeholder.com/150", 40.00, 1));

        // Initialize the adapter with the cart item list and set it to the RecyclerView
        cartAdapter = new CartAdapter(this, cartItemList, new CartAdapter.OnItemClickListener() {
            @Override
            public void onIncrementClick(CartItem cartItem, int position) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartAdapter.notifyItemChanged(position);
            }

            @Override
            public void onDecrementClick(CartItem cartItem, int position) {
                if (cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    cartAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onDeleteClick(CartItem cartItem, int position) {
                cartItemList.remove(position);
                cartAdapter.notifyItemRemoved(position);
                cartAdapter.notifyItemRangeChanged(position, cartItemList.size());
            }
        });

        recyclerView.setAdapter(cartAdapter);
    }
}
