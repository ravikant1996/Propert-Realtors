package com.example.propertyrealtors.A_EndUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.Post_property.UploadSliderAdapter;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.SharedPreference;
import com.example.propertyrealtors.model.AdditioanlDetailsModel;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.PropertyModel;
import com.example.propertyrealtors.model.User;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Start331AllResidential2_View extends AppCompatActivity {
    ImageView imageSlider;
    View OwnerLayout, AgentLayout;
    String phoneNo;
    TextView Price, CoveredArea, PropertSubType, Address, PlotArea, PricePer, Location, AgeofConst, AvailableFrom,
            Configuration, Status, Carparking, AAditionalFeature, Plot_Length, Facing, Overlooking, Security, SuperArea,
            Dimension, BookingAmount, Flooring, Landmark, Water, OpenSides, RoadWidth;

    TextView DimensionHead, PlotAreaHead, AddressHead, CoveredAreaHead, AAditionalFeatureHead, LocationHead, AgeofConstHead, AvailableFromHead,
            ConfigurationHead, StatusHead, CarparkingHead, FloorNoHead, FurnishingHead, FacingHead, OverlookingHead, SecurityHead, SuperAreaHead,
            MaintenanceHead, BookingAmountHead, FlooringHead, LandmarkHead, WaterHead, OpenSidesHead, RoadWidthHead;
    UploadSliderAdapter adapter;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    RecyclerView recyclerView;
    simillarAdapter simiAdapter;
    // Linear Layout Manager
    LinearLayoutManager horizontalLayout;
    ArrayList<Image> pic;
    List<String> propertySubTypes = new ArrayList<>();
    List<String > cityList= new ArrayList<>();
    String propertyType, propertyId;
    PropertyModel list;
    String[] getData;
    String gym, clubHouse, park, parking, lift, powerBackup, gasPipeline, swimPool;
    String refId, dateofposting, timeofposting, propertyFor, price, bedroom, locality, city, location, covered_area, pricePer, carparking, society,
            covered_areaParameter, carpet, propertyStatus, bathroom,
            floor, furnish, propertySubType, imageAddress;
    String carpetAreaParameter, superAreaParameter, plotAreaParameter, maintenance_parameter, roadWidthParameter;
    String construction, water, overlooking, facing, landmark, how_much_construction;

    String totalfloor, superArea, roadWidth, open_Sides, construction_done, boundary_wall,
            gated_colony, plotArea, plot_bredth, plot_length, cafateria, washroom, cornerShop, main_road_facing, personal_washroom,
            lock_in_periodString, token_amount, ageOfconstruction, availableFrom, security, maintenance, balcony;
    Toolbar toolbar;
    Menu menu;
    boolean mIsSaved = true;
    TextView imageCount;
    SharedPreference sharedPreference;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_residential2__view);
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
        sharedPreference = new SharedPreference();
        session = new SessionManager(getApplicationContext());

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
                    collapsingToolbarLayout.setTitle("₹ " + price + " * " + propertySubType);
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
        bindViews();

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

                Picasso.get()
                        .load(imageAddress)
                        //   .resize(200, 200)
                        .fit()
                        .noFade()
                        .into(imageSlider);

                getDataForSharedPrefercnce();

                propertyPosterDetails();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        visibilities();
        imageSliderMethod();
        getAditionanData();
        setTextData();
        recycler();

    }
    private void getDataForSharedPrefercnce() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
        Query query = reference.child(propertyType).orderByChild("keyId").equalTo(propertyId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    list = child.getValue(PropertyModel.class);
//                    propertyModelArrayList.add(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void recycler() {
        View similarproperty= findViewById(R.id.similarproperty);

        TextView showAll = similarproperty.findViewById(R.id.showall);
        recyclerView = similarproperty.findViewById(R.id.recyclerView);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        // Set LayoutManager on Recycler View
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        final ArrayList<PropertyModel> propertyModelArrayList1 = new ArrayList<PropertyModel>();
        final ArrayList<Image> imageArrayList1 = new ArrayList<Image>();
        // layout visibility
        // Adding items to RecyclerView.
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
        DatabaseReference reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor).limitToFirst(5);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        similarproperty.setVisibility(View.VISIBLE);
                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            if (!propertyId.equals(details.getKeyId())) {
                                propertyModelArrayList1.add(details);
                                cityList.add(details.getCity());

                                String id1 = details.getKeyId();
                                Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Image image = new Image("null");
                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                                image = areaSnapshot.getValue(Image.class);
                                                imageArrayList1.add(image);
                                                simiAdapter.notifyDataSetChanged();
                                            }
                                        } else {
                                            imageArrayList1.add(image);
                                            simiAdapter.notifyDataSetChanged();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recyclerView.setHasFixedSize(true);
                simiAdapter = new simillarAdapter(getApplicationContext(), propertyModelArrayList1, imageArrayList1);
                horizontalLayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(horizontalLayout);
                //       recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(simiAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Opsss......", Toast.LENGTH_SHORT).show();
            }
        });
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cityList.add(city);
                cityList.add(locality);
                if (propertySubType.equals("Plot") || propertySubType.equals("Commercial_Land") ||
                propertySubType.equals("Industrial_Land")) {
                    propertySubTypes.add("Plot");
                    propertySubTypes.add("Commercial_Land");
                    propertySubTypes.add("Industrial_Land");
                }else {
                    propertySubTypes.add("Agriculture_Land");
                }

                Intent intent= new Intent(Start331AllResidential2_View.this, ViewProperty.class);
                Bundle bundle= new Bundle();
                bundle.putString("propertyType", propertyType);
                bundle.putString("propertyFor", propertyFor);
                bundle.putStringArrayList("PROPERTYSUBTYPES", (ArrayList<String>) propertySubTypes);
                bundle.putStringArrayList("CITYLIST", (ArrayList<String>) cityList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void propertyPosterDetails() {

        View divider9= findViewById(R.id.divider9);
        divider9.setVisibility(View.GONE);

        TextView OName = OwnerLayout.findViewById(R.id.name);
        TextView Ocallnow = OwnerLayout.findViewById(R.id.booksiteVisit);
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
                        divider9.setVisibility(View.VISIBLE);
                    } else if (data.getUsertype().equals("Owner")) {
                        OwnerLayout.setVisibility(View.VISIBLE);
                        phoneNo = data.getPhone();
                        OName.setText(data.getName());
                        divider9.setVisibility(View.VISIBLE);
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
                if (isPermissionGranted()) {
                    SessionManager session = new SessionManager(getApplicationContext());
                    HashMap<String, String> userID = session.getUserIDs();
                    String userId = userID.get(SessionManager.KEY_ID);
                    if (userId==null){
                        Toast.makeText(Start331AllResidential2_View.this, "First register", Toast.LENGTH_SHORT).show();
                    }else {
                        call_action();
                    }
                }
            }
        });
        Ocallnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPermissionGranted()) {
                    SessionManager session = new SessionManager(getApplicationContext());
                    HashMap<String, String> userID = session.getUserIDs();
                    String userId = userID.get(SessionManager.KEY_ID);
                    if (userId==null){
                        Toast.makeText(Start331AllResidential2_View.this, "First register", Toast.LENGTH_SHORT).show();
                    }else {
                        call_action();
                    }
                }
            }
        });
    }
    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void call_action() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNo));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

    private void visibilities() {

        AAditionalFeature.setVisibility(View.GONE);
        AAditionalFeatureHead.setVisibility(View.GONE);
        CoveredAreaHead.setVisibility(View.INVISIBLE);
        CoveredArea.setVisibility(View.INVISIBLE);
        PricePer.setVisibility(View.GONE);
        AvailableFrom.setVisibility(View.GONE);
        AvailableFromHead.setVisibility(View.GONE);
        PlotArea.setVisibility(View.GONE);
        PlotAreaHead.setVisibility(View.GONE);
        Dimension.setVisibility(View.GONE);
        DimensionHead.setVisibility(View.GONE);
        Status.setVisibility(View.GONE);
        StatusHead.setVisibility(View.GONE);
        BookingAmount.setVisibility(View.GONE);
        BookingAmountHead.setVisibility(View.GONE);
        RoadWidthHead.setVisibility(View.GONE);
        RoadWidth.setVisibility(View.GONE);
        OpenSides.setVisibility(View.GONE);
        OpenSidesHead.setVisibility(View.GONE);
        Location.setVisibility(View.GONE);
        LocationHead.setVisibility(View.GONE);
        Landmark.setVisibility(View.GONE);
        LandmarkHead.setVisibility(View.GONE);
        Water.setVisibility(View.GONE);
        WaterHead.setVisibility(View.GONE);
        Overlooking.setVisibility(View.GONE);
        OverlookingHead.setVisibility(View.GONE);
        Facing.setVisibility(View.GONE);
        FacingHead.setVisibility(View.GONE);
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
                            overlooking = data.getOverLooking();
//                        flooring = data.getFlooring();
                            water = data.getWater();
//                        electricity = data.getElectricityAvailability();

//                        suitableFor  = data.getSuitableFor();
                            construction = data.getConstruction();
//                        openParking  = data.getOpenParking();
//                        closeParking = data.getCloseParking();
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
                            if (!covered_area.isEmpty()) {
                                CoveredAreaHead.setVisibility(View.VISIBLE);
                                CoveredArea.setVisibility(View.VISIBLE);
                                CoveredArea.setVisibility(View.VISIBLE);
                                PricePer.setVisibility(View.VISIBLE);
                                CoveredArea.setText(covered_area + " " + covered_areaParameter);
                                PricePer.setText(pricePer);
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
                            if (!water.isEmpty()) {
                                Water.setVisibility(View.VISIBLE);
                                WaterHead.setVisibility(View.VISIBLE);
                                Water.setText(water);
                            }
                            if (!overlooking.isEmpty()) {
                                Overlooking.setVisibility(View.VISIBLE);
                                OverlookingHead.setVisibility(View.VISIBLE);
                                Overlooking.setText(overlooking);
                            }
                            if (!facing.isEmpty()) {
                                Facing.setVisibility(View.VISIBLE);
                                FacingHead.setVisibility(View.VISIBLE);
                                Facing.setText(facing);
                            }
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Start331AllResidential2_View.this, "database error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTextData() {
        try {
            Price.setText("₹ " + price);
            PropertSubType.setText(propertySubType);
            Address.setText(locality);
            if (!availableFrom.isEmpty()) {
                AvailableFrom.setVisibility(View.VISIBLE);
                AvailableFromHead.setVisibility(View.VISIBLE);
                AvailableFrom.setText(availableFrom);
            }
            if (!plotArea.isEmpty()) {
                PlotArea.setVisibility(View.VISIBLE);
                PlotAreaHead.setVisibility(View.VISIBLE);
                PlotArea.setText(plotArea + " " + plotAreaParameter);
            }
            if (!plot_length.isEmpty()) {
                Dimension.setVisibility(View.VISIBLE);
                DimensionHead.setVisibility(View.VISIBLE);
                if (!plot_bredth.isEmpty()) {
                    Dimension.setText(plot_bredth + " * " + plot_length);
                } else {
                    int area = Integer.parseInt(plotArea);
                    int l = Integer.parseInt(plot_length);
                    int b = area / l;
                    Dimension.setText(plot_length + " * " + b);
                }
            }
            String gate = "In a gated colony";
            String bound = "with boundary walls";
            if (gated_colony.equals("Yes")) {
                if (construction_done.equals("Yes")) {
                    if (boundary_wall.equals("Yes")) {
                        AAditionalFeature.setText(gate + ", " + construction + ", " + bound);
                    } else {
                        AAditionalFeature.setText(gate + ", " + construction);
                    }
                } else if (boundary_wall.equals("Yes")) {
                    AAditionalFeature.setText(gate + ", " + bound);
                }
            } else if (construction_done.equals("Yes")) {
                if (boundary_wall.equals("Yes")) {
                    AAditionalFeature.setText(construction + ", " + bound);
                } else {
                    AAditionalFeature.setText(construction);
                }
            } else if (boundary_wall.equals("Yes")) {
                AAditionalFeature.setText(bound);
            } else {
                AAditionalFeature.setVisibility(View.GONE);
                AAditionalFeatureHead.setVisibility(View.GONE);
            }
            if (!propertyStatus.isEmpty()) {
                Status.setVisibility(View.VISIBLE);
                StatusHead.setVisibility(View.VISIBLE);
                Status.setText(propertyStatus);
            }
            if (!token_amount.isEmpty()) {
                BookingAmount.setVisibility(View.VISIBLE);
                BookingAmountHead.setVisibility(View.VISIBLE);
                BookingAmount.setText(token_amount);
            }
            if (!roadWidth.isEmpty()) {
                RoadWidthHead.setVisibility(View.VISIBLE);
                RoadWidth.setVisibility(View.VISIBLE);
                RoadWidth.setText(roadWidth + " " + "Meters");
            } else {
                RoadWidthHead.setVisibility(View.VISIBLE);
                RoadWidth.setVisibility(View.VISIBLE);
                RoadWidth.setText(main_road_facing);
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

    private void imageSliderMethod() {

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("PropertyTable")
                .child(propertyType).child(propertyId).child("images");
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        long no = dataSnapshot.getChildrenCount();
                        if (no < 1) {
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
    }

    private void bindViews() {
        try {
            OwnerLayout = findViewById(R.id.ownerDetails);
            AgentLayout = findViewById(R.id.agentDetails);

            imageSlider = findViewById(R.id.imageSlider);
            CoveredArea = findViewById(R.id.coveredArea);
            Price = findViewById(R.id.price);
            PropertSubType = findViewById(R.id.propertSubType);
            Address = findViewById(R.id.address);
            PlotArea = findViewById(R.id.plotarea);
            PricePer = findViewById(R.id.price_per);
            Location = findViewById(R.id.location);
            Status = findViewById(R.id.status);
            Facing = findViewById(R.id.facing);
            Overlooking = findViewById(R.id.overlooking);
            BookingAmount = findViewById(R.id.token_amount);
            Landmark = findViewById(R.id.landmark);
            Security = findViewById(R.id.security);
            RoadWidth = findViewById(R.id.road_width);
            Dimension = findViewById(R.id.dimension);
            AAditionalFeature = findViewById(R.id.AdditionalFeature);
            AvailableFrom = findViewById(R.id.availableFrom);
            imageCount = findViewById(R.id.imageCount);
            Water = findViewById(R.id.waterHead);

            // labels
            WaterHead = findViewById(R.id.water);
            AvailableFromHead = findViewById(R.id.availableFromHead);
            AAditionalFeatureHead = findViewById(R.id.AdditionalFeatureHead);
            CoveredAreaHead = findViewById(R.id.coveredAreaHeading);
            LocationHead = findViewById(R.id.locationHead);
            PlotAreaHead = findViewById(R.id.plotareaHead);
            DimensionHead = findViewById(R.id.dimensionHead);
            StatusHead = findViewById(R.id.statusHead);
            AvailableFromHead = findViewById(R.id.availableFromHead);
            FacingHead = findViewById(R.id.facingHead);
            OverlookingHead = findViewById(R.id.overlookingHead);
            BookingAmountHead = findViewById(R.id.token_amountHead);
            LandmarkHead = findViewById(R.id.landmarkHead);
            OpenSides = findViewById(R.id.openSides);
            OpenSidesHead = findViewById(R.id.openSidesHead);
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
        List<PropertyModel> list= sharedPreference.getFavorites(getApplicationContext());
        if (list != null) {
            Iterator<PropertyModel> iterator = list.iterator();
            while(iterator.hasNext()) {
                PropertyModel next = iterator.next();
                if(next.getKeyId().equals(propertyId)) {
                    menu.findItem(R.id.action_favourite).setIcon(R.drawable.heart_on);
                    mIsSaved=false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean result = true;
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_favourite:
                if (mIsSaved) {
                    try {
                        if (list!=null) {
                            mIsSaved = false;
                            menu.findItem(R.id.action_favourite)
                                    .setIcon(R.drawable.heart_on);
                            sharedPreference.addFavorite(getApplicationContext(), list);
                            Toast.makeText(this, "Added in the favourite", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException e) {
                        Toast.makeText(this, "empty String" + list, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        mIsSaved = true;
                        menu.findItem(R.id.action_favourite)
                                .setIcon(R.drawable.heart_off);
                        sharedPreference.removeFavorite(getApplicationContext(), propertyId);
                        Toast.makeText(this, "removed", Toast.LENGTH_SHORT).show();
                    } catch (NullPointerException e) {
                        Toast.makeText(this, "empty String" + list, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.action_share:
                Toast.makeText(Start331AllResidential2_View.this, "Share", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(Start331AllResidential2_View.this, view_images.class);
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
