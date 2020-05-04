package com.example.coronaapp.API;

import com.example.coronaapp.Model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {

    @GET("/")
    Call<List<Data>> getSummary();
}
