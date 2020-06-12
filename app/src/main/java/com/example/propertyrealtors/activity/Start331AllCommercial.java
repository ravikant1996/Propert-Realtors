package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Start331AllCommercial extends AppCompatActivity {
    Spinner spinTotal, spinFloor, areaSpin1, areaSpin2;
    EditText Plot_Area, road_Width_EditText, bredth, length;
    ArrayList<String> arrayList;
    String bathroom, cafateria, washroom, totalfloor, floorNo, furnished, carpetAreaParameter, superAreaParameter;
    String plotAreaParameter;
    EditText superArea;
    TextInputLayout carpetHead;
    EditText carpetArea;
    String  propertyType, city, project;
    static String propertyFor;
    static String propertySubType;
    String CarpetArea, SuperArea;
    String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_commercial);
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
             //   a1.setText(carpetAreaParameter);
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
              //  a2.setText(superAreaParameter);
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
        Intent intent = new Intent(Start331AllCommercial.this, Start331All.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @SuppressLint("SetTextI18n")
    public void selectWashroom(View view) {
        boolean selected = !view.isSelected();

        TextView bhk1 = findViewById(R.id.b1);
        TextView bhk2 = findViewById(R.id.b2);
        TextView bhk3 = findViewById(R.id.b3);
        TextView bhk4 = findViewById(R.id.b4);
        TextView bhk5 = findViewById(R.id.b5);
        TextView bhk6 = findViewById(R.id.b6);
        TextView bhk7 = findViewById(R.id.b7);
        TextView bhk8 = findViewById(R.id.b8);
        TextView bhk9 = findViewById(R.id.b9);
        TextView bhk10 = findViewById(R.id.b10);
        TextView bhk11 = findViewById(R.id.b11);
        bhk1.setText("  1  ");
        bhk2.setText("  2  ");
        bhk3.setText("  3  ");
        bhk4.setText("  4  ");
        bhk5.setText("  5  ");
        bhk6.setText("  6  ");
        bhk7.setText("  7  ");
        bhk8.setText("  8  ");
        bhk9.setText("  9  ");
        bhk10.setText("  10  ");
        bhk11.setText("  >10  ");


        switch (view.getId()){
            case R.id.b1:
                washroom= "1";
                bhk1.setText("1 \u2714");
                break;

            case R.id.b2:
                washroom= "2";
                bhk2.setText("2 \u2714");
                break;

            case R.id.b3:
                washroom= "3";
                bhk3.setText("3 \u2714");
                break;

            case R.id.b4:
                washroom= "4";
                bhk4.setText("4 \u2714");
                break;

            case R.id.b5:
                washroom= "5";
                bhk5.setText("5 \u2714");
                break;

            case R.id.b6:
                washroom= "6";
                bhk6.setText("6 \u2714");
                break;

            case R.id.b7:
                washroom= "7";
                bhk7.setText("7 \u2714");
                break;

            case R.id.b8:
                washroom= "8";
                bhk8.setText("8 \u2714");
                break;

            case R.id.b9:
                washroom= "9";
                bhk9.setText("9 \u2714");
                break;

            case R.id.b10:
                washroom= "10";
                bhk10.setText("10 \u2714");
                break;

            case R.id.b11:
                washroom= "10+";
                bhk11.setText(">10 \u2714");
                break;

        }
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {

            case R.id.unfurnished:
                furnished="Unfurnished";
                break;
            case R.id.semifurnished:
                furnished="Semi-furnished";
                break;
            case R.id.fullyfurnished:
                furnished="Fully-furnished";
                break;
        }
    }
    public void validate(){
        CarpetArea= carpetArea.getText().toString().trim();
        Log.e("Ofice", CarpetArea);
        SuperArea= superArea.getText().toString().trim();
        Log.e("Ofice", SuperArea);

        if(TextUtils.isEmpty(washroom)) {
            Toast.makeText(Start331AllCommercial.this, "Please Select Washroom", Toast.LENGTH_SHORT).show();

            return;
        }else if(TextUtils.isEmpty(totalfloor)){
                Toast.makeText(Start331AllCommercial.this, "Please Select Total Floor", Toast.LENGTH_SHORT).show();
            return;

        }else if(TextUtils.isEmpty(floorNo)){
            Toast.makeText(Start331AllCommercial.this, "Please Select Floor No of your Property", Toast.LENGTH_SHORT).show();
            return;

        }else if(TextUtils.isEmpty(furnished)){
            Toast.makeText(Start331AllCommercial.this, "Please Select Furnishing", Toast.LENGTH_SHORT).show();
            return;

        }else if(TextUtils.isEmpty(CarpetArea)){
            Toast.makeText(Start331AllCommercial.this, "Please Enter Area", Toast.LENGTH_SHORT).show();
            return;

        }
        /*else if(TextUtils.isEmpty(SuperArea)){
            Toast.makeText(Start331AllCommercial.this, "Please Enter Area", Toast.LENGTH_SHORT).show();

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
            bundle.putString("WashROOM", washroom);
            bundle.putString("TOTALFLOOR", totalfloor);
            bundle.putString("FLOORNO", floorNo);
            bundle.putString("FURNISHED", furnished);
            bundle.putString("CARPETAREA", CarpetArea);
            bundle.putString("SUPERAREA", SuperArea);
            bundle.putString("CARPETAREA_PARAMETER", carpetAreaParameter);
            bundle.putString("SUPERAREA_PARAMETER", superAreaParameter);
            if(propertyFor.equals("SELL")) {
                intent = new Intent(Start331AllCommercial.this, Start331AllResidentialPrice.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(propertyFor.equals("RENT or LEASE")){
                intent = new Intent(Start331AllCommercial.this, Start332AllResidentialPrice.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            return;
        }
    }

    public void next(View view) {
        validate();
    }

    public void onRadioButtonClicked2(View v) {
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

}
