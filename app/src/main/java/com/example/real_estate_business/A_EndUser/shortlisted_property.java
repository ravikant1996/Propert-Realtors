package com.example.real_estate_business.A_EndUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SharedPreference;
import com.example.real_estate_business.model.Image;
import com.example.real_estate_business.model.PropertyModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class shortlisted_property extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<PropertyModel> propertyModelArrayList;
    LinearLayoutManager layoutManager;
    DatabaseReference reference;
    favouriteAdapter dataAdapter;
    ArrayList<PropertyModel> arrPackage;
    SharedPreferences sharedPreferences;
    SharedPreference sharedPreference;
    List<PropertyModel> favorites;
    String propertyFor;
    String propertyType;
    ArrayList<Image> imageArrayList1 = new ArrayList<Image>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortlisted_property);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        propertyModelArrayList = new ArrayList<PropertyModel>();

        recyclerView = findViewById(R.id.recyclerView);

        sharedPreference = new SharedPreference();
        favorites = sharedPreference.getFavorites(getApplicationContext());
        propertyModelArrayList = sharedPreference.getFavorites(getApplicationContext());

        if (propertyModelArrayList == null) {
            Snackbar.make(findViewById(R.id.favList_Layout), "No favourite - Null", Snackbar.LENGTH_LONG).show();
        } else {

            if (propertyModelArrayList.size() == 0) {
                Snackbar.make(findViewById(R.id.favList_Layout), "No favourite - size 0", Snackbar.LENGTH_LONG).show();
            }

            if (propertyModelArrayList != null) {
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                dataAdapter = new favouriteAdapter(getApplicationContext(), propertyModelArrayList);
                recyclerView.setAdapter(dataAdapter);
            }
        }

    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
