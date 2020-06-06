package com.example.propertyrealtors.Post_property;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.ResidentialModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailsAdding extends AppCompatActivity {

    SessionManager session;
    String propertyId;
    String [] getData;
    String UID, propertyFor, propertyType, price, bedroom, address,
            covered_areaParameter, covered_areaParameter2, carpet, propertyStatus, configuration,
            floor, furnish, PropertySubType;
    TextView Bedroom, Address, ConfigMode, Floor;
    EditText Money, CarpetArea, Status, Facing, Furnishing, Society, Location, CoverdArea, Flooring, OverLooking, Landmark,
            Water, Price_per_sqft_sqyrd, Carparking, ElectricityAvailability;
    Button Submit, Next;
    Spinner StatusSpinner, FurnishSpinner;
    ResidentialModel residentialModel;
    DatabaseReference reference;
    private static String ID_NO;
    String additionalId;
    int flag=0;
    private boolean isPropStatTouched = false;
    private boolean isFurnishTouched = false;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_adding);

        //back button
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Additional Details");
        try {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        // userId
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> userID = session.getUserIDs();
        UID = userID.get(SessionManager.KEY_ID);

        bindView();

        residentialModel = new ResidentialModel();

        Bundle bundle = getIntent().getExtras();
        try {
            getData = bundle.getStringArray("DATAARRAY");
            if (getData != null) {
                propertyId = getData[0];
                propertyFor = getData[1];
                propertyType = getData[2];
                price = getData[3];
                bedroom = getData[4];
                address = getData[5];
                carpet = getData[6];
                propertyStatus = getData[7];
                configuration = getData[8];
                floor = getData[9];
                furnish = getData[10];
                PropertySubType = getData[11];

                Money.setText(price);
                Bedroom.setText(bedroom);
                Address.setText(address);
                CarpetArea.setText(carpet);
                Status.setText(propertyStatus);
                ConfigMode.setText(configuration);
                Floor.setText(floor);
                Furnishing.setText(furnish);

                if(PropertySubType.equals("Plot")){
                    Price_per_sqft_sqyrd.setHint("₹ /Sqyrd");
                    CoverdArea.setHint("₹ Sqyrd");
                    covered_areaParameter = "Sqyrd";
                    covered_areaParameter2 = "Sqyrd";
                }else {
                    Price_per_sqft_sqyrd.setHint("₹ /Sqft");
                    CoverdArea.setHint("₹ Sqft");
                    covered_areaParameter = "Sqft";
                    covered_areaParameter2 = "Sqft";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

     //   Next.setEnabled();

        reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
        DatabaseReference refer= reference.child("additionalInfo");
        Query query = refer.orderByChild("refId").equalTo(propertyId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Submit.setEnabled(false);
                    Next.setEnabled(true);
                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                        ResidentialModel data = child.getValue(ResidentialModel.class);
                        additionalId = data.getId();
                        Facing.setText(data.getFacing());
                        CoverdArea.setText(data.getCoverdArea());
                        Price_per_sqft_sqyrd.setText(data.getPricePSS());
                        Society.setText(data.getSociety());
                        Location.setText(data.getLocation());
                        Flooring.setText(data.getFlooring());
                        OverLooking.setText(data.getOverLooking());
                        Landmark.setText(data.getLandmark());
                        Water.setText(data.getWater());
                        Carparking.setText(data.getCarparking());
                        ElectricityAvailability.setText(data.getElectricityAvailability());
                    }
                }else {
                    Next.setEnabled(false);
                    Submit.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailsAdding.this, "database error "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        StatusSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isPropStatTouched = true;
                return false;
            }
        });
        StatusSpinner.setPrompt("Property Status");
        StatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                if (!isPropStatTouched) {
                    return;
                }else {
                    Status.setText(adapter.getItemAtPosition(arg2).toString().trim());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        FurnishSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isFurnishTouched = true;
                return false;
            }
        });
        FurnishSpinner.setPrompt("Furnishing");
        FurnishSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                if (!isFurnishTouched) {
                    return;
                }else {
                    Furnishing.setText(adapter.getItemAtPosition(arg2).toString().trim());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(propertyId) && Facing.getText().toString().length() == 0 &&
                        Price_per_sqft_sqyrd.getText().toString().length() == 0 &&   Carparking.getText().toString().length() == 0 &&
                Flooring.getText().toString().length() == 0 && Landmark.getText().toString().length() == 0 &&
                        OverLooking.getText().toString().length() == 0 && Society.getText().toString().length() == 0 &&
                        Water.getText().toString().length() == 0 && ElectricityAvailability.getText().toString().length() == 0 ) {
                    Toast.makeText(DetailsAdding.this, "Please fill details", Toast.LENGTH_SHORT).show();
                } else {
                  //  Next.setEnabled(false);
                    addData();
                }
            }
        });
        Next.setOnClickListener(v -> {
          //  Submit.setEnabled(false);
            updateAdditionalDetails();
            updateDetails();
            Intent intent = new Intent(DetailsAdding.this, ImagesUpload.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("KEY_ID", additionalId);
            bundle2.putString("TYPE", propertyType);
            bundle2.putString("PROPERTYID_Keyid", propertyId);
            intent.putExtras(bundle2);
            startActivity(intent);
            finish();
        });

    }

    private void updateAdditionalDetails() {
        DatabaseReference db = reference.child("additionalInfo").child(additionalId);
        Map<String, Object> updates = new HashMap<String,Object>();
        updates.put("facing",                 Facing.getText().toString().trim());
        updates.put("coverdArea",               CoverdArea.getText().toString().trim()/*.concat(" "+covered_areaParameter)*/);
        updates.put("flooring",              Flooring.getText().toString().trim());
        updates.put("landmark",               Landmark.getText().toString().trim());
        updates.put("overLooking",            OverLooking.getText().toString().trim());
        updates.put("society",              Society.getText().toString().trim());
        updates.put("water",                  Water.getText().toString().trim());
        updates.put("location",             Location.getText().toString().trim());
        updates.put("pricePSS",               Price_per_sqft_sqyrd.getText().toString().trim()/*.concat("/"+covered_areaParameter2)*/);
        updates.put("carparking",             Carparking.getText().toString().trim());
        updates.put("electricityAvailability",ElectricityAvailability.getText().toString().trim());
        db.updateChildren(updates);
    }

    public void nextActi(){

    }

    private void bindView() {

        Price_per_sqft_sqyrd = findViewById(R.id.price_per_sqft_sqyrd);
        Carparking = findViewById(R.id.carparking);
        ElectricityAvailability = findViewById(R.id.electricityAvailability);
        Money = findViewById(R.id.money);
        Bedroom = findViewById(R.id.bedroom);
        Address = findViewById(R.id.address);
        Facing = findViewById(R.id.facing);
        CoverdArea = findViewById(R.id.covere2);
        CarpetArea = findViewById(R.id.carpetArea);
        Society = findViewById(R.id.society);
        Location = findViewById(R.id.location);
        Status = findViewById(R.id.status);
        Floor = findViewById(R.id.floorNo);
        ConfigMode = findViewById(R.id.configure);
        Furnishing = findViewById(R.id.furnishing);
        Flooring = findViewById(R.id.flooring);
        OverLooking = findViewById(R.id.overlooking);
        Landmark = findViewById(R.id.landmark);
        Water = findViewById(R.id.waterAvailability);
        Submit = findViewById(R.id.submit);
        Next = findViewById(R.id.next);
        StatusSpinner = findViewById(R.id.statusSpinner);
        FurnishSpinner = findViewById(R.id.furnishSpinner);

    }

    public void setDataValue() {

        residentialModel.setRefId(propertyId);
        residentialModel.setFacing                  (Facing.getText().toString().trim());
        residentialModel.setCoverdArea              (CoverdArea.getText().toString().concat(" "+covered_areaParameter));
        residentialModel.setFlooring                (Flooring.getText().toString().trim());
        residentialModel.setLandmark                (Landmark.getText().toString().trim());
        residentialModel.setOverLooking             (OverLooking.getText().toString().trim());
        residentialModel.setSociety                 (Society.getText().toString().trim());
        residentialModel.setWater                   (Water.getText().toString().trim());
        residentialModel.setLocation                (Location.getText().toString().trim());
        residentialModel.setPricePSS                (Price_per_sqft_sqyrd.getText().toString().concat("/"+covered_areaParameter2));
        residentialModel.setCarparking              (Carparking.getText().toString().trim());
        residentialModel.setElectricityAvailability (ElectricityAvailability.getText().toString().trim());
    }

    public void addData() {
      //reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
      //Random No generator
        Random random = new Random();
        int rand_int1 = random.nextInt(1000000000);
        String keyId= reference.push().getKey();
        keyId = keyId.concat("KANT"+rand_int1);
      DatabaseReference ref= reference.child("additionalInfo");
        String finalKeyId = keyId;
        residentialModel.setId(finalKeyId);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setDataValue();
                ref.child(finalKeyId).setValue(residentialModel);
                Toast.makeText(DetailsAdding.this, "Added", Toast.LENGTH_SHORT).show();
              //  ID_NO = finalKeyId;
                updateDetails();
                Intent intent = new Intent(DetailsAdding.this, ImagesUpload.class);
                Bundle bundle = new Bundle();
                bundle.putString("KEY_ID", finalKeyId);
                bundle.putString("TYPE", propertyType);
                bundle.putString("PROPERTYID_Keyid", propertyId);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailsAdding.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateDetails() {
        DatabaseReference db = reference.child(propertyType).child(propertyId);
        Map<String, Object> updates = new HashMap<String,Object>();
        updates.put("price", Money.getText().toString().trim());
        updates.put("carpetArea", CarpetArea.getText().toString().trim());
        updates.put("property_status", Status.getText().toString().trim());
        updates.put("furnished", Furnishing.getText().toString().trim());
        db.updateChildren(updates);
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
