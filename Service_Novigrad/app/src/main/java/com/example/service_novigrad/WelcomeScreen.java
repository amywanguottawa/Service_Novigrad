package com.example.service_novigrad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeScreen extends AppCompatActivity {

    TextView tvWelcomeName, tvWelcomeRole;
    String firstName, lastName,role;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        tvWelcomeName = (TextView) findViewById(R.id.tvWelcomeName);
        tvWelcomeRole = (TextView) findViewById(R.id.tvWelcomeRole);

        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");
        role = UserTracker.getRole();

        tvWelcomeName.setText(firstName +" "+lastName);
        tvWelcomeRole.setText(role);
        int timeout = 1500;
        if (role.equals("Administrator")){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();

                    Intent homepage = new Intent(WelcomeScreen.this, AdminScreen.class);
                    homepage.putExtra("role",role);

                    startActivity(homepage);
                }
            }, timeout);


        } else if(role.equals("Employee")){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();

                    Intent homepage = new Intent(WelcomeScreen.this, employeeHub.class);
                    homepage.putExtra("role",role);

                    startActivity(homepage);
                }
            }, timeout);


        }else if (role.equals("Customer")){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();

                    Intent homepage = new Intent(WelcomeScreen.this, CustomerHub.class);
                    homepage.putExtra("role",role);

                    startActivity(homepage);
                }
            }, timeout);

        }



    }
}