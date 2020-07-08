package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.City;
import com.example.propertyrealtors.model.Locality;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Start131 extends AppCompatActivity {

    DatabaseReference mRef;

    String selectedCity, locality;
    private long number;
    AutoCompleteTextView City, Society;
    String PURPOSE;
    List<String> CityList;
    List<String> localityList;
    List<String> textList;
    List<String> list;
    List<String> cList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start131);

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            PURPOSE = bundle.getString("PURPOSE_OF_USER");
            Log.e(" ", PURPOSE);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        City = findViewById(R.id.search);
        CityList = new ArrayList<String>();
        localityList = new ArrayList<String>();
        list = new ArrayList<String>();
        textList = new ArrayList<String>((int) number);

        getCity();
        AddCityInautoTextView();


    }

    private void AddCityInautoTextView() {
        try {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            City.setAdapter(arrayAdapter);
            City.setThreshold(1);
            City.setTextColor(Color.BLUE);
            City.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String data = parent.getItemAtPosition(position).toString();
                    City.setText("");
//                    AddCityInautoTextView();
                    creterTextView(data);
                    Log.e("Auto", data);
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void creterTextView(String data) {
        try {

            boolean check = true;
            LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);
            for (int i = 0; i < textList.size(); i++) {
                if (data.equals(textList.get(i))) {
                    check = false;
                }
            }
            if (check) {
                TextView location = new TextView(this);
                location.setText(data + " ✗ ");
                location.setPadding(20, 20, 20, 20);
                location.setBackgroundColor(Color.RED);
                location.setTextColor(Color.WHITE);
                layout.addView(location);
                textList.add(data);

                cList.add(data);

                location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            layout.removeView(location);
                            textList.remove(data);
                            cList.remove(data);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Log.e("size", " " + textList.size());

                Log.e("clist", " " + cList);

            }

            Log.e("ListTectView", String.valueOf(textList) + "  " + textList.size());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void getCity() {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("City").child("CityName");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    number = dataSnapshot.getChildrenCount();
                    for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                        City city = areaSnapshot.getValue(City.class);
                        CityList.add(city.getCity());
                        getLocality(city.getCity());
                    }
                    list.addAll(CityList);
//                    Log.e("city", String.valueOf(CityList));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Start131.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getLocality(String city) {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("City").child("Locality").child(city);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    number = number + dataSnapshot.getChildrenCount();

                    for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                        Locality locality = areaSnapshot.getValue(Locality.class);
                        localityList.add(locality.getLocality());
                    }
                    for (String s : localityList) {
                        if (!list.contains(s)) {
                            list.add(s);
                        }
                    }
//                    list.addAll(localityList);
                    Log.e("localityList1", String.valueOf(localityList));
                    Log.e("list2", String.valueOf(list));


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void next(View view) {
        try {
            if (cList.size() == 0) {
                Toast.makeText(this, "Enter City", Toast.LENGTH_LONG).show();
                return;
            } else {
                //session
                SessionManager session = new SessionManager(getApplicationContext());
                session.createSearchSession("residential", PURPOSE);

                Bundle bundle = new Bundle();
                bundle.putString("PROPERTY_FOR", PURPOSE);
                bundle.putStringArrayList("CITYLIST", (ArrayList<String>) cList);

                Intent intent = new Intent(Start131.this, Start13_1_2_3_all.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
