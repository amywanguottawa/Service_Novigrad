package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DriversLicenseRequests extends AppCompatActivity {

    ArrayList<String> array;
    ArrayList<DataSnapshot> dataArray;
    DatabaseReference reference, branchReference,tmpReference;
    String selectedRequest;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_license_requests);
        loadSpinnerData();
    }


    private void loadSpinnerData() {
        array = new ArrayList<>();
        dataArray = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference();

        branchReference = reference.child("service_request").child(UserTracker.getUser()).child("drivers_license");

        ValueEventListener eventListener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    dataArray.add(ds);
                    array.add(ds.getKey());

                }
                Spinner spinnerRole = findViewById(R.id.sDriversLicenseRequests);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(DriversLicenseRequests.this, android.R.layout.simple_list_item_1, array);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRole.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


        branchReference.addListenerForSingleValueEvent(eventListener);
    }

    public void onClick(View v){
        /*for(DataSnapshot ds: dataArray){
            if (ds.getValue() == selectedRequest){
                value = ds.getKey();
            }
        }*/
        switch(v.getId()){
            case R.id.bAccept:
                Spinner spinnerRole = findViewById(R.id.sDriversLicenseRequests);
                selectedRequest = spinnerRole.getSelectedItem().toString();
                tmpReference = FirebaseDatabase.getInstance().getReference("service_request").child(UserTracker.getUser()).child("drivers_license").child(selectedRequest);
                tmpReference.removeValue();
                Toast.makeText(getApplicationContext(),"Drivers license request accepted!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,employeeHub.class));
                break;
            case R.id.bReject:
                Spinner spinnerRole1 = findViewById(R.id.sDriversLicenseRequests);
                selectedRequest = spinnerRole1.getSelectedItem().toString();
                tmpReference = FirebaseDatabase.getInstance().getReference("service_request").child(UserTracker.getUser()).child("drivers_license").child(selectedRequest);
                tmpReference.removeValue();
                Toast.makeText(getApplicationContext(),"Drivers license request rejected!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,employeeHub.class));
                break;


        }





    }

}