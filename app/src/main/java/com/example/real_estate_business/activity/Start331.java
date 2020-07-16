package com.example.real_estate_business.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_estate_business.R;

public class Start331 extends AppCompatActivity {
    String propertyFor, propertyType;
    Button Residential, commercial, skip, next;
    String name, UID;
    EditText Name;
    TextView seterror, Heading;
    RadioGroup radioGroup1, radioGroup2;
    static final String TAG= "Start331";
    public static String  PROPERTY_STATIC;
    int flag =0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Residential = findViewById(R.id.residential);
        commercial = findViewById(R.id.commercial);
        seterror = findViewById(R.id.propertytype);
        next = findViewById(R.id.next);
        Name = findViewById(R.id.name);
        Heading = findViewById(R.id.textView3);
        Heading.setVisibility(View.INVISIBLE);
        radioGroup1 = findViewById(R.id.radio1);
        radioGroup2 = findViewById(R.id.radio2);
        RadioButton radio31 = findViewById(R.id.radio31);

        radioGroup1.setVisibility(View.INVISIBLE);
        radioGroup2.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            propertyFor = bundle.getString("PURPOSE", "defValue");
            UID = bundle.getString("UID","defValue");
         //   Log.e(TAG, UID);


            Log.e(TAG, propertyFor);
          //  PROPERTY_STATIC= propertyFor;
            if(propertyFor.equals("RENT or LEASE")) {
                radio31.setVisibility(View.VISIBLE);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            Log.e("", "");
        }

    }

    public void select(View v) {

        if (v.getId() == R.id.residential){
            propertyType = "residential";
            seterror.setText(propertyType);
            Residential.setEnabled(false);
            commercial.setEnabled(true);
            Heading.setVisibility(View.VISIBLE);
            radioGroup1.setVisibility(View.VISIBLE);
            radioGroup2.setVisibility(View.INVISIBLE);


        }
        else if (v.getId() == R.id.commercial){
            propertyType = "commercial";
            seterror.setText(propertyType);
            commercial.setEnabled(false);
            Residential.setEnabled(true);
            Heading.setVisibility(View.VISIBLE);
            radioGroup2.setVisibility(View.VISIBLE);
            radioGroup1.setVisibility(View.INVISIBLE);

        }
    }


    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        //now check which radio button is selected
        if(seterror.getText().toString().length() ==0) {
            seterror.setError("please select Property \nType");
            Toast.makeText(Start331.this, "Please Select Property\nType", Toast.LENGTH_SHORT).show();

        }else {
            Intent intent = new Intent(Start331.this, Start331All.class);
            Bundle bundle = new Bundle();
            bundle.putString("UID", UID);

            switch (v.getId()) {

                case R.id.radio11:
                    bundle.putString("R/C_TYPE", "Flat/Apartment");
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putInt("FLAG", flag);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio12:
                    bundle.putInt("FLAG", flag);
                    bundle.putString("R/C_TYPE", "House");
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    break;
                case R.id.radio13:
                    bundle.putInt("FLAG", flag);
                    bundle.putString("R/C_TYPE", "Villa");
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    break;
                case R.id.radio14:
                    bundle.putInt("FLAG", flag);
                    bundle.putString("R/C_TYPE", "Builder_Floor");
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    break;
                case R.id.radio15:
                    bundle.putInt("FLAG", flag);
                    bundle.putString("R/C_TYPE", "Plot");
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    break;
                case R.id.radio16:
                    bundle.putInt("FLAG", flag);
                    bundle.putString("R/C_TYPE", "Studio_Apartment");
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    break;
                case R.id.radio17:
                    bundle.putInt("FLAG", flag);
                    bundle.putString("R/C_TYPE", "Pentahouse");
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    break;
                case R.id.radio18:
                    bundle.putInt("FLAG", flag);
                    bundle.putString("R/C_TYPE", "Farm_House");
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    break;
            }
        }
    }

    public void onRadioButton2Clicked(View v) {
            boolean checked = ((RadioButton) v).isChecked();
            //now check which radio button is selected
            //android switch statement
        if(seterror.getText().toString().length() ==0) {
            seterror.setError("please select Property \nType");
            Toast.makeText(Start331.this, "Please Select Property\nType", Toast.LENGTH_SHORT).show();

        }else {
            Intent intent = new Intent(Start331.this, Start331All.class);
            Bundle bundle = new Bundle();
            bundle.putString("UID", UID);


            switch (v.getId()) {
                case R.id.radio21:
                    bundle.putInt("FLAG", flag);
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "office");
                    intent.putExtras(bundle);
                    startActivity(intent);

                    break;
                case R.id.radio22:
                    bundle.putInt("FLAG", flag);

                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "IT_Park");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio23:
                    bundle.putInt("FLAG", flag);

                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "Shop");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio24:
                    bundle.putInt("FLAG", flag);

                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "Showroom");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio25:
                    bundle.putInt("FLAG", flag);

                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "Commercial_Land");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio26:
                    bundle.putInt("FLAG", flag);

                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "Warehouse");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio27:
                    bundle.putInt("FLAG", flag);

                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "Industrial_Land");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio28:
                    bundle.putInt("FLAG", flag);

                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "Industrial_Building");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio29:
                    bundle.putInt("FLAG", flag);

                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "Industrial_Shed");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio30:
                    bundle.putInt("FLAG", flag);

                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "Agriculture_Land");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.radio31:
                    bundle.putInt("FLAG", flag);
                    bundle.putString("PROPERTY_FOR", propertyFor);
                    bundle.putString("PROPERTY_TYPE", propertyType);
                    bundle.putString("R/C_TYPE", "Coworking_Space");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
            }
        }
    }
}
