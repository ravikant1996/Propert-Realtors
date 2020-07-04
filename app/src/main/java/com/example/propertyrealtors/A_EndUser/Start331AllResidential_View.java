package com.example.propertyrealtors.A_EndUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.Post_property.DetailsAdding;
import com.example.propertyrealtors.Post_property.ImagesUpload;
import com.example.propertyrealtors.Post_property.Start331AllResidential_Edit;
import com.example.propertyrealtors.Post_property.UploadSliderAdapter;
import com.example.propertyrealtors.Post_property.edit_image;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.activity.MainActivity;
import com.example.propertyrealtors.activity.Start33;
import com.example.propertyrealtors.activity.Start331AllResidential;
import com.example.propertyrealtors.model.AdditioanlDetailsModel;
import com.example.propertyrealtors.model.City;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.Locality;
import com.example.propertyrealtors.model.PropertyModel;
import com.example.propertyrealtors.model.ResidentialModel;
import com.example.propertyrealtors.model.User;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Start331AllResidential_View extends AppCompatActivity {
    ImageView imageSlider;
    Context context;
    View OwnerLayout, AgentLayout;
    String phoneNo;
    TextView Price, CarpetArea, PropertSubType, Address, CoveredArea, PricePer, Location, AgeofConst, AvailableFrom,
            Configuration, Status, Carparking, FloorNo, Furnishing, Facing, Overlooking, Security, SuperArea,
            Maintenance, BookingAmount, Flooring, Landmark, Water, OpenSides, RoadWidth, ElectricityAvailability, Society;

    TextView CarpetAreaHead, PropertSubTypeHead, AddressHead, CoveredAreaHead, LocationHead, SocietyHead, AgeofConstHead, AvailableFromHead,
            ConfigurationHead, StatusHead, CarparkingHead, FloorNoHead, FurnishingHead, FacingHead, OverlookingHead, SecurityHead, SuperAreaHead,
            MaintenanceHead, BookingAmountHead, FlooringHead, LandmarkHead, WaterHead, OpenSidesHead, RoadWidthHead, ElectricHead;
    UploadSliderAdapter adapter;
    ArrayList<Image> pic;
    String propertyType, propertyId;
    String[] getData;
    ArrayList<PropertyModel> propertyModelArrayList = new ArrayList<>();
    String gym, clubHouse, park, parking, lift, powerBackup, gasPipeline, swimPool;
    String location, overlooking, facing, landmark, flooring, water, covered_area, pricePer, electricity,
            carparking, society, suitableFor, construction,
            openParking, closeParking, imageAddress;
    String dateofposting, timeofposting, refId, propertyFor, price, bedroom, locality, city,
            covered_areaParameter, carpet, propertyStatus, bathroom,
            floor, furnish, propertySubType;
    String carpetAreaParameter, superAreaParameter, plotAreaParameter, maintenance_parameter, roadWidthParameter;

    String totalfloor, superArea, roadWidth, open_Sides, construction_done, boundary_wall,
            gated_colony, plotArea, plot_bredth, plot_length, cafateria, washroom, cornerShop, main_road_facing, personal_washroom,
            lock_in_periodString, token_amount, ageOfconstruction, availableFrom, security, maintenance, balcony;
    Toolbar toolbar;
    TextView imageCount;
    Menu menu;
    boolean mIsSaved = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_residential__view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout3);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + verticalOffset == 0) {
//                    TitleHead.setText("₹ "+price +" "+propertySubType);
                    collapsingToolbarLayout.setTitle("₹ " + price + " " + bedroom + " BHK " + propertySubType);
                    HideImageCount();
                    isShow = true;
                    showOption(R.id.action_share);
                } else if (isShow) {
//                    TitleHead.setText("");
                    collapsingToolbarLayout.setTitle("");
                    ShowImageCount();
                    isShow = false;
                    hideOption(R.id.action_share);
                }
            }
        });

        bindView();
        propertyPosterDetails();
        visibilities();
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
                imageAddress = getData[40];
                refId = getData[41];
                dateofposting = getData[42];
                timeofposting = getData[43];

                propertyPosterDetails();
                // session storage
                getDataForSharedPrefercnce();

                Picasso.get()
                        .load(imageAddress)
                        //   .resize(200, 200)
                        .fit()
                        .noFade()
                        .into(imageSlider);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        imageSliderMethod();
        getAditionanData();
        setTextData();
        if (propertyFor.equals("SELL")) {
            Maintenance.setVisibility(View.GONE);
            SecurityHead.setVisibility(View.GONE);
            MaintenanceHead.setVisibility(View.GONE);
            Security.setVisibility(View.GONE);
        } else {
            BookingAmount.setVisibility(View.GONE);
            BookingAmountHead.setVisibility(View.GONE);
        }

    }

    private void propertyPosterDetails() {
        OwnerLayout.setVisibility(View.GONE);
        AgentLayout.setVisibility(View.GONE);

        TextView OName = OwnerLayout.findViewById(R.id.name);
        TextView ObooksiteVisit = OwnerLayout.findViewById(R.id.booksiteVisit);
        Button OviewPhoneNo = OwnerLayout.findViewById(R.id.viewPhoneNo);
        TextView Odateofposting = OwnerLayout.findViewById(R.id.dateofposting);
        Odateofposting.setText(dateofposting);

        TextView AName = AgentLayout.findViewById(R.id.name);
        TextView AbooksiteVisit = AgentLayout.findViewById(R.id.booksiteVisit);
        TextView Aoperating_since = AgentLayout.findViewById(R.id.operating_since);
        TextView Acommercial = AgentLayout.findViewById(R.id.commercial);
        TextView Acallnow = AgentLayout.findViewById(R.id.callnow);
        Button AviewPhoneNo = AgentLayout.findViewById(R.id.viewPhoneNo);
        TextView Adateofposting = AgentLayout.findViewById(R.id.dateofposting);
        Adateofposting.setText(dateofposting);

        DatabaseReference def = FirebaseDatabase.getInstance().getReference().child("User");
        Query query = def.orderByChild("id").equalTo(refId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    User data = child.getValue(User.class);
                    if (data.getUsertype().equals("Agent")) {
                        AgentLayout.setVisibility(View.VISIBLE);
                        AName.setText(data.getName());
                        phoneNo = data.getPhone();
                    } else if (data.getUsertype().equals("Owner")) {
                        OwnerLayout.setVisibility(View.VISIBLE);
                        phoneNo = data.getPhone();
                        OName.setText(data.getName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Acallnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "call"+phoneNo, Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNo));
                if (ActivityCompat.checkSelfPermission(Start331AllResidential_View.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });
    }

    private void getDataForSharedPrefercnce() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
        Query query = reference.child(propertyType).orderByChild("keyId").equalTo(propertyId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    PropertyModel data = child.getValue(PropertyModel.class);
                    propertyModelArrayList.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void getAditionanData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
        DatabaseReference refer = reference.child("additionalInfo");
        Query query = refer.orderByChild("refId").equalTo(propertyId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            AdditioanlDetailsModel data = child.getValue(AdditioanlDetailsModel.class);
//                        additionalId = data.getId();
                            facing = data.getFacing();
                            location = data.getLocation();
                            landmark = data.getLandmark();
                            covered_area = data.getCoverdArea();
                            pricePer = data.getPricePSS();
                            society = data.getSociety();
                            flooring = data.getFlooring();
                            overlooking = data.getOverLooking();
                            water = data.getWater();
                            electricity = data.getElectricityAvailability();

                            suitableFor = data.getSuitableFor();
                            construction = data.getConstruction();
                            openParking = data.getOpenParking();
                            closeParking = data.getCloseParking();
                            carparking = data.getCarparking();
                            covered_areaParameter = data.getCovered_areaParameter();
                            parking = data.getParking();
                            lift = data.getLift();
                            gym = data.getGym();
                            powerBackup = data.getPowerBackup();
                            gasPipeline = data.getGasPipeline();
                            park = data.getPark();
                            clubHouse = data.getClubHouse();
                            swimPool = data.getSwimPool();

                            if (!carparking.isEmpty()) {
                                Carparking.setVisibility(View.VISIBLE);
                                CarparkingHead.setVisibility(View.VISIBLE);
                                if (carparking.equals("Yes")) {
                                    if (!openParking.isEmpty()) {
                                        if (!closeParking.isEmpty()) {
                                            Carparking.setText(openParking + " Open, " + closeParking + " Covered");
                                        } else {
                                            Carparking.setText(carparking + " Open");
                                        }
                                    }
                                } else {
                                    Carparking.setVisibility(View.GONE);
                                    CarparkingHead.setVisibility(View.GONE);
                                }
                            }
                            if (!covered_area.isEmpty()) {
                                CoveredArea.setVisibility(View.VISIBLE);
                                CoveredAreaHead.setVisibility(View.VISIBLE);
                                PricePer.setVisibility(View.VISIBLE);
                                CoveredArea.setText(covered_area + " " + covered_areaParameter);
                                PricePer.setText(pricePer);
                            }
                            if (!society.isEmpty()) {
                                Society.setVisibility(View.VISIBLE);
                                SocietyHead.setVisibility(View.VISIBLE);
                                Society.setText(society);
                            }
                            if (!location.isEmpty()) {
                                Location.setVisibility(View.VISIBLE);
                                LocationHead.setVisibility(View.VISIBLE);
                                Location.setText(location);
                            }
                            if (!landmark.isEmpty()) {
                                Landmark.setVisibility(View.VISIBLE);
                                LandmarkHead.setVisibility(View.VISIBLE);
                                Landmark.setText(landmark);
                            }
                            if (!overlooking.isEmpty()) {
                                Overlooking.setText(overlooking);
                                Overlooking.setVisibility(View.VISIBLE);
                                OverlookingHead.setVisibility(View.VISIBLE);
                            }
                            if (!water.isEmpty()) {
                                Water.setVisibility(View.VISIBLE);
                                WaterHead.setVisibility(View.VISIBLE);
                                Water.setText(water);
                            }
                            if (!flooring.isEmpty()) {
                                Flooring.setText(flooring);
                                Flooring.setVisibility(View.VISIBLE);
                                FlooringHead.setVisibility(View.VISIBLE);
                            }
                            if (!facing.isEmpty()) {
                                Facing.setText(facing);
                                Facing.setVisibility(View.VISIBLE);
                                FacingHead.setVisibility(View.VISIBLE);
                            }
                            if (!electricity.isEmpty()) {
                                ElectricityAvailability.setText(electricity);
                                ElectricityAvailability.setVisibility(View.VISIBLE);
                                ElectricHead.setVisibility(View.VISIBLE);
                            }

                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Start331AllResidential_View.this, "database error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTextData() {
        try {
            Price.setText("₹" + price);
            Address.setText(locality);
            if (bedroom.isEmpty()) {
                PropertSubType.setText(propertySubType);
            } else {
                PropertSubType.setText(bedroom + " BHK " + propertySubType);
            }
            if (!bedroom.isEmpty()) {
                Configuration.setVisibility(View.VISIBLE);
                ConfigurationHead.setVisibility(View.VISIBLE);
                if (!bathroom.isEmpty()) {
                    if (!balcony.isEmpty()) {
                        Configuration.setText(bedroom + " Bed, " + bathroom + " Bath, " + balcony + " Balcony");
                    } else {
                        Configuration.setText(bedroom + " Bed, " + bathroom + " Bath");
                    }
                } else if (!balcony.isEmpty()) {
                    Configuration.setText(bedroom + " Bed, " + balcony + " Balcony");
                } else {
                    Configuration.setText(bedroom + " Bed");
                }
            } else if (!bathroom.isEmpty()) {
                Configuration.setVisibility(View.VISIBLE);
                ConfigurationHead.setVisibility(View.VISIBLE);
                if (!balcony.isEmpty()) {
                    Configuration.setText(bathroom + " Bath, " + balcony + " Balcony");
                } else {
                    Configuration.setText(bathroom + " Bath");
                }
            }
            if (!carpet.isEmpty()) {
                CarpetArea.setVisibility(View.VISIBLE);
                CarpetAreaHead.setVisibility(View.VISIBLE);
                CarpetArea.setText(carpet + " " + carpetAreaParameter);
            }
            if (!floor.isEmpty()) {
                FloorNo.setVisibility(View.VISIBLE);
                FloorNoHead.setVisibility(View.VISIBLE);
                FloorNo.setText(floor + " of the " + totalfloor + " Floors");
            }
            if (!availableFrom.isEmpty()) {
                AvailableFrom.setVisibility(View.VISIBLE);
                AvailableFromHead.setVisibility(View.VISIBLE);
                AvailableFrom.setText(availableFrom);
            }
            if (!furnish.isEmpty()) {
                FurnishingHead.setVisibility(View.VISIBLE);
                Furnishing.setVisibility(View.VISIBLE);
                Furnishing.setText(furnish);
            }
            if (!ageOfconstruction.isEmpty()) {
                AgeofConst.setVisibility(View.VISIBLE);
                AgeofConstHead.setVisibility(View.VISIBLE);
                AgeofConst.setText(ageOfconstruction);
            }
            if (!propertyStatus.isEmpty()) {
                Status.setVisibility(View.VISIBLE);
                StatusHead.setVisibility(View.VISIBLE);
                Status.setText(propertyStatus);
            }
            if (!token_amount.isEmpty()) {
                BookingAmount.setVisibility(View.VISIBLE);
                BookingAmountHead.setVisibility(View.VISIBLE);
                BookingAmount.setText("₹" + token_amount);
            }
            if (!maintenance.isEmpty()) {
                Maintenance.setVisibility(View.VISIBLE);
                MaintenanceHead.setVisibility(View.VISIBLE);
                Maintenance.setText("₹" + maintenance + " " + maintenance_parameter);
            }
            if (!superArea.isEmpty()) {
                SuperArea.setVisibility(View.VISIBLE);
                SuperAreaHead.setVisibility(View.VISIBLE);
                SuperArea.setText(superArea + " " + superAreaParameter);
            }
            if (!security.isEmpty()) {
                Security.setVisibility(View.VISIBLE);
                SecurityHead.setVisibility(View.VISIBLE);
                Security.setText("₹" + security);
            }
            if (!roadWidth.isEmpty()) {
                RoadWidthHead.setVisibility(View.VISIBLE);
                RoadWidth.setVisibility(View.VISIBLE);
                RoadWidth.setText(roadWidth);
            } else if (!main_road_facing.isEmpty()) {
                RoadWidthHead.setVisibility(View.VISIBLE);
                RoadWidth.setVisibility(View.VISIBLE);
                RoadWidth.setText(main_road_facing);
            } else {
                RoadWidthHead.setVisibility(View.GONE);
                RoadWidth.setVisibility(View.GONE);
            }
            if (!open_Sides.isEmpty()) {
                OpenSides.setVisibility(View.VISIBLE);
                OpenSidesHead.setVisibility(View.VISIBLE);
                OpenSides.setText(open_Sides);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void visibilities() {
        try {

            CarpetArea.setVisibility(View.GONE);
            CarpetAreaHead.setVisibility(View.GONE);
            Configuration.setVisibility(View.GONE);
            ConfigurationHead.setVisibility(View.GONE);
            CoveredArea.setVisibility(View.INVISIBLE);
            CoveredAreaHead.setVisibility(View.INVISIBLE);
            Overlooking.setVisibility(View.GONE);
            OverlookingHead.setVisibility(View.GONE);
            Flooring.setVisibility(View.GONE);
            FlooringHead.setVisibility(View.GONE);
            Water.setVisibility(View.GONE);
            WaterHead.setVisibility(View.GONE);
            PricePer.setVisibility(View.GONE);
            FloorNo.setVisibility(View.GONE);
            FloorNoHead.setVisibility(View.GONE);
            AgeofConstHead.setVisibility(View.GONE);
            AgeofConst.setVisibility(View.GONE);
            AvailableFrom.setVisibility(View.GONE);
            AvailableFromHead.setVisibility(View.GONE);
            FurnishingHead.setVisibility(View.GONE);
            Furnishing.setVisibility(View.GONE);
            Security.setVisibility(View.GONE);
            SecurityHead.setVisibility(View.GONE);
            SuperArea.setVisibility(View.GONE);
            SuperAreaHead.setVisibility(View.GONE);
            BookingAmount.setVisibility(View.GONE);
            BookingAmountHead.setVisibility(View.GONE);
            Maintenance.setVisibility(View.GONE);
            MaintenanceHead.setVisibility(View.GONE);
            Carparking.setVisibility(View.GONE);
            CarparkingHead.setVisibility(View.GONE);
            Society.setVisibility(View.GONE);
            SocietyHead.setVisibility(View.GONE);
            RoadWidthHead.setVisibility(View.GONE);
            RoadWidth.setVisibility(View.GONE);
            OpenSides.setVisibility(View.GONE);
            OpenSidesHead.setVisibility(View.GONE);
            Landmark.setVisibility(View.GONE);
            LandmarkHead.setVisibility(View.GONE);
            Location.setVisibility(View.GONE);
            LocationHead.setVisibility(View.GONE);
            Facing.setVisibility(View.GONE);
            FacingHead.setVisibility(View.GONE);
            ElectricityAvailability.setVisibility(View.GONE);
            ElectricHead.setVisibility(View.GONE);
            Status.setVisibility(View.GONE);
            StatusHead.setVisibility(View.GONE);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void imageSliderMethod() {
        try {
            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("PropertyTable")
                    .child(propertyType).child(propertyId).child("images");
            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        if (dataSnapshot.exists()) {
                            long no = dataSnapshot.getChildrenCount();
                            if (no <= 0) {
                                imageCount.setVisibility(View.GONE);
                            } else {
                                imageCount.setText(no + "+");
                            }
                        }
                    } catch (NullPointerException e) {
                        e.getMessage();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void bindView() {
        try {
            OwnerLayout= findViewById(R.id.ownerDetails);
            AgentLayout= findViewById(R.id.agentDetails);

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
            AvailableFrom = findViewById(R.id.availableFrom);
            AgeofConst = findViewById(R.id.ageOfConst);
            ElectricityAvailability = findViewById(R.id.electricityAvailability);
            imageCount = findViewById(R.id.imageCount);
            Society = findViewById(R.id.society);

            // labels
            SocietyHead = findViewById(R.id.societyHead);
            AgeofConstHead = findViewById(R.id.ageOfConstHead);
            AvailableFromHead = findViewById(R.id.availableFromHead);
            ElectricHead = findViewById(R.id.electricHead);
            LocationHead = findViewById(R.id.locationHead);
            CarparkingHead = findViewById(R.id.carparkingHead);
            CarpetAreaHead = findViewById(R.id.carpetArea);
            CoveredAreaHead = findViewById(R.id.coveredAreaHeading);
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
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_share);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        boolean result = true;
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_favourite:
                if (mIsSaved) {
                    mIsSaved = false;
                    menu.findItem(R.id.action_favourite)
                            .setIcon(R.drawable.heart_on);
                    Toast.makeText(Start331AllResidential_View.this, "Added", Toast.LENGTH_SHORT).show();
                    SessionManager session = new SessionManager(getApplicationContext());
                    session.saveArrayList(propertyModelArrayList, propertyId);
                    ArrayList<PropertyModel> list = new ArrayList<>();
                    list = session.getArrayList(propertyId);
                        Toast.makeText(this, "exists " + list , Toast.LENGTH_SHORT).show();
                } else {
                    mIsSaved = true;
                    menu.findItem(R.id.action_favourite)
                            .setIcon(R.drawable.heart_off);
                    Toast.makeText(Start331AllResidential_View.this, "removed", Toast.LENGTH_SHORT).show();
                    SessionManager session = new SessionManager(getApplicationContext());
                    ArrayList<PropertyModel> list = new ArrayList<>();
                    list = session.getArrayList(propertyId);
                    Toast.makeText(this, "exists " + list , Toast.LENGTH_SHORT).show();

                        Toast.makeText(this, "exists " + propertyModelArrayList, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_share:
                Toast.makeText(Start331AllResidential_View.this, "Share", Toast.LENGTH_SHORT).show();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }


    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    public void ShowImages(View view) {
        Intent intent = new Intent(Start331AllResidential_View.this, view_images.class);
        Bundle bundle = new Bundle();
        bundle.putString("PropertyId", propertyId);
        bundle.putString("propertyType", propertyType);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void ShowImageCount() {
        imageCount.setVisibility(View.VISIBLE);
    }

    private void HideImageCount() {
        imageCount.setVisibility(View.GONE);

    }
}
