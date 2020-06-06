package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;

public class Start13_1_2_3_all extends AppCompatActivity {

    View includedLayout;
    String selectedCity, locality, areaParameter;
    Spinner minRupees, maxRupees, areaParameterSpinner, areaMinSpinner, areaMaxSpinner;

    TextView bhk1, bhk2, bhk3, bhk4, bhk5, bhk6, bhk7, bhk8, bhk9, bhk10, bhk11;
    int b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;
    String minimumArea, maximumArea;
    TextView error1, error2;
    String minRupeeString, maxRupeeString, flat, plot, house;
    String PAGE_LOAD;
    ImageButton Flatbtn, Plotbtn, Housebtn;
    TextView bedroomTextview, AreaTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start13_1_2_3_all);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            PAGE_LOAD = bundle.getString("PAGE_LOAD", null);
            selectedCity = bundle.getString("selectedCity", null);
            locality = bundle.getString("locality", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        minRupees = (Spinner) findViewById(R.id.minRupee);
        maxRupees = (Spinner) findViewById(R.id.maxRupee);
        areaMaxSpinner = (Spinner) findViewById(R.id.area_max);
        areaMinSpinner = (Spinner) findViewById(R.id.area_min);
        areaParameterSpinner = (Spinner) findViewById(R.id.area_parameter);
        error1 = findViewById(R.id.minError);
        error2 =  findViewById(R.id.maxError);
        Flatbtn =  findViewById(R.id.flat);
        Plotbtn =  findViewById(R.id.plot);
        Housebtn =  findViewById(R.id.villa);
        bedroomTextview =  findViewById(R.id.textView4);
        AreaTextview =  findViewById(R.id.textView8);
        AreaTextview.setVisibility(View.INVISIBLE);

        minRupees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                minRupeeString = parent.getItemAtPosition(position).toString();
                error1.setText(minRupeeString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });

        maxRupees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                maxRupeeString = parent.getItemAtPosition(position).toString();
                error2.setText(maxRupeeString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });
        areaParameterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                areaParameter= parent.getItemAtPosition(position).toString();
              //  error2.setText(maxRupeeString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });
        areaMinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                minimumArea= parent.getItemAtPosition(position).toString();
                //  error2.setText(maxRupeeString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });
        areaMaxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                maximumArea= parent.getItemAtPosition(position).toString();
                //  error2.setText(maxRupeeString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });

        // include bedroom layout
        includedLayout = findViewById(R.id.bedroom);
        bhk1 = (TextView) includedLayout.findViewById(R.id.bhk1);
        bhk2 = (TextView) includedLayout.findViewById(R.id.bhk2);
        bhk3 = (TextView) includedLayout.findViewById(R.id.bhk3);
        bhk4 = (TextView) includedLayout.findViewById(R.id.bhk4);
        bhk5 = (TextView) includedLayout.findViewById(R.id.bhk5);
        bhk6 = (TextView) includedLayout.findViewById(R.id.bhk6);
        bhk7 = (TextView) includedLayout.findViewById(R.id.bhk7);
        bhk8 = (TextView) includedLayout.findViewById(R.id.bhk8);
        bhk9 = (TextView) includedLayout.findViewById(R.id.bhk9);
        bhk10 = (TextView) includedLayout.findViewById(R.id.bhk10);
        bhk11 = (TextView) includedLayout.findViewById(R.id.bhk11);




    }

    public void visibility(){
        if(Flatbtn.isSelected() || Housebtn.isSelected()){
            includedLayout.setVisibility(View.VISIBLE);
            bedroomTextview.setVisibility(View.VISIBLE);
        }
    }
    public void back(View view) {
        if(PAGE_LOAD.equals("131")) {
            startActivity(new Intent(Start13_1_2_3_all.this, Start131.class));
        }else if(PAGE_LOAD.equals("132")) {
            startActivity(new Intent(Start13_1_2_3_all.this, Start132.class));
        }else if(PAGE_LOAD.equals("133")) {
            startActivity(new Intent(Start13_1_2_3_all.this, Start133.class));
        }
    }

    public void next(View view) {
        Intent intent = new Intent(Start13_1_2_3_all.this, Search_Property.class);
        Bundle bundle = new Bundle();

        bundle.putString("minRupees", minRupeeString);
        bundle.putString("maxRupees", maxRupeeString);
        bundle.putString("selectedCity", selectedCity);
        bundle.putString("locality", locality);
        bundle.putString("plot", plot);
        bundle.putString("flat", flat);
        bundle.putString("house", house);
        bundle.putString("maximumArea", maximumArea);
        bundle.putString("minimumArea", minimumArea);
        bundle.putString("areaParameter", areaParameter);


        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void clearAll(View view) {
        minRupees.setSelection(0);
        maxRupees.setSelection(0);
        areaMaxSpinner.setSelection(0);
        areaMinSpinner.setSelection(0);
        areaParameterSpinner.setSelection(0);
        if (Plotbtn.isSelected()) {
            Plotbtn.setBackgroundColor(Color.WHITE);
            plot = "";
        }
        if (Flatbtn.isSelected()) {
            Flatbtn.setBackgroundColor(Color.WHITE);
            flat = "";
        }
        if (Housebtn.isSelected()) {
            Housebtn.setBackgroundColor(Color.WHITE);
            house = "";
        }
        if(bhk1.isSelected() && bhk2.isSelected() && bhk3.isSelected() && bhk4.isSelected() &&
                bhk5.isSelected() && bhk6.isSelected() && bhk7.isSelected() && bhk8.isSelected() &&
                bhk9.isSelected() && bhk10.isSelected() && bhk11.isSelected()){
            bhk1.setBackgroundColor(Color.WHITE);
            bhk2.setBackgroundColor(Color.WHITE);
            bhk3.setBackgroundColor(Color.WHITE);
            bhk4.setBackgroundColor(Color.WHITE);
            bhk5.setBackgroundColor(Color.WHITE);
            bhk6.setBackgroundColor(Color.WHITE);
            bhk7.setBackgroundColor(Color.WHITE);
            bhk8.setBackgroundColor(Color.WHITE);
            bhk9.setBackgroundColor(Color.WHITE);
            bhk10.setBackgroundColor(Color.WHITE);
            bhk11.setBackgroundColor(Color.WHITE);

            b1=b2=b3=b4=b5=b6=b7=b8=b9=b10=b11=0;
        }

    }

    public void btnFlat(View view) {
        ImageButton textView = (ImageButton) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            flat="";
            bedroomTextview.setVisibility(View.GONE);
            includedLayout.setVisibility(View.GONE);

        } else {
            view.setSelected(true);
            visibility();
            textView.setBackgroundColor(Color.LTGRAY);
            flat="FLat";
        }
    }

    public void btnVilla(View view) {
        ImageButton textView = (ImageButton) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            //   check.setTextColor(Color.BLUE);
            view.setSelected(false);
            house= "";
            bedroomTextview.setVisibility(View.GONE);
            includedLayout.setVisibility(View.GONE);
        } else {
            visibility();
            textView.setBackgroundColor(Color.LTGRAY);
            view.setSelected(true);
            house= "House/Villa";

        }
    }

    public void btnPlot(View view) {
        ImageButton textView = (ImageButton) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            plot="";
            AreaTextview.setVisibility(View.INVISIBLE);
            areaParameterSpinner.setVisibility(View.INVISIBLE);
            areaMinSpinner.setVisibility(View.INVISIBLE);
            areaMaxSpinner.setVisibility(View.INVISIBLE);
        } else {
            textView.setBackgroundColor(Color.LTGRAY);
            plot = "Plot";
            AreaTextview.setVisibility(View.VISIBLE);
            areaParameterSpinner.setVisibility(View.VISIBLE);
            areaMinSpinner.setVisibility(View.VISIBLE);
            areaMaxSpinner.setVisibility(View.VISIBLE);
            view.setSelected(true);
        }
    }

    public void selectBHK1(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b1=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b1=1;
        }
    }

    public void selectBHK2(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b2=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b2=2;
        }
    }

    public void selectBHK3(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b3=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b3=3;
        }
    }

    public void selectBHK4(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b4=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b4=4;
        }
    }

    public void selectBHK5(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b5=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b5=5;
        }
    }

    public void selectBHK6(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b6=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b6=6;
        }
    }

    public void selectBHK7(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b7=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b7=7;
        }
    }

    public void selectBHK8(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b8=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b8=8;
        }
    }

    public void selectBHK9(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b9=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b9=9;
        }
    }

    public void selectBHK10(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b10=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b10=10;
        }
    }

    public void selectBHK11(View view) {
        TextView textView = (TextView) view;
        textView.setBackgroundColor(Color.WHITE);
        if (textView.isSelected()) {
            textView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            b11=0;
        } else {
            view.setSelected(true);
            textView.setBackgroundColor(Color.LTGRAY);
            b11=11;
        }
    }
}
