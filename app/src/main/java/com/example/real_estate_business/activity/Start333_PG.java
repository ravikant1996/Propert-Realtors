package com.example.real_estate_business.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.real_estate_business.R;

public class Start333_PG extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start333__p_g);

        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://pgnhostels.com/our-contact/")));
    }
}
