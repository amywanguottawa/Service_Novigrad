package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class employeeHub extends AppCompatActivity implements View.OnClickListener {

    Button  bEditProfileInfo, bSetWorkingHours, bViewServiceRequests, bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_hub);

        bEditProfileInfo = (Button) findViewById(R.id.bEditProfileInfo);
        bSetWorkingHours = (Button) findViewById(R.id.bSetWorkingHours);
        bViewServiceRequests = (Button) findViewById(R.id.bViewServiceRequests);
        bLogout = (Button) findViewById(R.id.bLogout);

        bEditProfileInfo.setOnClickListener(this);
        bSetWorkingHours.setOnClickListener(this);
        bViewServiceRequests.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            //clicks on Edit profile info

            case R.id.bEditProfileInfo:
                startActivity(new Intent(this, employeeProfileInfo.class));
                break;
            case R.id.bSetWorkingHours:
                startActivity(new Intent(this,StoreHours.class));
                break;
            case R.id.bViewServiceRequests:
                startActivity(new Intent(this, ViewService.class));
                break;
            case R.id.bViewEmployeeServices:
                startActivity(new Intent(this,ViewEmployeeServices.class));
                break;
            case R.id.bLogout:
                startActivity(new Intent(this,Login.class));
                break;
        }
    }
}