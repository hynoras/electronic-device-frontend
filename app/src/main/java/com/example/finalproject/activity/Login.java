package com.example.finalproject.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.api.LoginApi;
import com.example.finalproject.model.User;
import com.example.finalproject.api.ApiClient;

public class Login extends AppCompatActivity {

     private LoginApi loginAPI;

     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.login);

          fetchLogin();

          Button btnSignUp2 = findViewById(R.id.btnSignUp2);
          btnSignUp2.setOnClickListener(v -> {
               startSignUp(this, SignUp.class);
          });
     }

     public void startSignUp(Context context, Class<?> newActivity) {
          Intent signUpIntent = new Intent(context, newActivity);
          startActivity(signUpIntent);
     }

     public void fetchLogin() {

          loginAPI = ApiClient.getLoginApi();

          Button btnLogin = findViewById(R.id.btnLogin1);
          EditText usernameEditText = findViewById(R.id.usernameHolder);
          EditText passwordEditText = findViewById(R.id.passwordHolder);


          btnLogin.setOnClickListener(v -> {
               String username = usernameEditText.getText().toString();
               String password = passwordEditText.getText().toString();

               // Create a User object with the entered data
               User user = new User(username, password);

               // Call the register API
               loginAPI.loginUser(user).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                         if (response.isSuccessful()) {
                              Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                              // Redirect to login page
                              Intent homeIntent = new Intent(Login.this, MainActivity.class);
                              startActivity(homeIntent);
                         } else {
                              Toast.makeText(Login.this, "Failed to login user", Toast.LENGTH_SHORT).show();
                         }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                         Toast.makeText(Login.this, "Error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
               });
          });
     }
}
