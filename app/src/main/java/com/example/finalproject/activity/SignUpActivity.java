package com.example.finalproject.activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.api.RegisterApi;
import com.example.finalproject.api.ApiClient;
import com.example.finalproject.model.User;

public class SignUpActivity extends AppCompatActivity {

    private RegisterApi registerAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        startLogin();
        fetchRegister();

    }

    public void startLogin() {
        Button btnLogin2 = findViewById(R.id.btnLogin2);
        btnLogin2.setOnClickListener(v -> {
            Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });
    }

    public void fetchRegister() {

        registerAPI = ApiClient.getRegisterApi();

        Button btnSignUp = findViewById(R.id.btnSignUp1);
        EditText usernameEditText = findViewById(R.id.usernameHolder);
        EditText passwordEditText = findViewById(R.id.passwordHolder);
        EditText confirmPasswordEditText = findViewById(R.id.confirmPasswordHolder);

        btnSignUp.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            // Make sure passwords match
            if (!password.equals(confirmPassword)) {
                Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a User object with the entered data
            User user = new User(username, password);

            // Call the register API
            registerAPI.registerUser(user).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        // Redirect to login page
                        startLogin();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "Error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
