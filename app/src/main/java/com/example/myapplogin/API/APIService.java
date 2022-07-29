package com.example.myapplogin.API;

import com.example.myapplogin.Models.ResponeAPI;
import com.example.myapplogin.Models.ResponeUser;
import com.example.myapplogin.Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
//    URL: http://192.168.1.2:5000

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy/MM/dd HH:mm:ss")
            .create();

    APIService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.2:5000")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("/logout")
    Call<ResponeAPI> Logout(@Query("username") String username);


    @POST("/login")
    Call<ResponeUser> Login(@Body User user);



}
