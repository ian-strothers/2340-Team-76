package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocDetailScreen extends AppCompatActivity {

    private Button back;
    private TextView name;
    private TextView type;
    private TextView lat;
    private TextView lon;
    private TextView address;
    private TextView phone;

    private Intent toLocs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_detail_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = (Button) findViewById(R.id.button9);
        name = (TextView) findViewById(R.id.textView4);
        type = (TextView) findViewById(R.id.textView2);
        lat = (TextView) findViewById(R.id.textView6);
        lon = (TextView) findViewById(R.id.textView5);
        address = (TextView) findViewById(R.id.textView7);
        phone = (TextView) findViewById(R.id.textView8);

        toLocs = new Intent(this, LocationsScreen.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Location List");
                startActivity(toLocs);
            }
        });

        //location from list passed in from list view
        Location loc = (Location) getIntent().getExtras().getSerializable("LOC");

        //init text on screen
        name.setText(loc.getName());
        type.setText(loc.getType().toString());
        lat.setText(String.format("%.3f", loc.getLatitude()));
        lon.setText(String.format("%.3f", loc.getLongitude()));
        address.setText(loc.getAddress());
        phone.setText(loc.getPhone());

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
