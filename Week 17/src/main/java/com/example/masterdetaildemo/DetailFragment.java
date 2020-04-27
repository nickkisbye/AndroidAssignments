package com.example.masterdetaildemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    private String value = "";

    // Used to "construct" the detailFragment everytime we use it in other classes.
    public static DetailFragment newInstance(String value) {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.value = value;
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Creates a view with detaillayout.xml in it.
        View view = inflater.inflate(R.layout.detaillayout, container, false);
        // Sets detaillayout.xml textView to the value from the item clicked on.
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(value);
        return view;
    }
}
