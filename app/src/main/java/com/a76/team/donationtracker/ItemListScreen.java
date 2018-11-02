package com.a76.team.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ItemListScreen extends AppCompatActivity {
    private ListView view;
    private Button back;

    private Intent toLocDetail;
    private Intent toItemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Location l = (Location) getIntent().getExtras().getSerializable("LOC");
        final List<DonationItem> items = LocalDB.donationItems.get(l);

        String[] names = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            names[i] = items.get(i).getName();
        }

        back = (Button) findViewById(R.id.button16);
        view = (ListView) findViewById(R.id.items_view);

        toLocDetail = new Intent(this, LocDetailScreen.class);
        toItemDetail = new Intent(this, ItemDetailScreen.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Welcome Screen");
                Bundle b = new Bundle();
                b.putSerializable("LOC", l);
                toLocDetail.putExtras(b);
                startActivity(toLocDetail);
            }
        });

        view.setAdapter(new ArrayAdapter<>(this,
                R.layout.activity_listview, names));

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SCREEN SWAP: ", "To Item Detail Screen");

                DonationItem item = items.get(position);

                Bundle b = new Bundle();
                b.putSerializable("IT", item);
                b.putSerializable("LOC", l);
                toItemDetail.putExtras(b);

                startActivity(toItemDetail);
            }
        });
    }

}
