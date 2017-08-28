package com.example.glen.githubapi.rest;

import com.example.glen.githubapi.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by glen on 8/28/17.
 */
public interface GithubUserEndpoints {

    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);
}
