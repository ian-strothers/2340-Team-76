package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {
    private final String correctUser = "user";
    private final String correctPass = "pass";

    private EditText userName;
    private EditText password;
    private Button login;
    private Button back;

    private Intent toTemp;
    private Intent toWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        userName = findViewById(R.id.editText9);
        password = findViewById(R.id.editText7);
        login = findViewById(R.id.button);
        back = findViewById(R.id.button5);

        toTemp = new Intent(this, Temp.class);
        toWelcome = new Intent(this, WelcomeScreen.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().equals(correctUser) &&
                        password.getText().toString().equals(correctPass)) {
                    Toast.makeText(getApplicationContext(), "Correct Credentials",
                            Toast.LENGTH_SHORT).show();

                    Log.d("SCREEN SWAP: ", "To Temporary Screen");
                    startActivity(toTemp);
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect Credentials",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SCREEN SWAP: ", "To Welcome Screen");
                startActivity(toWelcome);
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
