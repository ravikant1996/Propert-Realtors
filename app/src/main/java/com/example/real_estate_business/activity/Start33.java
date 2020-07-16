package com.example.real_estate_business.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;

public class Start33 extends AppCompatActivity {
    String usertype, UID;
    static final String TAG= "Start33";
    Toolbar toolbar;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start33);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Start33.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        session= new SessionManager(getApplicationContext());
        session.isLoggedIn();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            UID = bundle.getString("UID",null);
       //     Log.e(TAG, usertype);
       //     Log.e(TAG, UID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        String purpose;
        switch (v.getId()) {

            case R.id.radioButton0:
                if (checked) {
                    purpose= "SELL";
                    Intent intent = new Intent(Start33.this, Start331.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PURPOSE", purpose);
                    bundle.putString("UID", UID);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.radioButton1:
                if (checked) {
                    purpose= "RENT or LEASE";
                    Intent intent = new Intent(Start33.this, Start331.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("UID", UID);
                    bundle.putString("PURPOSE", purpose);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.radioButton2:
                if (checked) {
                    purpose= "PG";
                    Intent intent = new Intent(Start33.this, Start333_PG.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PURPOSE", purpose);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }

    public void back(View view) {
        Intent intent =new Intent(Start33.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        if (!TextUtils.isEmpty(UID)) {
            startActivity(new Intent(Start33.this, MainActivity.class));
            finish();
            return;
        }
        super.onBackPressed();
    }

}