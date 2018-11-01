package com.a76.team.donationtracker;

import android.content.ClipData;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategorySearchScreen extends AppCompatActivity {
    private Button back;
    private Spinner category;
    private Spinner locations;
    private Button search;

    private Intent toSearch;
    private Intent toResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] listNames = new String[LocalDB.locations.size() + 1];
        listNames[0] = "All";
        //names for the list
        for (int i = 1; i < listNames.length; i++) {
            listNames[i] = LocalDB.locations.get(i - 1).getName();
        }

        back = (Button) findViewById(R.id.button24);
        category = (Spinner) findViewById(R.id.spinner4);
        locations = (Spinner) findViewById(R.id.spinner5);
        search = (Button) findViewById(R.id.button23);

        toSearch = new Intent(this, SearchScreen.class);
        toResults = new Intent(this, SearchResultsActivity.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Search Screen");
                startActivity(toSearch);
            }
        });

        /*
            Set up the adapter to display the class standings in the spinner
         */
        ArrayAdapter<ItemType> standingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ItemType.values());
        standingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(standingAdapter);

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listNames);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        locations.setAdapter(ad);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //this needs to be HEAVILY changed with firebase
                try {
                    ItemType cat = (ItemType) category.getSelectedItem();
                    String locName = (String) locations.getSelectedItem();

                    ArrayList<DonationItem> potResults = new ArrayList<>();
                    ArrayList<DonationItem> results = new ArrayList<>();

                    if (locName == "All") {
                        for (Location loc : LocalDB.locations) {
                            List<DonationItem> items = LocalDB.donationItems.get(loc);
                            potResults.addAll(items);
                        }
                    } else {
                        Location loc = LocalDB.locations.get(locations.getSelectedItemPosition() - 1);

                        List<DonationItem> items = LocalDB.donationItems.get(loc);
                        potResults.addAll(items);
                    }

                    for (DonationItem it : potResults) {
                        if (it.getCategory() == cat) {
                            results.add(it);
                        }
                    }

                    Bundle b = new Bundle();
                    b.putSerializable("ITS", results);
                    toResults.putExtras(b);
                    startActivity(toResults);
                } catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Search", //login attempt denied
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
