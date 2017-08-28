package com.example.glen.githubapi.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by glen on 8/28/17.
 */
public class APIClient {

    public static final String BASE_URL = "https://api.github.com/";

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient() {

        if (mRetrofit == null) {

            mRetrofit = (new Retrofit.Builder())
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }

        return mRetrofit;
    }
}
