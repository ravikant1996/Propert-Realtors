package com.example.propertyrealtors.activity;

import androidx.annotation.NonNull;
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
import com.example.propertyrealtors.model.City;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Start132 extends AppCompatActivity {

    TextView error, header;
    DatabaseReference mRef;

    SearchView searchView;
    ListView listView, listView2;
    List<String> list;
    ArrayList<City> getCitys;
    ArrayAdapter<String> adapter;
    Spinner classSpinner, divSpinner;
    // string variable to store selected values
    String selectedCity, locality;
    String PURPOSE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start132);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PURPOSE = bundle.getString("PURPOSE_OF_USER", null);

    }

    public void next(View view) {
        if(header.getText().toString().length()==0){
            header.setError("please select city");
            Toast.makeText(Start132.this, "Please Select City", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Start132.this, "\n Class: \t " + selectedCity +
                    "\n Div: \t" + locality, Toast.LENGTH_LONG).show();
            String key = "131";
            Bundle bundle = new Bundle();
            bundle.putString("selectedCity", selectedCity);
            bundle.putString("locality", locality);
            bundle.putString("PAGE_LOAD", key);

            if(PURPOSE.equals("BUY_A_PROPERTY")) {
                Intent intent = new Intent(Start132.this, Start13_1_2_3_all.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }else {
                Intent intent = new Intent(Start132.this, Start23_1_2_3_all.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
}
