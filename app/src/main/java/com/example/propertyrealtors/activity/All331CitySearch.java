package com.example.propertyrealtors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.propertyrealtors.City_Adapter.SearchPlaceAdapter;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.model.City;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class All331CitySearch extends AppCompatActivity {

     SearchPlaceAdapter dataAdapter;
     ArrayList<City> cityList;
     RecyclerView mRecyclerView;
     LinearLayoutManager layoutManager;
     DatabaseReference mRef;
    LinearLayoutManager HorizontalLayout;
    String selectedCity;

    CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all331_city_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Search City");
       /* ImageButton back= findViewById(R.id.back);
        setSupportActionBar(back);


        public void back(View view) {
            startActivity(new Intent(All331CitySearch.this, Start331All.class));
        }*/

        cardView1= findViewById(R.id.view2);
        cardView2= findViewById(R.id.view3);
        cardView3= findViewById(R.id.view4);
        cardView4= findViewById(R.id.view5);
        cardView5= findViewById(R.id.view6);
        cardView6= findViewById(R.id.view7);

        cityList = new ArrayList<City>();
        mRef = FirebaseDatabase.getInstance().getReference().child("City").child("CityName");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    City city = areaSnapshot.getValue(City.class);
                    cityList.add(city);
                }
                mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(All331CitySearch.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                dataAdapter = new SearchPlaceAdapter(All331CitySearch.this, cityList);

              /*  HorizontalLayout = new LinearLayoutManager(All331CitySearch.this, LinearLayoutManager.HORIZONTAL,
                        false);
                mRecyclerView.setLayoutManager(HorizontalLayout);
*/
                mRecyclerView.setAdapter(dataAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                if (dataAdapter != null){
                    dataAdapter.getFilter().filter(newText);
                }

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void new_delhi(View view) {
        selectedCity= "New Delhi";
        Intent intent = new Intent(All331CitySearch.this, Start331All.class);
     //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        int flag=1;
        bundle.putInt("FLAG", flag);
        bundle.putString("CITY_SELECTED", selectedCity);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void Mumbai(View view) {
        selectedCity= "Mumbai";
        Intent intent = new Intent(All331CitySearch.this, Start331All.class);
        //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        int flag=1;
        bundle.putInt("FLAG", flag);
        bundle.putString("CITY_SELECTED", selectedCity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void noida(View view) {
        selectedCity= "Noida";
        Intent intent = new Intent(All331CitySearch.this, Start331All.class);
        //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        int flag=1;
        bundle.putInt("FLAG", flag);
        bundle.putString("CITY_SELECTED", selectedCity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void faridabad(View view) {
        selectedCity= "Faridabad";
        Intent intent = new Intent(All331CitySearch.this, Start331All.class);
        //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        int flag=1;
        bundle.putInt("FLAG", flag);
        bundle.putString("CITY_SELECTED", selectedCity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void gugrgram(View view) {
        selectedCity= "Gurugram";
        Intent intent = new Intent(All331CitySearch.this, Start331All.class);
        //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        int flag=1;
        bundle.putInt("FLAG", flag);
        bundle.putString("CITY_SELECTED", selectedCity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void delhi(View view) {
        selectedCity= "Delhi";
        Intent intent = new Intent(All331CitySearch.this, Start331All.class);
        //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        int flag=1;
        bundle.putInt("FLAG", flag);
        bundle.putString("CITY_SELECTED", selectedCity);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
