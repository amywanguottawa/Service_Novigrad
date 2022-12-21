package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PhotoIdRequests extends AppCompatActivity {

    ArrayList<String> array;
    DatabaseReference reference, branchReference, tmpReference;
    String selectedRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_id_requests);
        loadSpinnerData();
    }


    private void loadSpinnerData() {
        array = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference();

        branchReference = reference.child("service_request").child(UserTracker.getUser()).child("photo_id");

        ValueEventListener eventListener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    array.add(ds.getKey());

                }
                Spinner spinnerRole = findViewById(R.id.sPhotoIdRequests);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(PhotoIdRequests.this, android.R.layout.simple_list_item_1, array);
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
        switch(v.getId()){
            case R.id.bAccept:
                Spinner spinnerRole = findViewById(R.id.sPhotoIdRequests);
                selectedRequest = spinnerRole.getSelectedItem().toString();
                tmpReference = FirebaseDatabase.getInstance().getReference("service_request").child(UserTracker.getUser()).child("photo_id").child(selectedRequest);
                tmpReference.removeValue();
                Toast.makeText(getApplicationContext(),"Photo ID request accepted!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,employeeHub.class));
                break;
            case R.id.bReject:
                Spinner spinnerRole1 = findViewById(R.id.sPhotoIdRequests);
                selectedRequest = spinnerRole1.getSelectedItem().toString();
                tmpReference = FirebaseDatabase.getInstance().getReference("service_request").child(UserTracker.getUser()).child("photo_id").child(selectedRequest);
                tmpReference.removeValue();
                Toast.makeText(getApplicationContext(),"Photo ID request rejected!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,employeeHub.class));
                break;


        }
    }
}