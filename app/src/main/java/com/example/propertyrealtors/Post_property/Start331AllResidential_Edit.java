package com.example.propertyrealtors.Post_property;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.AdditioanlDetailsModel;
import com.example.propertyrealtors.model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;
import com.tuyenmonkey.mkloader.MKLoader;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;


public class Start331AllResidential_Edit extends AppCompatActivity {
    int flag = 0;
    ConstraintLayout ExpandableView, ExpandableView2, ExpandableView3, ExpandableView4, ExpandableView5,
            ExpandableView6, ExpandableView7, ExpandableView8, ExpandableView9, ExpandableView10, ExpandableView11, ExpandableView12;
    Button ArrowBtn, ArrowBtn2, ArrowBtn3, ArrowBtn4, ArrowBtn5, ArrowBtn6, ArrowBtn7, ArrowBtn8, ArrowBtn9, ArrowBtn10, ArrowBtn11, ArrowBtn12;
    CardView CardView, CardView2, CardView3, CardView4, CardView5, CardView6, CardView7, CardView8, CardView9, CardView10, CardView11, CardView12;
    ImageView imageView;
    Button Upload, Adddetails, Adddetails2, Adddetails3, Adddetails4, Adddetails5, Adddetails6, Adddetails7, Adddetails8, Adddetails9,
            Adddetails10, Adddetails11;
    Toolbar toolbar;
    SessionManager session;
    String propertyId;
    String[] getData;
    CardView Gym, ClubHouse, Park, Parking, Lift, PowerBackup, GasPipeline, SwimPool;
    String gym, clubHouse, park, parking, lift, powerBackup, gasPipeline, swimPool, carparking, construction, suitableFor, openParking,
            closeParking, pricePer, facing, location, landmark, coveredArea, society, flooring, overlooking, water,
            electricityAvailability;
    String UID, propertyFor, propertyType, price, bedroom, locality, city, carpetArea, propertyStatus, bathroom,
            floor, furnish, propertySubType;
    String carpetAreaParameter, superAreaParameter, plotAreaParameter, maintenance_parameter, roadWidthParameter,
            carpetAreaParameter1, superAreaParameter1, plotAreaParameter1, maintenance_parameter1, roadWidthParameter1,
            covered_areaParameter, covered_areaParameter1, pricePer_parameter;

    String totalfloor, superArea, roadWidth, open_Sides, construction_done, boundary_wall,
            gated_colony, plotArea, plot_bredth, plot_length, cafateria, washroom, cornerShop, main_road_facing, personal_washroom,
            lock_in_periodString, token_amount, ageOfconstruction, availableFrom, security, maintenance, balcony;

