package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class WelcomeScreen extends AppCompatActivity {
    private Button loginButton;
    private Button registerButton;

    private Intent toLoginScreen;
    private Intent toRegisterScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        if (!LocalDB.loaded) {
            try {
                LocalDB.loadData(new File(getFilesDir(), "data.bin"));
            } catch (IOException e) {
                Log.e("Deserialization:", "failed to deserialize, possible on first run of the app", e);
                readData();
            }
        }
        LocalDB.users.put("User", new User("User", "User", "pass", UserType.USER)); //default user for debug REMOVE

        loginButton = (Button) findViewById(R.id.button2);
        registerButton = (Button) findViewById(R.id.button3);

        toLoginScreen = new Intent(this, LoginScreen.class);
        toRegisterScreen = new Intent(this, RegistrationScreen.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Login Screen");
                startActivity(toLoginScreen);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Registration Screen");
                startActivity(toRegisterScreen);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void readData() {
        LocalDB.locations.clear();
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
                LocalDB.locations.add(loc);

                if (!LocalDB.donationItems.containsKey(loc)) {
                    LocalDB.donationItems.put(loc, new ArrayList<DonationItem>()); //every location needs donation item list
                }
            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error" + line, e);
            e.printStackTrace();
        }
    }

}
