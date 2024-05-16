package com.example.finalproject.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView productList;
    private ArrayList<String> products;

    private ArrayAdapter<String> cartAdapter;
    private ArrayList<String> cartItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize product list
        productList = findViewById(R.id.productList);
        products = new ArrayList<>();
        products.add("Laptop");
        products.add("Smartphone");
        products.add("Tablet");
        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        productList.setAdapter(productAdapter);

        // Initialize cart list
        ListView cartListView = findViewById(R.id.cartListView);
        cartItems = new ArrayList<>();
        cartAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItems);
        cartListView.setAdapter(cartAdapter);

        // Button to add selected item to cart
        Button addToCartButton = findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(v -> {
            int position = productList.getCheckedItemPosition();
            if (position != ListView.INVALID_POSITION) {
                String item = products.get(position);
                cartItems.add(item);
                cartAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Added to cart: " + item, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Please select a product", Toast.LENGTH_SHORT).show();
            }
        });

        // Button to purchase items in the cart
        Button purchaseButton = findViewById(R.id.purchaseButton);
        purchaseButton.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(MainActivity.this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                // Handle purchase logic here
                // For demo, just display a message
                Toast.makeText(MainActivity.this, "Purchase Successful!", Toast.LENGTH_SHORT).show();
                cartItems.clear();
                cartAdapter.notifyDataSetChanged();
            }
        });

        // Handle item click in product list
        productList.setOnItemClickListener((parent, view, position, id) -> {
            // Handle item click if needed
        });

        startSignUp();
    }

    public void startSignUp() {
        ImageButton signUpButton = findViewById(R.id.btnUser);
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        });
    }
}
