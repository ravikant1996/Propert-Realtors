package com.example.real_estate_business.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.real_estate_business.R;

public class Start13 extends AppCompatActivity {

    String PURPOSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start13);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start13.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        try {
            PURPOSE = bundle.getString("PURPOSE_OF_USER");
            Log.e(" ", PURPOSE);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        String type;
        Intent intent = null;
        Bundle bundle, bundle1, bundle2;
        switch (v.getId()) {

            case R.id.radio1:
                if (checked) {
                    intent = new Intent(Start13.this, Start131.class);
                    bundle = new Bundle();
                    bundle.putString("PURPOSE_OF_USER", PURPOSE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.radio2:
                if (checked) {
                    intent = new Intent(Start13.this, Start131.class);
                    bundle1 = new Bundle();
                    bundle1.putString("PURPOSE_OF_USER", PURPOSE);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                    finish();
                }
                break;

        }
    }
}
