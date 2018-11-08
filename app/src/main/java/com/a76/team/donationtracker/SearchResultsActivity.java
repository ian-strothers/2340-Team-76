package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
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

        String[] names;
        final ArrayList<DonationItem> results = (ArrayList<DonationItem>) getIntent().getExtras().getSerializable("ITS");
        if (results.size() == 0) {
            names = new String[1];
            names[0] = "Nothing found";
        } else {
            names = new String[results.size()];

            for (int i = 0; i < results.size(); i++) {
                names[i] = results.get(i).getName();
            }
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
    }

}
