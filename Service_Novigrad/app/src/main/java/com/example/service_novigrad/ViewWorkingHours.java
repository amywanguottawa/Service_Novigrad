package com.example.service_novigrad;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewWorkingHours extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private String dayOfWeek;

    TextView openingTime, closingTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_working_hours);

        openingTime = findViewById(R.id.openingTime);
        closingTime = findViewById(R.id.closingTime);


        Spinner sDayPicker = findViewById(R.id.sDayPicker);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sDayPicker.setAdapter(adapter);
        sDayPicker.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        /*if(dayOfWeek.equals("Monday")){
            openingTime.setText("Opens at: "+ DateHelper.mondayOpen);
            closingTime.setText("Closes at"+DateHelper.mondayClose);
        } else if(dayOfWeek.equals("Tuesday")){
            openingTime.setText("Opens at: "+ DateHelper.tuesdayOpen);
            closingTime.setText("Closes at"+DateHelper.tuesdayClose);
        }else if(dayOfWeek.equals("Wednesday")){
            openingTime.setText("Opens at: "+ DateHelper.wednesdayOpen);
            closingTime.setText("Closes at"+DateHelper.wednesdayClose);
        }else if(dayOfWeek.equals("Thursday")){
            openingTime.setText("Opens at: "+ DateHelper.thursdayOpen);
            closingTime.setText("Closes at"+DateHelper.thursdayClose);
        }else if(dayOfWeek.equals("Friday")){
            openingTime.setText("Opens at: "+ DateHelper.fridayOpen);
            closingTime.setText("Closes at"+DateHelper.fridayClose);
        } else if(dayOfWeek.equals("Saturday")){
            openingTime.setText("Opens at: "+ DateHelper.saturdayOpen);
            closingTime.setText("Closes at"+DateHelper.saturdayClose);
        } else if(dayOfWeek.equals("Sunday")){
            openingTime.setText("Opens at: "+ DateHelper.sundayOpen);
            closingTime.setText("Closes at"+DateHelper.sundayClose);
        }*/



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dayOfWeek = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), dayOfWeek, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}