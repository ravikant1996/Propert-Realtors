package com.example.real_estate_business.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_estate_business.City_Adapter.SearchPlaceLocalityAdapter;
import com.example.real_estate_business.R;
import com.example.real_estate_business.model.Locality;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class All331ProjectSearch extends AppCompatActivity {
    SearchPlaceLocalityAdapter dataAdapter;
    ArrayList<Locality> localityList;
    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    DatabaseReference mRef;
    String city;
    long lid=0;
    String addlocal;
    DatabaseReference databaseReference;
    DatabaseReference reference;
    Locality locality;
    TextView addlocalitybtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all331_project_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addlocalitybtn = findViewById(R.id.addbtn);
        textView = findViewById(R.id.add);
        addlocalitybtn.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            city = bundle.getString("CITY_SELECTED", null);

            if (!city.isEmpty()) {

                localityList = new ArrayList<Locality>();
                mRef = FirebaseDatabase.getInstance().getReference().child("City").child("Locality").child(city);
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            Locality locality = areaSnapshot.getValue(Locality.class);
                            localityList.add(locality);
                        }
                        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        mRecyclerView.setHasFixedSize(true);
                        layoutManager = new LinearLayoutManager(All331ProjectSearch.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        dataAdapter = new SearchPlaceLocalityAdapter(All331ProjectSearch.this, localityList);
                        mRecyclerView.setAdapter(dataAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconifiedByDefault(true); //this line
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                 return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                if (dataAdapter != null){
                    dataAdapter.getFilter().filter(newText);
                    if (dataAdapter.getItemCount() < 1){
                        if (newText.length() >= 3) {
                            addlocal = newText;
                            textView.setVisibility(View.VISIBLE);
                            addlocalitybtn.setVisibility(View.VISIBLE);
                        }else {
                            addlocal="";
                            textView.setVisibility(View.GONE);
                            addlocalitybtn.setVisibility(View.GONE);
                        }
                    }else {
                        textView.setVisibility(View.GONE);
                        addlocalitybtn.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);

                    }
                }

                return true;
            }
        });
        return true;
    }

    public void AddLocality(View view) {
        if (!addlocal.equals("")) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("City");
            reference = databaseReference.child("Locality");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        lid = (dataSnapshot.getChildrenCount());
                        locality = new Locality(addlocal+" ("+city+")");
                        reference.child(city).child(String.valueOf(lid + 1)).setValue(locality);
                        Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                        String project = addlocal+" ("+city+")";
                        Intent intent = new Intent(All331ProjectSearch.this, Start331All.class);
                     //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        int flag = 2;
                        bundle.putInt("FLAG", flag);
                        bundle.putString("PROJECT_TYPE", project);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Toast.makeText(All331ProjectSearch.this, "Search view is empty", Toast.LENGTH_SHORT).show();
        }
    }
}
