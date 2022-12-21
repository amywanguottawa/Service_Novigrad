package com.example.service_novigrad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewService extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ServiceHelperClass serviceHelperClass = new ServiceHelperClass(this);
        List<ServiceModelClass> serviceModelClasses = serviceHelperClass.getServiceList();

        if (serviceModelClasses.size() > 0){
            ServiceAdapterClass serviceadapterclass = new ServiceAdapterClass(serviceModelClasses, ViewService.this);
            recyclerView.setAdapter(serviceadapterclass);
        }else {
            Toast.makeText(this, "There is no service in the database", Toast.LENGTH_SHORT).show();
        }
    }

}