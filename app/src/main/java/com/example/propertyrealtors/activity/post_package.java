package com.example.propertyrealtors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.CommercialModel;
import com.example.propertyrealtors.model.PropertyModel;
import com.example.propertyrealtors.model.ResidentialModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class post_package extends AppCompatActivity {
    String propertyFor, propertyType, propertySubType;
    String price, token_amount, ageOfconstruction, availablefrom, property_status;
    String bathroom, bedroom, balcony, totalfloor, floorNo, furnished, carpetAreaParameter,
            superAreaParameter, lock_in_periodString;
    String  city, project, carpetArea, superArea,  plotArea, plot_length, plot_bredth;
    String  open_Sides, construction_done, boundary_wall, gated_colony, plotAreaParameter,
            cafateria, washroom, personal_washroom, cornerShop, main_road_facing;
    String security, roadWidth, maintenance, maintenance_parameter;

    ResidentialModel residentialModel;
    CommercialModel commercialModel;
    PropertyModel propertyModel;

    DatabaseReference reference, databaseReference;
    FirebaseAuth firebaseAuth;
    String UID;
    long cid= 0;
    long rid=0;
    Intent intent;
    SessionManager session;
    String keyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_package);
        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        session= new SessionManager(getApplicationContext());

     /*   try {
            HashMap<String, String> userID = session.getUserIDs();
            UID = userID.get(SessionManager.KEY_ID);
        }catch (Exception e){
            e.printStackTrace();
        }
       */ try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            UID = bundle.getString("UID", "");
            propertyFor = bundle.getString("PROPERTY_FOR", "");
            propertyType = bundle.getString("PROPERTY_TYPE", "");
            propertySubType = bundle.getString("R/C_TYPE", "");
            city = bundle.getString("CITY", "");
            project = bundle.getString("LOCALITY", "");

            bedroom = bundle.getString("BEDROOM", "");
            bathroom = bundle.getString("BATHROOM", "");
            balcony = bundle.getString("BALCONY", "");
            totalfloor = bundle.getString("TOTALFLOOR", "");
            floorNo = bundle.getString("FLOORNO", "");
            furnished = bundle.getString("FURNISHED", "");
            carpetArea = bundle.getString("CARPETAREA", "");
                carpetAreaParameter = bundle.getString("CARPETAREA_PARAMETER", "");
                carpetArea= carpetArea.concat(" "+carpetAreaParameter);

       //     Log.e("post_package_carpetArea", carpetArea);

            superArea = bundle.getString("SUPERAREA", "");
                superAreaParameter = bundle.getString("SUPERAREA_PARAMETER", "");
                superArea= superArea.concat(" "+superAreaParameter);

            Log.e("post_package_ superArea", superArea);

            roadWidth = bundle.getString("ROAD_WIDTH", "");
                 roadWidth= roadWidth.concat(" Meters");

            Log.e("post_package_roadwidth", roadWidth);

            open_Sides = bundle.getString("Open_SIDES", "");
            construction_done = bundle.getString("construction_done", "");
            boundary_wall = bundle.getString("boundary_wall", "");
            gated_colony = bundle.getString("gated_colony", "");
            plotArea = bundle.getString("PLOT_AREA", "");
                plotAreaParameter = bundle.getString("PLOT_AREA_PARAMETER", "");
                plotArea= plotArea.concat(" "+plotAreaParameter);

            Log.e("post_package_plotarea", plotArea);

            plot_bredth = bundle.getString("plot_bredth", "");
                plot_bredth= plot_bredth.concat(" yard");

            Log.e("post_package_breadth", plot_bredth);

            plot_length = bundle.getString("plot_length", "");
                plot_length= plot_length.concat(" yard");

            Log.e("post_package_plotlength", plot_length);

            cafateria = bundle.getString("CAFETERIA", "");
            washroom = bundle.getString("WashROOM", "");
            cornerShop = bundle.getString("CornerSHOP", "");
            main_road_facing = bundle.getString("MaIN_ROADFACING", "");
            personal_washroom = bundle.getString("PERSONAL_WASHROOM", "");
            lock_in_periodString = bundle.getString("LOCK_IN_PERIOD", "");

            price = bundle.getString("PRICE", "");
              //  price= price.concat(" ₹");
                price = "₹"+ price;
            token_amount = bundle.getString("TOKEN_AMOUNT", "");
            token_amount = "₹"+ token_amount;

            property_status = bundle.getString("PROPERTY_STATUS", "");
            ageOfconstruction = bundle.getString("AGE_OF_CONSTRUCTION", "");
            availablefrom = bundle.getString("AVAILABLEFROM", "");
            security = bundle.getString("SECURITY_AMOUNT", "");
            security = "₹"+security;
            maintenance = bundle.getString("MAINTENANCE_AMOUNT", null);
            maintenance_parameter = bundle.getString("MAINTENANCE_Parameter", "");
              //   security = security.concat("₹ "+maintenance_parameter);
                 maintenance = "₹"+maintenance+" "+ maintenance_parameter;

        }
        catch (Exception e) {
            e.printStackTrace();
        }/*
        Log.e("POST_PACKAGE", propertyFor);
        Log.e("POST_PACKAGE", propertyType);
        Log.e("POST_PACKAGE", UID);*/
        databaseReference= FirebaseDatabase.getInstance().getReference().child("PropertyTable");

    }

    private void postmethod() {
        propertyModel= new PropertyModel(keyId, propertyFor, UID, propertySubType,  city, project, bathroom,  bedroom,  balcony,  totalfloor,
                floorNo,  furnished,  carpetArea,  superArea, roadWidth,  open_Sides,  construction_done,  boundary_wall,
                gated_colony,  plotArea, plot_bredth, plot_length, cafateria, washroom, cornerShop, main_road_facing, personal_washroom,
                lock_in_periodString, price, token_amount, property_status, ageOfconstruction,  availablefrom, security, maintenance);

        reference=databaseReference.child(propertyType);
        keyId = reference.push().getKey();
        propertyModel.setKeyId(keyId);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reference.child(keyId).setValue(propertyModel);

                //    reference.child(String.valueOf(rid + 1)).setValue(propertyModel);
                Toast.makeText(post_package.this, "added", Toast.LENGTH_SHORT).show();
                Log.e("POST PACKAGE", "POSTPROPERTY");
                intent = new Intent(post_package.this, posted_property.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle bundle= new Bundle();
                bundle.putString("UID", UID);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(post_package.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void Standart(View view) {
        postmethod();
    }

    public void certifiedAgentBasic(View view) {
        postmethod();
    }

    public void certifiedAgentPlus(View view) {
        postmethod();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
