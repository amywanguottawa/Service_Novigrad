package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class customerViewServices extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String branchName;
    TextView s1,s2,s3,s4,s5,s6,p1,p2,p3,p4,p5,p6;
    Button bViewRequestS1, bViewRequestS2, bViewRequestS3, bViewRequestS4, bViewRequestS5, bViewRequestS6;
    String tmp;

    ArrayList<String> services, keys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_services);

        services = new ArrayList<>();
        keys = new ArrayList<>();
        s1 = (TextView) findViewById(R.id.tS1);
        s2 = (TextView) findViewById(R.id.tS2);
        s3 = (TextView) findViewById(R.id.tS3);
        s4 = (TextView) findViewById(R.id.tS4);
        s5 = (TextView) findViewById(R.id.tS5);
        s6 = (TextView) findViewById(R.id.tS6);

        p1 = (TextView) findViewById(R.id.price1);
        p2 = (TextView) findViewById(R.id.price2);
        p3 = (TextView) findViewById(R.id.price3);
        p4 = (TextView) findViewById(R.id.price4);
        p5 = (TextView) findViewById(R.id.price5);
        p6 = (TextView) findViewById(R.id.price6);

        bViewRequestS1 = (Button) findViewById(R.id.bViewRequestS1);
        bViewRequestS2 = (Button) findViewById(R.id.bViewRequestS2);
        bViewRequestS3 = (Button) findViewById(R.id.bViewRequestS3);
        bViewRequestS4 = (Button) findViewById(R.id.bViewRequestS4);
        bViewRequestS5 = (Button) findViewById(R.id.bViewRequestS5);
        bViewRequestS6 = (Button) findViewById(R.id.bViewRequestS6);



        Intent intent = getIntent();
        branchName = intent.getStringExtra("branch name");


        reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = reference.child("branch_services").child(branchName);;




        ValueEventListener eventListener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                int numChild = (int)dataSnapshot.getChildrenCount();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    services.add((String) ds.getValue());
                    keys.add(ds.getKey());

                }
                if(numChild == 0){
                    s1.setText("There are no services you have selected.");
                    bViewRequestS1.setVisibility(View.GONE);
                    bViewRequestS2.setVisibility(View.GONE);
                    bViewRequestS3.setVisibility(View.GONE);
                    bViewRequestS4.setVisibility(View.GONE);
                    bViewRequestS5.setVisibility(View.GONE);
                    bViewRequestS6.setVisibility(View.GONE);
                    p1.setVisibility(View.GONE);
                    p2.setVisibility(View.GONE);
                    p3.setVisibility(View.GONE);
                    p4.setVisibility(View.GONE);
                    p5.setVisibility(View.GONE);
                    p6.setVisibility(View.GONE);

                }

                if(numChild == 1){
                    s1.setText(services.get(0));
                    bViewRequestS2.setVisibility(View.GONE);
                    bViewRequestS3.setVisibility(View.GONE);
                    bViewRequestS4.setVisibility(View.GONE);
                    bViewRequestS5.setVisibility(View.GONE);
                    bViewRequestS6.setVisibility(View.GONE);
                    p2.setVisibility(View.GONE);
                    p3.setVisibility(View.GONE);
                    p4.setVisibility(View.GONE);
                    p5.setVisibility(View.GONE);
                    p6.setVisibility(View.GONE);

                } else if (numChild == 2){
                    s1.setText(services.get(0));
                    s2.setText(services.get(1));
                    bViewRequestS3.setVisibility(View.GONE);
                    bViewRequestS4.setVisibility(View.GONE);
                    bViewRequestS5.setVisibility(View.GONE);
                    bViewRequestS6.setVisibility(View.GONE);
                    p3.setVisibility(View.GONE);
                    p4.setVisibility(View.GONE);
                    p5.setVisibility(View.GONE);
                    p6.setVisibility(View.GONE);

                } else if (numChild == 3){
                    s1.setText(services.get(0));
                    s2.setText(services.get(1));
                    s3.setText(services.get(2));
                    bViewRequestS4.setVisibility(View.GONE);
                    bViewRequestS5.setVisibility(View.GONE);
                    bViewRequestS6.setVisibility(View.GONE);
                    p4.setVisibility(View.GONE);
                    p5.setVisibility(View.GONE);
                    p6.setVisibility(View.GONE);

                } else if (numChild == 4){
                    s1.setText(services.get(0));
                    s2.setText(services.get(1));
                    s3.setText(services.get(2));
                    s4.setText(services.get(3));

                    bViewRequestS5.setVisibility(View.GONE);
                    bViewRequestS6.setVisibility(View.GONE);
                    p5.setVisibility(View.GONE);
                    p6.setVisibility(View.GONE);

                } else if (numChild == 5 ){
                    s1.setText(services.get(0));
                    s2.setText(services.get(1));
                    s3.setText(services.get(2));
                    s4.setText(services.get(3));
                    s5.setText(services.get(4));
                    bViewRequestS6.setVisibility(View.GONE);
                    p6.setVisibility(View.GONE);


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



        usersdRef.addListenerForSingleValueEvent(eventListener);


    }
    public void onClick(View v){
        switch(v.getId()){

            case R.id.bViewRequestS1:
                tmp = services.get(0);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, drivers_license.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, photo_Id.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCard.class));
                }
                break;
            case R.id.bViewRequestS2:
                tmp = services.get(1);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, drivers_license.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, photo_Id.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCard.class));
                }
                break;
            case R.id.bViewRequestS3:
                tmp = services.get(2);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, drivers_license.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, photo_Id.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCard.class));
                }
                break;
            case R.id.bViewRequestS4:
                tmp = services.get(3);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, drivers_license.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, photo_Id.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCard.class));
                }
                break;
            case R.id.bViewRequestS5:
                tmp = services.get(4);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, drivers_license.class));
                } else if(tmp.equals("Photo Id")){
                    startActivity(new Intent(this, photo_Id.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCard.class));
                }
                break;
            case R.id.bViewRequestS6:
                tmp = services.get(5);
                if(tmp.equals("Drivers License")){
                    startActivity(new Intent(this, drivers_license.class));
                } else if( tmp.equals("Photo Id")){
                    startActivity(new Intent(this, photo_Id.class));
                } else if (tmp.equals("Health Card")){
                    startActivity(new Intent(this, HealthCard.class));
                }
                break;

        }
    }

}