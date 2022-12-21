package com.example.service_novigrad;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfileInfo extends AppCompatActivity {

    String phoneNumFormat = "^[+][0-9]{10,13}$";
    String addressFormat = "\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.";

    TextView addressDisplay , phoneNumberDisplay;
    DatabaseReference reference;
    String address, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_info);

        addressDisplay = (TextView) findViewById(R.id.addressDisplay);
        phoneNumberDisplay = (TextView) findViewById(R.id.phoneNumberDisplay);

        reference = FirebaseDatabase.getInstance().getReference().child("branch_info").child(UserTracker.getUser());




        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    address = dataSnapshot.child("address").getValue().toString();
                    phoneNumber = dataSnapshot.child("phoneNumber").getValue().toString();
                    addressDisplay.setText(address);
                    phoneNumberDisplay.setText(phoneNumber);
                    //Test Cases
                    //validatePhoneNumber(phoneNumber);
                    //validateAddress(address);

                } catch(NullPointerException e){
                    addressDisplay.setText("Please enter an address");
                    phoneNumberDisplay.setText("Please enter a phone number");
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                addressDisplay.setText("Please enter your address first.");
                phoneNumberDisplay.setText("Please enter your phone number first.");

            }
        });
    }

    /////////////////////////////////////TEST CASES//////////////////////////////////
//    public ViewProfileInfo(Context context){
//
//    }
//
//    public boolean validatePhoneNumber(String phoneNum) {
//        if (phoneNum.isEmpty())
//            return false;
//        else if (!phoneNum.trim().matches(phoneNumFormat))
//            return false;
//        else
//            return true;
//    }
//
//    public boolean validateAddress(String address){
//        if (address.isEmpty())
//            return false;
//        else if (!address.trim().matches(addressFormat))
//            return false;
//        else
//            return true;
//    }


}