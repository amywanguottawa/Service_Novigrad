package com.example.service_novigrad;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BranchSearch extends AppCompatActivity {
    //searchBranch
    //rvBranch
    DatabaseReference ref;
    ArrayList<modelBranch> list;
    ArrayList<String> nameList;

    RecyclerView rvBranch;
    SearchView searchBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_search);
        ref = FirebaseDatabase.getInstance().getReference().child("branch_info");
        rvBranch = (RecyclerView) findViewById(R.id.rvBranch);
        searchBranch = (SearchView) findViewById(R.id.searchBranch);
    }

    @Override
    protected void onStart() {

        super.onStart();
        if (ref != null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){

                        list = new ArrayList<>();
                        nameList = new ArrayList<>();

                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            modelBranch  m = ds.getValue(modelBranch.class);
                            m.setName(ds.getKey());
                            list.add(m);

                        }
                        adapterBranch adapBranch = new adapterBranch(list,BranchSearch.this);
                        rvBranch.setAdapter(adapBranch);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(BranchSearch.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (searchBranch != null){
            searchBranch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search(String str) {
        ArrayList<modelBranch> myList = new ArrayList<>();
        for (modelBranch object : list){
            if (object.getAddress().toLowerCase().contains(str.toLowerCase())) {
                myList.add(object);
            }else if (object.getPhoneNumber().toLowerCase().contains(str.toLowerCase())) {
                myList.add(object);
            }else if (object.getName().toLowerCase().contains(str.toLowerCase())) {
                myList.add(object);
            }

        }



        adapterBranch adapBranch = new adapterBranch(myList,BranchSearch.this);
        rvBranch.setAdapter(adapBranch);
    }

}
