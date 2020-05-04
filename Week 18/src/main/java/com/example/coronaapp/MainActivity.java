package com.example.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coronaapp.API.ApiClient;
import com.example.coronaapp.API.ApiInterface;

import lecho.lib.hellocharts.view.PieChartView;


public class MainActivity extends AppCompatActivity {

    PieChartView pieChartView;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChartView = findViewById(R.id.chart);

        ApiClient.handleFrontpageStatsRequest(pieChartView);
    }

    public void ListCountries(View view) {
        Intent intent = new Intent(this, CountryList.class);
        startActivity(intent);
    }

}
