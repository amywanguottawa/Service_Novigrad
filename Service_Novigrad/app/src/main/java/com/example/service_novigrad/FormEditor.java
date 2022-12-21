package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.data.DriversLicenseHelper;
import com.example.service_novigrad.data.HealthCardHelper;
import com.example.service_novigrad.data.PhotoIdHelper;


import java.util.ArrayList;

public class FormEditor extends AppCompatActivity {

    ArrayList<String> selection = new ArrayList<String>();
    TextView tvFinalResult;
    Button bViewForm;
    String formType;
    String firstName, lastName , birthday, address, residency, status, yourself;
    HealthCardHelper healthHelper;
    DriversLicenseHelper driverHelper;
    PhotoIdHelper photoHelper;

    CheckBox cbFirstName, cbLastName, cbBirthday, cbAddress, cbResidency, cbStatus, cbFacePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_editor);

        tvFinalResult = (TextView) findViewById(R.id.tvFinalResult);
        tvFinalResult.setEnabled(false);

        Intent intent = getIntent();
        formType = intent.getStringExtra("form type");

        bViewForm = (Button) findViewById(R.id.bViewForm);

        bViewForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formType.equals("Health Card")) {
                    Intent intent = new Intent(FormEditor.this, HealthCard.class);
                    startActivity(intent);
                } else if (formType.equals("Drivers License")) {
                    Intent intent = new Intent(FormEditor.this, drivers_license.class);
                    intent.putExtra("form fields",selection);
                    startActivity(intent);
                } else if (formType.equals("Photo Id")) {
                    Intent intent = new Intent(FormEditor.this, photo_Id.class);
                    intent.putExtra("form fields",selection);
                    startActivity(intent);
                }
            }
        });

        cbFirstName = (CheckBox) findViewById(R.id.cbFirstName);
        cbLastName = findViewById(R.id.cbLastName);
        cbAddress = findViewById(R.id.cbAddress);
        cbBirthday = findViewById(R.id.cbBirthday);
        cbResidency = findViewById(R.id.cbResidency);
        cbStatus = findViewById(R.id.cbStatus);
        cbFacePic = findViewById(R.id.cbFacePic);
        firstName = "";
        lastName = "";
        birthday = "";
        address = "";
        residency = "";
        status = "";
        yourself = "";





    }

    public void selectItem(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.cbFirstName:
                if(checked){
                    firstName = "first";
                    selection.add("First Name");
                } else  {
                    selection.remove("First Name");

                }
                break;
            case R.id.cbLastName:
                if(checked) {
                    lastName = "last";
                    selection.add("Last Name");
                } else  {
                    selection.remove("Last Name");
                }
                break;
            case R.id.cbAddress:
                if(checked) {
                    address = "address";
                    selection.add("Address");
                } else {
                    selection.remove("Address");
                }
                break;
            case R.id.cbBirthday:
                if(checked) {
                    birthday = "birthday";
                    selection.add("Birthday");
                } else {
                    selection.remove("Birthday");
                }
                break;
            case R.id.cbResidency:
                if(checked) {
                    residency = "residency";
                    selection.add("Proof of residency");
                } else {
                    selection.remove("Proof of residency");
                }
                break;
            case R.id.cbStatus:
                if(checked) {
                    status = "status";
                    selection.add("Proof of status");
                } else {
                    selection.remove("Proof of status");
                }
                break;
            case R.id.cbFacePic:
                if(checked) {
                    yourself = "yourself";
                    selection.add("Photo of yourself");
                } else {
                    selection.remove("Photo of yourself");
                }
                break;
        }
        if (formType.equals("Health Card")){
            healthHelper = new HealthCardHelper(firstName,lastName,birthday,address,residency,status,yourself);
        } else if (formType.equals("Drivers License")){
            driverHelper = new DriversLicenseHelper(firstName,lastName,birthday,address,residency,status,yourself);
        } else if (formType.equals("Photo Id")){
            photoHelper = new PhotoIdHelper(firstName,lastName,birthday,address,residency,status,yourself);
        }
    }

    public void finalSelection(View view) {

        String finalFormSelection = "";
        for (String Selections : selection) {
            finalFormSelection = finalFormSelection + Selections + "\n";
        }
        tvFinalResult.setText(finalFormSelection);
        tvFinalResult.setEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}