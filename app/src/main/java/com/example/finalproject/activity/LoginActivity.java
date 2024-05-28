package com.example.finalproject.activity;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.finalproject.api.CartApi;
import com.example.finalproject.model.CartIdResponse;
import com.example.finalproject.model.TokenResponse;
import com.example.finalproject.model.User;
import com.example.finalproject.api.ApiClient;

public class LoginActivity extends AppCompatActivity {

     private LoginApi loginAPI;
     private SharedPreferences sharedPreferences;

     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.login);

          sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

          fetchLogin();

          Button btnSignUp2 = findViewById(R.id.btnSignUp2);
          btnSignUp2.setOnClickListener(v -> {
               startSignUp(this, SignUpActivity.class);
          });
     }

     public void startSignUp(Context context, Class<?> newActivity) {
          Intent signUpIntent = new Intent(context, newActivity);
          startActivity(signUpIntent);
     }

     private void getCartIdFromServer() {
          String token = sharedPreferences.getString("jwt_token", null);
          if (token != null) {
               CartApi cartApi = ApiClient.getCartApi();
               Call<CartIdResponse> call = cartApi.getCartId("Bearer " + token); // Add "Bearer " prefix to the token

               call.enqueue(new Callback<CartIdResponse>() {
                    @Override
                    public void onResponse(Call<CartIdResponse> call, Response<CartIdResponse> response) {
                         if (response.isSuccessful() && response.body() != null) {
                              int cartId = response.body().getCartId();
                              SharedPreferences.Editor editor = sharedPreferences.edit();
                              editor.putInt("cart_id", cartId);
                              editor.apply();
                         } else {
                              Toast.makeText(LoginActivity.this, "Failed to retrieve cart ID", Toast.LENGTH_SHORT).show();
                         }
                    }

                    @Override
                    public void onFailure(Call<CartIdResponse> call, Throwable t) {
                         Toast.makeText(LoginActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                    }
               });
          }
     }

     public void fetchLogin() {

          loginAPI = ApiClient.getLoginApi();

          Button btnLogin = findViewById(R.id.btnLogin1);
          EditText usernameEditText = findViewById(R.id.usernameHolder);
          EditText passwordEditText = findViewById(R.id.passwordHolder);

          btnLogin.setOnClickListener(v -> {
               String username = usernameEditText.getText().toString();
               String password = passwordEditText.getText().toString();

               User user = new User(username, password);

               loginAPI.loginUser(user).enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                         if (response.isSuccessful()) {

                              String token = response.body().getToken();
                              int userId = response.body().getId();

                              SharedPreferences.Editor editor = sharedPreferences.edit();
                              editor.putString("jwt_token", token);
                              editor.putBoolean("is_logged_in", true);
                              editor.putInt("user_id", userId);
                              editor.apply();

                              getCartIdFromServer();

                              Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                              Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                              startActivity(homeIntent);
                         } else {
                              Toast.makeText(LoginActivity.this, "Failed to login user", Toast.LENGTH_SHORT).show();
                         }
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                         Toast.makeText(LoginActivity.this, "Error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
               });
          });
     }
}
