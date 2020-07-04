package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.A_EndUser.ViewProperty;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Start13_1_2_3_all extends AppCompatActivity {

    View includedLayout;
    String selectedCity, locality, areaParameter;
    Spinner minRupees, minRupeesRent, maxRupees, maxRupeesRent, areaParameterSpinner, areaMinSpinner, areaMaxSpinner;
    List<String> bedroom = new ArrayList<>();
    List<String> propertySubTypes = new ArrayList<>();
    List<String> cList = new ArrayList<>();
    String propertyFor, propertyType;
    int count = 0;
    int count2 = 0;
    String minimumArea, maximumArea;
    HashMap<String, Integer> map = new HashMap<>();
    TextView error1, error2;
    String minRupeeString, maxRupeeString, flat, plot, house;
    String PAGE_LOAD;
    ImageButton Flatbtn, Plotbtn, Housebtn;
    TextView bedroomTextview, AreaTextview;
    Menu menu;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start13_1_2_3_all);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(" ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            Bundle bundle = getIntent().getExtras();
            propertyFor = bundle.getString("PROPERTY_FOR");
            Log.e("propertyFor", "" + propertyFor);
            cList = bundle.getStringArrayList("CITYLIST");
            Log.e("clist", "" + cList);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        minRupees = (Spinner) findViewById(R.id.minRupee);
        minRupeesRent = (Spinner) findViewById(R.id.minRupeeRent);
        maxRupeesRent = (Spinner) findViewById(R.id.maxRupeeRent);
        maxRupees = (Spinner) findViewById(R.id.maxRupee);
        areaMaxSpinner = (Spinner) findViewById(R.id.area_max);
        areaMinSpinner = (Spinner) findViewById(R.id.area_min);
        areaParameterSpinner = (Spinner) findViewById(R.id.area_parameter);

        if (propertyFor.equals("SELL")) {
            areaMaxSpinner.setVisibility(View.GONE);
            areaMinSpinner.setVisibility(View.GONE);
            maxpriceSpinner();
            minpriceSpinner();
        } else if (propertyFor.equals("RENT or LEASE")) {
            minRupees.setVisibility(View.GONE);
            maxRupees.setVisibility(View.GONE);
            maxpriceSpinnerRent();
            minpriceSpinnerRent();
        }
        getarea_parameter();
        getarea_max();
        getarea_min();

    }

    public void maxpriceSpinner() {
      /*  ArrayAdapter<String> spinnerArrayAdapter;
        String[] testArray = new String[0];
        testArray = getResources().getStringArray(R.array.budget_max);
        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);


        maxRupees.setAdapter(spinnerArrayAdapter);*/
        maxRupees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                getValue();
                if (key.equals("₹ Max"))
                    maxRupeeString = "₹ Max";
                else {
                    maxRupeeString = String.valueOf(map.get(key));
                    Log.e("max", maxRupeeString);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void minpriceSpinner() {
      /*  String[] testArray = new String[0];

        testArray = getResources().getStringArray(R.array.budget_min);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);
        minRupees.setAdapter(spinnerArrayAdapter);*/
        minRupees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                getValue();
                if (key.equals("₹ Min"))
                    minRupeeString = "₹ Min";
                else {
                    minRupeeString = String.valueOf(map.get(key));
                    Log.e("min", minRupeeString);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void maxpriceSpinnerRent() {
       /* ArrayAdapter<String> spinnerArrayAdapter;
        String[] testArray = new String[0];
        testArray = getResources().getStringArray(R.array.budget_max_rent);
        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);


        maxRupeesRent.setAdapter(spinnerArrayAdapter);*/
        maxRupeesRent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                getValue();
                if (key.equals("₹ Max"))
                    maxRupeeString = "₹ Max";
                else {
                    maxRupeeString = String.valueOf(map.get(key));
                    Log.e("max", maxRupeeString);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void minpriceSpinnerRent() {
      /*  String[] testArray = new String[0];

        testArray = getResources().getStringArray(R.array.budget_min_rent);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);
        minRupeesRent.setAdapter(spinnerArrayAdapter);*/
        minRupeesRent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                getValue();
                if (key.equals("₹ Min"))
                    minRupeeString = "₹ Min";
                else {
                    minRupeeString = String.valueOf(map.get(key));
                    Log.e("min", minRupeeString);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void getValue() {

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

    private void getarea_max() {
        areaMaxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maximumArea = parent.getItemAtPosition(position).toString();
                Log.e("max", maximumArea);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getarea_min() {

        areaMinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minimumArea = parent.getItemAtPosition(position).toString();
                Log.e("max", minimumArea);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getarea_parameter() {
        areaParameterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public void cardClickgp1(View view) {
        CardView cardView = (CardView) view;
        if (count2 == 0) {
            switch (view.getId()) {
                case R.id.flat:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        count--;
                        propertySubTypes.remove("Flat/Apartment");
                    } else {
                        propertySubTypes.add("Flat/Apartment");
                        count++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }
                    break;

                case R.id.house:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        count--;
                        propertySubTypes.remove("House");
                        propertySubTypes.remove("Villa");
                    } else {
                        propertySubTypes.add("House");
                        propertySubTypes.add("Villa");
                        count++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }
                    break;

                case R.id.plot:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        count--;
                        propertySubTypes.remove("Plot");
                    } else {
                        propertySubTypes.add("Plot");
                        count++;
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
        if (count == 0) {
            switch (view.getId()) {
                case R.id.office:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        count2--;
                        propertySubTypes.remove("office");
                        propertySubTypes.remove("IT_Park");
                    } else {
                        propertySubTypes.add("office");
                        propertySubTypes.add("IT_Park");
                        count2++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }

                    break;

                case R.id.shop:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        count2--;
                        propertySubTypes.remove("Shop");
                        propertySubTypes.remove("Showroom");
                    } else {
                        propertySubTypes.add("Shop");
                        propertySubTypes.add("Showroom");
                        count2++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }
                    break;

                case R.id.coworking:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        count2--;
                        propertySubTypes.remove("Coworking_Space");
                    } else {
                        propertySubTypes.add("Coworking_Space");
                        count2++;
                        view.setSelected(true);
                        cardView.setBackgroundColor(Color.LTGRAY);

                    }

                    break;
                case R.id.other:
                    cardView.setBackgroundColor(Color.WHITE);
                    if (cardView.isSelected()) {
                        cardView.setBackgroundColor(Color.WHITE);
                        view.setSelected(false);
                        count2--;
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
                        count2++;
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

    public void next(View view) {
        try {
            if (propertyFor.isEmpty()) {
                Toast.makeText(this, "Select Looking type", Toast.LENGTH_LONG).show();
                return;
            } else if (cList.size() == 0) {
                Toast.makeText(this, "Enter City", Toast.LENGTH_LONG).show();
                return;
            } else {
                try {
                    if (minRupeeString.equals("₹ Min")) {
                        if (maxRupeeString.equals("₹ Max")) {
                            maxRupeeString = "₹ Max";
                        }
                        minRupeeString = "₹ Min";
                    } else {
                        if (maxRupeeString.equals("₹ Max")) {
                            maxRupeeString = "₹ Max";
                        } else {
                            try {
                                if (Integer.parseInt(minRupeeString) > Integer.parseInt(maxRupeeString)) {
                                    Toast.makeText(this, "Minimum price is not greater then Maximum price", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (minimumArea.equals("Min")) {
                        if (maximumArea.equals("Max")) {
                            maximumArea = "";
                        }
                        minimumArea = "";
                    } else {
                        if (maximumArea.equals("Max")) {
                            maximumArea = "";
                        } else {
                            try {
                                if (Integer.parseInt(minimumArea) > Integer.parseInt(maximumArea)) {
                                    Toast.makeText(this, "Minimum area is not greater then Maximum area", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (propertySubTypes.size() != 0) {
                        if (count == 0) {
                            propertyType = "commercial";
                        } else {
                            propertyType = "residential";
                        }
                    }
                    SessionManager session = new SessionManager(getApplicationContext());
                    session.createSearchSession(propertyType, propertyFor);

                    Intent intent = new Intent(Start13_1_2_3_all.this, ViewProperty.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("propertyFor", propertyFor);
                    bundle.putStringArrayList("CITYLIST", (ArrayList<String>) cList);
                    bundle.putStringArrayList("PROPERTYSUBTYPES", (ArrayList<String>) propertySubTypes);
                    bundle.putStringArrayList("BEDROOM", (ArrayList<String>) bedroom);
                    bundle.putString("MINIMUM_RUPEES", minRupeeString);
                    bundle.putString("MAXIMUM_RUPEES", maxRupeeString);
                    bundle.putString("AREA_PARAMETER", areaParameter);
                    bundle.putString("AREA_MINIMUM", minimumArea);
                    bundle.putString("AREA_MAXIMUM", maximumArea);
                    bundle.putString("propertyType", propertyType);
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

            Toast.makeText(Start13_1_2_3_all.this, "Reset", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
