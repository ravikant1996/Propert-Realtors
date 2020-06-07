package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.propertyrealtors.Post_property.TabLayoutPostAdapter;
import com.example.propertyrealtors.R;
import com.google.android.material.tabs.TabLayout;

public class posted_property extends AppCompatActivity {

    String UID;
    TabLayout tabLayout;
    ViewPager viewPager;
    TabLayoutPostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_property);
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        try {
            Bundle bundle = getIntent().getExtras();
            UID = bundle.getString("UID", "------");
        }catch (Exception e){
            e.printStackTrace();
        }

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        // Residential
        tabLayout.addTab(tabLayout.newTab().setText("Rent/lease\nResidential"));
        tabLayout.addTab(tabLayout.newTab().setText("Sell\nResidential"));
        // Commercial
        tabLayout.addTab(tabLayout.newTab().setText("Rent/lease\nCommercial"));
        tabLayout.addTab(tabLayout.newTab().setText("Sell\nCommercial"));

      //  tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new TabLayoutPostAdapter(posted_property.this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(posted_property.this, MainActivity.class));
                finishAffinity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void back(View view) {
        Intent intent= new Intent(posted_property.this, MainActivity.class);
        Bundle bundle= new Bundle();
        bundle.putString("UID", UID);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(posted_property.this, MainActivity.class);
        Bundle bundle= new Bundle();
        bundle.putString("UID", UID);
        intent.putExtras(bundle);
        startActivity(intent);
        super.onBackPressed();

    }
}

