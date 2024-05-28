package com.example.finalproject.api;

import com.example.finalproject.model.Order;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi {

    @POST("/order/generateOrder/{cartId}")
    Call<ResponseBody> generateOrder(@Path("cartId") int cartId, @Body Order order);

}
