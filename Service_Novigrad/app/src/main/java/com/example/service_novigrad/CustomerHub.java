package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerHub extends AppCompatActivity implements View.OnClickListener {

    Button bSearchServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_hub);
        bSearchServices = (Button) findViewById(R.id.bSearchServices);
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.bSearchServices:
                startActivity(new Intent(this, BranchSearch.class));
                break;

        }


    }


}