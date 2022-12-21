package com.example.service_novigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceEditor extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button bAddService, bViewServices;
    private String formType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_editor);

        bAddService = (Button) findViewById(R.id.bAddService);
        bViewServices = (Button) findViewById(R.id.bViewServices);

        Spinner spinnerRole = findViewById(R.id.srServiceType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.serviceType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);
        spinnerRole.setOnItemSelectedListener(this);

        formType = spinnerRole.getSelectedItem().toString();

        //Adding a service to the list of services offered
        bAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (formType.equals("Health Card")){
                    ServiceHelperClass serviceHelperClass = new ServiceHelperClass(ServiceEditor.this);
                    ServiceModelClass serviceModelClass = new ServiceModelClass(formType);
                    serviceHelperClass.addService(serviceModelClass);
                    Toast.makeText(ServiceEditor.this, "Add Service Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
                else if (formType.equals("Drivers License")){
                    ServiceHelperClass serviceHelperClass = new ServiceHelperClass(ServiceEditor.this);
                    ServiceModelClass serviceModelClass = new ServiceModelClass(formType);
                    serviceHelperClass.addService(serviceModelClass);
                    Toast.makeText(ServiceEditor.this, "Add Service Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
                else if (formType.equals("Photo Id")){
                    ServiceHelperClass serviceHelperClass = new ServiceHelperClass(ServiceEditor.this);
                    ServiceModelClass serviceModelClass = new ServiceModelClass(formType);
                    serviceHelperClass.addService(serviceModelClass);
                    Toast.makeText(ServiceEditor.this, "Add Service Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        //Viewing all the added services
        bViewServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceEditor.this, ViewService.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        formType = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), formType, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
    }

}