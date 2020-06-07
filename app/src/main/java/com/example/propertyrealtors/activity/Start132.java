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

        error = findViewById(R.id.error);
        header = findViewById(R.id.header);
        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);
        listView2 = (ListView) findViewById(R.id.lv2);
        getCitys = new ArrayList<>();
        list = new ArrayList<>();
        spineerCity();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit (String query){
               /* if (list.contains(query)) {
                    adapter.getFilter().filter(query);
                } else {
                    Toast.makeText(All331CitySearch.this, "No Match found", Toast.LENGTH_LONG).show();
                }
              */
                return false;
            }
            @Override
            public boolean onQueryTextChange (String newText){
                if (list.contains(newText)) {
                    adapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedCity= listView.getItemAtPosition(position).toString();
                header.setText("You are searching in "+selectedCity);

                final List<String> list2 = new ArrayList<>();
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list2);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                listView2.setAdapter(adapter);
                mRef = FirebaseDatabase.getInstance().getReference().child("City").child("Locality");
                Query query= mRef.child(selectedCity);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                                String areaName = areaSnapshot.child("locality").getValue(String.class);
                                list2.add(areaName);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                locality= listView2.getItemAtPosition(position).toString();
            }
        });
    }
    public void spineerCity() {
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(adapter);
        mRef = FirebaseDatabase.getInstance().getReference().child("City");
        mRef.child("CityName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("city").getValue(String.class);
                    list.add(areaName);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void back(View view) {
        startActivity(new Intent(Start132.this, Start13.class));
        finish();
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
