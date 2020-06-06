package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.propertyrealtors.R;

public class searchFilter_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_1);
    }

    public void next(View view) {
    }

    public void back(View view) {
        startActivity(new Intent(searchFilter_1.this, MainActivity.class));
        finish();
    }

    public void reset(View view) {
    }
}
