package com.example.service_novigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.service_novigrad.data.UserHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountEditor extends AppCompatActivity {
    EditText etFirstName, etLastName, etEmail, etRole, etPassword;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private String firstName, lastName, email, role, password;
    String user;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    String[] items = {"Yes","No"};
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         this.user = getIntent().getStringExtra("user");
        setContentView(R.layout.activity_account_editor);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);


        final Spinner spinnerRole = findViewById(R.id.srRole);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rolesAdmin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);
        //spinnerRole.setOnItemSelectedListener(this);
        role = spinnerRole.getSelectedItem().toString();
        reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = reference.child("users");

        ValueEventListener eventListener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getKey().equals(user)){
                        UserHelper userHelper = ds.getValue(UserHelper.class);
                        assert userHelper != null;
                        etEmail.setText(userHelper.getEmail());
                        etFirstName.setText(userHelper.getFirstName());
                        etLastName.setText(userHelper.getLastName());
                        etPassword.setText(userHelper.getPassword());
                        switch (userHelper.getRole()){
                            case "Administrator":
                                spinnerRole.setSelection(0);
                                break;
                            case "Employee":
                                spinnerRole.setSelection(1);
                                break;
                            case "Customer":
                                spinnerRole.setSelection(2);
                                break;
                        }

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };



        usersdRef.addListenerForSingleValueEvent(eventListener);



    }
    public void save(View v) {
        boolean emptyCheck = true;

        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        rootNode = FirebaseDatabase.getInstance();
        final Spinner spinnerRole = findViewById(R.id.srRole);

        role = (String) spinnerRole.getSelectedItem();
        if (firstName.equals("")) {
            etFirstName.setError("Enter a Name");
            emptyCheck = false;
        }
        if (lastName.equals("")) {
            etLastName.setError("Enter a Last Name");
            emptyCheck = false;
        }
        if (email.equals("")) {
            etEmail.setError("Enter an Email");
            emptyCheck = false;
        }

        if (password.equals("")){
            etPassword.setError("Enter a Password");
            emptyCheck = false;
        }


        if (emptyCheck){
            reference = rootNode.getReference("users");
            UserHelper helperClass = new UserHelper(firstName,lastName,email,user,password,role);
            reference.child(user).setValue(helperClass);

            Toast.makeText(getApplicationContext(),"Account Edited!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AccountSelector.class);
            startActivity(intent);
        }


    }

    public void delete(View v) {
        intent  = new Intent(this, AccountSelector.class);
        builder = new AlertDialog.Builder(AccountEditor.this);
        reference = FirebaseDatabase.getInstance().getReference("users").child(user);

        builder.setMessage("Are you sure you want to delete this user?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(UserTracker.getUser().equals(user) ){
                    Toast.makeText(getApplicationContext(),"You can't Delete your own account!",Toast.LENGTH_SHORT).show();

                }else{
                    reference.removeValue();

                    Toast.makeText(getApplicationContext(),"Account Deleted!",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }



            }
        });


        dialog = builder.create();
        dialog.show();


    }


}