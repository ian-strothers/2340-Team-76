package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrationScreen extends AppCompatActivity {
    private EditText name;
    private EditText username;
    private EditText password;
    private Button register;
    private Button back;
    private Spinner type;

    private Intent toWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        name     = (EditText) findViewById(R.id.editText);
        username = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText4);
        register = (Button) findViewById(R.id.button7);
        back     = (Button) findViewById(R.id.button6);
        type     = (Spinner) findViewById(R.id.spinner);

        toWelcome = new Intent(this, WelcomeScreen.class);

        /*
            Set up the adapter to display the class standings in the spinner
         */
        ArrayAdapter<UserType> standingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UserType.values());
        standingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(standingAdapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try { //exception is thrown if any field hasn't been filled out
                    //create new user in database
                    User u = new User(name.getText().toString(), username.getText().toString(),
                            password.getText().toString(), (UserType) type.getSelectedItem());

                    Log.d("SCREEN SWAP: ", "To Welcome Screen");
                    Toast.makeText(getApplicationContext(), "Registration Successful",
                            Toast.LENGTH_SHORT).show();
                    startActivity(toWelcome);
                } catch (IllegalArgumentException e) { //some field was empty
                    Toast.makeText(getApplicationContext(), e.getMessage(),
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
