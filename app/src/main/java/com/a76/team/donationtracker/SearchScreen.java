package com.a76.team.donationtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchScreen extends AppCompatActivity {
    private Button back;
    private Button category;
    private Button search;
    private EditText searchBar;
    private Spinner locations;

    private Intent toMenu;
    private Intent toCat;
    private Intent toResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = (Button) findViewById(R.id.button20);
        category = (Button) findViewById(R.id.button22);
        search = (Button) findViewById(R.id.button18);
        searchBar = (EditText) findViewById(R.id.editText10);
        locations = (Spinner) findViewById(R.id.spinner3);

        toMenu = new Intent(this, MenuScreen.class);
        toResults = new Intent(this, SearchResultsActivity.class);
        toCat = new Intent(this, CategorySearchScreen.class);

        String[] listNames = new String[LocalDB.locations.size() + 1];
        listNames[0] = "All";
        //names for the list
        for (int i = 1; i < listNames.length; i++) {
            listNames[i] = LocalDB.locations.get(i - 1).getName();
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listNames);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        locations.setAdapter(ad);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Menu Screen");
                startActivity(toMenu);
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Category Search Screen");
                startActivity(toCat);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //this needs to be HEAVILY changed with firebase
                try {
                    String term = searchBar.getText().toString();
                    String locName = (String) locations.getSelectedItem();

                    ArrayList<DonationItem> potResults = new ArrayList<>();
                    ArrayList<DonationItem> results = new ArrayList<>();

                    if (locName.equals("All")) {
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
                        if (it.getName().contains(term)) {
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
    }
}
