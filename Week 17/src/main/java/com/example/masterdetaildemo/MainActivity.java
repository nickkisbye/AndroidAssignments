package com.example.masterdetaildemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    /**
     * In the layouts folder, I have made an activity_main file with w820dp at the end of the file name.
     * This tells android studio that this layout should only be available if the device with is 820dp or higher.
     */

    private ListFragment listFragment;
    private DetailFragment detailFragment;
    private boolean isTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // The correct version will be loaded, depending on the width of the screen.

        FrameLayout frameLayout = findViewById(R.id.phoneframeId);

        listFragment = ListFragment.newInstance(this); // we have to use listFragment in both cases (tablet and phone), so we created it here once.

        if (frameLayout != null) {
            /**
             * The frameLayout will only be null, if the user is using a tablet.
             * This is because it will load the 820dp version and the R.id.phoneframeId is not assigned
             * to that layout, therefore it will be null
             */
            isTablet = false;
            presentFragment(R.id.phoneframeId, listFragment); // Inserts the listFragment (i.e listlayout.xml) into phoneframeId
        } else {
            isTablet = true;
            //Creates a detailFragment with the start text "Welcome"
            detailFragment = DetailFragment.newInstance("This is a detail view");
            // Displays the listFragment and the detailFragment on tablet.
            presentFragment(R.id.frameLayoutTabletList, listFragment); // Insert the listFragment (i.e listlayout.xml) into frameLayoutTabletList
            presentFragment(R.id.framLayoutTabletDetail, detailFragment); // insert the detailFragment (i.e detaillayout.xml) into the frameLayoutTabletDetail
        }
    }

    public void handleItemClick(String item) {
        // "item" comes from listFragment class
        if (isTablet) {
            /**
             * If the device is a tablet, we simply "replace" the detail fragment content everytime
             * we click on an item.
            */
            detailFragment = DetailFragment.newInstance(item); // inflate a textView with the item (a string)
            presentFragment(R.id.framLayoutTabletDetail, detailFragment);
        } else {
            /**
             * If the device is a phone, we make a new intent when the item is clicked, because we want to go
             * to another page.
             */
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.KEY, item); // gives the intent the item / string for it to show.
            startActivity(intent);
        }
    }

    //Pushes the fragment to the view/layout
    private void presentFragment(int resourceID, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(resourceID, fragment)
                .commit();
    }

}
