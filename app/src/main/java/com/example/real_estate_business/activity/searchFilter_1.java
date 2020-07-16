package com.example.real_estate_business.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_estate_business.A_EndUser.ViewProperty;
import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;
import com.example.real_estate_business.model.City;
import com.example.real_estate_business.model.Locality;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class searchFilter_1 extends AppCompatActivity {

    private static final int NUM_OF_TEXTS = 20;
    Toolbar toolbar;
    Menu menu;
    Button Buy, Rent, Commercial, Project;
    TextView commButtoHead;
    Button buyComm, leaseComm;
    AutoCompleteTextView City, Society;
    TextView Submit;
    String propertyFor, propertyType;
    String propertySubType;
    boolean boolean1 = true, boolean2 = true;
    int flag1 = 3, flag2 = 3;
    int groupC1 = 0;
    int groupC2 = 0;
    String minRupees, maxRupees, areaParameter, areaMin, areaMax, floorMinString, floorMaxString;
    HashMap<String, Integer> map = new HashMap<>();
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

    private long number;
    Spinner minRupee, maxRupee, minRupeesRent, maxRupeesRent;
    Spinner area_parameter, area_min, area_max;

    Spinner minFloor, maxFloor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_1);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Filters");
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        propertyFor = "SELL";
        propertyType="residential";

        Buy = findViewById(R.id.buy);
        Rent = findViewById(R.id.rent);
        Commercial = findViewById(R.id.commercial);
        commButtoHead = findViewById(R.id.textView);
        buyComm = findViewById(R.id.buyComm);
        leaseComm = findViewById(R.id.leaseComm);
        minRupeesRent = (Spinner) findViewById(R.id.minRupeeRent);
        maxRupeesRent = (Spinner) findViewById(R.id.maxRupeeRent);

        City = findViewById(R.id.search);
        minRupee = findViewById(R.id.minRupee);
        maxRupee = findViewById(R.id.maxRupee);
        area_parameter = findViewById(R.id.area_parameter);
        area_max = findViewById(R.id.area_max);
        area_min = findViewById(R.id.area_min);
        minFloor = findViewById(R.id.minimumFloor);
        maxFloor = findViewById(R.id.maximumFloor);

        CityList = new ArrayList<String>();
        localityList = new ArrayList<String>();
        list = new ArrayList<String>();
        getCity();
        AddCityInautoTextView();

        minRupeesRent.setVisibility(View.GONE);
        maxRupeesRent.setVisibility(View.GONE);

        maxpriceSpinnerRent();
        minpriceSpinnerRent();
        maxpriceSpinner();
        minpriceSpinner();
        getarea_parameter();
        getarea_max();
        getarea_min();
        getMaxFloor();
        getMinFloor();
        textList = new ArrayList<String>((int) number);
        Submit = findViewById(R.id.next2);
        Buy.setBackgroundColor(Color.LTGRAY);
        Buy.setTextColor(Color.WHITE);
        Rent.setBackgroundColor(Color.WHITE);
        Commercial.setBackgroundColor(Color.WHITE);
        buyComm.setBackgroundColor(Color.WHITE);
        leaseComm.setBackgroundColor(Color.WHITE);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent returnIntent = new Intent();
                returnIntent.putExtra("PROPERTY_FOR",propertyFor);
                returnIntent.putExtra("PROPERTY_TYPE",propertyType);
                setResult(Activity.RESULT_OK,returnIntent);*/
                finish();
            }
        });
    }

    public void next(View view) {
        try {
            if (propertyFor.isEmpty() && propertyType.isEmpty()) {
                Toast.makeText(this, "Select type", Toast.LENGTH_LONG).show();
                return;
            } else if (propertyFor.isEmpty()) {
                Toast.makeText(this, "Select Looking type", Toast.LENGTH_LONG).show();
                return;
            } else if (cList.size() == 0) {
                Toast.makeText(this, "Enter City", Toast.LENGTH_LONG).show();
                return;
            } else {
                try {

                    if (minRupees.equals("₹ Min")) {
                        if (maxRupees.equals("₹ Max")) {
                            maxRupees = "₹ Max";
                        }
                        minRupees = "₹ Min";
                    } else {
                        if (maxRupees.equals("₹ Max")) {
                            maxRupees = "₹ Max";
                        } else {
                            try {
                                if (Integer.parseInt(minRupees) > Integer.parseInt(maxRupees)) {
                                    Toast.makeText(this, "Minimum price is not greater then Maximum price", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (area_min.getVisibility() != View.GONE) {
                        if (areaMin.equals("Min")) {
                            if (areaMax.equals("Max")) {
                                areaMax = "";
                            }
                            areaMin = "";
                        } else {
                            if (areaMax.equals("Max")) {
                                areaMax = "";
                            } else {
                                try {
                                    if (Integer.parseInt(areaMin) > Integer.parseInt(areaMax)) {
                                        Toast.makeText(this, "Minimum area is not greater then Maximum area", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (floorMinString.equals("Min")) {
                            if (floorMaxString.equals("Max")) {
                                floorMaxString = "";
                            }
                            floorMinString = "";
                        } else {
                            if (floorMaxString.equals("Max")) {
                                floorMaxString = "";
                            } else {
                                try {
                                    if (Integer.parseInt(floorMinString) > Integer.parseInt(floorMaxString)) {
                                        Toast.makeText(this, "Minimum floor is not greater then Maximum floor", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }else {
                        areaMin="";
                        areaMax="";
                        areaParameter="";
                        floorMaxString="";
                        floorMinString="";

                    }

                    //search session
                    SessionManager session = new SessionManager(getApplicationContext());
                    session.createSearchSession(propertyType, propertyFor);

                    Intent intent = new Intent(searchFilter_1.this, ViewProperty.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("propertyFor", propertyFor);
                    bundle.putString("propertyType", propertyType);
                    bundle.putStringArrayList("CITYLIST", (ArrayList<String>) cList);
                    bundle.putStringArrayList("PROPERTYSUBTYPES", (ArrayList<String>) propertySubTypes);
                    bundle.putStringArrayList("PROPERTY_STATUS", (ArrayList<String>) propertyStatus);
                    bundle.putStringArrayList("FURNISH", (ArrayList<String>) furnish);
                    bundle.putStringArrayList("FACING", (ArrayList<String>) facingList);
                    bundle.putStringArrayList("BEDROOM", (ArrayList<String>) bedroom);
                    bundle.putStringArrayList("BATHROOM", (ArrayList<String>) bathroom);
                    bundle.putString("MINIMUM_RUPEES", minRupees);
                    bundle.putString("MAXIMUM_RUPEES", maxRupees);
                    bundle.putString("AREA_PARAMETER", areaParameter);
                    bundle.putString("AREA_MINIMUM", areaMin);
                    bundle.putString("AREA_MAXIMUM", areaMax);
                    bundle.putString("floorMinString", floorMinString);
                    bundle.putString("floorMaxString", floorMaxString);
                    bundle.putString("park", park);
                    bundle.putString("parking", parking);
                    bundle.putString("lift", lift);
                    bundle.putString("gym", gym);
                    bundle.putString("powerBackup", powerBackup);
                    bundle.putString("gasPipeline", gasPipeline);
                    bundle.putString("clubHouse", clubHouse);
                    bundle.putString("swimPool", swimPool);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void getMinFloor() {
        minFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floorMinString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getMaxFloor() {
        maxFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floorMaxString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getarea_max() {
        area_max.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaMax = parent.getItemAtPosition(position).toString();
                Log.e("max", areaMax);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getarea_min() {

        area_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaMin = parent.getItemAtPosition(position).toString();
                Log.e("max", areaMin);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getarea_parameter() {
        area_parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaParameter = parent.getItemAtPosition(position).toString();
                Log.e("max", areaParameter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getCity() {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("City").child("CityName");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    number = dataSnapshot.getChildrenCount();
                    for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                        City city = areaSnapshot.getValue(City.class);
                        CityList.add(city.getCity());
                        getLocality(city.getCity());
                    }
                    list.addAll(CityList);
//                    Log.e("city", String.valueOf(CityList));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(searchFilter_1.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getLocality(String city) {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("City").child("Locality").child(city);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    number = number + dataSnapshot.getChildrenCount();

                    for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                        Locality locality = areaSnapshot.getValue(Locality.class);
                        localityList.add(locality.getLocality());
                    }
                    for (String s : localityList) {
                        if (!list.contains(s)) {
                            list.add(s);
                        }
                    }
//                    list.addAll(localityList);
                    Log.e("localityList1", String.valueOf(localityList));
                    Log.e("list2", String.valueOf(list));


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void AddCityInautoTextView() {
        try {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            City.setAdapter(arrayAdapter);
            City.setThreshold(1);
            City.setTextColor(Color.BLUE);
            City.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String data = parent.getItemAtPosition(position).toString();
                    City.setText("");
//                    AddCityInautoTextView();
                    creterTextView(data);
                    Log.e("Auto", data);
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void creterTextView(String data) {
        try {

            boolean check = true;
            LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);
            for (int i = 0; i < textList.size(); i++) {
                if (data.equals(textList.get(i))) {
                    check = false;
                }
            }
            if (check) {
                TextView location = new TextView(this);
                location.setText(data + " ✗ ");
                location.setPadding(10, 20, 10, 20);
                location.setBackgroundColor(Color.RED);
                location.setTextColor(Color.WHITE);
                layout.addView(location);
                textList.add(data);

                cList.add(data);

               /* for (int i = 0; i < CityList.size(); i++) {
                    if (data.equals(CityList.get(i))) {
                        cList.add(data);
                    }
                }*/
                /*for (int i = 0; i < localityList.size(); i++) {
                    if (data.equals(localityList.get(i))) {
                        lList.add(data);
                    }
                }*/
                location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            layout.removeView(location);
                            textList.remove(data);
                            cList.remove(data);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Log.e("size", " " + textList.size());

                Log.e("clist", " " + cList);

            }

            Log.e("ListTectView", String.valueOf(textList) + "  " + textList.size());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_reset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            propertyFor = "";
            propertyType = "";
            Toast.makeText(searchFilter_1.this, "Reset", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickPropertyFor(View view) {
        CardView cardView3 = findViewById(R.id.house);
        CardView cardView4 = findViewById(R.id.flat);
        CardView cardView2 = findViewById(R.id.plot);
        CardView cardView = findViewById(R.id.coworking);
        switch (view.getId()) {
            case R.id.buy:
                propertyFor = "SELL";
                propertyType = "residential";

                minRupee.setVisibility(View.VISIBLE);
                maxRupee.setVisibility(View.VISIBLE);
                minRupeesRent.setVisibility(View.GONE);
                maxRupeesRent.setVisibility(View.GONE);

                commButtoHead.setVisibility(View.GONE);
                buyComm.setVisibility(View.GONE);
                leaseComm.setVisibility(View.GONE);
                Buy.setBackgroundColor(Color.LTGRAY);
                Buy.setTextColor(Color.WHITE);
                Rent.setBackgroundColor(Color.WHITE);
                Rent.setTextColor(Color.BLACK);
                Commercial.setBackgroundColor(Color.WHITE);
                Commercial.setTextColor(Color.BLACK);
                cardView.setVisibility(View.GONE);
                cardView2.setVisibility(View.VISIBLE);
                cardView3.setVisibility(View.VISIBLE);
                cardView4.setVisibility(View.VISIBLE);
                break;
            case R.id.rent:
                propertyFor = "RENT or LEASE";
                propertyType = "residential";
                minRupee.setVisibility(View.GONE);
                maxRupee.setVisibility(View.GONE);
                minRupeesRent.setVisibility(View.VISIBLE);
                maxRupeesRent.setVisibility(View.VISIBLE);

                commButtoHead.setVisibility(View.GONE);
                buyComm.setVisibility(View.GONE);
                leaseComm.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
                cardView2.setVisibility(View.VISIBLE);
                cardView3.setVisibility(View.VISIBLE);
                cardView4.setVisibility(View.VISIBLE);
                Buy.setBackgroundColor(Color.WHITE);
                Buy.setTextColor(Color.BLACK);
                Rent.setBackgroundColor(Color.LTGRAY);
                Rent.setTextColor(Color.WHITE);
                Commercial.setBackgroundColor(Color.WHITE);
                Commercial.setTextColor(Color.BLACK);
                break;
            case R.id.commercial:
                propertyType = "commercial";
                propertyFor = "";

                commButtoHead.setVisibility(View.VISIBLE);
                buyComm.setVisibility(View.VISIBLE);
                leaseComm.setVisibility(View.VISIBLE);
                buyComm.setTextColor(Color.BLACK);
                buyComm.setBackgroundColor(Color.WHITE);
                leaseComm.setTextColor(Color.BLACK);
                leaseComm.setBackgroundColor(Color.WHITE);
                cardView.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                cardView4.setVisibility(View.GONE);
                Commercial.setBackgroundColor(Color.LTGRAY);
                Commercial.setTextColor(Color.WHITE);
                Buy.setBackgroundColor(Color.WHITE);
                Buy.setTextColor(Color.BLACK);
                Rent.setBackgroundColor(Color.WHITE);
                Rent.setTextColor(Color.BLACK);
                break;

        }
    }

    public void btnCommercial(View view) {
        CardView cardView3 = findViewById(R.id.house);
        CardView cardView4 = findViewById(R.id.flat);
        CardView cardView2 = findViewById(R.id.plot);
        CardView cardView = findViewById(R.id.coworking);
        switch (view.getId()) {
            case R.id.buyComm:
                propertyFor = "SELL";
                propertyType = "commercial";

                minRupeesRent.setVisibility(View.GONE);
                maxRupeesRent.setVisibility(View.GONE);
                minRupee.setVisibility(View.VISIBLE);
                maxRupee.setVisibility(View.VISIBLE);

                cardView.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                cardView4.setVisibility(View.GONE);
                buyComm.setBackgroundColor(Color.LTGRAY);
                buyComm.setTextColor(Color.WHITE);
                leaseComm.setBackgroundColor(Color.WHITE);
                leaseComm.setTextColor(Color.BLACK);
                break;
            case R.id.leaseComm:
                propertyFor = "LEASE";
                propertyType = "commercial";

                minRupee.setVisibility(View.GONE);
                maxRupee.setVisibility(View.GONE);
                minRupeesRent.setVisibility(View.VISIBLE);
                maxRupeesRent.setVisibility(View.VISIBLE);

                cardView.setVisibility(View.VISIBLE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                cardView4.setVisibility(View.GONE);
                buyComm.setBackgroundColor(Color.WHITE);
                buyComm.setTextColor(Color.BLACK);
                leaseComm.setBackgroundColor(Color.LTGRAY);
                leaseComm.setTextColor(Color.WHITE);
                break;
        }
    }

    public void cardClickgp1(View view) {
        CardView cardView = (CardView) view;
        if (groupC2 == 0) {
            propertyType = "residential";
            switch (view.getId()) {
                case R.id.flat:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        groupC1--;
                        propertySubTypes.remove("Flat/Apartment");
                    } else {
                        propertySubTypes.add("Flat/Apartment");
                        groupC1++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }
                    break;

                case R.id.house:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        propertySubType = "";
                        groupC1--;
                        propertySubTypes.remove("House");
                        propertySubTypes.remove("Villa");
                    } else {
                        propertySubTypes.add("House");
                        propertySubTypes.add("Villa");
                        groupC1++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);
                        propertySubType = "House";

                    }
                    break;

                case R.id.plot:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        propertySubType = "";
                        groupC1--;
                        propertySubTypes.remove("Plot");
                    } else {
                        propertySubTypes.add("Plot");
                        groupC1++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }
                    break;
            }

        } else {
            Log.e("list", String.valueOf(propertySubTypes));
            Toast.makeText(this, "You cann't select both residential and commercial property types", Toast.LENGTH_LONG).show();
        }
    }

    public void cardClickgp2(View view) {
        CardView cardView = (CardView) view;
        if (groupC1 == 0) {
            propertyType = "commercial";
            switch (view.getId()) {
                case R.id.office:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        propertySubType = "";
                        groupC2--;
                        propertySubTypes.remove("office");
                        propertySubTypes.remove("IT_Park");
                    } else {
                        propertySubTypes.add("office");
                        propertySubTypes.add("IT_Park");
                        groupC2++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }

                    break;

                case R.id.shop:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        groupC2--;
                        propertySubTypes.remove("Shop");
                        propertySubTypes.remove("Showroom");
                    } else {
                        propertySubTypes.add("Shop");
                        propertySubTypes.add("Showroom");
                        groupC2++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }
                    break;

                case R.id.coworking:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        groupC2--;
                        propertySubTypes.remove("Coworking_Space");
                    } else {
                        propertySubTypes.add("Coworking_Space");
                        groupC2++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }

                    break;
                case R.id.other:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        groupC2--;
                        propertySubTypes.remove("Commercial_Land");
                        propertySubTypes.remove("Agriculture_Land");
                        propertySubTypes.remove("Warehouse");
                        propertySubTypes.remove("Industrial_Building");
                        propertySubTypes.remove("Industrial_Shed");
                        propertySubTypes.remove("Industrial_Land");
                    } else {
                        propertySubTypes.add("Commercial_Land");
                        propertySubTypes.add("Agriculture_Land");
                        propertySubTypes.add("Warehouse");
                        propertySubTypes.add("Industrial_Building");
                        propertySubTypes.add("Industrial_Shed");
                        propertySubTypes.add("Industrial_Land");
                        groupC2++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);
                    }
                    break;
            }

        } else {
            Log.e("list", String.valueOf(propertySubTypes));
            Toast.makeText(this, "You cann't select both residential and commercial property types", Toast.LENGTH_LONG).show();
        }
    }

    public void maxpriceSpinner() {
        maxRupee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                getValue();
                if (key.equals("₹ Max"))
                    maxRupees = "₹ Max";
                else {
                    maxRupees = String.valueOf(map.get(key));
                    Log.e("max", maxRupees);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void minpriceSpinner() {

        minRupee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                getValue();
                if (key.equals("₹ Min"))
                    minRupees = "₹ Min";
                else {
                    minRupees = String.valueOf(map.get(key));
                    Log.e("min", minRupees);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void maxpriceSpinnerRent() {
        maxRupeesRent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                getValue();
                if (key.equals("₹ Max"))
                    maxRupees = "₹ Max";
                else {
                    maxRupees = String.valueOf(map.get(key));
                    Log.e("max", maxRupees);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void minpriceSpinnerRent() {

        minRupeesRent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                getValue();
                if (key.equals("₹ Min"))
                    minRupees = "₹ Min";
                else {
                    minRupees = String.valueOf(map.get(key));
                    Log.e("min", minRupees);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void getValue() {

        map.put("₹ 0", 0);
        map.put("₹ 5000", 5000);
        map.put("₹ 10000", 10000);
        map.put("₹ 15000", 15000);
        map.put("₹ 20000", 20000);
        map.put("₹ 25000", 25000);
        map.put("₹ 30000", 30000);
        map.put("₹ 35000", 35000);
        map.put("₹ 40000", 40000);
        map.put("₹ 50000", 50000);
        map.put("₹ 60000", 60000);
        map.put("₹ 70000", 70000);
        map.put("₹ 800000 ", 800000);
        map.put("₹ 1 Lac", 100000);
        map.put("₹ 1.5 Lac", 150000);
        map.put("₹ 2 Lac", 200000);
        map.put("₹ 4 Lac", 400000);
        map.put("₹ 5 Lac", 500000);
        map.put("₹ 7 Lac", 700000);
        map.put("₹ 10 Lac", 1000000);
        map.put("₹ 20 Lac", 2000000);
        map.put("₹ 30 Lac", 3000000);
        map.put("₹ 40 Lac", 4000000);
        map.put("₹ 50 Lac", 5000000);
        map.put("₹ 60 Lac", 6000000);
        map.put("₹ 70 Lac", 7000000);
        map.put("₹ 80 Lac", 8000000);
        map.put("₹ 90 Lac", 9000000);
        map.put("₹ 1 Cr", 10000000);
        map.put("₹ 1.2 Cr", 12000000);
        map.put("₹ 1.4 Cr", 14000000);
        map.put("₹ 1.6 Cr", 16000000);
        map.put("₹ 1.8 Cr", 18000000);
        map.put("₹ 2 Cr", 20000000);
        map.put("₹ 2.3 Cr", 23000000);
        map.put("₹ 2.6 Cr", 26000000);
        map.put("₹ 3 Cr", 30000000);
        map.put("₹ 3.5 Cr", 35000000);
        map.put("₹ 4 Cr", 40000000);
        map.put("₹ 4.5 Cr", 45000000);
        map.put("₹ 5 Cr", 50000000);
        map.put("₹ 10 Cr", 100000000);
        map.put("₹ 15 Cr", 150000000);
    }

    public void selectBHK(View view) {
        CardView textView = (CardView) view;
        textView.setBackgroundColor(Color.WHITE);
        switch (view.getId()) {
            case R.id.bhk1:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("1");
                } else {
                    bedroom.add("1");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                }
                break;
            case R.id.bhk2:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("2");
                } else {
                    bedroom.add("2");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                }
                break;
            case R.id.bhk3:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("3");
                } else {
                    bedroom.add("3");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                }
                break;
            case R.id.bhk4:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("4");
                } else {
                    bedroom.add("4");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                }
                break;
            case R.id.bhk5:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("5");
                } else {
                    bedroom.add("5");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                }
                break;
            case R.id.bhk6:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("6");
                } else {
                    bedroom.add("6");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                }
                break;
            case R.id.bhk7:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("7");
                } else {
                    bedroom.add("7");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                }
                break;
            case R.id.bhk8:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("8");
                } else {
                    bedroom.add("8");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                }
                break;
            case R.id.bhk9:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("9");
                } else {
                    bedroom.add("9");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);

                }
                break;
            case R.id.bhk10:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("10");
                } else {
                    bedroom.add("10");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);

                }
                break;
            case R.id.bhk11:
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bedroom.remove("10+");
                } else {
                    bedroom.add("10+");
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                }
                break;
        }
    }

    public void status(View view) {
        Button button = (Button) view;
        switch (view.getId()) {
            case R.id.under:
                button.setBackgroundColor(Color.WHITE);
                if (button.isSelected()) {
                    button.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    propertyStatus.remove("Under Construction");
                } else {
                    propertyStatus.add("Under Construction");
                    view.setSelected(true);
                    button.setBackgroundColor(Color.LTGRAY);

                }
                break;

            case R.id.ready:
                button.setBackgroundColor(Color.WHITE);
                if (button.isSelected()) {
                    button.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    propertyStatus.remove("Ready to Move");
                } else {
                    propertyStatus.add("Ready to Move");
                    view.setSelected(true);
                    button.setBackgroundColor(Color.LTGRAY);
                }
                break;
        }
    }

    public void furnishing(View view) {
        CardView imageButton = (CardView) view;
        switch (view.getId()) {
            case R.id.furnished:
                imageButton.setBackgroundColor(Color.WHITE);
                if (imageButton.isSelected()) {
                    imageButton.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    furnish.remove("Fully-furnished");
                } else {
                    furnish.add("Fully-furnished");
                    view.setSelected(true);
                    imageButton.setBackgroundColor(Color.LTGRAY);

                }
                break;

            case R.id.semifurnished:
                imageButton.setBackgroundColor(Color.WHITE);
                if (imageButton.isSelected()) {
                    imageButton.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    furnish.remove("Semi-furnished");
                } else {
                    furnish.add("Semi-furnished");
                    view.setSelected(true);
                    imageButton.setBackgroundColor(Color.LTGRAY);
                }
                break;
            case R.id.unfurnished:
                imageButton.setBackgroundColor(Color.WHITE);
                if (imageButton.isSelected()) {
                    imageButton.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    furnish.remove("Unfurnished");
                } else {
                    furnish.add("Unfurnished");
                    view.setSelected(true);
                    imageButton.setBackgroundColor(Color.LTGRAY);
                }
                break;
        }

    }

    public void Visibilities(View view) {

        TextView more = findViewById(R.id.more);
        more.setVisibility(View.GONE);
        TextView textView4 = findViewById(R.id.textView4);
        HorizontalScrollView view1 = findViewById(R.id.bedroom);
        HorizontalScrollView horizontalScrollView3 = findViewById(R.id.horizontalScrollView3);
        HorizontalScrollView horizontalScrollView5 = findViewById(R.id.horizontalScrollView5);
        HorizontalScrollView bathroom = findViewById(R.id.bathroom);
        TextView textView8 = findViewById(R.id.textView8);
        TextView textView600 = findViewById(R.id.textView600);
        TextView textView500 = findViewById(R.id.textView500);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView004 = findViewById(R.id.textView004);
        TextView textView11 = findViewById(R.id.textView11);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView81 = findViewById(R.id.textView81);
        CardView furnished = findViewById(R.id.furnished);
        CardView semifurnished = findViewById(R.id.semifurnished);
        CardView unfurnished = findViewById(R.id.unfurnished);
        Button under = findViewById(R.id.under);
        Button ready = findViewById(R.id.ready);
        Spinner minimumFloor = findViewById(R.id.minimumFloor);
        Spinner maximumFloor = findViewById(R.id.maximumFloor);
        Spinner area_parameter = findViewById(R.id.area_parameter);
        Spinner area_min = findViewById(R.id.area_min);
        Spinner area_max = findViewById(R.id.area_max);

        bathroom.setVisibility(View.VISIBLE);
        under.setVisibility(View.VISIBLE);
        minimumFloor.setVisibility(View.VISIBLE);
        ready.setVisibility(View.VISIBLE);
        maximumFloor.setVisibility(View.VISIBLE);
        area_parameter.setVisibility(View.VISIBLE);
        area_min.setVisibility(View.VISIBLE);
        area_max.setVisibility(View.VISIBLE);
        horizontalScrollView5.setVisibility(View.VISIBLE);
        horizontalScrollView3.setVisibility(View.VISIBLE);
        view1.setVisibility(View.VISIBLE);
        textView8.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        textView81.setVisibility(View.VISIBLE);
        furnished.setVisibility(View.VISIBLE);
        semifurnished.setVisibility(View.VISIBLE);
        unfurnished.setVisibility(View.VISIBLE);
        textView600.setVisibility(View.VISIBLE);
        textView500.setVisibility(View.VISIBLE);
        textView5.setVisibility(View.VISIBLE);
        textView11.setVisibility(View.VISIBLE);
        textView004.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);


    }

    public void amenities(View view) {
        CardView cardView = (CardView) view;
        switch (view.getId()) {
            case R.id.parking:
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
                break;
            case R.id.lift:
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
                break;
            case R.id.gymnasium:
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
                break;
            case R.id.clubhouse:
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
                break;
            case R.id.powerBackup:
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
                break;
            case R.id.gaspipeline:
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
                break;
            case R.id.park:
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
                break;
            case R.id.swimmingpool:
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
                break;
        }
    }

    public void facing(View view) {
        CardView textView = (CardView) view;
        switch (view.getId()) {
            case R.id.east:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    facingList.remove("East");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    facingList.add("East");
                }
                break;
            case R.id.north:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    facingList.remove("North");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    facingList.add("North");
                }
                break;
            case R.id.northeast:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    facingList.remove("North-East");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    facingList.add("North-East");
                }
                break;
            case R.id.northwest:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    facingList.remove("North-West");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    facingList.add("North-West");
                }
                break;
            case R.id.south:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    facingList.remove("South");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    facingList.add("South");
                }
                break;
            case R.id.southeast:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    facingList.remove("South-East");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    facingList.add("South-East");
                }
                break;
            case R.id.southwest:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    facingList.remove("South-West");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    facingList.add("South-West");
                }
                break;
            case R.id.west:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    facingList.remove("West");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    facingList.add("West");
                }
                break;
        }
    }

    public void selectBATHROOM(View view) {
        CardView textView = (CardView) view;
        switch (view.getId()) {
            case R.id.room1:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("1");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("1");
                }
                break;
            case R.id.room2:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("2");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("2");
                }
                break;
            case R.id.room3:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("3");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("3");
                }
                break;
            case R.id.room4:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("4");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("4");
                }
                break;
            case R.id.room5:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("5");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("5");
                }
                break;
            case R.id.room6:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("6");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("6");
                }
                break;
            case R.id.room7:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("7");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("7");
                }
                break;
            case R.id.room8:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("8");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("8");
                }
                break;
            case R.id.room9:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("9");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("9");
                }
                break;
            case R.id.room10:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("10");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("10");
                }
                break;
            case R.id.room11:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    bathroom.remove("10+");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.LTGRAY);
                    bathroom.add("10+");
                }
                break;
        }

    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("PROPERTY_FOR",propertyFor);
        returnIntent.putExtra("PROPERTY_TYPE",propertyType);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
        *//*Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();*//*
    }*/
}
