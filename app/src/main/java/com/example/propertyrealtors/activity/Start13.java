package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.propertyrealtors.R;

public class Start13 extends AppCompatActivity {

    String PURPOSE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start13);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        try {
            PURPOSE = bundle.getString("PURPOSE_OF_USER", null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void back(View view) {
        startActivity(new Intent(Start13.this, MainActivity.class));
        finish();
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        String type;
        switch (v.getId()) {

            case R.id.radio1:
                if (checked) {
                    type = "BUY_A_PROPERTY";
                    Intent intent = new Intent(Start13.this, Start131.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PURPOSE_OF_USER", PURPOSE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.radio2:
                if (checked) {
                    type = "RENT_A_PROPERTY";
                    Intent intent = new Intent(Start13.this, Start132.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PURPOSE_OF_USER", PURPOSE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.radio3:
                if (checked) {
                    type = "SELL/RENT_USER";
                    Intent intent = new Intent(Start13.this, Start133.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PURPOSE_OF_USER", PURPOSE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }
}
