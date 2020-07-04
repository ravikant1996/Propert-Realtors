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

public class Start331AllResidential3 extends AppCompatActivity {
    Spinner spinTotal, spinFloor, areaSpin1;
    EditText plot_Area, road_Width, bredth, length;
    ArrayList<String> arrayList;
    String totalfloor, open_Sides, construction_done, boundary_wall, gated_colony, plotAreaParameter;
    String propertyType, city, project, PlotArea, Plot_length, Plot_bredth, RoadWidth;
    String UID;
    static String propertyFor;
    static String propertySubType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_residential3);
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
            UID = bundle.getString("UID", "defValue");
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
        plot_Area = findViewById(R.id.areaEdit1);
        road_Width = findViewById(R.id.road_Width);
        bredth = findViewById(R.id.plot_breadth);
        length = findViewById(R.id.plot_length);

        arrayList = new ArrayList<>();

        getTotalFloor();
        getFloor();


        areaSpin1.setPrompt("Parameter");
        areaSpin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                plotAreaParameter = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });


    }

    public void getTotalFloor() {

        spinTotal.setPrompt("Total Floors");
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
        Intent intent = new Intent(Start331AllResidential3.this, Start331All.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {
            case R.id.radio1:
                construction_done = "Yes";
                break;
            case R.id.radio2:
                construction_done = "No";
                break;
        }
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

    public void onRadioButtonClicked3(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        switch (v.getId()) {
            case R.id.radio5:
                gated_colony = "Yes";
                break;
            case R.id.radio6:
                gated_colony = "No";
                break;
        }
    }

    public void validate() {
        RoadWidth = road_Width.getText().toString().trim();
        PlotArea = plot_Area.getText().toString().trim();
        Plot_length = length.getText().toString().trim();
        Plot_bredth = bredth.getText().toString().trim();

        if (totalfloor.equals("Select")) {
            Toast.makeText(Start331AllResidential3.this, "Select Total Floors", Toast.LENGTH_LONG).show();
            return;
        }
        else if (TextUtils.isEmpty(construction_done)) {
            Toast.makeText(Start331AllResidential3.this, "Select Construction option", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(boundary_wall)) {
            Toast.makeText(Start331AllResidential3.this, "Select Boundary wall option", Toast.LENGTH_LONG).show();
            return;

        } else if (TextUtils.isEmpty(gated_colony)) {
            Toast.makeText(Start331AllResidential3.this, "Select Gated Colony option", Toast.LENGTH_LONG).show();
            return;

        } else if (plot_Area.getText().toString().length() == 0) {
            plot_Area.setError("Please Enter Area");
            Toast.makeText(Start331AllResidential3.this, "Please Enter Area", Toast.LENGTH_LONG).show();

        }/*else if(length.getText().toString().length()==0){
            length.setError("Please Enter length");
            Toast.makeText(Start331AllResidential3.this, "Please Enter length", Toast.LENGTH_LONG).show();

        }else if(bredth.getText().toString().length()==0){
            bredth.setError("Please Enter breadth");
            Toast.makeText(Start331AllResidential3.this, "Please Enter breadth", Toast.LENGTH_LONG).show();
        }*/ else {
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
            bundle.putString("construction_done", construction_done);
            bundle.putString("boundary_wall", boundary_wall);
            bundle.putString("gated_colony", gated_colony);
            bundle.putString("PLOT_AREA", PlotArea);
            bundle.putString("PLOT_AREA_PARAMETER", plotAreaParameter);
            bundle.putString("Plot_bredth", Plot_bredth);
            bundle.putString("Plot_length", Plot_length);

            intent = new Intent(Start331AllResidential3.this, Start331AllResidentialPrice.class);
            intent.putExtras(bundle);
            startActivity(intent);

          /*  if(propertyFor.equals("SELL")) {

            }else if(propertyFor.equals("RENT or LEASE")){
                intent = new Intent(Start331AllResidential3.this, Start332AllResidentialPrice.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }*/
        }
    }

    public void next(View view) {

        validate();
    }
}
