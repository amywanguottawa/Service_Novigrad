package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewEmployeeServices extends AppCompatActivity {
    TextView s1,s2,s3,s4,s5,s6;
    Button bDeleteS1 ,bDeleteS2 ,bDeleteS3 ,bDeleteS4 ,bDeleteS5 ,bDeleteS6, bViewRequestS1, bViewRequestS2, bViewRequestS3, bViewRequestS4, bViewRequestS5, bViewRequestS6;
    FirebaseDatabase rootNode;
    DatabaseReference reference, branchServices;
    ArrayList<String> services, keys;
    String tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_services);
        services = new ArrayList<>();
        keys = new ArrayList<>();
        s1 = (TextView) findViewById(R.id.tS1);
        s2 = (TextView) findViewById(R.id.tS2);
        s3 = (TextView) findViewById(R.id.tS3);
        s4 = (TextView) findViewById(R.id.tS4);
        s5 = (TextView) findViewById(R.id.tS5);
        s6 = (TextView) findViewById(R.id.tS6);

        bDeleteS1 = (Button) findViewById(R.id.bDeleteS1);
        bDeleteS2 = (Button) findViewById(R.id.bDeleteS2);
        bDeleteS3 =  (Button) findViewById(R.id.bDeleteS3);
        bDeleteS4 = (Button) findViewById(R.id.bDeleteS4);
        bDeleteS5 = (Button) findViewById(R.id.bDeleteS5);
        bDeleteS6 = (Button) findViewById(R.id.bDeleteS6);

        bViewRequestS1 = (Button) findViewById(R.id.bViewRequestS1);
        bViewRequestS2 = (Button) findViewById(R.id.bViewRequestS2);
        bViewRequestS3 = (Button) findViewById(R.id.bViewRequestS3);
        bViewRequestS4 = (Button) findViewById(R.id.bViewRequestS4);
        bViewRequestS5 = (Button) findViewById(R.id.bViewRequestS5);
        bViewRequestS6 = (Button) findViewById(R.id.bViewRequestS6);

        reference = FirebaseDatabase.getInstance().getReference();
        System.out.println(UserTracker.getUser());
        branchServices = reference.child("branch_services").child(UserTracker.getUser());

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int numChild = (int)dataSnapshot.getChildrenCount();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    services.add((String) ds.getValue());
                    keys.add(ds.getKey());
                }

                if(numChild == 0){
                    s1.setText("There are no services you have selected.");
                    bDeleteS1.setVisibility(View.GONE);
                    bDeleteS2.setVisibility(View.GONE);
                    bDeleteS3.setVisibility(View.GONE);
                    bDeleteS4.setVisibility(View.GONE);
                    bDeleteS5.setVisibility(View.GONE);
                    bDeleteS6.setVisibility(View.GONE);
                    bViewRequestS1.setVisibility(View.GONE);
                    bViewRequestS2.setVisibility(View.GONE);
                    bViewRequestS3.setVisibility(View.GONE);
                    bViewRequestS4.setVisibility(View.GONE);
                    bViewRequestS5.setVisibility(View.GONE);
                    bViewRequestS6.setVisibility(View.GONE);

                }

                if(numChild == 1){
                        s1.setText(services.get(0));
                        bDeleteS2.setVisibility(View.GONE);
                        bDeleteS3.setVisibility(View.GONE);
                        bDeleteS4.setVisibility(View.GONE);
                        bDeleteS5.setVisibility(View.GONE);
                        bDeleteS6.setVisibility(View.GONE);
                        bViewRequestS2.setVisibility(View.GONE);
                        bViewRequestS3.setVisibility(View.GONE);
                        bViewRequestS4.setVisibility(View.GONE);
                        bViewRequestS5.setVisibility(View.GONE);
                        bViewRequestS6.setVisibility(View.GONE);

                } else if (numChild == 2){
                        s1.setText(services.get(0));
                        s2.setText(services.get(1));
                        bDeleteS3.setVisibility(View.GONE);
                        bDeleteS4.setVisibility(View.GONE);
                        bDeleteS5.setVisibility(View.GONE);
                        bDeleteS6.setVisibility(View.GONE);
                        bViewRequestS3.setVisibility(View.GONE);
                        bViewRequestS4.setVisibility(View.GONE);
                        bViewRequestS5.setVisibility(View.GONE);
                        bViewRequestS6.setVisibility(View.GONE);

                } else if (numChild == 3){
                        s1.setText(services.get(0));
                        s2.setText(services.get(1));
                        s3.setText(services.get(2));
                        bDeleteS4.setVisibility(View.GONE);
                        bDeleteS5.setVisibility(View.GONE);
                        bDeleteS6.setVisibility(View.GONE);
                        bViewRequestS4.setVisibility(View.GONE);
                        bViewRequestS5.setVisibility(View.GONE);
                        bViewRequestS6.setVisibility(View.GONE);

                } else if (numChild == 4){
                        s1.setText(services.get(0));
                        s2.setText(services.get(1));
                        s3.setText(services.get(2));
                        s4.setText(services.get(3));
                        bDeleteS5.setVisibility(View.GONE);
                        bDeleteS6.setVisibility(View.GONE);
                        bViewRequestS5.setVisibility(View.GONE);
                        bViewRequestS6.setVisibility(View.GONE);

                } else if (numChild == 5 ){
                        s1.setText(services.get(0));
                        s2.setText(services.get(1));
                        s3.setText(services.get(2));
                        s4.setText(services.get(3));
                        s5.setText(services.get(4));
                        bDeleteS6.setVisibility(View.GONE);
                        bViewRequestS6.setVisibility(View.GONE);


                } else if (numChild == 6){
                        s1.setText(services.get(0));
                        s2.setText(services.get(1));
                        s3.setText(services.get(2));
                        s4.setText(services.get(3));
                        s5.setText(services.get(4));
                        s6.setText(services.get(5));


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        branchServices.addListenerForSingleValueEvent(eventListener);


    }

    //@Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bViewRequestS1:
                tmp = services.get(0);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, DriversLicenseRequests.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, PhotoIdRequests.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCardRequests.class));
                }
                break;
            case R.id.bViewRequestS2:
                tmp = services.get(1);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, DriversLicenseRequests.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, PhotoIdRequests.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCardRequests.class));
                }
                break;
            case R.id.bViewRequestS3:
                tmp = services.get(2);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, DriversLicenseRequests.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, PhotoIdRequests.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCardRequests.class));
                }
                break;
            case R.id.bViewRequestS4:
                tmp = services.get(3);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, DriversLicenseRequests.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, PhotoIdRequests.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCardRequests.class));
                }
                break;
            case R.id.bViewRequestS5:
                tmp = services.get(4);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, DriversLicenseRequests.class));
                } else if(tmp.equals("Photo Id")){
                    startActivity(new Intent(this, PhotoIdRequests.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCardRequests.class));
                }
                break;
            case R.id.bViewRequestS6:
                tmp = services.get(5);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, DriversLicenseRequests.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, PhotoIdRequests.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCardRequests.class));
                }
                break;

        }
    }
}