package com.example.coronaapp.DB;

import com.example.coronaapp.Model.Data;
import com.example.coronaapp.Utilities.CountrySorter;

import java.util.ArrayList;
import java.util.Collections;


public class DataBase {

    public static ArrayList<Data> countryList;

    public DataBase() {
    }

    static {
        countryList = new ArrayList<>();
    }

    public static void addToCountryList(Data data) {
        countryList.add(data);
    }

    public static ArrayList<Data> getCountryList() {
        Collections.sort(countryList, new CountrySorter());
        return countryList;
    }

    public static void setCountryList(ArrayList<Data> data) {
        countryList = data;
    }

    public static Data getIndex(int index) {
        return countryList.get(index);
    }

}
