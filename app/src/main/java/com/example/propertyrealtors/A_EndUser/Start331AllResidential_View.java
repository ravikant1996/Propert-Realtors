package com.example.propertyrealtors.A_EndUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.propertyrealtors.Post_property.ImagesUpload;
import com.example.propertyrealtors.Post_property.UploadSliderAdapter;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.activity.Start331AllResidential;
import com.example.propertyrealtors.model.City;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.Locality;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Start331AllResidential_View extends AppCompatActivity {
    SliderView imageSlider;
    TextView Price, CarpetArea, PropertSubType, Address, CoveredArea, PricePer, Location, AgeofConst, AvailableFrom,
    Configuration, Status, Carparking, FloorNo, Furnishing, Facing, Overlooking, Security, SuperArea,
    Maintenance, BookingAmount, Flooring, Landmark, Water, OpenSides, RoadWidth;

    TextView CarpetAreaHead, PropertSubTypeHead, AddressHead, CoveredAreaHead, PricePerHead, LocationHead, AgeofConstHead, AvailableFromHead,
            ConfigurationHead, StatusHead, CarparkingHead, FloorNoHead, FurnishingHead, FacingHead, OverlookingHead, SecurityHead, SuperAreaHead,
            MaintenanceHead, BookingAmountHead, FlooringHead, LandmarkHead, WaterHead, OpenSidesHead, RoadWidthHead;
    UploadSliderAdapter adapter;
    ArrayList<Image> pic;
    String propertyType, propertyId;
    String[] getData;
    String gym, clubHouse, park, parking, lift, powerBackup, gasPipeline, swimPool;
    String location, overlooking, facing, landmark, flooring, water, how_much_construction;
    String UID, propertyFor, price, bedroom, locality, city,
            covered_areaParameter, carpet, propertyStatus, bathroom,
            floor, furnish, propertySubType;
    String carpetAreaParameter, superAreaParameter, plotAreaParameter, maintenance_parameter, roadWidthParameter;

    String totalfloor, superArea, roadWidth, open_Sides, construction_done, boundary_wall,
            gated_colony, plotArea, plot_bredth, plot_length, cafateria, washroom, cornerShop, main_road_facing, personal_washroom,
            lock_in_periodString, token_amount, ageOfconstruction, availableFrom, security, maintenance, balcony;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_residential__view);

        bindView();
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
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if(propertyFor.equals("SELL")){
            Maintenance.setVisibility(View.GONE);
            SecurityHead.setVisibility(View.GONE);
            MaintenanceHead.setVisibility(View.GONE);
            Security.setVisibility(View.GONE);
        }else {
            BookingAmount.setVisibility(View.GONE);
            BookingAmountHead.setVisibility(View.GONE);
        }
        setTextData();
        imageSliderMethod();
    }
    private void setTextData() {
        try {
            Price.setText("₹ "+price);
            if (bedroom.isEmpty()){
                PropertSubType.setText(propertySubType);
            }else {
                PropertSubType.setText(bedroom+" BHK "+ propertySubType);
            }
            if(!(bathroom.isEmpty() && balcony.isEmpty())) {
                Configuration.setText(bedroom + " Bed" + bathroom + " Bath" + balcony + " Balcony");
            }else if (bathroom.isEmpty() && balcony.isEmpty()) {
                Configuration.setText(bedroom + " Bed");
            }else if (!bathroom.isEmpty() && balcony.isEmpty()){
                Configuration.setText(bedroom + " Bed" + bathroom + " Bath");
            }else if (bathroom.isEmpty() && !balcony.isEmpty()){
                Configuration.setText(bedroom + " Bed" + balcony + " Balcony");
            }else if(bedroom.isEmpty() && !bathroom.isEmpty() && !balcony.isEmpty()){
                Configuration.setText(bathroom + " Bath" + balcony + " Balcony");
            }else if(bedroom.isEmpty() && !bathroom.isEmpty() && balcony.isEmpty()){
                Configuration.setText(bathroom + " Bath");
            }
            if (!carpet.isEmpty()) {
                CarpetArea.setText(carpet);
            }else {
                CarpetArea.setVisibility(View.GONE);
                CarpetAreaHead.setVisibility(View.GONE);
            }
            if (!floor.isEmpty()) {
                FloorNo.setText(floor+" of the "+totalfloor);
            }else {
                FloorNo.setVisibility(View.GONE);
                FloorNoHead.setVisibility(View.GONE);
            }
            Address.setText(locality+" "+city);
            if(availableFrom.isEmpty()){
                AvailableFrom.setVisibility(View.GONE);
                AvailableFromHead.setVisibility(View.GONE);
            }else {
                AvailableFrom.setText(availableFrom);
            }
            if(ageOfconstruction.isEmpty()){
                AgeofConst.setVisibility(View.GONE);
                AgeofConstHead.setVisibility(View.GONE);
            }else {
                AgeofConst.setText(ageOfconstruction);
            }
            if(security.isEmpty()){
                Security.setVisibility(View.GONE);
                SecurityHead.setVisibility(View.GONE);
            }else {
                Security.setText(security);
            }
            if(superArea.isEmpty()){
                SuperArea.setVisibility(View.GONE);
                SuperAreaHead.setVisibility(View.GONE);
            }else {
                SuperArea.setText(superArea);
            }
            if(token_amount.isEmpty()){
                BookingAmount.setVisibility(View.GONE);
                BookingAmountHead.setVisibility(View.GONE);
            }else {
                BookingAmount.setText(token_amount);
            }
            if(maintenance.isEmpty()){
                Maintenance.setVisibility(View.GONE);
                MaintenanceHead.setVisibility(View.GONE);
            }else {
                Maintenance.setText(maintenance);
            }
            if(roadWidth.isEmpty()){
                RoadWidthHead.setVisibility(View.GONE);
                RoadWidth.setVisibility(View.GONE);
            }else {
                RoadWidth.setText(roadWidth);
            }
            if(open_Sides.isEmpty()){
                OpenSides.setVisibility(View.GONE);
                OpenSidesHead.setVisibility(View.GONE);
            }else {
                OpenSides.setText(open_Sides);
            }
            if(location.isEmpty()){
                Location.setVisibility(View.GONE);
                LocationHead.setVisibility(View.GONE);
            }else {
                Location.setText(location);
            }
            if(landmark.isEmpty()){
                Landmark.setVisibility(View.GONE);
                LandmarkHead.setVisibility(View.GONE);
            }else {
                Landmark.setText(landmark);
            }
            if(overlooking.isEmpty()){
                Overlooking.setVisibility(View.GONE);
                OverlookingHead.setVisibility(View.GONE);
            }else {
                Overlooking.setText(overlooking);
            }
            if(facing.isEmpty()){
                Facing.setVisibility(View.GONE);
                FacingHead.setVisibility(View.GONE);
            }else {
                Facing.setText(facing);
            }

        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    private void imageSliderMethod() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("PropertyTable")
                .child(propertyType).child(propertyId).child("images");
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Image img= new Image("abc");
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            img = areaSnapshot.getValue(Image.class);
                            pic.add(img);
                        }
                    }else {
                        pic.add(img);
                    }
                        adapter = new UploadSliderAdapter(Start331AllResidential_View.this, pic);
                        imageSlider.setSliderAdapter(adapter);
                        imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                        imageSlider.setIndicatorSelectedColor(Color.WHITE);
                        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
                        imageSlider.setScrollTimeInSec(800); //set scroll delay in seconds :
                        imageSlider.startAutoCycle();

                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void bindView() {
        imageSlider = findViewById(R.id.imageSlider);
        Price = findViewById(R.id.price);
        PropertSubType = findViewById(R.id.propertSubType);
        CarpetArea = findViewById(R.id.carpetAreas);
        Address = findViewById(R.id.address);
        CoveredArea = findViewById(R.id.coveredArea);
        PricePer = findViewById(R.id.price_per);
        Location = findViewById(R.id.location);
        Configuration = findViewById(R.id.configure);
        Status = findViewById(R.id.status);
        Carparking = findViewById(R.id.carparking);
        FloorNo = findViewById(R.id.floorNo);
        Furnishing = findViewById(R.id.furnishing);
        Facing = findViewById(R.id.facing);
        Overlooking = findViewById(R.id.overlooking);
        Maintenance = findViewById(R.id.maintenance);
        BookingAmount = findViewById(R.id.booking);
        Flooring = findViewById(R.id.flooring);
        Landmark = findViewById(R.id.landmark);
        Security = findViewById(R.id.security);
        SuperArea = findViewById(R.id.superArea);
        Water = findViewById(R.id.waterHead);

        // labels
        LocationHead =findViewById(R.id.locationHead);
        CarparkingHead = findViewById(R.id.carparkingHead);
        CarpetAreaHead = findViewById(R.id.coveredAreaHeading);
        ConfigurationHead = findViewById(R.id.configureHead);
        StatusHead = findViewById(R.id.statusHead);
        AvailableFromHead = findViewById(R.id.availableFromHead);
        AgeofConstHead = findViewById(R.id.ageOfConstHead);
        SecurityHead = findViewById(R.id.SecurityHead);
        SuperAreaHead = findViewById(R.id.superAreaHead);
        FloorNoHead = findViewById(R.id.floorhead);
        FurnishingHead = findViewById(R.id.furnishingHead);
        FacingHead = findViewById(R.id.facingHead);
        OverlookingHead = findViewById(R.id.overlookingHead);
        MaintenanceHead = findViewById(R.id.maintenanceheading);
        BookingAmountHead = findViewById(R.id.bookinghead);
        FlooringHead = findViewById(R.id.flooringHead);
        LandmarkHead = findViewById(R.id.landmarkHead);
        WaterHead = findViewById(R.id.water);
        OpenSides = findViewById(R.id.openSides);
        OpenSidesHead = findViewById(R.id.openSidesHead);
        RoadWidth = findViewById(R.id.road_width);
        RoadWidthHead = findViewById(R.id.road_widthHead);
    }
}
