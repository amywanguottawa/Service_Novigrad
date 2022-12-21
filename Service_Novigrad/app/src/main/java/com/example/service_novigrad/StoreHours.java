package com.example.service_novigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.WorkSource;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.service_novigrad.data.DateHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreHours extends AppCompatActivity implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    TextView closingTime;
    TextView openingTime;
    TextView mondayOpen,tuesdayOpen,wednesdayOpen,thursdayOpen,fridayOpen,saturdayOpen,sundayOpen;
    TextView mondayClose,tuesdayClose,wednesdayClose,thursdayClose,fridayClose,saturdayClose,sundayClose;
    CheckBox working;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String day;
    Spinner spinnerDay;
    TimePickerDialog time;
    int currentHour;
    int currentMinute;
    String amPm;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = reference.child("hours");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.getKey().equals(UserTracker.user)){
                        DateHelper dateHelper = ds.getValue(DateHelper.class);
                        mondayOpen.setText(dateHelper.getMondayOpen());
                        mondayClose.setText(dateHelper.getMondayClose());

                        tuesdayOpen.setText(dateHelper.getTuesdayOpen());
                        tuesdayClose.setText(dateHelper.getTuesdayClose());

                        wednesdayOpen.setText(dateHelper.getWednesdayOpen());
                        wednesdayClose.setText(dateHelper.getWednesdayClose());

                        thursdayOpen.setText(dateHelper.getThursdayOpen());
                        thursdayClose.setText(dateHelper.getThursdayClose());

                        fridayOpen.setText(dateHelper.getFridayOpen());
                        fridayClose.setText(dateHelper.getFridayClose());

                        saturdayOpen.setText(dateHelper.getSaturdayOpen());
                        saturdayClose.setText(dateHelper.getSaturdayClose());

                        sundayOpen.setText(dateHelper.getSundayOpen());
                        sundayClose.setText(dateHelper.getSundayClose());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        usersdRef.addListenerForSingleValueEvent(eventListener);

        setContentView(R.layout.activity_store_hours);
        working = findViewById(R.id.cbWorking);
        mondayOpen = findViewById(R.id.txtMondayOpen);
        mondayClose = findViewById(R.id.txtMondayClose);
        tuesdayOpen = findViewById(R.id.txtTuesdayOpen);
        tuesdayClose = findViewById(R.id.txtTuesdayClose);
        wednesdayOpen = findViewById(R.id.txtWednesdayOpen);
        wednesdayClose = findViewById(R.id.txtWednesdayClose);
        thursdayOpen = findViewById(R.id.txtThursdayOpen);
        thursdayClose = findViewById(R.id.txtThursdayClose);
        fridayOpen = findViewById(R.id.txtFridayOpen);
        fridayClose = findViewById(R.id.txtFridayClose);
        saturdayOpen = findViewById(R.id.txtSaturdayOpen);
        saturdayClose = findViewById(R.id.txtSaturdayClose);
        sundayOpen = findViewById(R.id.txtSundayOpen);
        sundayClose = findViewById(R.id.txtSundayClose);

        spinnerDay = findViewById(R.id.srChooseDay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapter);
        spinnerDay.setOnItemSelectedListener(this);
        day = spinnerDay.getSelectedItem().toString();
        working.setOnCheckedChangeListener(this);


        closingTime = (TextView) findViewById(R.id.closingTime);

        closingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = new TimePickerDialog(StoreHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                            hourOfDay-=12;
                        } else {
                            amPm = "AM";
                        }
                        String time = String.format("%02d:%02d", hourOfDay, minutes) + amPm;
                        closingTime.setText(time);
                        switch (day){
                            case "Monday":
                                mondayClose.setText(time);
                                break;
                            case "Tuesday":
                                tuesdayClose.setText(time);

                                break;
                            case "Wednesday":
                                wednesdayClose.setText(time);

                                break;
                            case "Thursday":
                                thursdayClose.setText(time);

                                break;
                            case "Friday":
                                fridayClose.setText(time);

                                break;
                            case "Saturday":
                                saturdayClose.setText(time);

                                break;
                            case "Sunday":
                                sundayClose.setText(time);

                                break;


                        }


                    }
                }, currentHour, currentMinute, false);

                time.show();
            }
        });
        openingTime = (TextView) findViewById(R.id.openingTime);
        openingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                time = new TimePickerDialog(StoreHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        if (hourOfDay >= 12) {
                            amPm = "PM";
                            hourOfDay-=12;
                        } else {
                            amPm = "AM";
                        }

                        String time = String.format("%02d:%02d", hourOfDay, minutes) + amPm;

                        openingTime.setText(time);
                        switch (day){
                            case "Monday":
                                mondayOpen.setText(time);

                                break;
                            case "Tuesday":
                                tuesdayOpen.setText(time);

                                break;
                            case "Wednesday":
                                wednesdayOpen.setText(time);

                                break;
                            case "Thursday":
                                thursdayOpen.setText(time);

                                break;
                            case "Friday":
                                fridayOpen.setText(time);

                                break;
                            case "Saturday":
                                saturdayOpen.setText(time);

                                break;
                            case "Sunday":
                                sundayOpen.setText(time);

                                break;

                        }
                    }
                }, currentHour, currentMinute, false);

                time.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        day = spinnerDay.getSelectedItem().toString();
        switch (day){
            case "Monday":
                if (!mondayOpen.getText().equals("NA")){
                    working.setChecked(true);
                    openingTime.setText(mondayOpen.getText());
                    closingTime.setText(mondayClose.getText());

                }else{
                    openingTime.setText("");
                    closingTime.setText("");
                    working.setChecked(false);
                }


                break;
            case "Tuesday":
                if (!tuesdayOpen.getText().equals("NA")){
                    working.setChecked(true);
                    openingTime.setText(tuesdayOpen.getText());
                    closingTime.setText(tuesdayClose.getText());

                }else{
                    openingTime.setText("");
                    closingTime.setText("");
                    working.setChecked(false);
                }

                break;
            case "Wednesday":
                if (!wednesdayOpen.getText().equals("NA")){
                    working.setChecked(true);
                    openingTime.setText(wednesdayOpen.getText());
                    closingTime.setText(wednesdayClose.getText());

                }else{
                    openingTime.setText("");
                    closingTime.setText("");
                    working.setChecked(false);
                }

                break;
            case "Thursday":
                if (!thursdayOpen.getText().equals("NA")){
                    working.setChecked(true);
                    openingTime.setText(thursdayOpen.getText());
                    closingTime.setText(thursdayClose.getText());

                }else{
                    openingTime.setText("");
                    closingTime.setText("");
                    working.setChecked(false);
                }

                break;
            case "Friday":
                if (!fridayOpen.getText().equals("NA")){
                    working.setChecked(true);
                    openingTime.setText(fridayOpen.getText());
                    closingTime.setText(fridayClose.getText());

                }else{
                    openingTime.setText("");
                    closingTime.setText("");
                    working.setChecked(false);
                }


                break;
            case "Saturday":
                if (!saturdayOpen.getText().equals("NA")){
                    working.setChecked(true);
                    openingTime.setText(saturdayOpen.getText());
                    closingTime.setText(saturdayClose.getText());
                }else{
                    openingTime.setText("");
                    closingTime.setText("");
                    working.setChecked(false);
                }


                break;
            case "Sunday":
                if (!sundayOpen.getText().equals("NA")){
                    working.setChecked(true);
                    openingTime.setText(sundayOpen.getText());
                    closingTime.setText(sundayClose.getText());
                }else{
                    openingTime.setText("");
                    closingTime.setText("");
                    working.setChecked(false);
                }

                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
                openingTime.setText(mondayOpen.getText());
                closingTime.setText(mondayClose.getText());
    }
    public void save(View v){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("hours");
        DateHelper helperClass = new DateHelper((String) mondayOpen.getText(),(String)tuesdayOpen.getText(),(String)wednesdayOpen.getText(),(String)thursdayOpen.getText(),(String)fridayOpen.getText(),
                (String)saturdayOpen.getText(),(String)sundayOpen.getText(), (String)mondayClose.getText(),(String)tuesdayClose.getText(),(String)wednesdayClose.getText(),
                (String)thursdayClose.getText(),(String)fridayClose.getText(),(String)saturdayClose.getText(),(String)sundayClose.getText());
        reference.child(UserTracker.user).setValue(helperClass);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked){
            switch (day){
                case "Monday":

                    mondayOpen.setText("NA");
                    mondayClose.setText("NA");

                    break;
                case "Tuesday":
                    tuesdayOpen.setText("NA");
                    tuesdayClose.setText("NA");

                    break;
                case "Wednesday":
                    wednesdayOpen.setText("NA");
                    wednesdayClose.setText("NA");

                    break;
                case "Thursday":
                    thursdayOpen.setText("NA");
                    thursdayClose.setText("NA");

                    break;
                case "Friday":
                    fridayOpen.setText("NA");
                    fridayClose.setText("NA");

                    break;
                case "Saturday":
                    saturdayOpen.setText("NA");
                    saturdayClose.setText("NA");

                    break;
                case "Sunday":
                    sundayOpen.setText("NA");
                    sundayClose.setText("NA");

                    break;

            }
            closingTime.setVisibility(View.INVISIBLE);
            openingTime.setVisibility(View.INVISIBLE);

        }else{
            closingTime.setVisibility(View.VISIBLE);
            openingTime.setVisibility(View.VISIBLE);
        }
    }
}

