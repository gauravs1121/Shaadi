package com.demo.shaadi.network;

import com.demo.shaadi.holder.UserListHolder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICall {
    @GET("api")
    Call<UserListHolder> getUsers(@Query("results") String count);
}
