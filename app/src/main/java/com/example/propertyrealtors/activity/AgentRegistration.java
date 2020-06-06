package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.propertyrealtors.R;

import java.util.ArrayList;

public class AgentRegistration extends AppCompatActivity {

    Spinner operating_since;
    String since;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_registration);

        operating_since= findViewById(R.id.operating_since);
        arrayList = new ArrayList<>();

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }




        int a=1960;
        int b=2020;
        while (a<b){
            a= a+10;
            arrayList.add(" "+a);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operating_since.setAdapter(arrayAdapter);
        operating_since.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                since = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





    }


    public void next(View view) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void multistory(View view) {
    }

    public void BuilderFloor(View view) {
    }

    public void Residentialhouse(View view) {
    }

    public void villa(View view) {
    }

    public void Residentialplot(View view) {
    }

    public void pentahouse(View view) {
    }

    public void Studio(View view) {
    }

    public void Commercial(View view) {
    }

    public void Office(View view) {
    }

    public void Commercialshop(View view) {
    }

    public void showroom(View view) {
    }

    public void Commercialland(View view) {
    }

    public void warehouse(View view) {
    }

    public void industrialland(View view) {
    }

    public void Industrialshed(View view) {
    }

    public void Agriculture(View view) {
    }

    public void Farmhouse(View view) {
    }

    public void rent(View view) {
    }

    public void booking(View view) {
    }

    public void prelaunch(View view) {
    }

    public void resale(View view) {
    }

    public void other(View view) {
    }
}
