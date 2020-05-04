package com.example.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.coronaapp.API.ApiClient;
import com.example.coronaapp.API.ApiInterface;
import com.example.coronaapp.Model.Data;
import com.example.coronaapp.Utilities.CountrySorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;


public class CountryList extends AppCompatActivity {

    public ArrayAdapter<Data> adapter;
    public ListView listView;
    ApiInterface apiInterface;
    public List<Data> data = null;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        listView = findViewById(R.id.mylist);
        editText = findViewById(R.id.editText);

        setOnChangeListener();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Data>> call = apiInterface.getAllCountries();
        ApiClient.handleListResponse(call, this);
    }

    private void setOnChangeListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Data> filteredData = new ArrayList<>();

                for (Data temp: data) {
                    if (temp.getCountry().contains(s)) {
                        filteredData.add(temp);
                    }
                }

                Collections.sort(filteredData, new CountrySorter());
                adapter = new ArrayAdapter<Data>(getApplicationContext(), android.R.layout.simple_list_item_1, filteredData);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                OnClickListener(filteredData);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    public void OnClickListener(List<Data> data) {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(view.getContext(), CountryDetail.class);
            intent.putExtra("key", data.get(position).getSlug());
            startActivity(intent);
        });
    }
}
