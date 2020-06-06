package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.propertyrealtors.R;

public class Start332 extends AppCompatActivity {

    String propertyFor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start332);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        propertyFor = bundle.getString("PURPOSE", null);

    }

    public void back(View view) {
    }

    public void onRadioButtonClicked(View view) {
    }

    public void select(View view) {
    }

    public void onRadioButton2Clicked(View view) {
    }
}
