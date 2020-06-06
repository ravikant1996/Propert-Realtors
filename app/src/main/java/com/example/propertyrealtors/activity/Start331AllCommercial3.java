package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;

import java.util.ArrayList;

public class Start331AllCommercial3 extends AppCompatActivity {

    Spinner spinTotal, spinFloor, areaSpin1;
    EditText Plot_Area, road_Width_EditText, bredth, length;
    ArrayList<String> arrayList;
    String totalfloor, open_Sides, boundary_wall, plotAreaParameter;
    String propertyFor, propertyType, propertySubType, city, project, plotArea, plot_length, plot_bredth, RoadWidth;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_commercial3);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            UID = bundle.getString("UID","defValue");
            propertyFor = bundle.getString("PROPERTY_FOR", null);
            propertyType = bundle.getString("PROPERTY_TYPE", null);
            propertySubType = bundle.getString("R/C_TYPE", null);
            city = bundle.getString("CITY", null);
            project = bundle.getString("LOCALITY", null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        spinTotal = findViewById(R.id.floorSpin1);
        spinFloor = findViewById(R.id.floorSpin2);
        areaSpin1 = findViewById(R.id.areaSpin1);
        Plot_Area = findViewById(R.id.areaEdit1);
        road_Width_EditText = findViewById(R.id.road_Width);
        bredth = findViewById(R.id.plot_breadth);
        length = findViewById(R.id.plot_length);
        arrayList = new ArrayList<>();

        getTotalFloor();
        getFloor();




        areaSpin1.setPrompt("Select Parameter");
        areaSpin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                plotAreaParameter = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });


    }

    public void getTotalFloor() {

        for (int i = 0; i <= 100; i++) {
            arrayList.add("" + i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTotal.setAdapter(arrayAdapter);
        spinTotal.setPrompt("Total floors");
        spinTotal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                totalfloor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getFloor() {
        spinFloor.setPrompt("Open Sides");
        spinFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                open_Sides = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void validate(){
        RoadWidth = road_Width_EditText.getText().toString().trim();
        plot_bredth = bredth.getText().toString().trim();
        plot_length = length.getText().toString().trim();
        plotArea = Plot_Area.getText().toString().trim();
        Log.e("c2", plotArea);
        Log.e("c2", plot_length);
       if(TextUtils.isEmpty(boundary_wall)){
            Toast.makeText(Start331AllCommercial3.this, "Please Enter Area", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(plotArea)){
            Toast.makeText(Start331AllCommercial3.this, "Please Enter Area", Toast.LENGTH_SHORT).show();
           return;

        }else if(TextUtils.isEmpty(plot_length)){
            Toast.makeText(Start331AllCommercial3.this, "Please Enter length", Toast.LENGTH_SHORT).show();
           return;

       }else if(TextUtils.isEmpty(plot_bredth)){
            Toast.makeText(Start331AllCommercial3.this, "Please Enter breadth", Toast.LENGTH_SHORT).show();
           return;

       }
        else{
            Intent intent;
            Bundle bundle = new Bundle();
           bundle.putString("UID", UID);
           bundle.putString("R/C_TYPE", propertySubType);
            bundle.putString("PROPERTY_FOR", propertyFor);
            bundle.putString("PROPERTY_TYPE", propertyType);
            bundle.putString("CITY", city);
            bundle.putString("LOCALITY", project);

            bundle.putString("ROAD_WIDTH", RoadWidth);
            bundle.putString("TOTALFLOOR", totalfloor);
            bundle.putString("Open_SIDES", open_Sides);
            bundle.putString("boundary_wall", boundary_wall);
            bundle.putString("PLOT_AREA", plotArea);
            bundle.putString("PLOT_AREA_PARAMETER", plotAreaParameter);
            bundle.putString("plot_bredth", plot_bredth);
            bundle.putString("plot_length", plot_length);

           if(propertyFor.equals("SELL")) {
               intent = new Intent(Start331AllCommercial3.this, Start331AllResidentialPrice.class);
               intent.putExtras(bundle);
               startActivity(intent);
           }else if(propertyFor.equals("RENT or LEASE")){
               intent = new Intent(Start331AllCommercial3.this, Start332AllResidentialPrice.class);
               intent.putExtras(bundle);
               startActivity(intent);
           }
        }
    }

    public void next(View view) {
        validate();
    }

    public void back(View view) {
        startActivity(new Intent(Start331AllCommercial3.this, Start331All.class));
        finish();
    }

    public void onRadioButtonClicked2(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {
            case R.id.radio3:
                boundary_wall = "Yes";
                break;
            case R.id.radio4:
                boundary_wall = "No";
                break;
        }
    }
}
