package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
    public static String username;
    public String password;



    //getters for username and password
    public static String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

    }


    private boolean validateUsername(){
        String val = etUsername.getText().toString();
        if (val.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    private boolean validatePassword(){
        String val = etPassword.getText().toString();
        if (val.isEmpty()){
            return false;
        }else{
            return true;
        }
    }


    public void loginUser(View view){
        if (validatePassword() && validateUsername()){
            isUser();
        }
        if (!validatePassword()) etPassword.setError("Enter a password");
        if (!validateUsername()) etUsername.setError("Enter a username");


    }

    private void isUser() {
        final String userEnteredUsername = etUsername.getText().toString().trim();
        final String userEnteredPassword = etPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("userName").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)){
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String firstNameFromDB = dataSnapshot.child(userEnteredUsername).child("firstName").getValue(String.class);
                        String lastNameFromDB = dataSnapshot.child(userEnteredUsername).child("lastName").getValue(String.class);
                        String roleFromDB = dataSnapshot.child(userEnteredUsername).child("role").getValue(String.class);
                        UserTracker.setRole(roleFromDB);
                        UserTracker.setUser(userEnteredUsername);
                        Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);
                        intent.putExtra("firstName",firstNameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("lastName",lastNameFromDB);
                        intent.putExtra("role",roleFromDB);

                        startActivity(intent);

                    }else{
                        etPassword.setError("Wrong password");
                    }

                }else{
                    etUsername.setError("No such user exists");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }


    public void goToRegister(View v) {
            etUsername.setText("");
            etPassword.setText("");
            startActivity(new Intent(this, Register.class));

    }





}