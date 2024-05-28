package com.example.finalproject.api;

import okhttp3.OkHttpClient;
import java.util.concurrent.TimeUnit;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.1.11:3000/";
    //10.45.169.13, 192.168.1.10, 192.168.1.12, 192.168.1.8, 192.168.1.15
    //192.168.220.104, 172.30.59.143, 192.168.1.14, 192.168.1.11

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(60, TimeUnit.SECONDS); // Set connection timeout
            httpClient.readTimeout(60, TimeUnit.SECONDS); // Set read timeout
            httpClient.writeTimeout(60, TimeUnit.SECONDS); // Set write timeout

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // Set custom OkHttpClient
                    .build();
        }
        return retrofit;
    }

    public static RegisterApi getRegisterApi() {
        return getRetrofitInstance().create(RegisterApi.class);
    }

    public static LoginApi getLoginApi() {
        return getRetrofitInstance().create(LoginApi.class);
    }

    public static ProductApi getRetrieveAllProductApi() {
        return getRetrofitInstance().create(ProductApi.class);
    }

    public static CartApi getCartApi() {
        return getRetrofitInstance().create(CartApi.class);
    }

    public static OrderApi getOrderApi() {
        return getRetrofitInstance().create(OrderApi.class);
    }

}

