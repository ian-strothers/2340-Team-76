package com.a76.team.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;

public class LocationsScreen extends AppCompatActivity {
    private List<Location> locations = new ArrayList<>();

    private ListView view;
    private Button logout;

    private Intent toWelcome;
    private Intent toLocDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_screen);

        //load data from the file
        readData();

        String[] listNames = new String[locations.size()];

        //names for the list
        for (int i = 0; i < listNames.length; i++) {
            listNames[i] = locations.get(i).getName();
        }

        logout = (Button) findViewById(R.id.button8);
        view = (ListView) findViewById(R.id.locations_view);

        toWelcome = new Intent(this, WelcomeScreen.class);
        toLocDetail = new Intent(this, LocDetailScreen.class);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Welcome Screen");
                startActivity(toWelcome);
            }
        });

        view.setAdapter(new ArrayAdapter<>(this,
                R.layout.activity_listview, listNames));

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SCREEN SWAP: ", "To Loc Detail Screen");

                Location loc = locations.get(position);

                Bundle b = new Bundle();
                b.putSerializable("LOC", loc);
                toLocDetail.putExtras(b);

                startActivity(toLocDetail);
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

    private void readData() {
        InputStream is = getResources().openRawResource(R.raw.locationdata);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        try {
            reader.readLine(); //skip first line
            while ((line = reader.readLine()) != null) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokens = line.split(",");

                // Read the data and store it in Location object.
                Location loc = new Location();
                loc.setName(tokens[1]);
                loc.setLatitude(Double.parseDouble(tokens[2]));
                loc.setLongitude(Double.parseDouble(tokens[3]));
                loc.setAddress(tokens[4] + ", " + tokens[5] + " " + tokens[6] + " " + tokens[7]);
                loc.setPhone(tokens[9]);

                switch (tokens[8]) { //decide which type to make the loc
                    case "Store":
                        loc.setType(LocationType.STORE);
                        break;
                    case "Drop Off":
                        loc.setType(LocationType.DROP_OFF);
                        break;
                    case "Warehouse":
                        loc.setType(LocationType.WAREHOUSE);
                        break;
                    default:
                        throw new IOException("Invalid Type given to location object");
                }

                Log.d("LOCATION CREATED: ", loc.getName());
                locations.add(loc);
            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error" + line, e);
            e.printStackTrace();
        }
    }
}
