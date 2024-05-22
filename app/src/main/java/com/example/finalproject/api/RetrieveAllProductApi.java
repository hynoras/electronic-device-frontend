package com.example.finalproject.api;

import com.example.finalproject.model.Product;

import retrofit2.Call;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrieveAllProductApi {
    @GET("product/getAllProduct")
    Call<List<Product>> retrieveAllProduct();

    @GET("product/{id}")
    Call<List<Product>> getProductById(@Path("id") int id);
}
