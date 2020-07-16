package com.example.real_estate_business.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;

public class StartActivty extends AppCompatActivity {
    RadioButton radioButton0, radioButton1, radioButton2, radioButton3;
    RadioGroup radioGroup;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activty);

        session= new SessionManager(getApplicationContext());
   /*     if (session.isLoggedIn()) {
            startActivity(new Intent(StartActivty.this, MainActivity.class));
            finish();
        }
*/
        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class );
            startActivity(mainActivity);
            finish();
        }

        radioGroup = findViewById(R.id.radiogroup);
        radioButton0 = findViewById(R.id.radioButton0);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

    }
    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        //android switch statement
        String type;
        switch (v.getId()) {

            case R.id.radioButton0:
                if (checked) {
                    type= "SELL";
                    Intent intent = new Intent(StartActivty.this, Start11.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PURPOSE_OF_USER", type);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    savePrefsData();
                    finish();
                }
                break;
            case R.id.radioButton1:
                if (checked) {
                    type= "RENT or LEASE";
                    Intent intent = new Intent(StartActivty.this, Start11.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PURPOSE_OF_USER", type);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    savePrefsData();
                    finish();
                }
                break;
            case R.id.radioButton2:
                if (checked) {
                    type= "SELL/RENT_USER";
                    Intent intent = new Intent(StartActivty.this, Start31.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PURPOSE_OF_USER", type);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    savePrefsData();
                    finish();
                }
                break;
            case R.id.radioButton3:
                if (checked) {
                 //   type= "ALREADY_POSTED_USER";
                    Intent intent = new Intent(StartActivty.this, Login.class);
                   /* Bundle bundle = new Bundle();
                    bundle.putString("PURPOSE_OF_USER", type);
                    intent.putExtras(bundle);
                   */
                   startActivity(intent);
                    savePrefsData();
                    finish();
                }
                break;
        }
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();
    }

}
