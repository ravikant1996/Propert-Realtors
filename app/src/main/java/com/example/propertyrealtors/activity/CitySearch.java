package com.example.propertyrealtors.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

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

public class CitySearch extends AppCompatActivity {

    SearchView searchView;
    ListView listView, listView2;
    List<String> list;
    ArrayAdapter<String> adapter;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_search);

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);
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

                String city= listView.getItemAtPosition(position).toString();

                Intent intent = new Intent(CitySearch.this, Start331All.class);
                Bundle bundle = new Bundle();
                bundle.putString("CITY_NAME", city);
                intent.putExtras(bundle);
                startActivity(intent);
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
    }
}
