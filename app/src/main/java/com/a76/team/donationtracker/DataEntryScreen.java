package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataEntryScreen extends AppCompatActivity {
    private Button back;
    private EditText name;
    private EditText value;
    private EditText description;
    private Spinner category;
    private EditText comments;
    private Button submit;

    private Intent toLocDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back =        (Button) findViewById(R.id.button14);
        name =        (EditText) findViewById(R.id.editText8);
        value =       (EditText) findViewById(R.id.editText3);
        description = (EditText) findViewById(R.id.editText5);
        category =    (Spinner) findViewById(R.id.spinner2);
        comments =    (EditText) findViewById(R.id.editText6);
        submit =      (Button) findViewById(R.id.button15);

        toLocDetail = new Intent(this, LocDetailScreen.class);

        //location from list passed in from list view
        Location loc = (Location) getIntent().getExtras().getSerializable("LOC");
        Bundle b = new Bundle();
        b.putSerializable("LOC", loc);
        toLocDetail.putExtras(b);

        //Set up the adapter to display the class standings in the spinner
        ArrayAdapter<ItemType> standingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ItemType.values());
        standingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(standingAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Loc Details");
                startActivity(toLocDetail);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //create donation item based on details given

                    //get current time as string
                    Date now = Calendar.getInstance().getTime();
                    SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
                    String tstamp = s.format(now);

                    //get location passed from loc detail screen
                    Location loc = (Location) getIntent().getExtras().getSerializable("LOC");

                    String n = name.getText().toString();
                    float val = Float.parseFloat(value.getText().toString());
                    String desc = description.getText().toString();
                    ItemType type = (ItemType) category.getSelectedItem();
                    String comm = comments.getText().toString();


                    String shortDesc = desc; //shortened description
                    if (desc.length() > 60) {
                        shortDesc = desc.subSequence(0, 50) + "...";
                    }

                    DonationItem item = new DonationItem(n, tstamp, loc, desc, shortDesc, val, type, comm);

                    LocalDB.donationItems.get(loc).add(item); //add new item to location's items

                    Log.d("SCREEN SWAP: ", "To Loc Details");
                    startActivity(toLocDetail);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Value",
                            Toast.LENGTH_SHORT).show();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
