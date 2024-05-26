package com.example.finalproject.api;

import com.example.finalproject.model.CartDetail;
import com.example.finalproject.model.CartResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartApi {
    @POST("cart/addToCart/{cartId}/{prodId}")
    Call<CartDetail> addToCart(@Path("cartId") int cartId, @Path("prodId") int prodId, @Body QuantityRequest quantityRequest);

    @GET("cart/getCartId")
    Call<CartResponse> getCartId(@Header("Authorization") String token);

    class QuantityRequest {
        int currQuan;

        public QuantityRequest(int currQuan) {
            this.currQuan = currQuan;
        }
    }
}
