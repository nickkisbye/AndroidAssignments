package com.example.coronaapp.Utilities;

import com.example.coronaapp.Model.Data;

import java.util.Comparator;


public class CountrySorter implements Comparator<Data> {

    @Override
    public int compare(Data o1, Data o2) {
        return o1.getCountry().compareTo(o2.getCountry());
    }
}
