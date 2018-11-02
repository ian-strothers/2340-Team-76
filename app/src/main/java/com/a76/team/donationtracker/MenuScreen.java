package com.a76.team.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MenuScreen extends AppCompatActivity {
    private Button logout;
    private Button locationsButton;
    private Button searchButton;
    private Button saveButton;

    private Intent toLoginScreen;
    private Intent toLocations;
    private Intent toSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        logout =          (Button) findViewById(R.id.button11);
        locationsButton = (Button) findViewById(R.id.button10);
        searchButton    = (Button) findViewById(R.id.button19);
        saveButton      = (Button) findViewById(R.id.button25);

        toLoginScreen = new Intent(this, LoginScreen.class);
        toLocations = new Intent(this, LocationsScreen.class);
        toSearch = new Intent(this, SearchScreen.class);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Login Screen");
                startActivity(toLoginScreen);
            }
        });

        locationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Locations Screen");
                startActivity(toLocations);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Search Screen");
                startActivity(toSearch);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Serialization: ", "Saving data");

                try {
                    LocalDB.saveData(new File(getFilesDir(), "data.bin"));
                } catch (IOException e) {
                    Log.e("Serialization: ", "Serialization Failed", e);
                    Toast.makeText(getApplicationContext(), "Save failed", //login attempt denied
                            Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(), "Data Saved", //login attempt denied
                        Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
