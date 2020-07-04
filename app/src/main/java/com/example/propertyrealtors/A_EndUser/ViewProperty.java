package com.example.propertyrealtors.A_EndUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.propertyrealtors.Post_property.DetailsAdapterResiRorL;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.model.AdditioanlDetailsModel;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.PropertyModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewProperty extends AppCompatActivity {

    LinearLayoutManager layoutManager;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ViewPropertyAdapter adapter;
    ArrayList<PropertyModel> propertyModels;
    ArrayList<PropertyModel> propertyModelArrayList;
    String propertyFor, propertyType, city;
    ArrayList<Image> imageArrayList1;
    Toolbar toolbar;
    String minRupees, maxRupees, areaParameter, areaMin, areaMax, floorMinString, floorMaxString;
    List<String> bedroom = new ArrayList<>();
    List<String> CityList;
    List<String> localityList;
    List<String> textList;
    List<String> list;
    List<String> cList = new ArrayList<>();
    List<String> propertySubTypes = new ArrayList<>();
    List<String> propertyStatus = new ArrayList<>();
    List<String> furnish = new ArrayList<>();
    List<String> facingList = new ArrayList<>();
    List<String> bathroom = new ArrayList<>();
    String gym, clubHouse, park, parking, lift, powerBackup, gasPipeline, swimPool;
    String toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Filters");
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        propertyModels = new ArrayList<>();
        propertyModelArrayList = new ArrayList<>();
        imageArrayList1 = new ArrayList<Image>();
        Bundle bundle = getIntent().getExtras();
        try {
            propertyType = bundle.getString("propertyType");
            propertyFor = bundle.getString("propertyFor");
            if (propertyFor.equals("SELL")) {
                toolbarText = "Sale";
                toolbar.setTitle(toolbarText);
            } else if (propertyFor.equals("RENT or LEASE")) {
                toolbarText = "Rent";
                toolbar.setTitle(toolbarText);
            } else if (propertyFor.equals("LEASE")) {
                toolbarText = "Lease";
                toolbar.setTitle(toolbarText);
            }
            Log.e("propertyFor", "" + propertyFor);
            cList = bundle.getStringArrayList("CITYLIST");
            Log.e("clist", "" + cList);

            propertySubTypes = bundle.getStringArrayList("PROPERTYSUBTYPES");
            Log.e("propertySubTypes", "" + propertySubTypes);
            propertyStatus = bundle.getStringArrayList("PROPERTY_STATUS");
            Log.e("propertyStatus", "" + propertyStatus);
            furnish = bundle.getStringArrayList("FURNISH");
            Log.e("furnish", "" + furnish);
            facingList = bundle.getStringArrayList("FACING");
            Log.e("facingList", "" + facingList);
            bedroom = bundle.getStringArrayList("BEDROOM");
            Log.e("bedroom", "" + bedroom);
            bathroom = bundle.getStringArrayList("BATHROOM");
            Log.e("bathroom", "" + bathroom);
            minRupees = bundle.getString("MINIMUM_RUPEES");
            Log.e("minRupees", "" + minRupees);
            maxRupees = bundle.getString("MAXIMUM_RUPEES");
            Log.e("maxRupees", "" + maxRupees);
            areaParameter = bundle.getString("AREA_PARAMETER");
            Log.e("areaParameter", "" + areaParameter);
            areaMin = bundle.getString("AREA_MINIMUM");
            Log.e("areaMin", "" + areaMin);
            areaMax = bundle.getString("AREA_MAXIMUM");
            floorMinString = bundle.getString("floorMinString");
            floorMaxString = bundle.getString("floorMaxString");
            if (floorMinString.equals("Lower Basement") || floorMinString.equals("Upper Basement")
                    || floorMinString.equals("Ground")) {
                floorMinString = "0";

            }
            if (floorMaxString.equals("Upper Basement") || floorMaxString.equals("Lower Basement") ||
                    floorMaxString.equals("Ground")) {
                floorMaxString = "0";
            }

            Log.e("areaMax", "" + areaMax);
            park = bundle.getString("park");
            parking = bundle.getString("parking");
            lift = bundle.getString("lift");
            gym = bundle.getString("gym");
            powerBackup = bundle.getString("powerBackup");
            gasPipeline = bundle.getString("gasPipeline");
            clubHouse = bundle.getString("clubHouse");
            swimPool = bundle.getString("swimPool");
            Log.e("park", "" + park + " " + parking + " " + lift + " " + gym + " " + powerBackup + " " + gasPipeline + " " + clubHouse + " " + swimPool);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        findData();
    }

    private void findData() {
        try {

            reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child(propertyType);
            Query query = reference.orderByChild("propertyFor").equalTo(propertyFor);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                        PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                        try {
                            if (cList.size() != 0) {
                                for (int n = 0; n < cList.size(); n++) {
                                    if (cList.get(n).equals(details.getCity()) || cList.get(n).equals(details.getProject())) {
                                        //city sorting
                                        if (propertySubTypes.size() == 0) {
                                            if (minRupees.equals("₹ Min")) {
                                                if (maxRupees.equals("₹ Max")) {
                                                    if (bedroom.size() == 0) {
                                                        if (areaMax.isEmpty() || areaMax == null) {
                                                            if (areaMin.isEmpty() || areaMin == null) {
                                                                if (propertyStatus.size() == 0) {
                                                                    if (furnish.size() == 0) {
                                                                        if (facingList.size() == 0) {
                                                                            if (floorMinString.isEmpty() || floorMinString == null) {
                                                                                if (floorMaxString.isEmpty() || floorMinString == null) {
                                                                                    propertyModelArrayList.add(details);
                                                                                    String id1 = details.getKeyId();
                                                                                    getImage(id1);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
//                                         propertySubTypes sorting

                                        if (propertySubTypes.size() != 0) {
                                            for (int l = 0; l < propertySubTypes.size(); l++) {
                                                if (propertySubTypes.get(l).equals(details.getPropertySubType())) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel s : propertyModels) {
                                                        if (!propertyModelArrayList.contains(s)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            }
                                        }
//                                         price sorting
                                        if (!minRupees.equals("₹ Min")) {
                                            String price = details.getPrice();
                                            price = price.replace(",", "");
                                            if (!maxRupees.equals("₹ Max")) {
                                                if (Integer.parseInt(minRupees) <= Integer.parseInt(price)
                                                        && Integer.parseInt(maxRupees) >= Integer.parseInt(price)) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel s : propertyModels) {
                                                        if (!propertyModelArrayList.contains(s)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (Integer.parseInt(minRupees) > Integer.parseInt(price)) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel s : propertyModels) {
                                                        if (!propertyModelArrayList.contains(s)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!maxRupees.equals("₹ Max")) {
                                                String price = details.getPrice();
                                                price = price.replace(",", "");
                                                if (Integer.parseInt(maxRupees) <= Integer.parseInt(price)) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel s : propertyModels) {
                                                        if (!propertyModelArrayList.contains(s)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            }
                                        }
//                                        bedroom sorting
                                        if (bedroom.size() != 0) {
                                            for (int l = 0; l < bedroom.size(); l++) {
                                                if (bedroom.get(l).equals(details.getBedroom())) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel s : propertyModels) {
                                                        if (!propertyModelArrayList.contains(s)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            }
                                        }
//                                         area parameter
                                        if (areaParameter.equals(details.getCarpetAreaParameter())) {
                                            if (!areaMin.isEmpty()) {
                                                if (!areaMax.isEmpty()) {
                                                    if (Integer.parseInt(areaMin) < Integer.parseInt(details.getCarpetArea())
                                                            && Integer.parseInt(areaMax) > Integer.parseInt(details.getCarpetArea())) {
                                                        propertyModels.add(details);
                                                        for (PropertyModel s : propertyModels) {
                                                            if (!propertyModelArrayList.contains(s)) {
                                                                propertyModelArrayList.add(details);
                                                                String id1 = details.getKeyId();
                                                                getImage(id1);
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (Integer.parseInt(areaMin) >= Integer.parseInt(details.getCarpetArea())) {
                                                        propertyModels.add(details);
                                                        for (PropertyModel s : propertyModels) {
                                                            if (!propertyModelArrayList.contains(s)) {
                                                                propertyModelArrayList.add(details);
                                                                String id1 = details.getKeyId();
                                                                getImage(id1);
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!areaMax.isEmpty()) {
                                                    if (Integer.parseInt(areaMax) <= Integer.parseInt(details.getCarpetArea())) {
                                                        propertyModels.add(details);
                                                        for (PropertyModel s : propertyModels) {
                                                            if (!propertyModelArrayList.contains(s)) {
                                                                propertyModelArrayList.add(details);
                                                                String id1 = details.getKeyId();
                                                                getImage(id1);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
//                                        furnishing
                                        if (furnish.size() != 0) {
                                            for (int s = 0; s < furnish.size(); s++) {
                                                if (furnish.get(s).equals(details.getFurnished())) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel t : propertyModels) {
                                                        if (!propertyModelArrayList.contains(t)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            }
                                        }

//                                        floors
                                        String getfloor;
                                        if (details.getFloorNo().equals("Lower Basement")) {
                                            getfloor = "0";
                                        } else if (details.getFloorNo().equals("Upper Basement")) {
                                            getfloor = "0";
                                        } else if (details.getFloorNo().equals("Ground")) {
                                            getfloor = "0";
                                        } else {
                                            getfloor = details.getFloorNo();
                                        }
                                        if (!floorMinString.isEmpty()) {
                                            if (!floorMaxString.isEmpty()) {
                                                if (Integer.parseInt(floorMinString) <= Integer.parseInt(getfloor)
                                                        || Integer.parseInt(floorMaxString) >= Integer.parseInt(getfloor)) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel s : propertyModels) {
                                                        if (!propertyModelArrayList.contains(s)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (Integer.parseInt(floorMinString) >= Integer.parseInt(getfloor)) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel s : propertyModels) {
                                                        if (!propertyModelArrayList.contains(s)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!floorMaxString.isEmpty()) {
                                                if (Integer.parseInt(floorMaxString) <= Integer.parseInt(getfloor)) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel s : propertyModels) {
                                                        if (!propertyModelArrayList.contains(s)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        //facing
                                        if (facingList.size() != 0) {
                                            String id = details.getKeyId();
                                            DatabaseReference def = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child("additionalInfo");
                                            Query query1 = def.orderByChild("refId").equalTo(id);
                                            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    for (DataSnapshot areaSnapshot1 : snapshot.getChildren()) {
                                                        AdditioanlDetailsModel additioanlDetails = areaSnapshot1.getValue(AdditioanlDetailsModel.class);
                                                        for (int s = 0; s < facingList.size(); s++) {
                                                            if (facingList.get(s).equals(additioanlDetails.getFacing())) {
                                                                propertyModels.add(details);
                                                                for (PropertyModel t : propertyModels) {
                                                                    if (!propertyModelArrayList.contains(t)) {
                                                                        propertyModelArrayList.add(details);
                                                                        String id1 = details.getKeyId();
                                                                        getImage(id1);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        }
                                        //property status
     /*                                   if (propertyStatus.size() != 0) {
                                            for (int s = 0; s < propertyStatus.size(); s++) {
                                                if (propertyStatus.get(s).equals(details.getProperty_status())) {
                                                    propertyModels.add(details);
                                                    for (PropertyModel t : propertyModels) {
                                                        if (!propertyModelArrayList.contains(t)) {
                                                            propertyModelArrayList.add(details);
                                                            String id1 = details.getKeyId();
                                                            getImage(id1);
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        //amenities



     */
                                    }
                                    long no = propertyModelArrayList.size();
                                    /*Log.e("no", ""+propertyModelArrayList.size());
                                    if (propertyModelArrayList.size()==0){
                                        toolbar.setTitle("0 Filter Property for "+toolbarText);
                                    }else if (no>0 && no<2) {
                                        toolbar.setTitle(no + " Filter Property for " + toolbarText);
                                    }
                                    else*/
                                   /* if (no >= 2) {
                                        toolbar.setTitle(no + " Filter Properties for " + toolbarText);
                                    }*/
                                }
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(ViewProperty.this, "NumberFormatException", Toast.LENGTH_SHORT).show();
                        } catch (IndexOutOfBoundsException e) {
                            Toast.makeText(ViewProperty.this, "IndexOutOfBoundsException", Toast.LENGTH_SHORT).show();
                        } catch (NullPointerException e) {
                            Toast.makeText(ViewProperty.this, "NullPointerException", Toast.LENGTH_SHORT).show();
                        }catch (DatabaseException e){
                            Toast.makeText(ViewProperty.this, "DatabaseException", Toast.LENGTH_SHORT).show();
                        }


                        recyclerView.setHasFixedSize(true);
                        layoutManager = new

                                LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new

                                DefaultItemAnimator());
                        adapter = new

                                ViewPropertyAdapter(getApplicationContext(), propertyModelArrayList, imageArrayList1);
                        recyclerView.setAdapter(adapter);
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Something Error", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IndexOutOfBoundsException |
                NullPointerException e) {
            e.printStackTrace();
        }catch (DatabaseException e){
            Toast.makeText(ViewProperty.this, "DatabaseException", Toast.LENGTH_SHORT).show();
        }
    }

    private void getImage(String id1) {
        try {
            reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child(propertyType);
            Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Image image = new Image("null");
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            image = areaSnapshot.getValue(Image.class);
                            imageArrayList1.add(image);
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        imageArrayList1.add(image);
                        adapter.notifyDataSetChanged();
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

}
