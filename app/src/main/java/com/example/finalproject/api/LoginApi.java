package com.example.finalproject.api;

import com.example.finalproject.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface LoginApi {
    @POST("user/login")
    Call<Void> loginUser(@Body User user);
}
