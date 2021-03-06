package com.a76.team.donationtracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private Button login;
    private Button back;

    private Intent toMenu;
    private Intent toWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        userName = (EditText) findViewById(R.id.editText9);
        password = (EditText) findViewById(R.id.editText7);
        login = (Button) findViewById(R.id.button);
        back = (Button) findViewById(R.id.button5);

        toMenu = new Intent(this, MenuScreen.class);
        toWelcome = new Intent(this, WelcomeScreen.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = LocalDB.users.get(userName.getText().toString()); //get the user if the username exists in the system
                String pw = password.getText().toString(); //reference to password text for convenience

                //if (u != null && u.getPassword().equals(pw)) { //if username and password are correct
                if (u.checkUser(pw, u.getType())) {
                    Toast.makeText(getApplicationContext(), "Correct Credentials",
                            Toast.LENGTH_SHORT).show();

                    Log.d("SCREEN SWAP: ", "To Menu Screen");
                    startActivity(toMenu); //login!
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect Credentials", //login attempt denied
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
    }
    public boolean checkPassword() {
        return true;
    }
}
