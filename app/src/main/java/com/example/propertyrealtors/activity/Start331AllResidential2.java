package com.example.propertyrealtors.activity;

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

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class Start331AllResidential2 extends AppCompatActivity {

    Spinner spinTotal, spinFloor, areaSpin1, areaSpin2;
    TextView t1, t2, a1, a2, errorBed, errorBath, errorFurnish;
    EditText superArea, carpetArea, road_Width;
    ArrayList<String> arrayList;
    String UID;
    String bathroom, bedroom, balcony, totalfloor, open_Sides, furnished, carpetAreaParameter, superAreaParameter;
    String propertyFor, propertyType, propertySubType, city, project, CarpetArea, SuperArea, RoadWidth;

    static final String TAG= "Start331AllResidential2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_residential2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            UID= bundle.getString("UID", "defValue");
            propertyFor= bundle.getString("PROPERTY_FOR", null);
            propertyType= bundle.getString("PROPERTY_TYPE", null);
            propertySubType= bundle.getString("R/C_TYPE", null);
            city = bundle.getString("CITY", null);
            project = bundle.getString("LOCALITY", null);
            Log.e(TAG, UID + "===UID");

        }catch (Exception e){
            e.printStackTrace();
        }
        spinTotal = findViewById(R.id.floorSpin1);
        spinFloor = findViewById(R.id.floorSpin2);
        areaSpin1 = findViewById(R.id.areaSpin1);
        areaSpin2 = findViewById(R.id.areaSpin2);
        superArea = findViewById(R.id.areaEdit2);
        carpetArea = findViewById(R.id.areaEdit1);
        road_Width = findViewById(R.id.road_Width);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        errorBath = findViewById(R.id.errorBath);
        errorBed = findViewById(R.id.errorBed);
        errorFurnish = findViewById(R.id.errorFurnish);

        t1 = findViewById(R.id.spinText1);
        t2 = findViewById(R.id.spinText2);
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

        for(int i=1; i<=100; i++){
            arrayList.add(""+i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTotal.setAdapter(arrayAdapter);
        spinTotal.setPrompt("Number of Floor");
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
        spinFloor.setPrompt("Open sides");
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


    public void back(View view) {
        startActivity(new Intent(Start331AllResidential2.this, Start331All.class));
        finish();
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {

            case R.id.unfurnished:
                furnished="Unfurnished";
                errorFurnish.setText(furnished);
                break;
            case R.id.semifurnished:
                furnished="Semi-furnished";
                errorFurnish.setText(furnished);
                break;
            case R.id.fullyfurnished:
                furnished="Fully-furnished";
                errorFurnish.setText(furnished);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    public void selectBHK(View view) {
        boolean selected = !view.isSelected();
        TextView bhk1 = findViewById(R.id.bhk1);
        TextView bhk2 = findViewById(R.id.bhk2);
        TextView bhk3 = findViewById(R.id.bhk3);
        TextView bhk4 = findViewById(R.id.bhk4);
        TextView bhk5 = findViewById(R.id.bhk5);
        TextView bhk6 = findViewById(R.id.bhk6);
        TextView bhk7 = findViewById(R.id.bhk7);
        TextView bhk8 = findViewById(R.id.bhk8);
        TextView bhk9 = findViewById(R.id.bhk9);
        TextView bhk10 = findViewById(R.id.bhk10);
        TextView bhk11 = findViewById(R.id.bhk11);
        bhk1.setText(" 1 BHK ");
        bhk2.setText(" 2 BHK ");
        bhk3.setText(" 3 BHK ");
        bhk4.setText(" 4 BHK ");
        bhk5.setText(" 5 BHK ");
        bhk6.setText(" 6 BHK ");
        bhk7.setText(" 7 BHK ");
        bhk8.setText(" 8 BHK ");
        bhk9.setText(" 9 BHK ");
        bhk10.setText(" 10 BHK ");
        bhk11.setText(" >10 BHK4 ");

        switch (view.getId()){
            case R.id.bhk1:
                bedroom= "1";
                bhk1.setText("1 BHK ✔");
                break;
            case R.id.bhk2:
                bedroom= "2";
                bhk2.setText("2 BHK \u2714");
                break;

            case R.id.bhk3:
                bedroom= "3";
                bhk3.setText("3 BHK \u2714");
                break;

            case R.id.bhk4:
                bedroom= "4";
                bhk4.setText("4 BHK \u2714");
                break;

            case R.id.bhk5:
                bedroom= "5";
                bhk5.setText("5 BHK \u2714");
                break;

            case R.id.bhk6:
                bedroom= "6";

                bhk6.setText("6 BHK \u2714");

                break;

            case R.id.bhk7:
                bedroom= "7";
                bhk7.setText("7 BHK \u2714");
                break;

            case R.id.bhk8:
                bedroom= "8";
                bhk8.setText("8 BHK \u2714");
                break;

            case R.id.bhk9:
                bedroom= "9";
                bhk9.setText("9 BHK \u2714");
                break;

            case R.id.bhk10:
                bedroom= "10";
                bhk10.setText("10 BHK \u2714");
                break;

            case R.id.bhk11:
                bedroom= "10+";
                bhk11.setText(">10 BHK \u2714");
                break;
        }
        errorBed.setText(bedroom);
    }

    @SuppressLint("SetTextI18n")
    public void selectBATHROOM(View view) {
        boolean selected = !view.isSelected();
        TextView bhk1 = findViewById(R.id.room1);
        TextView bhk2 = findViewById(R.id.room2);
        TextView bhk3 = findViewById(R.id.room3);
        TextView bhk4 = findViewById(R.id.room4);
        TextView bhk5 = findViewById(R.id.room5);
        TextView bhk6 = findViewById(R.id.room6);
        TextView bhk7 = findViewById(R.id.room7);
        TextView bhk8 = findViewById(R.id.room8);
        TextView bhk9 = findViewById(R.id.room9);
        TextView bhk10 = findViewById(R.id.room10);
        TextView bhk11 = findViewById(R.id.room11);
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
            case R.id.room1:
                bathroom= "1";
                bhk1.setText("1 \u2714");
                break;

            case R.id.room2:
                bathroom= "2";
                bhk2.setText("2 \u2714");
                break;

            case R.id.room3:
                bathroom= "3";
                bhk3.setText("3 \u2714");
                break;

            case R.id.room4:
                bathroom= "4";
                bhk4.setText("4 \u2714");
                break;

            case R.id.room5:
                bathroom= "5";
                bhk5.setText("5 \u2714");
                break;

            case R.id.room6:
                bathroom= "6";
                bhk6.setText("6 \u2714");
                break;

            case R.id.room7:
                bathroom= "7";
                bhk7.setText("7 \u2714");
                break;

            case R.id.room8:
                bathroom= "8";
                bhk8.setText("8 \u2714");
                break;

            case R.id.room9:
                bathroom= "9";
                bhk9.setText("9 \u2714");
                break;

            case R.id.room10:
                bathroom= "10";
                bhk10.setText("10 \u2714");
                break;

            case R.id.room11:
                bathroom= "10+";
                bhk11.setText(">10 \u2714");
                break;

        }
        errorBath.setText(bathroom);
    }

    @SuppressLint("SetTextI18n")
    public void selectBALCONY(View view) {
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
                balcony= "1";
                bhk1.setText("1 \u2714");
                break;

            case R.id.b2:
                balcony= "2";
                bhk2.setText("2 \u2714");
                break;

            case R.id.b3:
                balcony= "3";
                bhk3.setText("3 \u2714");
                break;

            case R.id.b4:
                balcony= "4";
                bhk4.setText("4 \u2714");
                break;

            case R.id.b5:
                balcony= "5";
                bhk5.setText("5 \u2714");
                break;

            case R.id.b6:
                balcony= "6";
                bhk6.setText("6 \u2714");
                break;

            case R.id.b7:
                balcony= "7";
                bhk7.setText("7 \u2714");
                break;

            case R.id.b8:
                balcony= "8";
                bhk8.setText("8 \u2714");
                break;

            case R.id.b9:
                balcony= "9";
                bhk9.setText("9 \u2714");
                break;

            case R.id.b10:
                balcony= "10";
                bhk10.setText("10 \u2714");
                break;

            case R.id.b11:
                balcony= "10+";
                bhk11.setText(">10 \u2714");
                break;

        }
    }

    public void validate(){
        RoadWidth= road_Width.getText().toString().trim();
        CarpetArea= carpetArea.getText().toString().trim();
        SuperArea= superArea.getText().toString().trim();

        Log.e("Start331AllResidential2", RoadWidth);
        Log.e("Start331AllResidential2", CarpetArea);
        Log.e("Start331AllResidential2", SuperArea);

        if(errorBed.getText().toString().length()==0){
            errorBed.setError("Select Bedroom");
            Toast.makeText(Start331AllResidential2.this, "Select Bedroom", Toast.LENGTH_SHORT).show();

        }else if(errorBath.getText().toString().length()==0){
            errorBath.setError("Please Select Bathroom");
            Toast.makeText(Start331AllResidential2.this, "Please Select Bathroom", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(totalfloor)){
            Toast.makeText(Start331AllResidential2.this, "Please Select Total Floor", Toast.LENGTH_SHORT).show();
            return;

        }else if(errorFurnish.getText().toString().length()==0){
            errorFurnish.setError("Please Select Furnishing");
            Toast.makeText(Start331AllResidential2.this, "Please Select Furnishing", Toast.LENGTH_SHORT).show();

        }else if(carpetArea.getText().toString().length()==0){
            carpetArea.setError("Please Enter Area");
            Toast.makeText(Start331AllResidential2.this, "Please Enter Area", Toast.LENGTH_SHORT).show();

        }/*else if(superArea.getText().toString().length()==0){
            superArea.setError("Please Enter Area");
            Toast.makeText(Start331AllResidential2.this, "Please Enter Area", Toast.LENGTH_SHORT).show();

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

            bundle.putString("BEDROOM", bedroom);
            bundle.putString("BATHROOM", bathroom);
            bundle.putString("BALCONY", balcony);
            bundle.putString("TOTALFLOOR", totalfloor);
            bundle.putString("Open_SIDES", open_Sides);
            bundle.putString("FURNISHED", furnished);
            bundle.putString("CARPETAREA", CarpetArea);
            bundle.putString("SUPERAREA", SuperArea);
            bundle.putString("CARPETAREA_PARAMETER", carpetAreaParameter);
            bundle.putString("SUPERAREA_PARAMETER", superAreaParameter);
            bundle.putString("ROAD_WIDTH", RoadWidth);

            if(propertyFor.equals("SELL")) {
                intent = new Intent(Start331AllResidential2.this, Start331AllResidentialPrice.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(propertyFor.equals("RENT or LEASE")){
                intent = new Intent(Start331AllResidential2.this, Start332AllResidentialPrice.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    public void next(View view) {

        validate();
    }
}
