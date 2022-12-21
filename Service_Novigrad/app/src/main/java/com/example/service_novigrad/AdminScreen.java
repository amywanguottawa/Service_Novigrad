package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminScreen extends AppCompatActivity implements View.OnClickListener{

    Button bServiceList, bAccountList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        bServiceList = (Button) findViewById(R.id.bServiceList);
        bAccountList = (Button) findViewById(R.id.bAccountList);

        bServiceList.setOnClickListener(this);
        bAccountList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //admin clicks on Services button
            case R.id.bServiceList:
                String role = getIntent().getStringExtra("role");

                Intent service = new Intent(this, ServiceEditor.class);
                startActivity(service);
                break;
            //admin clicks on Accounts button
            case R.id.bAccountList:
                startActivity(new Intent(this, AccountSelector.class));
        }

    }
}