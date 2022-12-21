package com.example.service_novigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccountSelector extends AppCompatActivity {
    ArrayList<String> array;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_selector);
        loadSpinnerData();

    }
    private void loadSpinnerData() {
        array = new ArrayList<>();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("users");

        ValueEventListener eventListener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    array.add(ds.getKey());

                }
                Spinner spinnerRole = findViewById(R.id.srUser);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(AccountSelector.this, android.R.layout.simple_list_item_1, array);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRole.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


        usersdRef.addListenerForSingleValueEvent(eventListener);
    }
    public void goToEditor(View v) {
        Spinner spinnerRole = findViewById(R.id.srUser);
        user = spinnerRole.getSelectedItem().toString();
        Intent intent = new Intent(getApplicationContext(),AccountEditor.class);
        intent.putExtra("user",user);
        startActivity(intent);

    }

}