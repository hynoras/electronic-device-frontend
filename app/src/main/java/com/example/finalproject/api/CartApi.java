package com.example.finalproject.api;

import com.example.finalproject.model.CartDetail;
import com.example.finalproject.model.CartIdResponse;
import com.example.finalproject.model.Product;

import java.util.List;

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
    Call<CartIdResponse> getCartId(@Header("Authorization") String token);

    @GET("cart/getCartDetailById/{cartId}")
    Call<CartResponse> retrieveCartInfoById(@Path("cartId") int cartId);

    class CartResponse {
        String message;
        List<CartDetail> cartDetail;

        public List<CartDetail> getCartDetails() {
            return cartDetail;
        }
    }

    class QuantityRequest {
        int currQuan;
        public QuantityRequest(int currQuan) {
            this.currQuan = currQuan;
        }
    }
}
