package com.example.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.coronaapp.API.ApiClient;
import com.example.coronaapp.API.ApiInterface;
import com.example.coronaapp.Model.Data;

import java.util.List;

import retrofit2.Call;

public class CountryDetail extends AppCompatActivity {

    public TextView confirmed;
    public TextView deaths;
    public TextView country;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Intent intent = getIntent();
        String countrySlug = intent.getExtras().getString("key");

        confirmed = findViewById(R.id.textViewConfirmed);
        deaths = findViewById(R.id.textViewDeaths);
        country = findViewById(R.id.textViewCountry);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Data>> call = apiInterface.getOneCountryDetails(countrySlug);
        ApiClient.handleDetailResponse(call, this);
    }

}



