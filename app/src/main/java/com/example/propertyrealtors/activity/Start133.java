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
