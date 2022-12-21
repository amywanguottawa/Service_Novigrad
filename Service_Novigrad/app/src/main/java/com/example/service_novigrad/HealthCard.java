package com.example.service_novigrad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.data.HealthCardHelper;
import com.example.service_novigrad.data.UserHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class HealthCard extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    EditText etFirstName, etLastName, etAddress, etBirthday;
    private String firstName, lastName, address, birthday;
    private static final int RESULT_LOAD_IMAGE = 123;
    ImageView residencePreview,statusPreview;
    Button residenceBtn,statusBtn;
    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    int button;
    FirebaseStorage storage;
    private StorageReference ref,ref2;
    private Uri uploadedImage, uploadedImage2;

    String namePattern= "[a-zA-Z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = FirebaseStorage.getInstance();
        ref = storage.getReference();
        ref2 = storage.getReference();

        setContentView(R.layout.activity_health_card);
        //residencePreview = findViewById((R.id.residencePreview));
        residenceBtn = findViewById(R.id.residencePick);
        //statusPreview = findViewById(R.id.statusPreview);
        statusBtn = findViewById(R.id.statusPick);
        residenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button = 1;
                i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Upload proof of residency"), RESULT_LOAD_IMAGE);

            }

        });
        statusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button = 2;
                        i = new Intent();
                        i.setType("image/*");
                        i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(i, "Upload proof of status"), RESULT_LOAD_IMAGE);

            }

        });
        etFirstName = (EditText) findViewById(R.id.firstName);
        etLastName = (EditText) findViewById(R.id.lastName);
        etAddress = (EditText) findViewById(R.id.address);
        etBirthday = (EditText) findViewById(R.id.birthdate);


        if(HealthCardHelper.firstName.equals("")){
            etFirstName.setVisibility(View.GONE);
        }
        if(HealthCardHelper.lastName.equals("")){
            etLastName.setVisibility(View.GONE);
        }
        if(HealthCardHelper.birthday.equals("")){
            etBirthday.setVisibility(View.GONE);
        }
        if(HealthCardHelper.address.equals("")){
            etAddress.setVisibility(View.GONE);
        }
        if(HealthCardHelper.residency.equals("")){
            residenceBtn.setVisibility(View.GONE);;
        }
        if(HealthCardHelper.status.equals("")){
            statusBtn.setVisibility(View.GONE);;
        }




    }

    public void submitClick(View v){
        boolean emptyCheck = true;

        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        address = etAddress.getText().toString();
        birthday = etBirthday.getText().toString();
        rootNode = FirebaseDatabase.getInstance();


        if (firstName.equals("") && (etFirstName.getVisibility() == View.VISIBLE) && !validateFirstName(firstName)) {
            etFirstName.setError("Enter a Name");
            emptyCheck = false;
        } else if (etFirstName.getVisibility() == View.GONE){
            firstName = "";
        }
        if (lastName.equals("") && (etLastName.getVisibility() == View.VISIBLE) && !validateLastName(lastName)) {
            etLastName.setError("Enter a Last Name");
            emptyCheck = false;
        } else if (etLastName.getVisibility() == View.GONE){
            lastName = "";
        }
        if (address.equals("") && (etAddress.getVisibility() == View.VISIBLE)) {
            etAddress.setError("Enter an address");
            emptyCheck = false;
        } else if (etAddress.getVisibility() == View.GONE){
            address = "";
        }
        if (birthday.equals("") && (etBirthday.getVisibility() == View.VISIBLE) && !validateBirthday(birthday)) {
            etBirthday.setError("Enter a birthday");
            emptyCheck = false;
        } else if (etBirthday.getVisibility() == View.GONE){
            birthday = "";
        }


        if (emptyCheck){
            reference = rootNode.getReference("health_card");
            UserHelper helperClass = new UserHelper(firstName,lastName,address,birthday);
            reference.child(lastName).setValue(helperClass);
            Toast.makeText(getApplicationContext(),"Health card form updated!",Toast.LENGTH_SHORT).show();
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




    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            if (button == 1) {
                uploadedImage = data.getData();
                residencePreview.setImageURI(uploadedImage);
            } else if (button == 2) {
                uploadedImage2 = data.getData();
                statusPreview.setImageURI(uploadedImage2);
                }

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

            StorageReference tmpRef = ref.child("images/"+firstName+lastName+"HealthCardDocumentResidence");
            tmpRef.putFile(uploadedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(HealthCard.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(HealthCard.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
            StorageReference tmpRef2 = ref2.child("images/"+firstName+lastName+"HealthCardDocumentStatus");
            tmpRef2.putFile(uploadedImage2)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(HealthCard.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(HealthCard.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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




