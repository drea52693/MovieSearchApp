package com.example.dan.moviesearchapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static RetrofitSingleton single_instance = null;

    static final String OMDB_BASE_URL = "http://www.omdbapi.com";
    static final String FANDANGO_BASE_URL = "http://api.fandango.com";
    static final String ROVI_BASE_URL = "http://api.rovicorp.com";

    protected Retrofit retrofit;

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();

    private RetrofitSingleton(String BASE_URL){

        this.retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static RetrofitSingleton getInstance(String BASE_URL){

        if(single_instance == null) {
            single_instance = new RetrofitSingleton(BASE_URL);
        }
        return single_instance;
    }


}
