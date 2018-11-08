package com.a76.team.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.List;

public class LocationsScreen extends AppCompatActivity {
    private ListView view;
    private Button logout;
    private Button back;

    private Intent toWelcome;
    private Intent toLocDetail;
    private Intent toMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_screen);
        toMenu = new Intent(this, MenuScreen.class);

        //load data from the file
        final List<Location> locations = LocalDB.locations; // reference to loc database

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
        back     = (Button) findViewById(R.id.button26);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Menu Screen");
                startActivity(toMenu);
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
    }


}
