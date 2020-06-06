package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Start133 extends AppCompatActivity {

    SearchView searchView  ;
    RecyclerView rvSearch;
    TextView error, header;
    DatabaseReference mRef;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    Spinner classSpinner, divSpinner;
    // string variable to store selected values
    String selectedCity, locality;
    String PURPOSE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start133);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PURPOSE = bundle.getString("PURPOSE_OF_USER", null);

        error = findViewById(R.id.error);
        header = findViewById(R.id.header);
        classSpinner = (Spinner) findViewById(R.id.classSpinner);
        divSpinner = (Spinner) findViewById(R.id.divSpinner);

        // Class Spinner implementing onItemSelectedListener
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedCity = parent.getItemAtPosition(position).toString();

                header.setText("You are searching in "+ selectedCity);
                error.setText(selectedCity);
                switch (selectedCity)
                {

                    case "Faridabad":
                        // assigning div item list defined in XML to the div Spinner
                        divSpinner.setAdapter(new ArrayAdapter<String>(Start133.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.items_div_class_1)));
                        break;

                    case "Delhi":
                        divSpinner.setAdapter(new ArrayAdapter<String>(Start133.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.items_div_class_2)));
                        break;

                    case "Noida":
                        divSpinner.setAdapter(new ArrayAdapter<String>(Start133.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.items_div_class_3)));
                        break;

                    case "Gurugoan":
                        divSpinner.setAdapter(new ArrayAdapter<String>(Start133.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.items_div_class_4)));
                        break;
                }

                //set divSpinner Visibility to Visible
                divSpinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });

        // Div Spinner implementing onItemSelectedListener
        divSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                locality = parent.getItemAtPosition(position).toString();
                /*
                    Now that we have both values, lets create a Toast to
                    show the values on screen
                */
                Toast.makeText(Start133.this, "\n Class: \t " + selectedCity +
                        "\n Div: \t" + locality, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Start133.this, Start13_1_2_3_all.class);
                String key= "133";
                Bundle bundle = new Bundle();
                bundle.putString("selectedCity", selectedCity);
                bundle.putString("locality", locality);
                bundle.putString("PAGE_LOAD", key);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }

        });
    }

    public void back(View view) {
        startActivity(new Intent(Start133.this, Start13.class));
    }

    public void next(View view) {
        if(error.getText().toString().length()==0){
            error.setError("");
            Toast.makeText(Start133.this, "Please Select City", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Start133.this, "\n Class: \t " + selectedCity +
                    "\n Div: \t" + locality, Toast.LENGTH_LONG).show();
            String key = "131";
            Bundle bundle = new Bundle();
            bundle.putString("selectedCity", selectedCity);
            bundle.putString("locality", locality);
            bundle.putString("PAGE_LOAD", key);

            if(PURPOSE.equals("BUY_A_PROPERTY")) {
                Intent intent = new Intent(Start133.this, Start13_1_2_3_all.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }else {
                Intent intent = new Intent(Start133.this, Start23_1_2_3_all.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
}
