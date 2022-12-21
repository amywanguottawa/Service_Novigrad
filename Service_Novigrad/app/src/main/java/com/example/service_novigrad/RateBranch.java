package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RateBranch extends AppCompatActivity {

    RatingBar branchRating;
    Button bSubmitRating,bSubmitComment;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView etBranchName;
    String branchName;
    EditText etComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_branch);
        bSubmitRating = (Button) findViewById(R.id.bSubmitRating);
        branchRating = findViewById(R.id.branchRating);
        etBranchName = findViewById(R.id.etBranchName);
        bSubmitComment = (Button) findViewById(R.id.bSubmitComment);
        Intent intent = getIntent();
        branchName = intent.getStringExtra("branch name");
        etBranchName.setText(branchName);
        etComment = findViewById(R.id.etComment);
    }


    public void onClick(View v){
        rootNode = FirebaseDatabase.getInstance();
        switch (v.getId()){
            case R.id.bSubmitRating:
                float rating = branchRating.getRating();
                reference = rootNode.getReference("ratings");
                reference.child(branchName).child("rating").push().setValue(rating);
                Toast.makeText(getApplicationContext(),"You rated the branch "+rating+" stars!",Toast.LENGTH_LONG).show();
                break;
            case R.id.bSubmitComment:
                String userComment = etComment.getText().toString();
                reference = rootNode.getReference("ratings");
                reference.child(branchName).child("comment").push().setValue(userComment);
                Toast.makeText(getApplicationContext(),"Your comment has been passed on to the branch!",Toast.LENGTH_LONG).show();
                break;

        }
    }
}