package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Start332AllResidentialPrice extends AppCompatActivity {
    EditText ExpectedPrice, MaintenanceCharge, SecuityAmount;
    String price, maintenance, maintenance_parameter, secuityAmount;
    Spinner ChargesSpinner;
    String bathroom, bedroom, balcony, totalfloor, floorNo, furnished, carpetAreaParameter, RoadWidth, superAreaParameter, lock_in_periodString;
    String propertyFor, propertyType, propertySubType, city, project, CarpetArea, SuperArea,  plotArea, plot_length, plot_bredth;
    String  open_Sides, construction_done, boundary_wall, gated_colony, plotAreaParameter, cafateria, washroom, personal_washroom, cornerShop, main_road_facing;
    String UID;
    String Rupees = null, paise;
    TextView Watcher, Watcher2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start332_all_residential_price);

        ExpectedPrice = findViewById(R.id.expected_price);
        MaintenanceCharge = findViewById(R.id.security_amount);
        SecuityAmount = findViewById(R.id.token_amount);
        ChargesSpinner = findViewById(R.id.charge);
        Watcher = findViewById(R.id.watcher);
        Watcher2 = findViewById(R.id.watcher2);


        ExpectedPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = String.valueOf(s);
                convert(ss);
                Watcher.setText("" + Rupees + paise );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        SecuityAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = String.valueOf(s);
                convert(ss);
                Watcher2.setText("" + Rupees + paise);
            }

            @Override
            public void afterTextChanged(Editable s) {

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

        }catch (Exception e){
            e.printStackTrace();
        }
        ChargesSpinner.setPrompt("Select Mode of Payment");
        ChargesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maintenance_parameter = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void convert(String num) {
        try {
            BigDecimal bd = new BigDecimal(num);
            long number = bd.longValue();
            long no = bd.longValue();
            int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
            int digits_length = String.valueOf(no).length();
            int i = 0;
            ArrayList<String> str = new ArrayList<>();
            HashMap<Integer, String> words = new HashMap<>();
            words.put(0, "");
            words.put(1, "One");
            words.put(2, "Two");
            words.put(3, "Three");
            words.put(4, "Four");
            words.put(5, "Five");
            words.put(6, "Six");
            words.put(7, "Seven");
            words.put(8, "Eight");
            words.put(9, "Nine");
            words.put(10, "Ten");
            words.put(11, "Eleven");
            words.put(12, "Twelve");
            words.put(13, "Thirteen");
            words.put(14, "Fourteen");
            words.put(15, "Fifteen");
            words.put(16, "Sixteen");
            words.put(17, "Seventeen");
            words.put(18, "Eighteen");
            words.put(19, "Nineteen");
            words.put(20, "Twenty");
            words.put(30, "Thirty");
            words.put(40, "Forty");
            words.put(50, "Fifty");
            words.put(60, "Sixty");
            words.put(70, "Seventy");
            words.put(80, "Eighty");
            words.put(90, "Ninety");
            String digits[] = {"", "Hundred", "Thousand", "Lakh", "Crore", "arab", "kharab"};
            while (i < digits_length) {
                int divider = (i == 2) ? 10 : 100;
                number = no % divider;
                no = no / divider;
                i += divider == 10 ? 1 : 2;
                if (number > 0) {
                    int counter = str.size();
                    String plural = (counter > 0 && number > 9) ? "s" : "";
                    String tmp = (number < 21) ? words.get(Integer.valueOf((int) number)) + " " + digits[counter] + plural : words.get(Integer.valueOf((int) Math.floor(number / 10) * 10)) + " " + words.get(Integer.valueOf((int) (number % 10))) + " " + digits[counter] + plural;
                    str.add(tmp);
                } else {
                    str.add("");
                }
            }

            Collections.reverse(str);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Rupees = String.join(" ", str).trim();
            }

            paise = (decimal) > 0 ? " And Paise " + words.get(Integer.valueOf((int) (decimal - decimal % 10))) + " " + words.get(Integer.valueOf((int) (decimal % 10))) :"";
        }catch (NumberFormatException e){
            Toast.makeText(Start332AllResidentialPrice.this, "Enter number only", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        Intent intent = null;
        if(propertySubType.equals("Flat/Apartment") || propertySubType.equals("Builder_Floor")
                || propertySubType.equals("Pentahouse") || propertySubType.equals("Studio_Apartment")){

            intent = new Intent(Start332AllResidentialPrice.this, Start331AllResidential.class);
        }
        else if(propertySubType.equals("House") || propertySubType.equals("Farm_House") || propertySubType.equals("Villa")){
            intent = new Intent(Start332AllResidentialPrice.this, Start331AllResidential2.class);

        }
       /* else if(propertySubType.equals("Plot")){
            intent = new Intent(Start332AllResidentialPrice.this, Start331AllResidential3.class);

        }*/else if(propertySubType.equals("office") || propertySubType.equals("IT_Park")){
            intent = new Intent(Start332AllResidentialPrice.this, Start331AllCommercial.class);

        }else if(propertySubType.equals("Shop") || propertySubType.equals("Showroom")){
            intent = new Intent(Start332AllResidentialPrice.this, Start331AllCommercial2.class);

        }else if(propertySubType.equals("Commercial_Land") || propertySubType.equals("Agriculture_Land")){
            intent = new Intent(Start332AllResidentialPrice.this, Start331AllCommercial3.class);

        }else if(propertySubType.equals("Warehouse") || propertySubType.equals("Industrial_Building")
                || propertySubType.equals("Industrial_Shed")){
            intent = new Intent(Start332AllResidentialPrice.this, Start331AllCommercial4.class);

        }else if(propertySubType.equals("Industrial_Land")){
            intent = new Intent(Start332AllResidentialPrice.this, Start331AllCommercial5.class);

        }else if(propertySubType.equals("Coworking_Space")){
            intent = new Intent(Start332AllResidentialPrice.this, Start331AllCommercial6.class);

        }
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
        bundle.putString("MAINTENANCE_AMOUNT", maintenance);
        bundle.putString("SECURITY_AMOUNT", secuityAmount);
        bundle.putString("MAINTENANCE_Parameter", maintenance_parameter);

        intent= new Intent(Start332AllResidentialPrice.this, Start332AllPropertyStatus.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void next(View view) {

        price= ExpectedPrice.getText().toString();

        maintenance= MaintenanceCharge.getText().toString();

        secuityAmount= SecuityAmount.getText().toString();

        if(ExpectedPrice.getText().toString().length()==0){
            Toast.makeText(Start332AllResidentialPrice.this, "Enter Price", Toast.LENGTH_SHORT).show();
            ExpectedPrice.requestFocus();
        }else {
            // price
            if(!TextUtils.isEmpty(price)) {
                double result = Double.parseDouble(price);
                price = String.format("%,.2f", result);
            }
            // token
            if(!TextUtils.isEmpty(secuityAmount)) {
                double result2 = Double.parseDouble(secuityAmount);
                secuityAmount = String.format("%,.2f", result2);
                // maintenance
            }
            if(!TextUtils.isEmpty(maintenance)) {
                double result3 = Double.parseDouble(maintenance);
                maintenance = String.format("%,.2f", result3);
            }
            intenter();
        }

    }
}
