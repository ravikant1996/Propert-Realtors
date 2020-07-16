package com.example.real_estate_business.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;

public class Start31 extends AppCompatActivity {
    RadioButton radioButton0, radioButton1, radioButton2, radioButton3;
    RadioGroup radioGroup;
    String purpose;
    static final String TAG= "Start31";
    Boolean Signal;
    SessionManager sessionManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start31);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Start31.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            Signal = bundle.getBoolean("SiGNAL");
        }catch (Exception e){
            e.printStackTrace();
        }
      /*  sessionManager= new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        if (sessionManager.isLoggedIn()) {
            HashMap<String, String> userID = sessionManager.getUserIDs();
            String UID = userID.get(SessionManager.KEY_ID);
            Intent intent1 = new Intent(Start31.this, Start33.class) ;
            Bundle bundle1 = new Bundle();
            bundle.putString("UID", UID);
            intent.putExtras(bundle1);
            startActivity(intent1);
            finish();
        }
  */      radioGroup = findViewById(R.id.radiogroup);
        radioButton0 = findViewById(R.id.radioButton0);
        radioButton1 = findViewById(R.id.radioButton1);


    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        String userType;
        switch (v.getId()) {

            case R.id.radioButton0:
                if (checked) {
                    userType = "Owner";
                    Intent intent = new Intent(Start31.this, Start32.class);
                    Bundle bundle = new Bundle();
                //    bundle.putString("PURPOSE", purpose);
                    bundle.putString("USER_TYPE", userType);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.radioButton1:
                if (checked) {
                    userType= "Agent";
                    Intent intent = new Intent(Start31.this, Start32.class);
                    Bundle bundle = new Bundle();
                 //   bundle.putString("PURPOSE", purpose);
                    bundle.putString("USER_TYPE", userType);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(Start31.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();

    }

   /* public void back(View view) {
*//*        if(!Signal) {
            startActivity(new Intent(Start31.this, StartActivty.class));
        }else {
            startActivity(new Intent(Start31.this, MainActivity.class));
        }*//*

        Intent intent = new Intent(Start31.this, Start31.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }*/
}
