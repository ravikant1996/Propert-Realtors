package com.example.real_estate_business.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_estate_business.R;

public class Start331All extends AppCompatActivity {
    TextView City, Project;
    String city, project;
    static String CITY, PROJECT;
    String propertyFor, propertySubType, propertyType;
    int flag=0;
    Button Next;
    public static int CITY_FLAG=1;
    public static int INTENT=0;
    public static int LOCALITY_FLAG=2;
    static String  PROPERTY_FOR;
    static String  PROPERTY_TYPE;
    static String  PROPERTY_SUBTYPE;
    static String  UID;
    Toolbar toolbar;

    static final String TAG= "Start331All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all);

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
        City= findViewById(R.id.city);
        Project= findViewById(R.id.project);
        Next= findViewById(R.id.next);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            flag = bundle.getInt("FLAG");
            Log.e(TAG, "" + flag);

            if (flag == INTENT) {
                UID = bundle.getString("UID", "defValue");
                PROPERTY_FOR = bundle.getString("PROPERTY_FOR", "defValue_FOR");
                PROPERTY_TYPE = bundle.getString("PROPERTY_TYPE", "defValue_TYPE");
                PROPERTY_SUBTYPE = bundle.getString("R/C_TYPE", "defValue_SUBTYPE");
                if (!TextUtils.isEmpty(CITY)) {
                    City.setText(CITY);
                }
            /*    if (!TextUtils.isEmpty(PROJECT)) {
                    Project.setText(PROJECT);
                    Next.setVisibility(View.VISIBLE);
                }
         */   } else if (flag == CITY_FLAG) {
                city = bundle.getString("CITY_SELECTED", "defValue");
                City.setText(city);
                CITY = city;
            } else if (flag == LOCALITY_FLAG) {
                City.setText(CITY);
                project = bundle.getString("PROJECT_TYPE", "defValue");
                Project.setText(project);
                PROJECT = project;
                Next.setVisibility(View.VISIBLE);
            }

       /* //    Log.e(TAG, UID);
            Log.e(TAG, PROPERTY_SUBTYPE);
            Log.e(TAG, PROPERTY_FOR);
            Log.e(TAG, PROPERTY_TYPE);
*/

        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Log.e("", "");
        }
    }



    public void next(View view) {
        if (City.getText().toString().length() == 0) {

            Toast.makeText(getApplicationContext(),
                    "City cannot be Blank", Toast.LENGTH_LONG).show();
            City.setError("City cannot be Blank");

        }else if (Project.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Project cannot be Blank", Toast.LENGTH_LONG).show();
            Project.setError("Project cannot be Blank");
        } else
            {

            Intent intent;
            Bundle bundle = new Bundle();
            bundle.putString("R/C_TYPE", PROPERTY_SUBTYPE);
            bundle.putString("PROPERTY_FOR", PROPERTY_FOR);
            bundle.putString("PROPERTY_TYPE", PROPERTY_TYPE);
            bundle.putString("CITY", CITY);
            bundle.putString("LOCALITY", PROJECT);
            bundle.putString("UID", UID);


                switch (PROPERTY_SUBTYPE) {
                    case "Flat/Apartment":
                    case "Builder_Floor":
                    case "Pentahouse":
                    case "Studio_Apartment":

                        //  Studio m bhk not involved
                        intent = new Intent(Start331All.this, Start331AllResidential.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "House":
                    case "Farm_House":
                    case "Villa":
                        intent = new Intent(Start331All.this, Start331AllResidential2.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "Plot":
                        intent = new Intent(Start331All.this, Start331AllResidential3.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;
                    case "office":
                    case "IT_Park":
                        intent = new Intent(Start331All.this, Start331AllCommercial.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;
                    case "Shop":
                    case "Showroom":
                        intent = new Intent(Start331All.this, Start331AllCommercial2.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;
                    case "Commercial_Land":
                    case "Agriculture_Land":
                        intent = new Intent(Start331All.this, Start331AllCommercial3.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;
                    case "Warehouse":
                    case "Industrial_Building":
                    case "Industrial_Shed":
                        intent = new Intent(Start331All.this, Start331AllCommercial4.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;
                    case "Industrial_Land":
                        intent = new Intent(Start331All.this, Start331AllCommercial5.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;
                    case "Coworking_Space":
                        intent = new Intent(Start331All.this, Start331AllCommercial6.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;
                    default:
                        break;
                }

        }
    }

    public void searchCity(View view) {
        startActivity(new Intent(Start331All.this, All331CitySearch.class));

    }

    public void SearchProject(View view) {
        Intent intent = new Intent(Start331All.this, All331ProjectSearch.class);
        Bundle bundle = new Bundle();
        bundle.putString("CITY_SELECTED", CITY);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
