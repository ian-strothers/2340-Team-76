package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {
    private Button back;
    private ListView view;

    private Intent toSearch;
    private Intent toItemsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ArrayList<DonationItem> results = (ArrayList<DonationItem>) getIntent().getExtras().getSerializable("ITS");

        String[] names = new String[results.size()];
        for (int i = 0; i < results.size(); i++) {
            names[i] = results.get(i).getName();
        }

        back = (Button) findViewById(R.id.button21);
        view = (ListView) findViewById(R.id.search_list);

        toSearch = new Intent(this, SearchScreen.class);
        toItemsDetail = new Intent(this, ItemDetailScreen.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Search Screen");
                startActivity(toSearch);
            }
        });

        view.setAdapter(new ArrayAdapter<>(this,
                R.layout.activity_listview, names));

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SCREEN SWAP: ", "To Loc Detail Screen");

                DonationItem it = results.get(position);

                Bundle b = new Bundle();
                b.putSerializable("IT", it);
                toItemsDetail.putExtras(b);

                startActivity(toItemsDetail);
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
