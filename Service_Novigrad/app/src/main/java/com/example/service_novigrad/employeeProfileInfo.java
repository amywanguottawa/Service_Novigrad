package com.example.service_novigrad;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.data.UserHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class employeeProfileInfo extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button bSubmit;
    EditText etAddress , etPhoneNumber;
    private String address , phoneNumber, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile_info);
        reference = FirebaseDatabase.getInstance().getReference().child("branch_info").child(UserTracker.getUser());

        etAddress = (EditText) findViewById(R.id.etAddress);
        etPhoneNumber =  (EditText)findViewById(R.id.etPhoneNumber);
        PhoneNumberUtils.formatNumber(etPhoneNumber.getText().toString(), "1");

        bSubmit = (Button) findViewById(R.id.bSubmit);
        bSubmit.setOnClickListener(this);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    address = dataSnapshot.child("address").getValue().toString();
                    phoneNumber = dataSnapshot.child("phoneNumber").getValue().toString();
                    etAddress.setText(address);
                    etPhoneNumber.setText(phoneNumber);
                    //Test Cases
                    //validatePhoneNumber(phoneNumber);
                    //validateAddress(address);

                } catch(NullPointerException e){

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                etAddress.setText("Please enter your address first.");
                etPhoneNumber.setText("Please enter your phone number first.");

            }
        });
    }








    @Override
    public void onClick(View v){
        boolean emptyCheck = true;

        address = etAddress.getText().toString();
        phoneNumber = etPhoneNumber.getText().toString();
        rootNode = FirebaseDatabase.getInstance();
        if(address.equals("")){
            etAddress.setError("Enter an address.");
            emptyCheck = false;
        }
        if(phoneNumber.equals("")){
            etPhoneNumber.setError("Enter a phone number.");
            emptyCheck = false;
        }

        if(phoneNumber.contains("-") || phoneNumber.length() != 10){
            etPhoneNumber.setError("Not a valid phone number. Please do not use hyphens and make sure the phone number is 10 digits long.");
            emptyCheck = false;
        }


        if (emptyCheck){
            reference = rootNode.getReference("branch_info");
            UserHelper helperClass = new UserHelper(address,phoneNumber);
            username = getIntent().getStringExtra("username");
            reference.child(UserTracker.getUser()).setValue(helperClass);

            Toast.makeText(getApplicationContext(),"Branch Information Saved!",Toast.LENGTH_SHORT).show();

        }








    }


}