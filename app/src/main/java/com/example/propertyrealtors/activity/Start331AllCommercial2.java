package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class Start331AllCommercial2 extends AppCompatActivity {

    Spinner spinTotal, spinFloor, areaSpin1, areaSpin2, lock_in_period;
    ArrayList<String> arrayList;
    String lock_in_periodString, totalfloor, floorNo, furnished, carpetAreaParameter, superAreaParameter;
    EditText superArea, carpetArea;
    String  propertyType, city, project;
    static String propertyFor;
    static String propertySubType;
    String CarpetArea, SuperArea, personal_washroom, cornerShop, main_road_facing, cafateria;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_commercial2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        areaSpin2 = findViewById(R.id.areaSpin2);
        superArea = findViewById(R.id.areaEdit2);
        carpetArea = findViewById(R.id.areaEdit1);
        lock_in_period = findViewById(R.id.floorSpin3);
        TextView textView5= findViewById(R.id.textView5);
        lock_in_period.setVisibility(View.GONE);
        textView5.setVisibility(View.GONE);
        if(!propertyFor.equals("SELL")){
            lock_in_period.setVisibility(View.VISIBLE);
            textView5.setVisibility(View.VISIBLE);
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

        }

        arrayList = new ArrayList<>();

        getTotalFloor();
        getFloor();
        carpetarea();
        superarea();
    }
    public void carpetarea(){


        areaSpin1.setPrompt("Select Parameter");
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
        areaSpin2.setPrompt("Select Parameter");
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
        spinFloor.setPrompt("Floor no");
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
        Intent intent = new Intent(Start331AllCommercial2.this, Start331All.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    public void cafeteria(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {
            case R.id.dry:
                cafateria = "Dry";
                break;
            case R.id.wet:
                cafateria = "Wet";
                break;
            case R.id.notAvailable:
                cafateria = "Not Available";
                break;
        }
    }

    public void washroom(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {
            case R.id.yes:
                personal_washroom = "Yes";
                break;
            case R.id.no:
                personal_washroom = "No";
                break;
        }
    }

    public void mainRoad(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {
            case R.id.yess:
                main_road_facing = "Yes";
                break;
            case R.id.noo:
                main_road_facing = "No";
                break;
        }
    }

    public void validate(){
        CarpetArea= carpetArea.getText().toString().trim();
        Log.e("c2", CarpetArea);
        SuperArea= superArea.getText().toString().trim();
        Log.e("c2", SuperArea);

        if(TextUtils.isEmpty(totalfloor)){
            Toast.makeText(Start331AllCommercial2.this, "Please Select Total Floor", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(floorNo)){
            Toast.makeText(Start331AllCommercial2.this, "Please Select Floor No of your Property", Toast.LENGTH_SHORT).show();
            return;

        }else if(TextUtils.isEmpty(furnished)){
            Toast.makeText(Start331AllCommercial2.this, "Please Select Furnishing", Toast.LENGTH_SHORT).show();
            return;

        }else if(TextUtils.isEmpty(CarpetArea)){
            Toast.makeText(Start331AllCommercial2.this, "Carpet Area", Toast.LENGTH_SHORT).show();
            return;

        }/*else if(SuperArea.isEmpty()){
            Toast.makeText(Start331AllCommercial2.this, "Please Enter Area", Toast.LENGTH_SHORT).show();
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

            bundle.putString("CAFETERIA", cafateria);
            bundle.putString("CornerSHOP", cornerShop);
            bundle.putString("MaIN_ROADFACING", main_road_facing);
            bundle.putString("PERSONAL_WASHROOM", personal_washroom);
            bundle.putString("TOTALFLOOR", totalfloor);
            bundle.putString("LOCK_IN_PERIOD", lock_in_periodString);
            bundle.putString("FLOORNO", floorNo);
            bundle.putString("FURNISHED", furnished);
            bundle.putString("CARPETAREA", CarpetArea);
            bundle.putString("SUPERAREA", SuperArea);
            bundle.putString("CARPETAREA_PARAMETER", carpetAreaParameter);
            bundle.putString("SUPERAREA_PARAMETER", superAreaParameter);

            if(propertyFor.equals("SELL")) {
                intent = new Intent(Start331AllCommercial2.this, Start331AllResidentialPrice.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(propertyFor.equals("RENT or LEASE")){
                intent = new Intent(Start331AllCommercial2.this, Start332AllResidentialPrice.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        }
    }

    public void next(View view) {
        validate();
    }

    public void cornerShop(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {
            case R.id.yesss:
                cornerShop = "Yes";
                break;
            case R.id.nooo:
                cornerShop = "No";
                break;
        }
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
