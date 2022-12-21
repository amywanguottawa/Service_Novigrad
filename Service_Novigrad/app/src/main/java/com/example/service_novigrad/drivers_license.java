package com.example.service_novigrad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.data.DriversLicenseHelper;
import com.example.service_novigrad.data.UserHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class drivers_license extends AppCompatActivity implements OnItemSelectedListener {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private static final int RESULT_LOAD_IMAGE = 123;
    ImageView imageView;
    Button pickBtn;
    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    private String firstName, lastName, address, birthday, licenseType;
    EditText etFirstName, etLastName, etAddress, etBirthday;
    FirebaseStorage storage;
    private StorageReference ref;
    private Uri uploadedImage;

    String namePattern= "[a-zA-Z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_license);

        storage = FirebaseStorage.getInstance();
        ref = storage.getReference();

        Spinner spinnerLicenseType = findViewById(R.id.licenseSelect);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.licenseType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLicenseType.setAdapter(adapter);
        spinnerLicenseType.setOnItemSelectedListener(this);

        licenseType = spinnerLicenseType.getSelectedItem().toString();
        // = findViewById((R.id.imagePreview));
        pickBtn = findViewById(R.id.imagePick);
        etFirstName = (EditText) findViewById(R.id.firstName);
        etLastName = (EditText) findViewById(R.id.lastName);
        etAddress = (EditText) findViewById(R.id.address);
        etBirthday = (EditText) findViewById(R.id.birthdate);
        try {
            if(DriversLicenseHelper.firstName.equals("")){
                etFirstName.setVisibility(View.GONE);
            }
            if(DriversLicenseHelper.lastName.equals("")){
                etLastName.setVisibility(View.GONE);
            }
            if(DriversLicenseHelper.birthday.equals("")){
                etBirthday.setVisibility(View.GONE);
            }
            if(DriversLicenseHelper.address.equals("")){
                etAddress.setVisibility(View.GONE);
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }






        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"Upload proof of residency"),RESULT_LOAD_IMAGE );

            }

            });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        licenseType = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), licenseType, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void submitClick(View v){
        boolean emptyCheck = true;

        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        address = etAddress.getText().toString();
        birthday = etBirthday.getText().toString();
        rootNode = FirebaseDatabase.getInstance();

        if (firstName.equals("") && (etFirstName.getVisibility() == View.VISIBLE) && !validateFirstName(firstName) ) {
            etFirstName.setError("Enter a Name");
            emptyCheck = false;
        }
        if (lastName.equals("") && (etLastName.getVisibility() == View.VISIBLE) && !validateLastName(lastName)) {
            etLastName.setError("Enter a Last Name");
            emptyCheck = false;
        }
        if (address.equals("") && (etAddress.getVisibility()== View.VISIBLE)) {
            etAddress.setError("Enter an address");
            emptyCheck = false;
        }
        if (birthday.equals("") && (etBirthday.getVisibility()== View.VISIBLE) && !validateBirthday(birthday)) {
            etBirthday.setError("Enter a birthday");
            emptyCheck = false;
        }

        if (emptyCheck){
            reference = rootNode.getReference("drivers_license");
            UserHelper helperClass = new UserHelper(firstName,lastName,address,birthday,licenseType);
            reference.child(lastName).setValue(helperClass);

            Toast.makeText(getApplicationContext(),"License form updated!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HealthCard.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"One or more fields are empty",Toast.LENGTH_SHORT).show();

        }
        uploadImage();

    }

    /////////////////////////////////////TEST CASES//////////////////////////////////
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

    public boolean validateBirthday(String birthday){
        if (birthday.isEmpty())
            return false;
        else if (birthday.length() != 8)
            return false;
        else
            return true;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data !=null){
            uploadedImage = data.getData();
            imageView.setImageURI(uploadedImage);
        }
        try {
            Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), uploadedImage);
            imageView.setImageURI(uploadedImage);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    // method taken from https://code.tutsplus.com/tutorials/image-upload-to-firebase-in-android-application--cms-29934#:~:text=%20How%20to%20Upload%20Images%20to%20Firebase%20from,activity%20layout.%20Two%20buttons%20will%20be...%20More%20
    // modified for our use
    private void uploadImage() {

        if(uploadedImage != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference tmpRef = ref.child("images/"+ firstName+lastName+"DriverLicenseDocuments");
            tmpRef.putFile(uploadedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(drivers_license.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(drivers_license.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }


 
}