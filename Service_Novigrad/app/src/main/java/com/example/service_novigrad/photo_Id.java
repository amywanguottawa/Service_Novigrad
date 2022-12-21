package com.example.service_novigrad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_novigrad.data.PhotoIdHelper;
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

public class photo_Id extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 123;
    ImageView imageView;
    ImageView imageView2;
    EditText etFirstName, etLastName, etAddress, etBirthday;
    private String firstName, lastName, address, birthday;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button pickBtn;
    Button pickBtn2;
    int button;
    private Uri uploadedImage, uploadedImage2;
    private StorageReference ref, ref2;
    FirebaseStorage storage;

    String namePattern= "[a-zA-Z]+";

    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo__id);


        //imageView = findViewById((R.id.residencePreview));
        pickBtn = findViewById(R.id.residencePick);
        //imageView2 = findViewById((R.id.pImage));
        pickBtn2 = findViewById(R.id.personalImage);

        storage = FirebaseStorage.getInstance();
        ref = storage.getReference();
        ref2 = storage.getReference();

        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                button=1;
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"Upload proof of residency"),RESULT_LOAD_IMAGE );

            }

        });


        pickBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                button=2;
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"Upload a picture of yourself"),RESULT_LOAD_IMAGE );

            }

        });
        etFirstName = (EditText) findViewById(R.id.firstName);
        etLastName = (EditText) findViewById(R.id.lastName);
        etAddress = (EditText) findViewById(R.id.address);
        etBirthday = (EditText) findViewById(R.id.birthdate);
        try {
            if(PhotoIdHelper.firstName.equals("")){
                etFirstName.setVisibility(View.GONE);
            }
            if(PhotoIdHelper.lastName.equals("")){
                etLastName.setVisibility(View.GONE);
            }
            if(PhotoIdHelper.birthday.equals("")){
                etBirthday.setVisibility(View.GONE);
            }
            if(PhotoIdHelper.address.equals("")){
                etAddress.setVisibility(View.GONE);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
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
        }
        if (lastName.equals("") && (etLastName.getVisibility() == View.VISIBLE) && !validateLastName(lastName)) {
            etLastName.setError("Enter a Last Name");
            emptyCheck = false;
        }
        if (address.equals("") && (etAddress.getVisibility() == View.VISIBLE)) {
            etAddress.setError("Enter an address");
            emptyCheck = false;
        }
        if (birthday.equals("") && (etBirthday.getVisibility() == View.VISIBLE) && !validateBirthday(birthday)) {
            etBirthday.setError("Enter a birthday");
            emptyCheck = false;
        }

        if (emptyCheck){
            reference = rootNode.getReference("photo_id");
            UserHelper helperClass = new UserHelper(firstName,lastName,address,birthday);
            reference.child(lastName).setValue(helperClass);

            Toast.makeText(getApplicationContext(),"Photo id updated!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, photo_Id.class);
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




    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data !=null){
            if(button ==1){
                uploadedImage = data.getData();
                imageView.setImageURI(uploadedImage);

            }else if(button ==2){
                if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
                    uploadedImage2 = data.getData();
                    imageView2.setImageURI(uploadedImage);

                    try {
                        Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), uploadedImage);
                        imageView2.setImageURI(uploadedImage);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void uploadImage() {

        if(uploadedImage != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference tmpRef = ref.child("images/"+firstName+lastName+"PhotoIDDocumentResidency");
            tmpRef.putFile(uploadedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(photo_Id.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(photo_Id.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
            StorageReference tmpRef2 = ref2.child("images/"+firstName+lastName+"PhotoIDDocumentYourself");
            tmpRef2.putFile(uploadedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(photo_Id.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(photo_Id.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
