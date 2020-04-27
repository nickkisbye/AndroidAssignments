package com.example.masterdetaildemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {

    private MainActivity mainActivity;

    // We make a method that behaves like a constructor in order to avoid no-args constructor.
    public static ListFragment newInstance(MainActivity mainActivity) {
        ListFragment listFragment = new ListFragment();
        listFragment.mainActivity = mainActivity;
        return listFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Attaches the listlayout.xml to the listFragment view.
        View view = inflater.inflate(R.layout.listlayout, container, false);
        // myList has default dummy data found in the folder values -> strings.xml
        ListView listView = view.findViewById(R.id.mylist);
        listView.setOnItemClickListener((adapterView, view2, adapter_pos, row_id) -> {
            // Handles the click on the list and sends the string value to the handleItemClick method.
            TextView textView = (TextView) view2;
            if (textView != null) {
                mainActivity.handleItemClick(textView.getText().toString());
            }

        });
        return view;
    }
}
