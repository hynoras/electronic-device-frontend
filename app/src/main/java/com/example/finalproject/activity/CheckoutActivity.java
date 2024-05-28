package com.example.finalproject.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class CheckoutActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proceed_to_checkout);

        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

    }
}
