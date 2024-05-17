package com.example.finalproject.api;

import com.example.finalproject.model.Product;

import retrofit2.Call;
import java.util.List;
import retrofit2.http.GET;
public interface RetrieveAllProductApi {
    @GET("product/getAllProduct")
    Call<List<Product>> retrieveAllProduct();
}
