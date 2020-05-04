package com.example.coronaapp.API;

import android.graphics.Color;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.example.coronaapp.CountryDetail;
import com.example.coronaapp.CountryList;
import com.example.coronaapp.Model.Data;
import com.example.coronaapp.Utilities.CountrySorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class ApiClient {

    private static final String BASE_URL = "https://api.covid19api.com";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void handleListResponse(Call<List<Data>> call, CountryList activity) {
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                activity.data = response.body();
                Collections.sort(response.body(), new CountrySorter());
                ArrayAdapter<Data> adapter = new ArrayAdapter<Data>(activity.getApplicationContext(), android.R.layout.simple_list_item_1, response.body());
                activity.listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                activity.OnClickListener(response.body());
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });
    }

    public static void handleDetailResponse(Call<List<Data>> call, CountryDetail activity) {
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                Log.e(TAG, "onResponse: " + response.body());
                List<Data> data = response.body();

                if (data.size() > 0) {
                    int number = data.size() - 1;
                    activity.confirmed.setText(String.valueOf(data.get(number).getConfirmed()));
                    activity.deaths.setText(String.valueOf(data.get(number).getDeaths()));
                    activity.country.setText(data.get(number).getCountry());
                } else {
                    activity.confirmed.setText("No data available");
                    activity.deaths.setText("No data available");
                    activity.country.setText("No data available");
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public static void handleFrontpageStatsRequest(PieChartView pieChartView) {
        RestInterface restInterface;
        restInterface = RestClient.getClient().create(RestInterface.class);
        Call<List<Data>> call = restInterface.getSummary();
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                System.out.println("Success");
                List<SliceValue> pieData = new ArrayList<>();

                Data data = response.body().get(0);

                int totalCases = data.getTotalConfirmed() + data.getTotalDeaths() + data.getTotalRecovered();
                float totalConfirmed = (data.getTotalConfirmed() * 100) / totalCases;
                float totalDeaths = (data.getTotalDeaths() * 100) / totalCases;
                float totalRecovered = (data.getTotalRecovered() * 100) / totalCases;

                pieData.add(new SliceValue(totalConfirmed, Color.rgb(70,130,180)).setLabel("Total Cases: " + totalConfirmed + "%"));
                pieData.add(new SliceValue(totalDeaths, Color.rgb(230,24,24)).setLabel("Total Deaths: " + totalDeaths + "%"));
                pieData.add(new SliceValue(totalRecovered, Color.rgb(28,168,51)).setLabel("Total Recovered: " + totalRecovered + "%"));
                PieChartData pieChartData = new PieChartData(pieData);
                pieChartData.setHasLabels(true);
                pieChartView.setPieChartData(pieChartData);
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });


    }
}
