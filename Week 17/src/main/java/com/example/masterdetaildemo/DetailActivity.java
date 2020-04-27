package com.example.masterdetaildemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY = "intendKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        // Creates a detailFragment with the text from the intent.
        DetailFragment detailFragment = DetailFragment.newInstance(bundle.getString(KEY));
        presentFragment(R.id.detailActivityFrame, detailFragment); // insert the detailFragment (i.e detaillayout.xml) into the detailActivityFrame
    }

    //Pushes the fragment to the view/layout
    private void presentFragment(int resourceID, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(resourceID, fragment)
                .commit();
    }

}
