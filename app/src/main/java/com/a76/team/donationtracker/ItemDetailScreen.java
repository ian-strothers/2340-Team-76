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

public class ItemDetailScreen extends AppCompatActivity {
    private Button back;
    private TextView location;
    private TextView timeStamp;
    private TextView value;
    private TextView category;
    private TextView description;
    private TextView comments;

    private Intent toItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DonationItem it = (DonationItem) getIntent().getExtras().getSerializable("IT");
        final Location l = (Location) getIntent().getExtras().getSerializable("LOC");

        back     = (Button) findViewById(R.id.button17);
        location = (TextView) findViewById(R.id.textView3);
        timeStamp = (TextView) findViewById(R.id.textView9);
        value     = (TextView) findViewById(R.id.textView10);
        category  = (TextView) findViewById(R.id.textView12);
        description = (TextView) findViewById(R.id.textView13);
        comments    = (TextView) findViewById(R.id.textView11);

        toItemList = new Intent(this, ItemListScreen.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Item List");

                Bundle b = new Bundle();
                b.putSerializable("LOC", l);
                toItemList.putExtras(b);

                startActivity(toItemList);
            }
        });

        location.setText(it.getLocationName());
        timeStamp.setText(it.getTimestamp());
        value.setText("$" + String.format("%.2f", it.getValue()));
        category.setText(it.getCategory().toString());
        description.setText(it.getDescription());
        comments.setText(it.getComments());

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