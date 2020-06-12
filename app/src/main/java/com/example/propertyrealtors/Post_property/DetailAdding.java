package com.example.propertyrealtors.Post_property;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.ResidentialModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.Any;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.transition.Visibility;

public class DetailAdding extends AppCompatActivity {

    TextInputLayout textInputLayout1, textInputLayout2, textInputLayout3, textInputLayout4, textInputLayout5,textInputLayout6,textInputLayout7,textInputLayout8,textInputLayout9,
            textInputLayout10,textInputLayout11,textInputLayout12,textInputLayout13,textInputLayout14,textInputLayout15,textInputLayout16,textInputLayout17,textInputLayout18,
            textInputLayout19,textInputLayout20,textInputLayout21,textInputLayout22,textInputLayout23,textInputLayout24,textInputLayout25,textInputLayout26,textInputLayout27,
            textInputLayout28,textInputLayout29,textInputLayout30,textInputLayout31,textInputLayout32,textInputLayout33,textInputLayout34,textInputLayout35,textInputLayout36,
            textInputLayout37,textInputLayout38,textInputLayout39,textInputLayout40,textInputLayout41,textInputLayout42,textInputLayout43,textInputLayout44,textInputLayout45;
    SessionManager session;
    String propertyId;
    String[] getData;
    String gym, clubHouse, park, parking, lift, powerBackup, gasPipeline, swimPool;
    String UID, propertyFor, propertyType, price, bedroom, locality, city,
            covered_areaParameter, carpet, propertyStatus, bathroom,
            floor, furnish, propertySubType;
    String carpetAreaParameter, superAreaParameter, plotAreaParameter, maintenance_parameter, roadWidthParameter;

    String totalfloor, superArea, roadWidth, open_Sides, construction_done, boundary_wall,
            gated_colony, plotArea, plot_bredth, plot_length, cafateria, washroom, cornerShop, main_road_facing, personal_washroom,
            lock_in_periodString, token_amount, ageOfconstruction, availableFrom, security, maintenance, balcony;

