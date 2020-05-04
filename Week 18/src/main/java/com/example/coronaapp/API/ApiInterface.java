package com.example.coronaapp.API;

import com.example.coronaapp.Model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiInterface {

    @GET("/total/country/{slug}")
    Call<List<Data>> getOneCountryDetails(@Path("slug") String slug);

    @GET("/countries")
    Call<List<Data>> getAllCountries();

    @GET("/summary")
    Call<List<Data>> getSummary();

}
