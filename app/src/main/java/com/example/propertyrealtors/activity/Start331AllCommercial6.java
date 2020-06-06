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

public class Start331AllCommercial6 extends AppCompatActivity {

    Spinner spinTotal, spinFloor, areaSpin1, areaSpin2, lock_in_period;
    ArrayList<String> arrayList;
    String bathroom, bedroom, lock_in_periodString, totalfloor, floorNo, furnished, carpetAreaParameter, superAreaParameter;
    EditText superArea, carpetArea;
    String propertyFor, propertyType, propertySubType, city, project, plotArea, plot_length, plot_bredth, RoadWidth;
    String CarpetArea, SuperArea, personal_washroom, cornerShop, main_road_facing;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_commercial6);
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
        lock_in_period = findViewById(R.id.floorSpin3);
        areaSpin1 = findViewById(R.id.areaSpin1);
        areaSpin2 = findViewById(R.id.areaSpin2);
        superArea = findViewById(R.id.areaEdit2);
        carpetArea = findViewById(R.id.areaEdit1);

        arrayList = new ArrayList<>();


        lock_in_period.setPrompt("Lock In Period");
        lock_in_period.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lock_in_periodString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        getTotalFloor();
        getFloor();
        carpetarea();
        superarea();
        
    }
    public void carpetarea(){
        areaSpin1.setPrompt("Area Parameter");
        areaSpin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                carpetAreaParameter = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });
    }
    public void superarea(){
        areaSpin1.setPrompt("Area Parameter");
        areaSpin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                superAreaParameter = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void getTotalFloor() {

        for (int i = 1; i <= 100; i++) {
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

    public void getFloor(){
       spinFloor.setPrompt("Floor No");
        spinFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floorNo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void back(View view) {
        startActivity(new Intent(Start331AllCommercial6.this, Start331All.class));
        finish();

    }
    public void validate(){
        CarpetArea= carpetArea.getText().toString().trim();
        Log.e("area", CarpetArea);
        SuperArea= superArea.getText().toString().trim();
        if(TextUtils.isEmpty(totalfloor)){
            Toast.makeText(Start331AllCommercial6.this, "Please Select Total Floor", Toast.LENGTH_SHORT).show();
            return;

        }else if(TextUtils.isEmpty(floorNo)){
            Toast.makeText(Start331AllCommercial6.this, "Please Select Floor No of your Property", Toast.LENGTH_SHORT).show();
            return;

        }else if(TextUtils.isEmpty(furnished)){
            Toast.makeText(Start331AllCommercial6.this, "Please Select Furnishing", Toast.LENGTH_SHORT).show();
            return;

        }else if(TextUtils.isEmpty(CarpetArea)){
            Log.e("area?", CarpetArea);
            Toast.makeText(Start331AllCommercial6.this, "Carpet Area", Toast.LENGTH_SHORT).show();
            return;

        }/*else if(TextUtils.isEmpty(SuperArea)){
            Toast.makeText(Start331AllCommercial6.this, "Please Enter Area", Toast.LENGTH_SHORT).show();
                return;
        }*/
        else{
            Intent intent;
            Bundle bundle = new Bundle();
            bundle.putString("UID", UID);
            bundle.putString("R/C_TYPE", propertySubType);
            bundle.putString("PROPERTY_FOR", propertyFor);
            bundle.putString("PROPERTY_TYPE", propertyType);
            bundle.putString("CITY", city);
            bundle.putString("LOCALITY", project);

            bundle.putString("TOTALFLOOR", totalfloor);
            bundle.putString("LOCK_IN_PERIOD", lock_in_periodString);
            bundle.putString("FLOORNO", floorNo);
            bundle.putString("FURNISHED", furnished);
            bundle.putString("CARPETAREA", CarpetArea);
            bundle.putString("SUPERAREA", SuperArea);
            bundle.putString("CARPETAREA_PARAMETER", carpetAreaParameter);
            bundle.putString("SUPERAREA_PARAMETER", superAreaParameter);

            if(propertyFor.equals("SELL")) {
                intent = new Intent(Start331AllCommercial6.this, Start331AllResidentialPrice.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(propertyFor.equals("RENT or LEASE")){
                intent = new Intent(Start331AllCommercial6.this, Start332AllResidentialPrice.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    public void next(View view) {

        validate();
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {

            case R.id.unfurnished:
                furnished = "Unfurnished";
                break;
            case R.id.semifurnished:
                furnished = "Semi-furnished";
                break;
            case R.id.fullyfurnished:
                furnished = "Fully-furnished";
                break;
        }
    }
}
