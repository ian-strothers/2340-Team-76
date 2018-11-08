package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocDetailScreen extends AppCompatActivity {

    private Button back;
    private Button seeItems;
    private Button addItem;
    private TextView name;
    private TextView type;
    private TextView lat;
    private TextView lon;
    private TextView address;
    private TextView phone;

    private Intent toLocs;
    private Intent toDataEntry;
    private Intent toItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_detail_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //location from list passed in from list view
        Location loc = (Location) getIntent().getExtras().getSerializable("LOC");
        Bundle b = new Bundle();
        b.putSerializable("LOC", loc);

        back     = (Button) findViewById(R.id.button9);
        seeItems = (Button) findViewById(R.id.button13);
        addItem  = (Button) findViewById(R.id.button12);
        name     = (TextView) findViewById(R.id.textView4);
        type     = (TextView) findViewById(R.id.textView2);
        lat      = (TextView) findViewById(R.id.textView6);
        lon      = (TextView) findViewById(R.id.textView5);
        address  = (TextView) findViewById(R.id.textView7);
        phone    = (TextView) findViewById(R.id.textView8);

        toLocs = new Intent(this, LocationsScreen.class);
        toItemList = new Intent(this, ItemListScreen.class);
        toDataEntry = new Intent(this, DataEntryScreen.class);

        //pass location through to activities
        toItemList.putExtras(b);
        toDataEntry.putExtras(b);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Location List");
                startActivity(toLocs);
            }
        });

        seeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Item List");
                startActivity(toItemList);
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Data Entry");
                startActivity(toDataEntry);
            }
        });

        //init text on screen
        name.setText(loc.getName());
        type.setText(loc.getType().toString());
        lat.setText(String.format("%.3f", loc.getLatitude()));
        lon.setText(String.format("%.3f", loc.getLongitude()));
        address.setText(loc.getAddress());
        phone.setText(loc.getPhone());

    }

}
