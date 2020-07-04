package com.example.propertyrealtors.A_EndUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.propertyrealtors.Post_property.DetailsAdapterResiRorL;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.activity.MainActivity;
import com.example.propertyrealtors.activity.Start11;
import com.example.propertyrealtors.activity.Start13;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.PropertyModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class shortlisted_property extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    PropertyModel propertyModel;
    List<PropertyModel> propertyModelArrayList;
    LinearLayoutManager layoutManager;
    DatabaseReference reference;
    favouriteAdapter dataAdapter;
    ArrayList<PropertyModel> arrPackage;
    SharedPreferences sharedPreferences;
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
        mSwipeRefreshLayout = findViewById(R.id.swipeToRefresh);

        SessionManager session= new SessionManager(getApplicationContext());
        propertyModelArrayList = session.getFavorites();
       /* for (PropertyModel data : arrPackageData) {
            propertyModelArrayList.add(data);
        }*/

       if (propertyModelArrayList != null) {
           Toast.makeText(this, "exists "+ propertyModelArrayList, Toast.LENGTH_SHORT).show();

           recyclerView.setHasFixedSize(true);
           layoutManager = new LinearLayoutManager(getApplicationContext());
           recyclerView.setLayoutManager(layoutManager);
           recyclerView.setItemAnimator(new DefaultItemAnimator());
           dataAdapter = new favouriteAdapter(getApplicationContext(), propertyModelArrayList);
           recyclerView.setAdapter(dataAdapter);
       }else {
           Toast.makeText(this, "null "+ propertyModelArrayList, Toast.LENGTH_SHORT).show();
       }
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
}
