package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

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

}
