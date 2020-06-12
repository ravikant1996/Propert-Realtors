package com.example.propertyrealtors.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Start331AllPropertyStatus extends AppCompatActivity {

    Button UnderConst, ReadytoMove, next;
    String age;
    TextView From, From2;
    String UID, YearString;
    Spinner ageOfConst, MonthSpinner;
    ArrayList<String> arrayList;
    ArrayList<String> monthList;
    ArrayList<String> yearList;
    String price, token_amount, ageOfconstruction, availablefrom, property_status;
    String bathroom, bedroom, balcony, totalfloor, floorNo, furnished, carpetAreaParameter, RoadWidth, superAreaParameter, lock_in_periodString;
    String propertyFor, propertyType, propertySubType, city, project, CarpetArea, SuperArea,  plotArea, plot_length, plot_bredth;
    String  open_Sides, construction_done, boundary_wall, gated_colony, plotAreaParameter, cafateria, washroom, personal_washroom, cornerShop, main_road_facing;

    static final String TAG= "331AllPropertyStatus";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start311_all_property_status);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
            UID= bundle.getString("UID", null);
            propertyFor= bundle.getString("PROPERTY_FOR", null);
            propertyType= bundle.getString("PROPERTY_TYPE", null);
            propertySubType= bundle.getString("R/C_TYPE", null);
            city = bundle.getString("CITY", null);
            project = bundle.getString("LOCALITY", null);

            bedroom = bundle.getString("BEDROOM", null);
            bathroom = bundle.getString("BATHROOM", null);
            balcony = bundle.getString("BALCONY", null);
            totalfloor = bundle.getString("TOTALFLOOR", null);
            floorNo = bundle.getString("FLOORNO", null);
            furnished = bundle.getString("FURNISHED", null);
            CarpetArea = bundle.getString("CARPETAREA", null);
            SuperArea = bundle.getString("SUPERAREA", null);
            carpetAreaParameter = bundle.getString("CARPETAREA_PARAMETER", null);
            superAreaParameter = bundle.getString("SUPERAREA_PARAMETER", null);
            RoadWidth = bundle.getString("ROAD_WIDTH", null);
            open_Sides = bundle.getString("Open_SIDES", null);
            construction_done = bundle.getString("construction_done", null);
            boundary_wall = bundle.getString("boundary_wall", null);
            gated_colony = bundle.getString("gated_colony", null);
            plotArea = bundle.getString("PLOT_AREA", null);
            plotAreaParameter = bundle.getString("PLOT_AREA_PARAMETER", null);
            plot_bredth = bundle.getString("plot_bredth", null);
            plot_length = bundle.getString("plot_length", null);
            cafateria = bundle.getString("CAFETERIA", null);
            washroom = bundle.getString("WashROOM", null);
            cornerShop = bundle.getString("CornerSHOP", null);
            main_road_facing = bundle.getString("MaIN_ROADFACING", null);
            personal_washroom = bundle.getString("PERSONAL_WASHROOM", null);
            lock_in_periodString = bundle.getString("LOCK_IN_PERIOD", null);
            price = bundle.getString("PRICE", null);
            token_amount = bundle.getString("TOKEN_AMOUNT", null);
        }catch (Exception e){
            e.printStackTrace();
        }

        next = findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);
        UnderConst = findViewById(R.id.individual);
        ReadytoMove = findViewById(R.id.agent);
        ageOfConst = findViewById(R.id.ageOfConst);

        MonthSpinner = findViewById(R.id.name);
      //  YearSpinner = findViewById(R.id.year);
        From= findViewById(R.id.From);
        From2= findViewById(R.id.From2);

        From.setVisibility(View.INVISIBLE);
        From2.setVisibility(View.INVISIBLE);
        MonthSpinner.setVisibility(View.INVISIBLE);
      //  YearSpinner.setVisibility(View.INVISIBLE);

        arrayList = new ArrayList<>();
        monthList = new ArrayList<>();
      //  yearList = new ArrayList<>();

        ageOfConst.setVisibility(View.INVISIBLE);

    }

    public void AgeOfConstruction() {
        ageOfConst.setPrompt("Select how long is made");
        ageOfConst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ageOfconstruction = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void availablefromMONTH() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String string, str2;
        int x=year;
        int y=0;
        monthList.add("Select Month");
        for(int i=x;i<x+15;i++){
            for(int j=y ,z=month-1; j<12; j++, z++){
                try{
                    if(!(i==year)){
                        string = monthName[j];
                     //   System.out.println("Current month: "+string +"/"+i);
                        monthList.add(" "+string +"/"+i);

                    }
                    if(i==year){
                        str2= monthName[z];
                     //   System.out.println("Current month: "+str2 +"/"+i);
                        monthList.add(" "+str2 +"/"+i);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MonthSpinner.setAdapter(arrayAdapter);
        MonthSpinner.setPrompt("Select Month/Year");
        MonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                availablefrom = parent.getItemAtPosition(position).toString();
             //   availablefromYEAR();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void select(View v) {

        if (v.getId() == R.id.individual){
            property_status = "Under Construction";
            UnderConst.setBackgroundColor(Color.RED);
            UnderConst.setTextColor(Color.WHITE);
            ReadytoMove.setBackgroundColor(Color.WHITE);
            ReadytoMove.setTextColor(Color.BLACK);

            availablefromMONTH();
            next.setVisibility(View.VISIBLE);
            From2.setVisibility(View.INVISIBLE);
            ageOfConst.setVisibility(View.INVISIBLE);
            MonthSpinner.setVisibility(View.VISIBLE);
          //  YearSpinner.setVisibility(View.VISIBLE);
            From.setVisibility(View.VISIBLE);
        }
        else if (v.getId() == R.id.agent){
            property_status = "Ready to Move";
            ReadytoMove.setBackgroundColor(Color.RED);
            ReadytoMove.setTextColor(Color.WHITE);
            UnderConst.setBackgroundColor(Color.WHITE);
            UnderConst.setTextColor(Color.BLACK);

            if(propertySubType.equals("Plot")){
                From2.setVisibility(View.GONE);
                ageOfConst.setVisibility(View.GONE);
            }else {
                From2.setVisibility(View.VISIBLE);
                ageOfConst.setVisibility(View.VISIBLE);
                AgeOfConstruction();
            }
            next.setVisibility(View.VISIBLE);
            MonthSpinner.setVisibility(View.INVISIBLE);
         //   YearSpinner.setVisibility(View.INVISIBLE);
            From.setVisibility(View.INVISIBLE);
        }
    }

    public void back(View view) {
        Intent intent= new Intent(Start331AllPropertyStatus.this, Start331AllResidentialPrice.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putString("UID", UID);
        bundle.putString("R/C_TYPE", propertySubType);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    public void intenter(){
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
        bundle.putString("CornerSHOP", cornerShop);
        bundle.putString("MaIN_ROADFACING", main_road_facing);
        bundle.putString("PERSONAL_WASHROOM", personal_washroom);
        bundle.putString("ROAD_WIDTH", RoadWidth);
        bundle.putString("Open_SIDES", open_Sides);
        bundle.putString("boundary_wall", boundary_wall);
        bundle.putString("PLOT_AREA", plotArea);
        bundle.putString("PLOT_AREA_PARAMETER", plotAreaParameter);
        bundle.putString("plot_bredth", plot_bredth);
        bundle.putString("plot_length", plot_length);
        bundle.putString("construction_done", construction_done);
        bundle.putString("boundary_wall", boundary_wall);
        bundle.putString("LOCK_IN_PERIOD", lock_in_periodString);
        bundle.putString("BEDROOM", bedroom);
        bundle.putString("BATHROOM", bathroom);
        bundle.putString("BALCONY", balcony);
        bundle.putString("gated_colony", gated_colony);

        bundle.putString("PRICE", price);
        bundle.putString("TOKEN_AMOUNT", token_amount);
        bundle.putString("PROPERTY_STATUS", property_status);
        if(propertySubType.equals("Plot")) {
            if (property_status.equals("Under Construction")) {
                bundle.putString("AVAILABLEFROM", availablefrom);
            }
        }else {
            if (property_status.equals("Under Construction")) {
                bundle.putString("AVAILABLEFROM", availablefrom);
            } else if (property_status.equals("Ready to Move")) {
                bundle.putString("AGE_OF_CONSTRUCTION", ageOfconstruction);
            }
        }
        intent= new Intent(Start331AllPropertyStatus.this, post_package.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void next(View view) {
      //  Log.e("Status 331", availablefrom);
       /* if(property_status.equals("Under Construction")){
            ageOfconstruction="";

          //  if(SelectDate.getText().toString().length() == 0){
            if(TextUtils.isEmpty(availablefrom)){
                Toast.makeText(Start331AllPropertyStatus.this, "Select Available date", Toast.LENGTH_SHORT).show();
                return;
            }else {
                intenter();
            }
        }else if(property_status.equals("Ready to Move")) {
            availablefrom = "";

            if (TextUtils.isEmpty(ageOfconstruction)) {
                Toast.makeText(Start331AllPropertyStatus.this, "Select how long made", Toast.LENGTH_SHORT).show();
                return;
            } else {
                intenter();
            }
        }*/
        if(TextUtils.isEmpty(property_status)){
            Toast.makeText(Start331AllPropertyStatus.this, "Select Any Button", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            if(property_status.equals("Under Construction")){
                if(TextUtils.isEmpty(availablefrom) || availablefrom.equals("Select Month")){
                    Toast.makeText(Start331AllPropertyStatus.this, "Select Month", Toast.LENGTH_SHORT).show();
                    return;
                }
            }else if(!propertySubType.equals("Plot")) {
                if(TextUtils.isEmpty(ageOfconstruction) || ageOfconstruction.equals("----------")){
                    Toast.makeText(Start331AllPropertyStatus.this, "Select Age of Construction", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            intenter();
        }

    }
}
