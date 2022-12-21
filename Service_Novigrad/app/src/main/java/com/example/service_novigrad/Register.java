package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.data.UserHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button bRegister;
    EditText etFirstName, etLastName, etEmail, etRole, etUsername, etPassword;
    private String firstName, lastName, email, role, username, password;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePattern= "[a-zA-Z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);

        Spinner spinnerRole = findViewById(R.id.srRole);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);
        spinnerRole.setOnItemSelectedListener(this);

        role = spinnerRole.getSelectedItem().toString();

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById (R.id.bRegister);
        bRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        boolean emptyCheck = true;

        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        email = etEmail.getText().toString();
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        rootNode = FirebaseDatabase.getInstance();

        if (firstName.equals("") || !validFirstName() || validateFirstName(firstName)) {
            etFirstName.setError("Enter a valid Name");
            emptyCheck = false;
        }
        if (lastName.equals("") || !validLastName() || validateLastName(lastName)) {
            etLastName.setError("Enter a valid Last Name");
            emptyCheck = false;
        }
        if (email.equals("") || !validEmail() || validateEmail(email)) {
            etEmail.setError("Enter a valid Email");
            emptyCheck = false;
        }
        if (username.equals("")) {
            etUsername.setError("Enter a valid Username");
            emptyCheck = false;
        }
        if (password.equals("") || !validPassword()){
            etPassword.setError("Enter a valid Password");
            emptyCheck = false;
        }


        if (emptyCheck){
            reference = rootNode.getReference("users");
            UserHelper helperClass = new UserHelper(firstName,lastName,email,username,password,role);
            reference.child(username).setValue(helperClass);

            Toast.makeText(getApplicationContext(),"Account Created!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

    }

    private boolean validEmail(){
        String val = etEmail.getText().toString().trim();
        if(val != null){
            return android.util.Patterns.EMAIL_ADDRESS.matcher(val).matches();
        }else {
            return false;
        }

    }

    private boolean validFirstName(){
        String val = etFirstName.getText().toString().trim();
        return val.matches("[a-zA-Z]+");
    }
    private boolean validLastName(){
        String val = etLastName.getText().toString().trim();
        return val.matches("[a-zA-Z]+");
    }

    private boolean validPassword(){
        String val = etPassword.getText().toString();
        if(val.length() < 8){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        role = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), role, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /////////////////////////////////////TEST CASES//////////////////////////////////
    public boolean validateEmail(String email) {
        if(email.isEmpty())  //field is empty
            return false;
        else if (!email.trim().matches(emailPattern))    //input is not in an email format
            return false;
        else
            return true;
    }

    public boolean validateFirstName(String firstName){
        if (firstName.isEmpty())
            return false;
        else if (!firstName.matches(namePattern))
            return false;
        else
            return true;
    }

    public boolean validateLastName(String lastName){
        if (lastName.isEmpty())
            return false;
        else if (!lastName.matches(namePattern))
            return false;
        else
            return true;
    }

}