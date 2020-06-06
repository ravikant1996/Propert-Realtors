package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;

import java.util.HashMap;

public class Start31 extends AppCompatActivity {
    RadioButton radioButton0, radioButton1, radioButton2, radioButton3;
    RadioGroup radioGroup;
    String purpose;
    static final String TAG= "Start31";
    Boolean Signal;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start31);

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
                    Intent intent = new Intent(Start31.this, Start32Agent.class);
                    Bundle bundle = new Bundle();
                 //   bundle.putString("PURPOSE", purpose);
                    bundle.putString("USER_TYPE", userType);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }

    public void back(View view) {
/*        if(!Signal) {
            startActivity(new Intent(Start31.this, StartActivty.class));
        }else {
            startActivity(new Intent(Start31.this, MainActivity.class));
        }*/
        startActivity(new Intent(Start31.this, StartActivty.class));
        finish();
    }
}