    EditText Bathroom, Balcony, TotalFloor, City, Locality, Token_Amount, Security, Maintenance,
            AnyConst, Boundary, GatedColony,
            SuperArea, PlotArea, Plot_Breadth,
            Plot_Length, RoadWidth, Open_Sides, AgeofConst, AvailableFrom;
    EditText Bedroom, FloorNo, Price, CarpetArea, Facing, Society,
            CoverdArea, Flooring, OverLooking,
            Water, Price_per_sqft_sqyrd, Carparking, ElectricityAvailability;
    Button Submit, Next;
    Spinner StatusSpinner, FurnishSpinner, Main_Parameter, PlotArea_Parameter,
            CoveredArea_Parameter, Carpet_Parameter, SuperArea_Parameter;
    ResidentialModel residentialModel;
    DatabaseReference reference;
    private static String ID_NO;
    String additionalId;
    private boolean isPropStatTouched = false;
    private boolean isFurnishTouched = false;
    Toolbar toolbar;
    public static final int FROM_HTML_MODE_COMPACT = 63;
    public static final int FROM_HTML_MODE_LEGACY = 0;
    public static final int FROM_HTML_OPTION_USE_CSS_COLORS = 256;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE = 32;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_DIV = 16;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_HEADING = 2;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_LIST = 8;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM = 4;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH = 1;
    public static final int TO_HTML_PARAGRAPH_LINES_CONSECUTIVE = 0;
    public static final int TO_HTML_PARAGRAPH_LINES_INDIVIDUAL = 1;
    AppCompatAutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_adding);

        //back button
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Additional Details");
        try {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        // userId
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> userID = session.getUserIDs();
        UID = userID.get(SessionManager.KEY_ID);

        bindView();
        setHints();

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
                locality = getData[5];
                carpet = getData[6];
                propertyStatus = getData[7];
                bathroom = getData[8];
                floor = getData[9];
                furnish = getData[10];
                propertySubType = getData[11];
                availableFrom = getData[12];
                ageOfconstruction = getData[13];
                boundary_wall = getData[14];

                construction_done = getData[16];

                totalfloor = getData[18];
                gated_colony = getData[19];
                lock_in_periodString = getData[20];
                main_road_facing = getData[21];
                open_Sides = getData[22];
                personal_washroom = getData[23];
                plotArea = getData[24];
                plot_bredth = getData[25];
                plot_length = getData[26];
                roadWidth = getData[27];
                security = getData[28];
                superArea = getData[29];
                token_amount = getData[30];
                cafateria = getData[15];
                cornerShop = getData[17];
                washroom = getData[31];

                maintenance = getData[32];
                balcony = getData[33];
                carpetAreaParameter = getData[34];

                superAreaParameter = getData[35];
                plotAreaParameter = getData[36];
                maintenance_parameter = getData[37];
                roadWidthParameter = getData[38];
                city = getData[39];

                if (propertySubType.equals("Flat/Apartment") || propertySubType.equals("Builder_Floor") ||
                        propertySubType.equals("Pentahouse") || propertySubType.equals("Studio_Apartment")) {
                    if (propertySubType.equals("Studio_Apartment")) {
                        Bedroom.setVisibility(View.VISIBLE);
                    }

                    Open_Sides.setVisibility(View.GONE);textInputLayout22.setVisibility(View.INVISIBLE);
                    PlotArea.setVisibility(View.GONE);textInputLayout10.setVisibility(View.INVISIBLE);
                    Plot_Breadth.setVisibility(View.GONE);textInputLayout11.setVisibility(View.INVISIBLE);
                    Plot_Length.setVisibility(View.GONE);textInputLayout12.setVisibility(View.INVISIBLE);
                    PlotArea_Parameter.setVisibility(View.GONE);
                    AnyConst.setVisibility(View.GONE);textInputLayout32.setVisibility(View.INVISIBLE);
                    Boundary.setVisibility(View.GONE);textInputLayout33.setVisibility(View.INVISIBLE);
                    GatedColony.setVisibility(View.GONE);textInputLayout34.setVisibility(View.INVISIBLE);

                    ViewRnS();


                } else if (propertySubType.equals("House") || propertySubType.equals("Farm_House")
                        || propertySubType.equals("Villa")) {
                    PlotArea.setVisibility(View.GONE);textInputLayout10.setVisibility(View.INVISIBLE);
                    Plot_Breadth.setVisibility(View.GONE);textInputLayout11.setVisibility(View.INVISIBLE);
                    Plot_Length.setVisibility(View.GONE);textInputLayout12.setVisibility(View.INVISIBLE);
                    PlotArea_Parameter.setVisibility(View.GONE);
                    AnyConst.setVisibility(View.GONE);textInputLayout32.setVisibility(View.INVISIBLE);
                    Boundary.setVisibility(View.GONE);textInputLayout33.setVisibility(View.INVISIBLE);
                    GatedColony.setVisibility(View.GONE);textInputLayout34.setVisibility(View.INVISIBLE);
                    FloorNo.setVisibility(View.GONE);textInputLayout21.setVisibility(View.INVISIBLE);
                    ViewRnS();

                } else {
                    Bedroom.setVisibility(View.GONE);textInputLayout16.setVisibility(View.INVISIBLE);
                    Bathroom.setVisibility(View.GONE);textInputLayout17.setVisibility(View.INVISIBLE);
                    Balcony.setVisibility(View.GONE);textInputLayout18.setVisibility(View.INVISIBLE);
                    FloorNo.setVisibility(View.GONE);textInputLayout21.setVisibility(View.INVISIBLE);
                    AgeofConst.setVisibility(View.GONE);textInputLayout6.setVisibility(View.INVISIBLE);
                    SuperArea.setVisibility(View.GONE);textInputLayout9.setVisibility(View.INVISIBLE);
                    SuperArea_Parameter.setVisibility(View.GONE);
                    CarpetArea.setVisibility(View.GONE);textInputLayout8.setVisibility(View.INVISIBLE);
                    Carpet_Parameter.setVisibility(View.GONE);
                    FurnishSpinner.setVisibility(View.GONE);
                    Flooring.setVisibility(View.GONE);textInputLayout24.setVisibility(View.INVISIBLE);
                    Carparking.setVisibility(View.GONE);textInputLayout26.setVisibility(View.INVISIBLE);
                    Water.setVisibility(View.GONE);textInputLayout27.setVisibility(View.INVISIBLE);
                    ElectricityAvailability.setVisibility(View.GONE);textInputLayout28.setVisibility(View.INVISIBLE);
                    HorizontalScrollView hrv = findViewById(R.id.horizontalScrollView3);
                    hrv.setVisibility(View.GONE);
                    Society.setVisibility(View.GONE);textInputLayout19.setVisibility(View.INVISIBLE);
                    ViewRnS();


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setTextData();
        parameters();


        reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
        DatabaseReference refer = reference.child("additionalInfo");
        Query query = refer.orderByChild("refId").equalTo(propertyId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Submit.setEnabled(false);
                    Next.setEnabled(true);
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        ResidentialModel data = child.getValue(ResidentialModel.class);
                        additionalId = data.getId();
                        Facing.setText(data.getFacing());
                        CoverdArea.setText(data.getCoverdArea());
                        Price_per_sqft_sqyrd.setText(data.getPricePSS());
                        Society.setText(data.getSociety());
                        Flooring.setText(data.getFlooring());
                        OverLooking.setText(data.getOverLooking());
                        Water.setText(data.getWater());
                        Carparking.setText(data.getCarparking());
                        ElectricityAvailability.setText(data.getElectricityAvailability());

                        covered_areaParameter = data.getCovered_areaParameter();
                        parking = data.getParking();
                        lift = data.getLift();
                        gym = data.getGym();
                        powerBackup = data.getPowerBackup();
                        gasPipeline = data.getGasPipeline();
                        park = data.getPark();
                        clubHouse = data.getClubHouse();
                        swimPool = data.getSwimPool();

                    }
                } else {
                    Next.setEnabled(false);
                    Submit.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailAdding.this, "database error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(propertyId) && Price.getText().toString().length() == 0 &&
                        City.getText().toString().length() == 0 && Locality.getText().toString().length() == 0 && CoverdArea.getText().toString().length() ==0 && covered_areaParameter.isEmpty()) {
                    Toast.makeText(DetailAdding.this, "* is mandatory", Toast.LENGTH_SHORT).show();
                }else if (propertySubType.equals("Plot")) {
                    if (PlotArea.getText().toString().length() == 0 && TextUtils.isEmpty(plotAreaParameter) && AnyConst.getText().toString().length() == 0 &&
                            Boundary.getText().toString().length() == 0 && GatedColony.getText().toString().length() == 0) {
                        Toast.makeText(DetailAdding.this, "* is mandatory", Toast.LENGTH_SHORT).show();
                    }
                    else if (AgeofConst.getText().toString().length() == 0 || AvailableFrom.getText().toString().length() == 0) {
                        Toast.makeText(DetailAdding.this, "* is mandatory", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        addData();
                    }
                }else if (propertySubType.equals("Studio_Apartment")) {
                    if (CarpetArea.getText().toString().length() == 0 && TextUtils.isEmpty(carpetAreaParameter) &&
                            Bathroom.getText().toString().length() == 0 &&  TotalFloor.getText().toString().length() ==0 && TextUtils.isEmpty(furnish) ) {
                        Toast.makeText(DetailAdding.this, "* is mandatory", Toast.LENGTH_SHORT).show();
                    }
                    else if (AgeofConst.getText().toString().length() == 0 || AvailableFrom.getText().toString().length() == 0) {
                        Toast.makeText(DetailAdding.this, "* is mandatory", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        addData();
                    }
                }else if (CarpetArea.getText().toString().length() == 0 && TextUtils.isEmpty(carpetAreaParameter) &&
                            Bedroom.getText().toString().length() == 0 && TotalFloor.getText().toString().length() ==0 && TextUtils.isEmpty(furnish) ) {
                    Toast.makeText(DetailAdding.this, "* is mandatory", Toast.LENGTH_SHORT).show();
                } else if (AgeofConst.getText().toString().length() == 0 || AvailableFrom.getText().toString().length() == 0) {
                        Toast.makeText(DetailAdding.this, "* is mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    //  Next.setEnabled(false);
                    addData();
                }
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAdditionalDetails();
                updateDetails();
                Intent intent = new Intent(DetailAdding.this, ImagesUpload.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("KEY_ID", additionalId);
                bundle2.putString("TYPE", propertyType);
                bundle2.putString("PROPERTYID_Keyid", propertyId);
                intent.putExtras(bundle2);
                startActivity(intent);
                finish();
            }
        });

    }

    private void ViewRnS() {
        if (propertyFor.equals("SELL")) {
            Security.setVisibility(View.INVISIBLE);
            Maintenance.setVisibility(View.INVISIBLE);
            Main_Parameter.setVisibility(View.INVISIBLE);
        } else {
            Security.setVisibility(View.VISIBLE);
            Maintenance.setVisibility(View.VISIBLE);
            Main_Parameter.setVisibility(View.VISIBLE);
            Token_Amount.setVisibility(View.INVISIBLE);
        }
    }

    private String getColoredSpanned(String text) {
        return "<font color=#F30B0B>" + text + "</font>";
    }

    private void setHints() {

        if (Build.VERSION.SDK_INT >= 24) {
            // for 24 API  and more
            Price.setHint(Html.fromHtml("Price" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            Locality.setHint(Html.fromHtml("Locality" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            City.setHint(Html.fromHtml("City" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            AgeofConst.setHint(Html.fromHtml("Age of construction" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            AvailableFrom.setHint(Html.fromHtml("Available From" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            AnyConst.setHint(Html.fromHtml("Any Construction" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            Boundary.setHint(Html.fromHtml("Boundary wall" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            GatedColony.setHint(Html.fromHtml("Gated Colony" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            CarpetArea.setHint(Html.fromHtml("Carpet Area" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            PlotArea.setHint(Html.fromHtml("Plot Area" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            CoverdArea.setHint(Html.fromHtml("Covered Area" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            Price_per_sqft_sqyrd.setHint(Html.fromHtml("Price/ " + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            Bedroom.setHint(Html.fromHtml("Bedroom" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            TotalFloor.setHint(Html.fromHtml("TotalFloor" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            Water.setHint(Html.fromHtml("Water Availability" + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));
            ElectricityAvailability.setHint(Html.fromHtml("Electricity Availability " + getColoredSpanned("*"), FROM_HTML_OPTION_USE_CSS_COLORS));

        } else {
//            Html.fromHtml(String) // or for older API
            Price.setHint(Html.fromHtml("Price" + getColoredSpanned("*")));
            Locality.setHint(Html.fromHtml("Locality" + getColoredSpanned("*")));
            City.setHint(Html.fromHtml("City" + getColoredSpanned("*")));
            AgeofConst.setHint(Html.fromHtml("Age of construction" + getColoredSpanned("*")));
            AvailableFrom.setHint(Html.fromHtml("Available From" + getColoredSpanned("*")));
            AnyConst.setHint(Html.fromHtml("Any Construction" + getColoredSpanned("*")));
            Boundary.setHint(Html.fromHtml("Boundary wall" + getColoredSpanned("*")));
            GatedColony.setHint(Html.fromHtml("Gated Colony" + getColoredSpanned("*")));
            CarpetArea.setHint(Html.fromHtml("Carpet Area" + getColoredSpanned("*")));
            PlotArea.setHint(Html.fromHtml("Plot Area" + getColoredSpanned("*")));
            CoverdArea.setHint(Html.fromHtml("Covered Area" + getColoredSpanned("*")));
            Price_per_sqft_sqyrd.setHint(Html.fromHtml("Price/ " + getColoredSpanned("*")));
            Bedroom.setHint(Html.fromHtml("Bedroom" + getColoredSpanned("*")));
            TotalFloor.setHint(Html.fromHtml("TotalFloor" + getColoredSpanned("*")));
            Water.setHint(Html.fromHtml("Water Availability" + getColoredSpanned("*")));
            ElectricityAvailability.setHint(Html.fromHtml("Electricity Availability " + getColoredSpanned("*")));

        }

    }

    private void parameters() {
        try {
            if (!propertyStatus.isEmpty()) {
                StatusSpinner.setSelection(getIndex(StatusSpinner, propertyStatus));
            }
            StatusSpinner.setPrompt("Property Status");
            StatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    propertyStatus = adapter.getItemAtPosition(arg2).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            if (!furnish.isEmpty()) {
                FurnishSpinner.setSelection(getIndex(FurnishSpinner, furnish));
            }
            FurnishSpinner.setPrompt("Furnishing");
            FurnishSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    furnish = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            if (!superAreaParameter.isEmpty()) {
                SuperArea_Parameter.setSelection(getIndex(SuperArea_Parameter, superAreaParameter));
            }
            SuperArea_Parameter.setPrompt("Super Area Parameter");
            SuperArea_Parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    superAreaParameter = adapter.getItemAtPosition(arg2).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            if (!maintenance_parameter.isEmpty()) {
                Main_Parameter.setSelection(getIndex(Main_Parameter, maintenance_parameter));
            }
            Main_Parameter.setPrompt("Time bond");
            Main_Parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    maintenance_parameter = adapter.getItemAtPosition(arg2).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            if (!carpetAreaParameter.isEmpty()) {
                Carpet_Parameter.setSelection(getIndex(Carpet_Parameter, carpetAreaParameter));
            }
            Carpet_Parameter.setPrompt("Carpet Area Parameter");
            Carpet_Parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    carpetAreaParameter = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            if (!plotAreaParameter.isEmpty()) {
                PlotArea_Parameter.setSelection(getIndex(PlotArea_Parameter, plotAreaParameter));
            }
            PlotArea_Parameter.setPrompt("Plot Area Parameter");
            PlotArea_Parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    plotAreaParameter = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            if (!covered_areaParameter.isEmpty()) {
                CoveredArea_Parameter.setSelection(getIndex(CoveredArea_Parameter, covered_areaParameter));
            }
            CoveredArea_Parameter.setPrompt("Plot Area Parameter");
            CoveredArea_Parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    covered_areaParameter = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setTextData() {
        try {
            Price.setText(price);
            Bedroom.setText(bedroom);
            CarpetArea.setText(carpet);
            FloorNo.setText(floor);
            City.setText(city);
            Locality.setText(locality);
            Bathroom.setText(bathroom);
            AvailableFrom.setText(availableFrom);
            AgeofConst.setText(ageOfconstruction);
            Boundary.setText(boundary_wall);
            AnyConst.setText(construction_done);
            TotalFloor.setText(totalfloor);
            GatedColony.setText(gated_colony);
            Open_Sides.setText(open_Sides);
            PlotArea.setText(plotArea);
            Plot_Breadth.setText(plot_bredth);
            Plot_Length.setText(plot_length);
            RoadWidth.setText(roadWidth);
            Security.setText(security);
            SuperArea.setText(superArea);
            Token_Amount.setText(token_amount);
            Maintenance.setText(maintenance);
            Balcony.setText(balcony);

        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    private int getIndex(Spinner spinner, String carpet) {
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(carpet)) {
                index = i;
            }
        }
        return index;
    }


    private void updateAdditionalDetails() {
        DatabaseReference db = reference.child("additionalInfo").child(additionalId);
        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("facing", Facing.getText().toString().trim());
        updates.put("coverdArea", CoverdArea.getText().toString().trim());
        updates.put("flooring", Flooring.getText().toString().trim());
        updates.put("overLooking", OverLooking.getText().toString().trim());
        updates.put("society", Society.getText().toString().trim());
        updates.put("water", Water.getText().toString().trim());
        updates.put("pricePSS", Price_per_sqft_sqyrd.getText().toString().trim());
        updates.put("carparking", Carparking.getText().toString().trim());
        updates.put("electricityAvailability", ElectricityAvailability.getText().toString().trim());
        updates.put("park", park);
        updates.put("parking", parking);
        updates.put("lift", lift);
        updates.put("gym", gym);
        updates.put("powerBackup", powerBackup);
        updates.put("gasPipeline", gasPipeline);
        updates.put("clubHouse", clubHouse);
        updates.put("swimPool", swimPool);
        updates.put("covered_areaParameter", covered_areaParameter);
        db.updateChildren(updates);

    }

    private void bindView() {

        Price_per_sqft_sqyrd = findViewById(R.id.price_per_sqft_sqyrd);
        Carparking = findViewById(R.id.carparking);
        ElectricityAvailability = findViewById(R.id.electricityAvailability);
        Price = findViewById(R.id.money);
        Bedroom = findViewById(R.id.bedroom);
        Facing = findViewById(R.id.facing);
        CoverdArea = findViewById(R.id.coveredArea);
        CarpetArea = findViewById(R.id.carpetArea);
        Society = findViewById(R.id.society);
        FloorNo = findViewById(R.id.floorNo);
        Flooring = findViewById(R.id.flooring);
        OverLooking = findViewById(R.id.overlooking);
        Water = findViewById(R.id.waterAvailability);
        Submit = findViewById(R.id.submit);
        Next = findViewById(R.id.next);
        StatusSpinner = findViewById(R.id.statusSpinner);
        FurnishSpinner = findViewById(R.id.furnishSpinner);
        Bathroom = findViewById(R.id.bathroom);
        Balcony = findViewById(R.id.balcony);
        TotalFloor = findViewById(R.id.totalFloor);
        City = findViewById(R.id.city);
        Locality = findViewById(R.id.locality);
        Token_Amount = findViewById(R.id.token_amount);
        Security = findViewById(R.id.security_amount);
        Maintenance = findViewById(R.id.maintenance);
        Main_Parameter = findViewById(R.id.charge);
        AnyConst = findViewById(R.id.construction);
        Boundary = findViewById(R.id.boundaryWall);
        GatedColony = findViewById(R.id.gatedColony);
        Carpet_Parameter = findViewById(R.id.areaSpin1);
        SuperArea = findViewById(R.id.superArea);
        SuperArea_Parameter = findViewById(R.id.areaSpin2);
        PlotArea = findViewById(R.id.plotarea);
        PlotArea_Parameter = findViewById(R.id.plotareaParameter);
        Plot_Breadth = findViewById(R.id.plot_breadth);
        Plot_Length = findViewById(R.id.plot_length);
        RoadWidth = findViewById(R.id.road_width);
        CoveredArea_Parameter = findViewById(R.id.coveredAreaParameter);
        Open_Sides = findViewById(R.id.openSides);
        AgeofConst = findViewById(R.id.ageOfConst);
        AvailableFrom = findViewById(R.id.availableFrom);

        textInputLayout1 =findViewById(R.id.t1);
        textInputLayout2 =findViewById(R.id.t2);
        textInputLayout3 =findViewById(R.id.t3);
        textInputLayout4 =findViewById(R.id.t4);
        textInputLayout6 =findViewById(R.id.t6);
        textInputLayout7 =findViewById(R.id.t7);
        textInputLayout8 =findViewById(R.id.t8);
        textInputLayout9 =findViewById(R.id.t9);
        textInputLayout10 =findViewById(R.id.t10);
        textInputLayout11 =findViewById(R.id.t11);
        textInputLayout12 =findViewById(R.id.t12);
        textInputLayout13 =findViewById(R.id.t13);
        textInputLayout14 =findViewById(R.id.t14);
        textInputLayout15 =findViewById(R.id.t15);
        textInputLayout16 =findViewById(R.id.t16);
        textInputLayout17 =findViewById(R.id.t17);
        textInputLayout18 =findViewById(R.id.t18);
        textInputLayout20 =findViewById(R.id.t20);
        textInputLayout21 =findViewById(R.id.t21);
        textInputLayout22 =findViewById(R.id.t22);
        textInputLayout24 =findViewById(R.id.t24);
        textInputLayout25 =findViewById(R.id.t25);
        textInputLayout26 =findViewById(R.id.t26);
        textInputLayout27 =findViewById(R.id.t27);
        textInputLayout28 =findViewById(R.id.t28);
        textInputLayout29 =findViewById(R.id.t29);
        textInputLayout30 =findViewById(R.id.t30);
        textInputLayout31 =findViewById(R.id.t31);
        textInputLayout32 =findViewById(R.id.t32);
        textInputLayout33 =findViewById(R.id.t33);
        textInputLayout34 =findViewById(R.id.t34);
     /*   textInputLayout35 =findViewById(R.id.t35);
        textInputLayout36 =findViewById(R.id.t36);
        textInputLayout37 =findViewById(R.id.t37);
        textInputLayout38 =findViewById(R.id.t38);
        textInputLayout39 =findViewById(R.id.t39);
        textInputLayout40 =findViewById(R.id.t40);
        textInputLayout41 =findViewById(R.id.t41);*/


    }

    public void setDataValue() {
        try {
            residentialModel.setRefId(propertyId);
            residentialModel.setFacing(Facing.getText().toString().trim());
            residentialModel.setCoverdArea(CoverdArea.getText().toString().trim());
            residentialModel.setFlooring(Flooring.getText().toString().trim());
            residentialModel.setOverLooking(OverLooking.getText().toString().trim());
            residentialModel.setSociety(Society.getText().toString().trim());
            residentialModel.setWater(Water.getText().toString().trim());
            residentialModel.setPricePSS(Price_per_sqft_sqyrd.getText().toString().trim());
            residentialModel.setCarparking(Carparking.getText().toString().trim());
            residentialModel.setElectricityAvailability(ElectricityAvailability.getText()
                    .toString().trim());
            residentialModel.setParking(parking);
            residentialModel.setClubHouse(clubHouse);
            residentialModel.setGasPipeline(gasPipeline);
            residentialModel.setGym(gym);
            residentialModel.setLift(lift);
            residentialModel.setCovered_areaParameter(covered_areaParameter);
            residentialModel.setPowerBackup(powerBackup);
            residentialModel.setSwimPool(swimPool);
            residentialModel.setPark(park);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addData() {
        //reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
        //Random No generator
        Random random = new Random();
        int rand_int1 = random.nextInt(1000000000);
        String keyId = reference.push().getKey();
        keyId = keyId.concat("KANT" + rand_int1);
        final DatabaseReference ref = reference.child("additionalInfo");
        final String finalKeyId = keyId;
        residentialModel.setId(finalKeyId);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setDataValue();
                ref.child(finalKeyId).setValue(residentialModel);
                Toast.makeText(DetailAdding.this, "Added", Toast.LENGTH_SHORT).show();
                //  ID_NO = finalKeyId;
                updateDetails();
                Intent intent = new Intent(DetailAdding.this, ImagesUpload.class);
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
                Toast.makeText(DetailAdding.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateDetails() {
        try {
            DatabaseReference db = reference.child(propertyType).child(propertyId);
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("price", Price.getText().toString().trim());
            updates.put("carpetArea", CarpetArea.getText().toString().trim());
            updates.put("city", City.getText().toString().trim());
            updates.put("project", Locality.getText().toString().trim());
            updates.put("bathroom", Bathroom.getText().toString().trim());
            updates.put("superArea", SuperArea.getText().toString().trim());
            updates.put("bedroom", Bedroom.getText().toString().trim());
            updates.put("balcony", Balcony.getText().toString().trim());
            updates.put("totalfloor", TotalFloor.getText().toString().trim());
            updates.put("floorNo", FloorNo.getText().toString().trim());
            updates.put("roadWidth", RoadWidth.getText().toString().trim());
            updates.put("open_Sides", Open_Sides.getText().toString().trim());
            updates.put("construction_done", AnyConst.getText().toString().trim());
            updates.put("boundary_wall", Boundary.getText().toString().trim());
            updates.put("gated_colony", GatedColony.getText().toString().trim());
            updates.put("plotArea", PlotArea.getText().toString().trim());
            updates.put("plot_length", Plot_Length.getText().toString().trim());
            updates.put("plot_bredth", Plot_Breadth.getText().toString().trim());
            updates.put("token_amount", Token_Amount.getText().toString().trim());
            updates.put("ageOfconstruction", AgeofConst.getText().toString().trim());
            updates.put("availablefrom", AvailableFrom.getText().toString().trim());
            updates.put("security", Security.getText().toString().trim());
            updates.put("maintenance", Maintenance.getText().toString().trim());
            updates.put("furnished", furnish);
            updates.put("carpetAreaParameter", carpetAreaParameter);
            updates.put("superAreaParameter", superAreaParameter);
            updates.put("plotAreaParameter", plotAreaParameter);
            updates.put("property_status", propertyStatus);
            updates.put("maintenance_parameter", maintenance_parameter);
            updates.put("roadWidthParameter", roadWidthParameter);

            db.updateChildren(updates);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void Parking(View view) {
        CardView cardView = (CardView) view;
        cardView.setBackgroundColor(Color.WHITE);
        if (cardView.isSelected()) {
            cardView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            parking = "No";

        } else {
            view.setSelected(true);
            cardView.setBackgroundColor(Color.LTGRAY);
            parking = "Yes";
        }
    }

    public void Lift(View view) {
        CardView cardView = (CardView) view;
        cardView.setBackgroundColor(Color.WHITE);
        if (cardView.isSelected()) {
            cardView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            lift = "No";

        } else {
            view.setSelected(true);
            cardView.setBackgroundColor(Color.LTGRAY);
            lift = "Yes";
        }
    }

    public void ClubHouse(View view) {
        CardView cardView = (CardView) view;
        cardView.setBackgroundColor(Color.WHITE);
        if (cardView.isSelected()) {
            cardView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            clubHouse = "No";

        } else {
            view.setSelected(true);
            cardView.setBackgroundColor(Color.LTGRAY);
            clubHouse = "Yes";
        }
    }

    public void SwimPool(View view) {
        CardView cardView = (CardView) view;
        cardView.setBackgroundColor(Color.WHITE);
        if (cardView.isSelected()) {
            cardView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            swimPool = "No";

        } else {
            view.setSelected(true);
            cardView.setBackgroundColor(Color.LTGRAY);
            swimPool = "Yes";
        }
    }

    public void Park(View view) {
        CardView cardView = (CardView) view;
        cardView.setBackgroundColor(Color.WHITE);
        if (cardView.isSelected()) {
            cardView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            park = "No";

        } else {
            view.setSelected(true);
            cardView.setBackgroundColor(Color.LTGRAY);
            park = "Yes";
        }
    }

    public void GasPipeline(View view) {
        CardView cardView = (CardView) view;
        cardView.setBackgroundColor(Color.WHITE);
        if (cardView.isSelected()) {
            cardView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            gasPipeline = "No";

        } else {
            view.setSelected(true);
            cardView.setBackgroundColor(Color.LTGRAY);
            gasPipeline = "Yes";
        }
    }

    public void PowerBackup(View view) {
        CardView cardView = (CardView) view;
        cardView.setBackgroundColor(Color.WHITE);
        if (cardView.isSelected()) {
            cardView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            powerBackup = "No";

        } else {
            view.setSelected(true);
            cardView.setBackgroundColor(Color.LTGRAY);
            powerBackup = "Yes";
        }
    }

    public void GYM(View view) {
        CardView cardView = (CardView) view;
        cardView.setBackgroundColor(Color.WHITE);
        if (cardView.isSelected()) {
            cardView.setBackgroundColor(Color.WHITE);
            view.setSelected(false);
            gym = "No";

        } else {
            view.setSelected(true);
            cardView.setBackgroundColor(Color.LTGRAY);
            gym = "Yes";
        }
    }
}
