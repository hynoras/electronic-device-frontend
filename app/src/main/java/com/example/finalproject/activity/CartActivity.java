package com.example.finalproject.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.model.CartDetail;
import com.example.finalproject.R;
import com.example.finalproject.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartDetail> cartDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a list of CartItems (sample data)
        cartDetailList = new ArrayList<>();
//        cartDetailList.add(new CartDetail("Product 1", "https://via.placeholder.com/150", 198.00, 1));
//        cartDetailList.add(new CartDetail("Product 2", "https://via.placeholder.com/150", 245.00, 1));
//        cartDetailList.add(new CartDetail("Product 3", "https://via.placeholder.com/150", 40.00, 1));

        // Initialize the adapter with the cart item list and set it to the RecyclerView
        cartAdapter = new CartAdapter(this, cartDetailList, new CartAdapter.OnItemClickListener() {
            @Override
            public void onIncrementClick(CartDetail cartDetail, int position) {
                cartDetail.setCurrQuan(cartDetail.getCurrQuan() + 1);
                cartAdapter.notifyItemChanged(position);
            }

            @Override
            public void onDecrementClick(CartDetail cartDetail, int position) {
                if (cartDetail.getCurrQuan() > 1) {
                    cartDetail.setCurrQuan(cartDetail.getCurrQuan() - 1);
                    cartAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onDeleteClick(CartDetail cartDetail, int position) {
                cartDetailList.remove(position);
                cartAdapter.notifyItemRemoved(position);
                cartAdapter.notifyItemRangeChanged(position, cartDetailList.size());
            }
        });

        recyclerView.setAdapter(cartAdapter);
    }
}
