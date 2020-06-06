package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.propertyrealtors.R;

public class Start32Agent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start32_agent);




    }

    public void back(View view) {
        startActivity(new Intent(Start32Agent.this, Start31.class));
        finish();

    }

    public void next(View view) {
    }
}