    EditText Token_Amount, Maintenance, ExpectedAmount, Security_amount, Location, Landmark,
            SuperArea, PlotArea, Plot_Breadth,
            Plot_Length, RoadWidth;
    EditText CarpetArea, Facing, Society, Flooring, PricePer,
            CoverdArea, OverLooking, Construction,
            Water, ElectricityAvailability;
    TextInputLayout Token_AmountHead, Security_amountHead, MaintenanceHead, CarpetAreaHead, SuperAreaHead, PlotAreaHead, Plot_LengthHead,
            Plot_bredthHead, FlooringHead,
            SocietyHead, LocationHead, LandmarkHead, RoadWidthHead, FacingHead, OverlookingHead, WaterHead, ElectricHead;
    RadioGroup RadioGroup1, RadioGroup_AnyConst, RadioGroup_Boundary, RadioGroup_Gated, RadioGroup_Cafeteria, RadioGroup_CornerShop, RadioGroup_personalWashroom, RadioGroup_Furnish;
    RadioButton Radio_Yes, Radio_No, AnyConst_Yes, AnyConst_No, CornerShop_Yes, CornerShop_No, Cafeteria_Yes, Cafeteria_No, Cafeteria_NotAvailable, GatedColony_Yes, GatedColony_No, Bounadry_Yes,
            Bounadry_No, PersonalWashroomYes, PersonalWashroomNo, Unfur, Semi_fer, Fully_fer;
    TextView City, Locality, BedroomHead, BathroomHead, BalconyHead, TotalFloorHead, FloorNoHead, Open_SidesHead, Status_Head, Status2_Head, AgeofConstHead, AvailableFromHead,
            OpenHead, CloseHead, PersonalWashroomHead, WashRoomHead, CarparkingHead, SuitableForHead, GatedColonyHead, CafeteriaHead, CornerShopHead, BoundaryHead, AnyConstHead;
    HorizontalScrollView horizontalScrollView, horizontalScrollView3, horizontalScrollView1, horizontalScrollView2, horizontalScrollView4, horizontalScrollView5, horizontalScrollView6,
            horizontalScrollView7, horizontalScrollView8;
    Spinner StatusSpinner, Maintenance_Spinner, TotalFloorSpinner, FloorNoSpinner, Open_SidesSpinner, StatusSpinner2, WashRoomSpinner, SuitableForSpinner,
            BedRoomSpinner, BathRoomSpinner, BalconySpinner, FacingSpinner,
            OpenParkingSpinner, CloseParkingSpinner, Lock_in_periodSpinner, PlotArea_Parameter,
            CoveredArea_Parameter, Carpet_Parameter, SuperArea_Parameter, AgeofConstSpinner, AvailableFromSpinner;
    AdditioanlDetailsModel detailsModel;
    DatabaseReference reference;
    private static String ID_NO;
    String additionalId;
    UploadSliderAdapter adapter;
    MKLoader mkLoader;
    StorageReference storageReference, ref, imagePath;
    DatabaseReference databaseReference;
    private final int PERMISSION_ALL = 12;
    private ImagePicker imagePicker;
    int images = 0;
    Image image;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start331_all_residential__edit);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        toolbar.setTitle("Additional Details");
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> userID = session.getUserIDs();
        UID = userID.get(SessionManager.KEY_ID);

        reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
        final String[] PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
        };

        bindViews();
        detailsModel = new AdditioanlDetailsModel();
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
                carpetArea = getData[6];
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
                carpetAreaParameter1 = getData[34];

                superAreaParameter = getData[35];
                superAreaParameter1 = getData[35];
                plotAreaParameter = getData[36];
                plotAreaParameter1 = getData[36];
                maintenance_parameter = getData[37];
                maintenance_parameter1 = getData[37];
                roadWidthParameter = getData[38];
                roadWidthParameter1 = getData[38];
                city = getData[39];
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        check_Id_Existence();
        if ("Plot".equals(propertySubType) || "Commercial_Land".equals(propertySubType) || "Agriculture_Land".equals(propertySubType)
                || "Industrial_Land".equals(propertySubType) || propertySubType.equals("Shop") || propertySubType.equals("Showroom")
                || propertySubType.equals("Warehouse") || propertySubType.equals("Industrial_Building") ||
                propertySubType.equals("Industrial_Shed")) {
            CardView3.setVisibility(View.GONE);
            CardView11.setVisibility(View.GONE);

        }
        if (propertyFor.equals("SELL")) {
            CardView7.setVisibility(View.GONE);
        }
        visibilities();
        CallingBtn();
        parametersSpinner();

        storageReference = FirebaseStorage.getInstance().getReference()
                .child("PropertyImages/" + propertyId);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");

        imagecounter_From_Database();

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasPermissions(Start331AllResidential_Edit.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(Start331AllResidential_Edit.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    //  imageView.setImageURI(null);
                    imagePicker.choosePicture(true /*show camera intents*/);
                }
            }
        });
        imagePicker = new ImagePicker(this, /* activity non null*/
                null, /* fragment nullable*/
                new OnImagePickedListener() {
                    @Override
                    public void onImagePicked(Uri imageUri) {
                        try {
                            UCrop.of(imageUri, getTempUri())
                                    .withAspectRatio(1, 1)
                                    .start(Start331AllResidential_Edit.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } catch (UnsatisfiedLinkError e) {
                            e.printStackTrace();
                        }
                    }
                });
        Adddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick();
            }
        });
        Adddetails2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick2();
            }
        });
        Adddetails3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick3();
            }
        });
        Adddetails4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick4();

            }
        });
        Adddetails5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick5();

            }
        });
        Adddetails6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick6();

            }
        });
        Adddetails7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick7();

            }
        });
        Adddetails8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick8();
            }
        });
        Adddetails9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick9();
            }
        });
        Adddetails10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick10();
            }
        });
        Adddetails11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick11();
            }
        });

    }

    private void visibilities() {
        Security_amountHead.setVisibility(View.GONE);
        MaintenanceHead.setVisibility(View.GONE);
        Security_amount.setVisibility(View.GONE);
        Maintenance.setVisibility(View.GONE);
        Maintenance_Spinner.setVisibility(View.GONE);
        Token_AmountHead.setVisibility(View.GONE);
        Token_Amount.setVisibility(View.GONE);

        SuperArea.setVisibility(View.GONE);
        SuperAreaHead.setVisibility(View.GONE);
        CarpetArea.setVisibility(View.GONE);
        CarpetAreaHead.setVisibility(View.GONE);
        SuperArea_Parameter.setVisibility(View.GONE);
        Carpet_Parameter.setVisibility(View.GONE);
        PlotArea.setVisibility(View.GONE);
        PlotArea_Parameter.setVisibility(View.GONE);
        Plot_Length.setVisibility(View.GONE);
        Plot_LengthHead.setVisibility(View.GONE);
        Plot_Breadth.setVisibility(View.GONE);
        Plot_bredthHead.setVisibility(View.GONE);
        PlotAreaHead.setVisibility(View.GONE);

        BedRoomSpinner.setVisibility(View.GONE);
        BedroomHead.setVisibility(View.GONE);
        WashRoomHead.setVisibility(View.GONE);
        WashRoomSpinner.setVisibility(View.GONE);
        SuitableForHead.setVisibility(View.GONE);
        SuitableForSpinner.setVisibility(View.GONE);
        BathroomHead.setVisibility(View.GONE);
        BathRoomSpinner.setVisibility(View.GONE);
        BalconyHead.setVisibility(View.GONE);
        BalconySpinner.setVisibility(View.GONE);

        Open_SidesHead.setVisibility(View.GONE);
        Open_SidesSpinner.setVisibility(View.GONE);
        FloorNoHead.setVisibility(View.GONE);
        FloorNoSpinner.setVisibility(View.GONE);

        Status2_Head.setVisibility(View.GONE);
        StatusSpinner2.setVisibility(View.GONE);
        AgeofConstHead.setVisibility(View.GONE);
        AgeofConstSpinner.setVisibility(View.GONE);
        AvailableFromHead.setVisibility(View.GONE);
        AvailableFromSpinner.setVisibility(View.GONE);
        Status_Head.setVisibility(View.GONE);
        StatusSpinner.setVisibility(View.GONE);

        CarparkingHead.setVisibility(View.GONE);
        horizontalScrollView1.setVisibility(View.GONE);
        GatedColonyHead.setVisibility(View.GONE);
        horizontalScrollView4.setVisibility(View.GONE);
        CafeteriaHead.setVisibility(View.GONE);
        horizontalScrollView5.setVisibility(View.GONE);
        CornerShopHead.setVisibility(View.GONE);
        horizontalScrollView6.setVisibility(View.GONE);
        PersonalWashroomHead.setVisibility(View.GONE);
        horizontalScrollView7.setVisibility(View.GONE);
        CafeteriaHead.setVisibility(View.GONE);
        AnyConstHead.setVisibility(View.GONE);
        horizontalScrollView2.setVisibility(View.GONE);
        BoundaryHead.setVisibility(View.GONE);
        horizontalScrollView3.setVisibility(View.GONE);

        FlooringHead.setVisibility(View.GONE);
        Flooring.setVisibility(View.GONE);
        horizontalScrollView.setVisibility(View.GONE);


    }

    private void imagecounter_From_Database() {
        Query query = databaseReference.child(propertyType).orderByChild("keyId").equalTo(propertyId);
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    images = dataSnapshot.child(propertyId).child("imageCount").getValue(Integer.class);
                    if (!(images == 10)) {
                        Upload.setText(images + " out of " + "10 images are uploaded");
                    } else {
                        Upload.setText("Bucket is full");
                        Upload.setEnabled(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Start331AllResidential_Edit.this, "database error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Uri getTempUri() {
        String dir = Environment.getExternalStorageDirectory() + File.separator + "Temp";
        File dirFile = new File(dir);
        dirFile.mkdir();
        String file = dir + File.separator + "temp.png";
        File tempFile = new File(file);
        try {
            tempFile.createNewFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }


        return Uri.fromFile(tempFile);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissionsList, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissionsList, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
        switch (requestCode) {
            case PERMISSION_ALL: {
                if (grantResults.length > 0) {
                    boolean flag = true;
                    for (int i = 0; i < permissionsList.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        imagePicker.choosePicture(true /*show camera intents*/);
                    }
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode, requestCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);

            upload(resultUri);

        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Log.e("cropError", "OnActivityResult: " + cropError.getMessage());
        }
    }


    void upload(Uri uri) {
        mkLoader.setVisibility(View.VISIBLE);
        ref = storageReference.child(System.currentTimeMillis() + ".png");
        imagePath = storageReference.child(System.currentTimeMillis() + ".png");
        ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        mkLoader.setVisibility(View.GONE);
                        imageView.setImageURI(null);
                        Picasso.get().load(uri).into(imageView);
                        Log.e("url", " " + uri);
                        Toast.makeText(Start331AllResidential_Edit.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                        images++;
                        updateCounterUpdateInDatabase();
                        imagecounter_From_Database();
                        String link = uri.toString();
                        addIntoDataabase(link);

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("upload Image", "onFailure: " + e.getMessage());
            }
        });
    }

    private void updateCounterUpdateInDatabase() {
        DatabaseReference db = databaseReference.child(propertyType).child(propertyId);
        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("imageCount", images);
        db.updateChildren(updates);

    }

    private void addIntoDataabase(String link) {
        image = new Image();
        image.setImageAddress(link);
        final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("PropertyTable")
                .child(propertyType).child(propertyId);
        //   Image im = new Image(image);
        Random rand = new Random();
        int limit = 50000;
        final int no = rand.nextInt(limit);
        final String keyId = reference1.push().getKey();

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                reference1.child("images").child(keyId + "" + no).setValue(image);
//                reference1.child("images").child(String.valueOf(images)).setValue(image);
                Toast.makeText(Start331AllResidential_Edit.this, "added in database", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Start331AllResidential_Edit.this, "database error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void buttonClick11() {
        try {
            DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
            Map<String, Object> update = new HashMap<String, Object>();
            update.put("park", park);
            update.put("parking", parking);
            update.put("lift", lift);
            update.put("gym", gym);
            update.put("powerBackup", powerBackup);
            update.put("gasPipeline", gasPipeline);
            update.put("clubHouse", clubHouse);
            update.put("swimPool", swimPool);
            db1.updateChildren(update);
            Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
            UpdateActivity();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void buttonClick10() {
        try {
            electricityAvailability = ElectricityAvailability.getText().toString().trim();
            overlooking = OverLooking.getText().toString().trim();
            water = Water.getText().toString().trim();
            roadWidth = RoadWidth.getText().toString().trim();
            if (facing.equals("Select Facing")) {
                Toast.makeText(this, "Please Select Property Facing", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if ("Plot".equals(propertySubType) || "Commercial_Land".equals(propertySubType) || "Agriculture_Land".equals(propertySubType)
                        || "Industrial_Land".equals(propertySubType)) {


                    DatabaseReference db = reference.child(propertyType).child(propertyId);
                    Map<String, Object> updates = new HashMap<String, Object>();
                    updates.put("roadWidth", roadWidth);
                    db.updateChildren(updates);

                    DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                    Map<String, Object> update = new HashMap<String, Object>();
                    update.put("electricityAvailability", electricityAvailability);
                    update.put("overLooking", overlooking);
                    update.put("water", water);
                    update.put("facing", facing);
                    db1.updateChildren(update);
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                    UpdateActivity();
                } else {
                    flooring = Flooring.getText().toString().trim();
                    DatabaseReference db = reference.child(propertyType).child(propertyId);
                    Map<String, Object> updates = new HashMap<String, Object>();
                    updates.put("roadWidth", roadWidth);
                    updates.put("furnished", furnish);
                    db.updateChildren(updates);

                    DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                    Map<String, Object> update = new HashMap<String, Object>();
                    update.put("flooring", flooring);
                    update.put("electricityAvailability", electricityAvailability);
                    update.put("overLooking", overlooking);
                    update.put("water", water);
                    update.put("facing", facing);
                    db1.updateChildren(update);
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                    UpdateActivity();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void UpdateActivity() {

    }

    private void buttonClick9() {
        try {
            if (Location.getText().toString().length() <= 5 ||
                    Landmark.getText().toString().length() == 0) {
                Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
            } else {
                location = Location.getText().toString().trim();
                landmark = Landmark.getText().toString().trim();
                DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                Map<String, Object> update = new HashMap<String, Object>();
                update.put("location", location);
                update.put("landmark", landmark);
                db1.updateChildren(update);
                Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                UpdateActivity();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void buttonClick8() {
        try {
            if ("Commercial_Land".equals(propertySubType) || "Agriculture_Land".equals(propertySubType) || "Industrial_Land".equals(propertySubType)) {
                if (TextUtils.isEmpty(boundary_wall) || TextUtils.isEmpty(construction_done)) {
                    Toast.makeText(this, "Please Select option", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    if (construction_done.equals("Yes")) {
                        construction = Construction.getText().toString().trim();
                    } else {
                        construction = "";
                    }
                    DatabaseReference db = reference.child(propertyType).child(propertyId);
                    Map<String, Object> updates = new HashMap<String, Object>();
                    updates.put("construction_done", construction_done);
                    updates.put("boundary_wall", boundary_wall);
                    db.updateChildren(updates);

                    DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                    Map<String, Object> update = new HashMap<String, Object>();
                    update.put("construction", construction);
                    db1.updateChildren(update);
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                }
            } else if ("Plot".equals(propertySubType)) {
                if (TextUtils.isEmpty(boundary_wall) || TextUtils.isEmpty(gated_colony) || TextUtils.isEmpty(construction_done)) {
                    Toast.makeText(this, "Please Select option", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    if (construction_done.equals("Yes")) {
                        construction = Construction.getText().toString().trim();
                    } else {
                        construction = "";
                    }
                    DatabaseReference db = reference.child(propertyType).child(propertyId);
                    Map<String, Object> updates = new HashMap<String, Object>();
                    updates.put("construction_done", construction_done);
                    updates.put("gated_colony", gated_colony);
                    updates.put("boundary_wall", boundary_wall);
                    db.updateChildren(updates);

                    DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                    Map<String, Object> update = new HashMap<String, Object>();
                    update.put("construction", construction);
                    db1.updateChildren(update);
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                    UpdateActivity();
                }
            } else if ("office".equals(propertySubType) || "IT_Park".equals(propertySubType) || "Shop".equals(propertySubType)
                    || "Showroom".equals(propertySubType)) {

                if (TextUtils.isEmpty(carparking) || TextUtils.isEmpty(cornerShop) || TextUtils.isEmpty(personal_washroom) || TextUtils.isEmpty(cafateria)) {
                    Toast.makeText(this, "Please Select option", Toast.LENGTH_LONG).show();
                } else {
                    if (carparking.equals("Yes")) {
                        if (openParking.equals("Select") || closeParking.equals("Select")) {
                            Toast.makeText(this, "Select No.", Toast.LENGTH_LONG).show();
                        }
                    }
                    DatabaseReference db = reference.child(propertyType).child(propertyId);
                    Map<String, Object> updates = new HashMap<String, Object>();
                    updates.put("cornerShop", cornerShop);
                    updates.put("personal_washroom", personal_washroom);
                    updates.put("cafateria", cafateria);
                    db.updateChildren(updates);

                    DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                    Map<String, Object> update = new HashMap<String, Object>();
                    update.put("carparking", carparking);
                    update.put("openParking", openParking);
                    update.put("closeParking", closeParking);
                    db1.updateChildren(update);
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                    UpdateActivity();
                }
            } else {
                if (carparking.equals("Yes")) {
                    if (openParking.equals("Select") || closeParking.equals("Select")) {
                        Toast.makeText(this, "Select No.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                    Map<String, Object> update = new HashMap<String, Object>();
                    update.put("carparking", carparking);
                    update.put("openParking", openParking);
                    update.put("closeParking", closeParking);
                    db1.updateChildren(update);
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                    UpdateActivity();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void buttonClick7() {
        try {
            if (TextUtils.isEmpty(lock_in_periodString) || lock_in_periodString.equals("Select")) {
                Toast.makeText(this, "Please Select Lock in Periods", Toast.LENGTH_LONG).show();
                return;
            } else {
                DatabaseReference db = reference.child(propertyType).child(propertyId);
                Map<String, Object> updates = new HashMap<String, Object>();
                updates.put("lock_in_periodString", lock_in_periodString);
                db.updateChildren(updates);
                Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                UpdateActivity();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void buttonClick6() {
        try {
            if (Society.getText().toString().length() == 0) {
                Toast.makeText(this, "Society Name", Toast.LENGTH_LONG).show();
            } else {
                society = Society.getText().toString().trim();
                DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                Map<String, Object> updates = new HashMap<String, Object>();
                updates.put("society", society);
                db1.updateChildren(updates);
                Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                UpdateActivity();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void buttonClick5() {
        try {
            switch (propertySubType) {
                case "Plot":
                case "Commercial_Land":
                case "Agriculture_Land":
                case "Industrial_Land":
                    if (TextUtils.isEmpty(propertyStatus)) {
                        Toast.makeText(this, "Please Select Status of your Property", Toast.LENGTH_LONG).show();
                    } else {
                        DatabaseReference db = reference.child(propertyType).child(propertyId);
                        Map<String, Object> updates = new HashMap<String, Object>();
                        updates.put("property_status", propertyStatus);
                        db.updateChildren(updates);
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        UpdateActivity();
                    }
                    break;
                case "office":
                case "IT_Park":
                case "Shop":
                case "Showroom":
                case "Warehouse":
                case "Industrial_Building":
                case "Industrial_Shed":
                case "Coworking_Space":
                case "Flat/Apartment":
                case "Builder_Floor":
                case "Pentahouse":
                case "Studio_Apartment":
                case "House":
                case "Farm_House":
                case "Villa":
                    if (TextUtils.isEmpty(propertyStatus)) {
                        Toast.makeText(this, "Please Select Status of your Property", Toast.LENGTH_LONG).show();
                    } else if (TextUtils.isEmpty(ageOfconstruction) || TextUtils.isEmpty(availableFrom) || availableFrom.equals("Select Month")) {
                        Toast.makeText(this, "Please Select ", Toast.LENGTH_LONG).show();
                    } else {
                        DatabaseReference db = reference.child(propertyType).child(propertyId);
                        Map<String, Object> updates = new HashMap<String, Object>();
                        updates.put("property_status", propertyStatus);
                        updates.put("ageOfconstruction", ageOfconstruction);
                        updates.put("availablefrom", availableFrom);
                        db.updateChildren(updates);
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        UpdateActivity();
                    }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void buttonClick4() {
        try {
            switch (propertySubType) {
                case "Flat/Apartment":
                case "Builder_Floor":
                case "Pentahouse":
                case "Studio_Apartment":
                case "office":
                case "IT_Park":
                case "Coworking_Space":
                case "Shop":
                case "Showroom":
                case "Warehouse":
                case "Industrial_Building":
                case "Industrial_Shed":
                    if (TextUtils.isEmpty(totalfloor) || totalfloor.equals("Select")) {
                        Toast.makeText(this, "Please Select Floors", Toast.LENGTH_LONG).show();
                        return;
                    } else if (floor.equals("Select")) {
                        Toast.makeText(this, "Select Floor", Toast.LENGTH_LONG).show();
                    } else {
                        DatabaseReference db = reference.child(propertyType).child(propertyId);
                        Map<String, Object> updates = new HashMap<String, Object>();
                        updates.put("totalfloor", totalfloor);
                        updates.put("floorNo", floor);
                        db.updateChildren(updates);
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        UpdateActivity();

                    }
                    break;
                case "House":
                case "Farm_House":
                case "Villa":
                case "Plot":
                case "Commercial_Land":
                case "Agriculture_Land":
                case "Industrial_Land":
                    if (TextUtils.isEmpty(totalfloor) || totalfloor.equals("Select")) {
                        Toast.makeText(this, "no. of Floors Allowed for Construction", Toast.LENGTH_LONG).show();
                        return;
                    } else if (open_Sides.equals("Select")) {
                        Toast.makeText(this, "Select Open-Sides", Toast.LENGTH_LONG).show();
                    } else {
                        DatabaseReference db1 = reference.child(propertyType).child(propertyId);
                        Map<String, Object> updates2 = new HashMap<String, Object>();
                        updates2.put("totalfloor", totalfloor);
                        updates2.put("open_Sides", open_Sides);
                        db1.updateChildren(updates2);
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        UpdateActivity();
                    }

                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void buttonClick3() {
        try {
            switch (propertySubType) {
                case "Flat/Apartment":
                case "Builder_Floor":
                case "Pentahouse":
                case "House":
                case "Farm_House":
                case "Villa":
                    if (TextUtils.isEmpty(bedroom) || bedroom.equals("Select")) {
                        Toast.makeText(this, "Please Select Bedroom", Toast.LENGTH_LONG).show();
                        return;
                    } else if (bathroom.equals("Select")) {
                        Toast.makeText(this, "Please Select Bathroom", Toast.LENGTH_LONG).show();
                    } else if (balcony.equals("Select")) {
                        Toast.makeText(this, "Please Select Balcony", Toast.LENGTH_LONG).show();
                    } else {
                        DatabaseReference db = reference.child(propertyType).child(propertyId);
                        Map<String, Object> updates = new HashMap<String, Object>();
                        updates.put("bathroom", bathroom);
                        updates.put("bedroom", bedroom);
                        updates.put("balcony", balcony);
                        db.updateChildren(updates);
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        UpdateActivity();
                    }
                    break;
                case "Studio_Apartment":
                    if (bathroom.equals("Select")) {
                        Toast.makeText(this, "Please Select Bathroom", Toast.LENGTH_LONG).show();
                    } else if (balcony.equals("Select")) {
                        Toast.makeText(this, "Please Select Balcony", Toast.LENGTH_LONG).show();
                    } else {
                        DatabaseReference db = reference.child(propertyType).child(propertyId);
                        Map<String, Object> updates = new HashMap<String, Object>();
                        updates.put("bathroom", bathroom);
                        updates.put("balcony", balcony);
                        db.updateChildren(updates);
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        UpdateActivity();
                    }
                    break;

                case "office":
                case "IT_Park":
                case "Coworking_Space":
                    if (TextUtils.isEmpty(washroom) || washroom.equals("Select")) {
                        Toast.makeText(this, "Please Select Washroom", Toast.LENGTH_LONG).show();
                        return;
                    } else if (suitableFor.equals("Select")) {
                        Toast.makeText(this, "Suitable for minimum persons", Toast.LENGTH_LONG).show();
                    } else {
                        DatabaseReference db = reference.child(propertyType).child(propertyId);
                        Map<String, Object> updates = new HashMap<String, Object>();
                        updates.put("washroom", washroom);
                        db.updateChildren(updates);

                        DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                        Map<String, Object> updates1 = new HashMap<String, Object>();
                        updates1.put("suitableFor", suitableFor);
                        db1.updateChildren(updates1);
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        UpdateActivity();
                    }
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void buttonClick2() {
        try {
            if (CoverdArea.getText().toString().length() <= 2) {
                Toast.makeText(this, "Covered area is not empty or less than 2 number", Toast.LENGTH_LONG).show();
            } else {
                pricePer = PricePer.getText().toString().trim();
                coveredArea = CoverdArea.getText().toString().trim();
                if (propertySubType.equals("Plot") || propertySubType.equals("Commercial_Land") || propertySubType.equals("Agriculture_Land")
                        || propertySubType.equals("Industrial_Land")) {
                    if (PlotArea.getText().toString().length() <= 2) {
                        Toast.makeText(this, "Plot area is not empty or less than 2 number", Toast.LENGTH_LONG).show();
                    } else {
                        plotArea = PlotArea.getText().toString().trim();
                        plot_bredth = Plot_Breadth.getText().toString().trim();
                        plot_length = Plot_Length.getText().toString().trim();

                        DatabaseReference db = reference.child(propertyType).child(propertyId);
                        Map<String, Object> updates = new HashMap<String, Object>();
                        updates.put("plotArea", plotArea);
                        updates.put("plotAreaParameter", plotAreaParameter);
                        updates.put("plot_length", plot_length);
                        updates.put("plot_bredth", plot_bredth);
                        db.updateChildren(updates);

                        DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                        Map<String, Object> updates1 = new HashMap<String, Object>();
                        updates1.put("coverdArea", coveredArea);
                        updates1.put("pricePSS", pricePer);
                        updates1.put("covered_areaParameter", covered_areaParameter);
                        db1.updateChildren(updates1);
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        UpdateActivity();

                    }
                } else {
                    if (CarpetArea.getText().toString().length() <= 2) {
                        Toast.makeText(this, "Carpet area is not empty or less than 2 number", Toast.LENGTH_LONG).show();
                    } else {
                        carpetArea = CarpetArea.getText().toString().trim();
                        superArea = SuperArea.getText().toString().trim();

                        DatabaseReference db = reference.child(propertyType).child(propertyId);
                        Map<String, Object> updates = new HashMap<String, Object>();
                        updates.put("superArea", superArea);
                        updates.put("superAreaParameter", superAreaParameter);
                        updates.put("carpetArea", carpetArea);
                        updates.put("carpetAreaParameter", carpetAreaParameter);
                        db.updateChildren(updates);

                        DatabaseReference db1 = reference.child("additionalInfo").child(additionalId);
                        Map<String, Object> updates1 = new HashMap<String, Object>();
                        updates1.put("coverdArea", coveredArea);
                        updates1.put("pricePSS", pricePer);
                        updates1.put("covered_areaParameter", covered_areaParameter);
                        db1.updateChildren(updates1);
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        UpdateActivity();
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void check_Id_Existence() {

        DatabaseReference refer = reference.child("additionalInfo");
        Query query = refer.orderByChild("refId").equalTo(propertyId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        AdditioanlDetailsModel data = child.getValue(AdditioanlDetailsModel.class);
                        additionalId = data.getId();
                        facing = data.getFacing();
                        location = data.getLocation();
                        landmark = data.getLandmark();
                        coveredArea = data.getCoverdArea();
                        pricePer = data.getPricePSS();
                        society = data.getSociety();
                        flooring = data.getFlooring();
                        overlooking = data.getOverLooking();
                        water = data.getWater();
                        electricityAvailability = data.getElectricityAvailability();

                        suitableFor = data.getSuitableFor();
                        construction = data.getConstruction();
                        openParking = data.getOpenParking();
                        closeParking = data.getCloseParking();
                        carparking = data.getCarparking();
                        covered_areaParameter = data.getCovered_areaParameter();
                        covered_areaParameter1 = data.getCovered_areaParameter();
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
                    addData();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Snackbar.make(findViewById(R.id.content), "Please go back and come again",
                        Snackbar.LENGTH_SHORT).show();
                Toast.makeText(Start331AllResidential_Edit.this, "database error " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addData() {
        String keyId = reference.push().getKey();
        keyId = keyId.concat("ID" + propertyId);
        final DatabaseReference ref = reference.child("additionalInfo");
        final String finalKeyId = keyId;
        additionalId = finalKeyId;
        detailsModel.setId(finalKeyId);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setDataValue();
                ref.child(finalKeyId).setValue(detailsModel);
                Toast.makeText(Start331AllResidential_Edit.this, "Id generated", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Start331AllResidential_Edit.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                Snackbar.make(findViewById(R.id.content), databaseError.getMessage(),
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void setDataValue() {
        try {
            detailsModel.setRefId(propertyId);
            detailsModel.setFacing("");
            detailsModel.setCoverdArea("");
            detailsModel.setFlooring("");
            detailsModel.setOverLooking("");
            detailsModel.setSociety("");
            detailsModel.setWater("");
            detailsModel.setPricePSS("");
            detailsModel.setSuitableFor("");
            detailsModel.setCarparking("");
            detailsModel.setOpenParking("");
            detailsModel.setCloseParking("");
            detailsModel.setConstruction("");
            detailsModel.setLocation("");
            detailsModel.setElectricityAvailability("");
            detailsModel.setParking("");
            detailsModel.setClubHouse("");
            detailsModel.setGasPipeline("");
            detailsModel.setGym("");
            detailsModel.setLift("");
            detailsModel.setCovered_areaParameter("");
            detailsModel.setPowerBackup("");
            detailsModel.setSwimPool("");
            detailsModel.setPark("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parametersSpinner() {
        try {

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

            StatusSpinner2.setPrompt("Property Status");
            StatusSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    propertyStatus = adapter.getItemAtPosition(arg2).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


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


            Maintenance_Spinner.setPrompt("Time bond");
            Maintenance_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    maintenance_parameter = adapter.getItemAtPosition(arg2).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


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


            CoveredArea_Parameter.setPrompt("Covered Area Parameter");
            CoveredArea_Parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    covered_areaParameter = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            BedRoomSpinner.setPrompt("no. of Bedroom");
            BedRoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    bedroom = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            BathRoomSpinner.setPrompt("no. of Bathroom");
            BathRoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    bathroom = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            BalconySpinner.setPrompt("no. of Balcony");
            BalconySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    balcony = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            WashRoomSpinner.setPrompt("no. of Washroom");
            WashRoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    washroom = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            SuitableForSpinner.setPrompt("Suitable for minimum persons");
            SuitableForSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    suitableFor = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            FacingSpinner.setPrompt("Select Property Facing");
            FacingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    facing = adapter.getItemAtPosition(arg2).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            TotalFloorSpinner.setPrompt("Total Floors");
            TotalFloorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    totalfloor = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            FloorNoSpinner.setPrompt("no. of Floors");
            FloorNoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    floor = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            Open_SidesSpinner.setPrompt("Property Open Sides");
            Open_SidesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    open_Sides = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            AgeofConstSpinner.setPrompt("Age of Construction");
            AgeofConstSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    ageOfconstruction = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            availablefromMONTH();


            Lock_in_periodSpinner.setPrompt("Age of Construction");
            Lock_in_periodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    lock_in_periodString = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            OpenParkingSpinner.setPrompt("no. of Open Parking");
            OpenParkingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    openParking = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            CloseParkingSpinner.setPrompt("no. of Covered Parking");
            CloseParkingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    closeParking = adapter.getItemAtPosition(arg2).toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void availablefromMONTH() {
        ArrayList<String> monthList = new ArrayList<>();

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String string, str2;

        int x = year;
        int y = 0;
        monthList.add("Select Month");
        for (int i = x; i < x + 15; i++) {
            for (int j = y, z = month - 1; j < 12; j++, z++) {
                try {
                    if (!(i == year)) {
                        string = monthName[j];
                        //   System.out.println("Current month: "+string +"/"+i);
                        monthList.add(" " + string + "/" + i);
                    }
                    if (i == year) {
                        str2 = monthName[z];
                        //   System.out.println("Current month: "+str2 +"/"+i);
                        monthList.add(" " + str2 + "/" + i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AvailableFromSpinner.setAdapter(arrayAdapter);
        AvailableFromSpinner.setPrompt("Available From");
        AvailableFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                availableFrom = adapter.getItemAtPosition(arg2).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    private void ArrowBtnView() {
        ExpandableView.setVisibility(View.VISIBLE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void CallingBtn() {
        try {
            ArrowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        if (ExpandableView.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView, new AutoTransition());
                            ArrowBtnView();
                            try {
                                ExpectedAmount.setText(price);
                                if (propertyFor.equals("SELL")) {
                                    Token_Amount.setText(token_amount);
                                    Token_AmountHead.setVisibility(View.VISIBLE);
                                    Token_Amount.setVisibility(View.VISIBLE);
                               /*     if (price.equals(ExpectedAmount.getText().toString().trim()) &&
                                            token_amount.equals(Token_Amount.getText().toString().trim())) {
                                        flag = 0;
                                    } else {
                                        flag = 1;
                                    }*/
                                } else {
                                    Security_amount.setText(security);
                                    Maintenance.setText(security);
                                    if (!maintenance_parameter.isEmpty()) {
                                        Maintenance_Spinner.setSelection(getIndex(Maintenance_Spinner, maintenance_parameter));
                                    }
                                    Security_amountHead.setVisibility(View.VISIBLE);
                                    MaintenanceHead.setVisibility(View.VISIBLE);
                                    Security_amount.setVisibility(View.VISIBLE);
                                    Maintenance.setVisibility(View.VISIBLE);
                                    Maintenance_Spinner.setVisibility(View.VISIBLE);
                                  /*  if (price.equals(ExpectedAmount.getText().toString().trim()) &&
                                            security.equals(Security_amount.getText().toString().trim()) &&
                                            maintenance.equals(Maintenance.getText().toString().trim()) &&
                                            maintenance_parameter.equals(maintenance_parameter1)) {
                                        flag = 0;
                                    } else {
                                        flag = 1;
                                    }*/
                                }
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                        } else {
                            TransitionManager.beginDelayedTransition(CardView, new AutoTransition());
                            ExpandableView.setVisibility(View.GONE);
                            ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }

                }
            });
            ArrowBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        if (ExpandableView2.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView2, new AutoTransition());
                            ArrowBtn2View();
                            CoverdArea.setText(coveredArea);
                            if (!covered_areaParameter.isEmpty()) {
                                CoveredArea_Parameter.setSelection(getIndex(CoveredArea_Parameter, covered_areaParameter));
                            }
                            PricePer.setText(pricePer);


                            if (propertySubType.equals("Plot") || propertySubType.equals("Commercial_Land") || propertySubType.equals("Agriculture_Land")
                                    || propertySubType.equals("Industrial_Land")) {
                                PlotArea.setVisibility(View.VISIBLE);
                                PlotArea_Parameter.setVisibility(View.VISIBLE);
                                Plot_Length.setVisibility(View.VISIBLE);
                                Plot_LengthHead.setVisibility(View.VISIBLE);
                                Plot_Breadth.setVisibility(View.VISIBLE);
                                Plot_bredthHead.setVisibility(View.VISIBLE);
                                PlotAreaHead.setVisibility(View.VISIBLE);

                                PlotArea.setText(plotArea);
                                if (!plotAreaParameter.isEmpty()) {
                                    PlotArea_Parameter.setSelection(getIndex(PlotArea_Parameter, plotAreaParameter));
                                }
                                Plot_Breadth.setText(plot_bredth);
                                Plot_Length.setText(plot_length);

                                    /*if (coveredArea.equals(CoverdArea.getText().toString().trim()) &&
                                            covered_areaParameter.equals(covered_areaParameter1) && pricePer.equals(PricePer.getText().toString().trim())
                                            && plotArea.equals(PlotArea.getText().toString().trim()) && plot_bredth.equals(Plot_Breadth.getText().toString().trim()) &&
                                    plot_length.equals(Plot_Length.getText().toString().trim()) && plotAreaParameter.equals(plotAreaParameter1)){
                                        Log.e("2", coveredArea+" "+covered_areaParameter+" "+ pricePer+" "+ plotArea+" "+
                                                plot_length+" "+plot_bredth+" "+ plotAreaParameter);
                                        flag=0;
                                    }else {
                                        flag=1;
                                    }*/
                            } else {
                                SuperArea.setText(superArea);
                                if (!superAreaParameter.isEmpty()) {
                                    SuperArea_Parameter.setSelection(getIndex(SuperArea_Parameter, superAreaParameter));
                                }
                                CarpetArea.setText(carpetArea);
                                if (!carpetAreaParameter.isEmpty()) {
                                    Carpet_Parameter.setSelection(getIndex(Carpet_Parameter, carpetAreaParameter));
                                }
                                SuperArea.setVisibility(View.VISIBLE);
                                SuperAreaHead.setVisibility(View.VISIBLE);
                                CarpetArea.setVisibility(View.VISIBLE);
                                CarpetAreaHead.setVisibility(View.VISIBLE);
                                SuperArea_Parameter.setVisibility(View.VISIBLE);
                                Carpet_Parameter.setVisibility(View.VISIBLE);
                                /*    if (coveredArea.equals(CoverdArea.getText().toString().trim()) &&
                                            covered_areaParameter.equals(covered_areaParameter1) && pricePer.equals(PricePer.getText().toString().trim())
                                            && carpetArea.equals(CarpetArea.getText().toString().trim()) && superArea.equals(SuperArea.getText().toString().trim()) &&
                                            carpetAreaParameter.equals(carpetAreaParameter1) && superAreaParameter.equals(superAreaParameter1)){
                                        flag=0;
                                    }else {
                                        flag=1;
                                    }*/
                            }
                        } else {
                            TransitionManager.beginDelayedTransition(CardView2, new AutoTransition());
                            ExpandableView2.setVisibility(View.GONE);
                            ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
            });
            ArrowBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView3.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView3, new AutoTransition());
                            ArrowBtn3View();
                            if (!bedroom.isEmpty()) {
                                BedRoomSpinner.setSelection(getIndex(BedRoomSpinner, bedroom));
                            }
                            if (!bathroom.isEmpty()) {
                                BathRoomSpinner.setSelection(getIndex(BathRoomSpinner, bathroom));
                            }
                            if (!balcony.isEmpty()) {
                                BalconySpinner.setSelection(getIndex(BalconySpinner, balcony));
                            }
                            if (!washroom.isEmpty()) {
                                WashRoomSpinner.setSelection(getIndex(WashRoomSpinner, washroom));
                            }
                            if (!suitableFor.isEmpty()) {
                                SuitableForSpinner.setSelection(getIndex(SuitableForSpinner, suitableFor));
                            }
                            switch (propertySubType) {
                                case "Flat/Apartment":
                                case "Builder_Floor":
                                case "Pentahouse":
                                case "House":
                                case "Farm_House":
                                case "Villa":
                                    BedRoomSpinner.setVisibility(View.VISIBLE);
                                    BedroomHead.setVisibility(View.VISIBLE);
                                    BathroomHead.setVisibility(View.VISIBLE);
                                    BathRoomSpinner.setVisibility(View.VISIBLE);
                                    BalconyHead.setVisibility(View.VISIBLE);
                                    BalconySpinner.setVisibility(View.VISIBLE);
                                    break;
                                case "Studio_Apartment":
                                    BathroomHead.setVisibility(View.VISIBLE);
                                    BathRoomSpinner.setVisibility(View.VISIBLE);
                                    BalconyHead.setVisibility(View.VISIBLE);
                                    BalconySpinner.setVisibility(View.VISIBLE);
                                    break;

                                case "office":
                                case "IT_Park":
                                case "Coworking_Space":
                                    WashRoomHead.setVisibility(View.VISIBLE);
                                    WashRoomSpinner.setVisibility(View.VISIBLE);
                                    SuitableForHead.setVisibility(View.VISIBLE);
                                    SuitableForSpinner.setVisibility(View.VISIBLE);
                                    break;
                            }
                        } else {
                            TransitionManager.beginDelayedTransition(CardView3, new AutoTransition());
                            ExpandableView3.setVisibility(View.GONE);
                            ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
            });
            ArrowBtn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView4.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView4, new AutoTransition());
                            ArrowBtn4View();
                            if (!totalfloor.isEmpty()) {
                                TotalFloorSpinner.setSelection(getIndex(TotalFloorSpinner, totalfloor));
                            }
                            if (!floor.isEmpty()) {
                                FloorNoSpinner.setSelection(getIndex(FloorNoSpinner, floor));
                            }
                            if (!open_Sides.isEmpty()) {
                                Open_SidesSpinner.setSelection(getIndex(Open_SidesSpinner, open_Sides));
                            }


                            switch (propertySubType) {
                                case "Flat/Apartment":
                                case "Builder_Floor":
                                case "Pentahouse":
                                case "Studio_Apartment":
                                case "office":
                                case "IT_Park":
                                case "Coworking_Space":
                                case "Shop":
                                case "Showroom":
                                case "Warehouse":
                                case "Industrial_Building":
                                case "Industrial_Shed":
                                    FloorNoHead.setVisibility(View.VISIBLE);
                                    FloorNoSpinner.setVisibility(View.VISIBLE);

                                    break;
                                case "House":
                                case "Farm_House":
                                case "Villa":
                                    TotalFloorSpinner.setPrompt("Total Floors");
                                    Open_SidesHead.setVisibility(View.VISIBLE);
                                    Open_SidesSpinner.setVisibility(View.VISIBLE);
                                    break;
                                case "Plot":
                                case "Commercial_Land":
                                case "Agriculture_Land":
                                case "Industrial_Land":
                                    TotalFloorSpinner.setPrompt("No. of Floors Allowed for Construction");
                                    Open_SidesHead.setVisibility(View.VISIBLE);
                                    Open_SidesSpinner.setVisibility(View.VISIBLE);
                                    break;
                            }
                        } else {
                            TransitionManager.beginDelayedTransition(CardView4, new AutoTransition());
                            ExpandableView4.setVisibility(View.GONE);
                            ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
            ArrowBtn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView5.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView5, new AutoTransition());

                            ArrowBtn5View();

                            if (!ageOfconstruction.isEmpty()) {
                                AgeofConstSpinner.setSelection(getIndex(AgeofConstSpinner, ageOfconstruction));
                            }
                            if (!availableFrom.isEmpty()) {
                                AvailableFromSpinner.setSelection(getIndex(AvailableFromSpinner, availableFrom));
                            }

                            if (propertyFor.equals("SELL")) {
                                if (!propertyStatus.isEmpty()) {
                                    StatusSpinner.setSelection(getIndex(StatusSpinner, propertyStatus));
                                }
                                Status_Head.setVisibility(View.VISIBLE);
                                StatusSpinner.setVisibility(View.VISIBLE);
                                if ("Plot".equals(propertySubType) || "Commercial_Land".equals(propertySubType) || "Agriculture_Land".equals(propertySubType) || "Industrial_Land".equals(propertySubType)) {

                                } else if (!ageOfconstruction.isEmpty()) {
                                    AgeofConstSpinner.setVisibility(View.VISIBLE);
                                    AgeofConstHead.setVisibility(View.VISIBLE);

                                } else {
                                    AvailableFromSpinner.setVisibility(View.VISIBLE);
                                    AvailableFromHead.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!propertyStatus.isEmpty()) {
                                    StatusSpinner2.setSelection(getIndex(StatusSpinner2, propertyStatus));
                                }
                                Status2_Head.setVisibility(View.VISIBLE);
                                StatusSpinner2.setVisibility(View.VISIBLE);
                                if ("Plot".equals(propertySubType) || "Commercial_Land".equals(propertySubType) || "Agriculture_Land".equals(propertySubType) || "Industrial_Land".equals(propertySubType)) {

                                } else if (!ageOfconstruction.isEmpty()) {
                                    AgeofConstSpinner.setVisibility(View.VISIBLE);
                                    AgeofConstHead.setVisibility(View.VISIBLE);

                                } else {
                                    AvailableFromSpinner.setVisibility(View.VISIBLE);
                                    AvailableFromHead.setVisibility(View.VISIBLE);
                                }
                            }
                        } else {
                            TransitionManager.beginDelayedTransition(CardView5, new AutoTransition());
                            ExpandableView5.setVisibility(View.GONE);
                            ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
            ArrowBtn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView6.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView6, new AutoTransition());
                            ArrowBtn6View();
                            if (!society.isEmpty()) {
                                Society.setText(society);
                            }

                        } else {
                            TransitionManager.beginDelayedTransition(CardView6, new AutoTransition());
                            ExpandableView6.setVisibility(View.GONE);
                            ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
            ArrowBtn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView7.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView7, new AutoTransition());
                            ArrowBtn7View();
                            if (!lock_in_periodString.isEmpty()) {
                                Lock_in_periodSpinner.setSelection(getIndex(Lock_in_periodSpinner, lock_in_periodString));
                            }
                        } else {
                            TransitionManager.beginDelayedTransition(CardView7, new AutoTransition());
                            ExpandableView7.setVisibility(View.GONE);
                            ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
            ArrowBtn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView8.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView8, new AutoTransition());
                            ArrowBtn8View();
                            checkOptions();
                            if (!construction.isEmpty()) {
                                Construction.setText(construction);
                            }

                            if ("Commercial_Land".equals(propertySubType) || "Agriculture_Land".equals(propertySubType)
                                    || "Industrial_Land".equals(propertySubType)) {
                                AnyConstHead.setVisibility(View.VISIBLE);
                                horizontalScrollView2.setVisibility(View.VISIBLE);
                                BoundaryHead.setVisibility(View.VISIBLE);
                                horizontalScrollView3.setVisibility(View.VISIBLE);
                            } else if ("Plot".equals(propertySubType)) {
                                GatedColonyHead.setVisibility(View.VISIBLE);
                                horizontalScrollView4.setVisibility(View.VISIBLE);
                                AnyConstHead.setVisibility(View.VISIBLE);
                                horizontalScrollView2.setVisibility(View.VISIBLE);
                                BoundaryHead.setVisibility(View.VISIBLE);
                                horizontalScrollView3.setVisibility(View.VISIBLE);

                            } else if ("office".equals(propertySubType) || "IT_Park".equals(propertySubType) || "Shop".equals(propertySubType)
                                    || "Showroom".equals(propertySubType)) {
                                CarparkingHead.setVisibility(View.VISIBLE);
                                horizontalScrollView1.setVisibility(View.VISIBLE);
                                CafeteriaHead.setVisibility(View.VISIBLE);
                                horizontalScrollView5.setVisibility(View.VISIBLE);
                                CornerShopHead.setVisibility(View.VISIBLE);
                                horizontalScrollView6.setVisibility(View.VISIBLE);
                                PersonalWashroomHead.setVisibility(View.VISIBLE);
                                horizontalScrollView7.setVisibility(View.VISIBLE);
                                CafeteriaHead.setVisibility(View.VISIBLE);
                            } else {
                                CarparkingHead.setVisibility(View.VISIBLE);
                                horizontalScrollView1.setVisibility(View.VISIBLE);
                            }
                        } else {
                            TransitionManager.beginDelayedTransition(CardView8, new AutoTransition());
                            ExpandableView8.setVisibility(View.GONE);
                            ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
            ArrowBtn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView9.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView9, new AutoTransition());
                            ArrowBtn9View();
                            Locality.setText(locality);
                            City.setText(city);
                            if (!location.isEmpty()) {
                                Location.setText(location);
                            }
                            if (!landmark.isEmpty()) {
                                Landmark.setText(landmark);
                            }

                        } else {
                            TransitionManager.beginDelayedTransition(CardView9, new AutoTransition());
                            ExpandableView9.setVisibility(View.GONE);
                            ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
            ArrowBtn10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView10.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView10, new AutoTransition());
                            ArrowBtn10View();
                            if (!roadWidth.isEmpty()) {
                                RoadWidth.setText(roadWidth);
                            }
                            if (!facing.isEmpty()) {
                                FacingSpinner.setSelection(getIndex(FacingSpinner, facing));
                            }
                            if (!flooring.isEmpty()) {
                                Flooring.setText(flooring);
                            }
                            if (!overlooking.isEmpty()) {
                                OverLooking.setText(overlooking);
                            }
                            if (!water.isEmpty()) {
                                Water.setText(water);
                            }
                            if (!electricityAvailability.isEmpty()) {
                                ElectricityAvailability.setText(electricityAvailability);
                            }

                            if ("Plot".equals(propertySubType) || "Commercial_Land".equals(propertySubType) || "Agriculture_Land".equals(propertySubType)
                                    || "Industrial_Land".equals(propertySubType)) {
                                FlooringHead.setVisibility(View.GONE);
                                Flooring.setVisibility(View.GONE);
                                horizontalScrollView.setVisibility(View.GONE);
                            } else {
                                FlooringHead.setVisibility(View.VISIBLE);
                                Flooring.setVisibility(View.VISIBLE);
                                horizontalScrollView.setVisibility(View.VISIBLE);
                                if (furnish.equals("Unfurnished")) {
                                    Unfur.setChecked(true);
                                } else if (furnish.equals("Semi-furnished")) {
                                    Semi_fer.setChecked(true);
                                } else {
                                    Fully_fer.setChecked(true);
                                }

                            }
                        } else {
                            TransitionManager.beginDelayedTransition(CardView10, new AutoTransition());
                            ExpandableView10.setVisibility(View.GONE);
                            ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
            ArrowBtn11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView11.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView11, new AutoTransition());
                            ArrowBtn11View();
                            horizontalScrollView.setVisibility(View.VISIBLE);
                            checkOptionsforAminieties();

                        } else {
                            TransitionManager.beginDelayedTransition(CardView11, new AutoTransition());
                            ExpandableView11.setVisibility(View.GONE);
                            ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
            ArrowBtn12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ExpandableView12.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(CardView12, new AutoTransition());
                            ArrowBtn12View();

                        } else {
                            TransitionManager.beginDelayedTransition(CardView12, new AutoTransition());
                            ExpandableView12.setVisibility(View.GONE);
                            ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void checkOptions() {
        if (cafateria.equals("Dry")) {
            Cafeteria_Yes.setChecked(true);
        } else if (cafateria.equals("Wet")) {
            Cafeteria_No.setChecked(true);
        } else {
            Cafeteria_NotAvailable.setChecked(true);
        }
        if (cornerShop.equals("Yes")) {
            CornerShop_Yes.setChecked(true);
        } else {
            CornerShop_No.setChecked(true);
        }
        if (gated_colony.equals("Yes")) {
            GatedColony_Yes.setChecked(true);
        } else {
            GatedColony_No.setChecked(true);
        }
        if (personal_washroom.equals("Yes")) {
            PersonalWashroomYes.setChecked(true);
        } else {
            PersonalWashroomYes.setChecked(true);
        }
        if (boundary_wall.equals("Yes")) {
            Bounadry_Yes.setChecked(true);
        } else {
            Bounadry_No.setChecked(true);
        }
        if (carparking.equals("Yes")) {
            Radio_Yes.setChecked(true);
            OpenHead.setVisibility(View.VISIBLE);
            CloseHead.setVisibility(View.VISIBLE);
            OpenParkingSpinner.setVisibility(View.VISIBLE);
            CloseParkingSpinner.setVisibility(View.VISIBLE);
            if (!openParking.isEmpty()) {
                OpenParkingSpinner.setSelection(getIndex(OpenParkingSpinner, openParking));
            }
            if (!closeParking.isEmpty()) {
                CloseParkingSpinner.setSelection(getIndex(CloseParkingSpinner, closeParking));
            }
        } else {
            Radio_No.setChecked(true);
            OpenHead.setVisibility(View.GONE);
            CloseHead.setVisibility(View.GONE);
            OpenParkingSpinner.setVisibility(View.GONE);
            CloseParkingSpinner.setVisibility(View.GONE);
        }
        if (construction_done.equals("Yes")) {
            AnyConst_Yes.setChecked(true);
            Construction.setVisibility(View.VISIBLE);
        } else {
            AnyConst_No.setChecked(true);
            Construction.setVisibility(View.GONE);
        }
    }

    private void ArrowBtn12View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.VISIBLE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void ArrowBtn11View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.VISIBLE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void ArrowBtn10View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.VISIBLE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void ArrowBtn9View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.VISIBLE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void ArrowBtn8View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.VISIBLE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void ArrowBtn7View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.VISIBLE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void ArrowBtn6View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.VISIBLE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void ArrowBtn5View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView5.setVisibility(View.VISIBLE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void ArrowBtn4View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.VISIBLE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void ArrowBtn3View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.GONE);
        ExpandableView3.setVisibility(View.VISIBLE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
    }

    private void buttonClick() {
        if (ExpectedAmount.getText().toString().length() == 0) {
            Toast.makeText(this, "Price is mandatory", Toast.LENGTH_LONG).show();
        } else {
            price = ExpectedAmount.getText().toString().trim();
            token_amount = Token_Amount.getText().toString().trim();
            security = Security_amount.getText().toString().trim();
            maintenance = Maintenance.getText().toString().trim();

            DatabaseReference db = reference.child(propertyType).child(propertyId);
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("price", price);
            updates.put("token_amount", token_amount);
            updates.put("security", security);
            updates.put("maintenance", maintenance);
            updates.put("maintenance_parameter", maintenance_parameter);
            db.updateChildren(updates);
            Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();

        }
    }

    private void ArrowBtn2View() {
        ExpandableView.setVisibility(View.GONE);
        ExpandableView2.setVisibility(View.VISIBLE);
        ExpandableView3.setVisibility(View.GONE);
        ExpandableView4.setVisibility(View.GONE);
        ExpandableView5.setVisibility(View.GONE);
        ExpandableView6.setVisibility(View.GONE);
        ExpandableView7.setVisibility(View.GONE);
        ExpandableView8.setVisibility(View.GONE);
        ExpandableView9.setVisibility(View.GONE);
        ExpandableView10.setVisibility(View.GONE);
        ExpandableView11.setVisibility(View.GONE);
        ExpandableView12.setVisibility(View.GONE);
        ArrowBtn2.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        ArrowBtn.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn3.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn4.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn5.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn6.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn7.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn8.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn9.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn10.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn11.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        ArrowBtn12.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);

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

    private void bindViews() {
        try {
            ExpectedAmount = findViewById(R.id.expected_price);
            Token_Amount = findViewById(R.id.token_amount);
            Token_AmountHead = findViewById(R.id.textInputLayout6);
            Security_amount = findViewById(R.id.security_amount);
            Security_amountHead = findViewById(R.id.abc);
            Maintenance = findViewById(R.id.maintenance);
            MaintenanceHead = findViewById(R.id.bcd);
            Maintenance_Spinner = findViewById(R.id.charge);

            CoverdArea = findViewById(R.id.coveredArea);
            PricePer = findViewById(R.id.price_per);
            CoveredArea_Parameter = findViewById(R.id.coveredAreaParameter);
            CarpetAreaHead = findViewById(R.id.area1);
            CarpetArea = findViewById(R.id.areaEdit1);
            Carpet_Parameter = findViewById(R.id.areaSpin1);
            SuperAreaHead = findViewById(R.id.area2);
            SuperArea = findViewById(R.id.areaEdit2);
            SuperArea_Parameter = findViewById(R.id.areaSpin2);
            PlotArea = findViewById(R.id.plotArea);
            PlotAreaHead = findViewById(R.id.plotareaHead);
            PlotArea_Parameter = findViewById(R.id.plotareaParameter);
            Plot_Length = findViewById(R.id.plot_length);
            Plot_LengthHead = findViewById(R.id.length);
            Plot_Breadth = findViewById(R.id.plot_breadth);
            Plot_bredthHead = findViewById(R.id.bredth);

            BedRoomSpinner = findViewById(R.id.bedroom);
            BedroomHead = findViewById(R.id.textView21);
            BathroomHead = findViewById(R.id.textView22);
            BalconyHead = findViewById(R.id.textView23);
            WashRoomHead = findViewById(R.id.textView24);
            SuitableForHead = findViewById(R.id.textView25);
            BathRoomSpinner = findViewById(R.id.bathroom);
            BalconySpinner = findViewById(R.id.balcony);
            WashRoomSpinner = findViewById(R.id.washroom);
            SuitableForSpinner = findViewById(R.id.suitableFor);

            TotalFloorHead = findViewById(R.id.textv1);
            FloorNoHead = findViewById(R.id.floorNoHead);
            TotalFloorSpinner = findViewById(R.id.totalFloor);
            FloorNoSpinner = findViewById(R.id.floorNo);
            Open_SidesHead = findViewById(R.id.openSidesHead);
            Open_SidesSpinner = findViewById(R.id.openSides);

            Status_Head = findViewById(R.id.From1);
            Status2_Head = findViewById(R.id.From12);
            AgeofConstHead = findViewById(R.id.From2);
            AvailableFromHead = findViewById(R.id.From);
            AgeofConstSpinner = findViewById(R.id.ageOfConst);
            AvailableFromSpinner = findViewById(R.id.availabeSpinner);
            StatusSpinner = findViewById(R.id.statusSpinner);
            StatusSpinner2 = findViewById(R.id.statusSpinner2);

            SocietyHead = findViewById(R.id.t19);
            Society = findViewById(R.id.society);

            Lock_in_periodSpinner = findViewById(R.id.floorSpin3);

            RadioGroup1 = findViewById(R.id.radioGroup);
            RadioGroup_AnyConst = findViewById(R.id.radioGroup2);
            RadioGroup_Boundary = findViewById(R.id.radioGroup3);
            RadioGroup_Gated = findViewById(R.id.radioGroup4);
            RadioGroup_Cafeteria = findViewById(R.id.radioGroup5);
            RadioGroup_CornerShop = findViewById(R.id.radioGroup6);
            RadioGroup_personalWashroom = findViewById(R.id.radioGroup7);
            RadioGroup_Furnish = findViewById(R.id.radiogroup);
            Radio_Yes = findViewById(R.id.yesBtn);
            Radio_No = findViewById(R.id.noBtn);
            CarparkingHead = findViewById(R.id.carparkingHead);
            AnyConst_Yes = findViewById(R.id.AnyConst_Yes);
            AnyConst_No = findViewById(R.id.AnyConst_No);
            CornerShop_Yes = findViewById(R.id.cornerShop_Yes);
            CornerShop_No = findViewById(R.id.cornerShop_No);
            Cafeteria_Yes = findViewById(R.id.cafeteria_Yes);
            Cafeteria_No = findViewById(R.id.cafeteria_No);
            Cafeteria_NotAvailable = findViewById(R.id.cafeteria_NotAvailable);
            GatedColony_Yes = findViewById(R.id.gatedColony_Yes);
            GatedColony_No = findViewById(R.id.gatedColony_No);
            Bounadry_Yes = findViewById(R.id.Bounadry_Yes);
            Bounadry_No = findViewById(R.id.Bounadry_No);
            PersonalWashroomYes = findViewById(R.id.personalWashroomYes);
            PersonalWashroomNo = findViewById(R.id.personalWashroomNo);
            Unfur = findViewById(R.id.unfurnished);
            Semi_fer = findViewById(R.id.semifurnished);
            Fully_fer = findViewById(R.id.fullyfurnished);
            OpenHead = findViewById(R.id.openHead);
            CloseHead = findViewById(R.id.closeHead);
            OpenParkingSpinner = findViewById(R.id.open);
            CloseParkingSpinner = findViewById(R.id.close);

            Locality = findViewById(R.id.locality);
            City = findViewById(R.id.city);
            LocationHead = findViewById(R.id.locationHead);
            Location = findViewById(R.id.location);
            Landmark = findViewById(R.id.landmark);
            LandmarkHead = findViewById(R.id.landmarkHead);

            RoadWidthHead = findViewById(R.id.road_widthHead);
            RoadWidth = findViewById(R.id.road_width);
            FacingSpinner = findViewById(R.id.facing);

            FacingHead = findViewById(R.id.propertyFacingHead);
            Flooring = findViewById(R.id.flooring);
            FlooringHead = findViewById(R.id.flooringHead);
            PersonalWashroomHead = findViewById(R.id.personalWashroomHead);
            GatedColonyHead = findViewById(R.id.gatedColonyHead);
            CafeteriaHead = findViewById(R.id.cafeteriaHead);
            CornerShopHead = findViewById(R.id.cornerShopHead);
            AnyConstHead = findViewById(R.id.anyConstHead);
            BoundaryHead = findViewById(R.id.boundaryWallHead);
            horizontalScrollView = findViewById(R.id.horizontalScrollView);
            horizontalScrollView1 = findViewById(R.id.horizontalScrollView1);
            horizontalScrollView2 = findViewById(R.id.horizontalScrollView2);
            horizontalScrollView3 = findViewById(R.id.horizontalScrollView3);
            horizontalScrollView4 = findViewById(R.id.horizontalScrollView4);
            horizontalScrollView5 = findViewById(R.id.horizontalScrollView5);
            horizontalScrollView6 = findViewById(R.id.horizontalScrollView6);
            horizontalScrollView7 = findViewById(R.id.horizontalScrollView7);
            horizontalScrollView8 = findViewById(R.id.horizontalScrollView8);


            OverLooking = findViewById(R.id.overlooking);
            Construction = findViewById(R.id.construction);
            OverlookingHead = findViewById(R.id.overlookingHead);
            Water = findViewById(R.id.waterAvailability);
            WaterHead = findViewById(R.id.waterHead);
            ElectricityAvailability = findViewById(R.id.electricityAvailability);
            ElectricHead = findViewById(R.id.electricHead);

            mkLoader = findViewById(R.id.loader);
            mkLoader.setVisibility(View.INVISIBLE);
            imageView = findViewById(R.id.imageView);
            Upload = findViewById(R.id.submit);

            Adddetails = findViewById(R.id.addDetails);
            Adddetails2 = findViewById(R.id.addDetails2);
            Adddetails3 = findViewById(R.id.addDetails3);
            Adddetails4 = findViewById(R.id.addDetails4);
            Adddetails5 = findViewById(R.id.addDetails5);
            Adddetails6 = findViewById(R.id.addDetails6);
            Adddetails7 = findViewById(R.id.addDetails7);
            Adddetails8 = findViewById(R.id.addDetails8);
            Adddetails9 = findViewById(R.id.addDetails9);
            Adddetails10 = findViewById(R.id.addDetails10);
            Adddetails11 = findViewById(R.id.addDetails11);


            ExpandableView = findViewById(R.id.expandableView);
            ExpandableView2 = findViewById(R.id.expandableView2);
            ExpandableView3 = findViewById(R.id.expandableView3);
            ExpandableView4 = findViewById(R.id.expandableView4);
            ExpandableView5 = findViewById(R.id.expandableView5);
            ExpandableView6 = findViewById(R.id.expandableView6);
            ExpandableView7 = findViewById(R.id.expandableView7);
            ExpandableView8 = findViewById(R.id.expandableView8);
            ExpandableView9 = findViewById(R.id.expandableView9);
            ExpandableView10 = findViewById(R.id.expandableView10);
            ExpandableView11 = findViewById(R.id.expandableView11);
            ExpandableView12 = findViewById(R.id.expandableView12);

            ArrowBtn = findViewById(R.id.arrowBtn);
            ArrowBtn2 = findViewById(R.id.arrowBtn2);
            ArrowBtn3 = findViewById(R.id.arrowBtn3);
            ArrowBtn4 = findViewById(R.id.arrowBtn4);
            ArrowBtn5 = findViewById(R.id.arrowBtn5);
            ArrowBtn6 = findViewById(R.id.arrowBtn6);
            ArrowBtn7 = findViewById(R.id.arrowBtn7);
            ArrowBtn8 = findViewById(R.id.arrowBtn8);
            ArrowBtn9 = findViewById(R.id.arrowBtn9);
            ArrowBtn10 = findViewById(R.id.arrowBtn10);
            ArrowBtn11 = findViewById(R.id.arrowBtn11);
            ArrowBtn12 = findViewById(R.id.arrowBtn12);

            CardView = findViewById(R.id.cardView);
            CardView2 = findViewById(R.id.cardView2);
            CardView3 = findViewById(R.id.cardView3);
            CardView4 = findViewById(R.id.cardView4);
            CardView5 = findViewById(R.id.cardView5);
            CardView6 = findViewById(R.id.cardView6);
            CardView7 = findViewById(R.id.cardView7);
            CardView8 = findViewById(R.id.cardView8);
            CardView9 = findViewById(R.id.cardView9);
            CardView10 = findViewById(R.id.cardView10);
            CardView11 = findViewById(R.id.cardView11);
            CardView12 = findViewById(R.id.cardView12);
        } catch (NullPointerException e) {
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


    private void checkOptionsforAminieties() {
        try {
            CardView Gym, ClubHouse, Park, Parking, Lift, PowerBackup, GasPipeline, SwimPool;
            Gym = findViewById(R.id.gymnasium);
            ClubHouse = findViewById(R.id.clubhouse);
            Park = findViewById(R.id.park);
            Parking = findViewById(R.id.parking);
            Lift = findViewById(R.id.lift);
            PowerBackup = findViewById(R.id.powerBackup);
            GasPipeline = findViewById(R.id.gaspipeline);
            SwimPool = findViewById(R.id.swimmingpool);
            if (parking.equals("Yes")) {
                Parking.setBackgroundColor(Color.LTGRAY);
            }
            if (gym.equals("Yes")) {
                Gym.setBackgroundColor(Color.LTGRAY);
            }
            if (clubHouse.equals("Yes")) {
                ClubHouse.setBackgroundColor(Color.LTGRAY);
            }
            if (park.equals("Yes")) {
                Park.setBackgroundColor(Color.LTGRAY);
            }
            if (lift.equals("Yes")) {
                Lift.setBackgroundColor(Color.LTGRAY);
            }
            if (powerBackup.equals("Yes")) {
                PowerBackup.setBackgroundColor(Color.LTGRAY);
            }
            if (gasPipeline.equals("Yes")) {
                GasPipeline.setBackgroundColor(Color.LTGRAY);
            }
            if (swimPool.equals("Yes")) {
                SwimPool.setBackgroundColor(Color.LTGRAY);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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

    public void ViewImages(View view) {

        Intent intent = new Intent(Start331AllResidential_Edit.this, edit_image.class);
        Bundle bundle = new Bundle();
        bundle.putString("PropertyId", propertyId);
        bundle.putString("propertyType", propertyType);
        intent.putExtras(bundle);
        startActivity(intent);
     /*   try {
            final List<Image> pic = new ArrayList<>();
            final Dialog dialog = new Dialog(this, R.style.Dialog);

            // Include dialog.xml file
            dialog.setContentView(R.layout.image_dialog);
            // Set dialog title
            dialog.setTitle("Loaded Images");

            final SliderView sliderView = dialog.findViewById(R.id.imageSlider);

            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("PropertyTable")
                    .child(propertyType).child(propertyId).child("images");
            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            Image img = areaSnapshot.getValue(Image.class);
                            pic.add(img);
                        }
                        adapter = new UploadSliderAdapter(Start331AllResidential_Edit.this, pic);
                        sliderView.setSliderAdapter(adapter);
                        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                        sliderView.setIndicatorSelectedColor(Color.WHITE);
                        sliderView.setIndicatorUnselectedColor(Color.GRAY);
                        sliderView.setScrollTimeInSec(800); //set scroll delay in seconds :
                        sliderView.startAutoCycle();
                        //  adapter.deleteItem();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            dialog.show();
            sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
                @Override
                public void onIndicatorClicked(int position) {
                    sliderView.setCurrentPagePosition(position);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void check_Cafeteria(View view) {

        //android switch statement
        switch (view.getId()) {

            case R.id.cafeteria_Yes:
                cafateria = "Dry";
                break;
            case R.id.cafeteria_No:
                cafateria = "Wet";
                break;
            case R.id.cafeteria_NotAvailable:
                cafateria = "Not Available";
                break;
        }
    }

    public void check_CornerShop(View view) {

        //android switch statement
        switch (view.getId()) {

            case R.id.cornerShop_Yes:
                cornerShop = "Yes";
                break;
            case R.id.cornerShop_No:
                cornerShop = "No";
                break;
        }
    }

    public void check_getedColony(View view) {

        //android switch statement
        switch (view.getId()) {

            case R.id.gatedColony_Yes:
                gated_colony = "Yes";
                break;
            case R.id.gatedColony_No:
                gated_colony = "No";
                break;
        }
    }

    public void Check_Furnish(View v) {

        //android switch statement
        switch (v.getId()) {

            case R.id.unfurnished:
                furnish = "Unfurnished";
                break;
            case R.id.semifurnished:
                furnish = "Semi-furnished";
                break;
            case R.id.fullyfurnished:
                furnish = "Fully-furnished";
                break;
        }
    }

    public void check_Carparkking(View view) {
        //android switch statement
        switch (view.getId()) {

            case R.id.yesBtn:
                carparking = "Yes";
                OpenHead.setVisibility(View.VISIBLE);
                CloseHead.setVisibility(View.VISIBLE);
                OpenParkingSpinner.setVisibility(View.VISIBLE);
                CloseParkingSpinner.setVisibility(View.VISIBLE);
                break;
            case R.id.noBtn:
                carparking = "No";
                OpenHead.setVisibility(View.GONE);
                CloseHead.setVisibility(View.GONE);
                OpenParkingSpinner.setVisibility(View.GONE);
                CloseParkingSpinner.setVisibility(View.GONE);
                break;
        }
    }

    public void check_AnyCont(View view) {

        //android switch statement
        switch (view.getId()) {

            case R.id.AnyConst_Yes:
                construction_done = "Yes";
                Construction.setVisibility(View.VISIBLE);
                break;
            case R.id.AnyConst_No:
                Construction.setVisibility(View.GONE);
                construction_done = "No";
                break;
        }
    }

    public void check_boundary(View view) {

        //android switch statement
        switch (view.getId()) {

            case R.id.Bounadry_Yes:
                boundary_wall = "Yes";
                break;
            case R.id.Bounadry_No:
                boundary_wall = "No";
                break;
        }
    }

    public void check_PersonalWashRoom(View view) {
        //android switch statement
        switch (view.getId()) {

            case R.id.personalWashroomYes:
                personal_washroom = "Yes";
                break;
            case R.id.personalWashroomNo:
                personal_washroom = "No";
                break;
        }
    }

}
