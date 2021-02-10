package com.ujjaval.comparejson.interfaces;

import com.ujjaval.comparejson.model.PostDetails;
import com.ujjaval.comparejson.model.UserDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/users")
    Call<List<UserDetails>> getusers();

    @GET("/posts")
    Call<List<PostDetails>> getpost();
}
