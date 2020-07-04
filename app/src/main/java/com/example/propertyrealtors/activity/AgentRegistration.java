package com.example.propertyrealtors.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AgentRegistration extends AppCompatActivity {

    Spinner operating_since;
    String since;
    ArrayList<String> arrayList;
    Toolbar toolbar;
    List<String> transitionType = new ArrayList<>();
    List<String> propertyType = new ArrayList<>();
    EditText Locality, Address, Contact, AgencyName, Description;
    String locality, address, contact, agencyName, description;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_registration);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Locality = findViewById(R.id.locality);
        Address = findViewById(R.id.address);
        Contact = findViewById(R.id.contact);
        AgencyName = findViewById(R.id.agency_name);
        Description = findViewById(R.id.description);
        operating_since = findViewById(R.id.operating_since);

        arrayList = new ArrayList<>();

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }


        arrayList.add("Operating Since");
        int a = 1960;
        int b = 2020;
        while (a < b) {
            a = a + 10;
            arrayList.add(" " + a);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operating_since.setAdapter(arrayAdapter);
        operating_since.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                since = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Locality.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextInputLayout localityView = findViewById(R.id.localityView);
                if (s.length() >= 3) {
                    localityView.setErrorEnabled(false);
                } else {
                    localityView.setErrorEnabled(true);
                    Locality.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextInputLayout addressView = findViewById(R.id.addressView);
                if (s.length() >= 3) {
                    addressView.setErrorEnabled(false);
                } else {
                    addressView.setErrorEnabled(true);
                    Address.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Contact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextInputLayout contactView = findViewById(R.id.contactView);

                if (s.length() >= 3) {
                    contactView.setErrorEnabled(false);
                } else {
                    contactView.setErrorEnabled(true);
                    Contact.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        AgencyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextInputLayout agencyNmeView = findViewById(R.id.agencyNmeView);

                if (s.length() >= 3) {
                    agencyNmeView.setErrorEnabled(false);
                } else {
                    agencyNmeView.setErrorEnabled(true);
                    AgencyName.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextInputLayout descripView = findViewById(R.id.descripView);

                if (s.length() >= 3) {
                    descripView.setErrorEnabled(false);
                } else {
                    descripView.setErrorEnabled(true);
                    Description.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void next(View view) {
        locality = Locality.getText().toString().trim();
        address = Address.getText().toString().trim();
        contact = Contact.getText().toString().trim();
        agencyName = AgencyName.getText().toString().trim();
        description = Description.getText().toString().trim();
        if (Locality.getText().toString().length() <= 3) {
            Locality.requestFocus();
            TextInputLayout localityView = findViewById(R.id.localityView);
            localityView.setError("Locality shouldn't be blank");

        } else if (Address.getText().toString().length() <= 3) {
            Address.requestFocus();
            TextInputLayout addressView = findViewById(R.id.addressView);
            addressView.setError("Address shouldn't be blank");
        } else if (Contact.getText().toString().length() <= 3) {
            Contact.requestFocus();
            TextInputLayout contactView = findViewById(R.id.contactView);
            contactView.setError("Contact shouldn't be blank");
        } else if (AgencyName.getText().toString().length() <= 3) {
            AgencyName.requestFocus();
            TextInputLayout agencyNmeView = findViewById(R.id.agencyNmeView);
            agencyNmeView.setError("Agency Name shouldn't be blank");
        } else if (Description.getText().toString().length() <= 3) {
            Description.requestFocus();
            TextInputLayout descripView = findViewById(R.id.descripView);
            descripView.setError("Description shouldn't be blank");
        } else if (since.equals("Operating Since")) {
            Toast.makeText(this, "Select Operating since", Toast.LENGTH_SHORT).show();
            return;
        } else if (transitionType.size() == 0) {
            Toast.makeText(this, "Select Transition type", Toast.LENGTH_SHORT).show();
            return;
        } else if (propertyType.size() == 0) {
            Toast.makeText(this, "Select property type", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "great!", Toast.LENGTH_SHORT).show();
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

    public void propertyTypes(View view) {
        TextView textView = (TextView) view;
        switch (view.getId()) {
            case R.id.multistory:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Multistory Apartment");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Multistory Apartment");
                }
                break;
            case R.id.builderfloor:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Builder Floor Apartment");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Builder Floor Apartment");
                }
                break;
            case R.id.residentialhouse:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Residential House");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Residential House");
                }
                break;
            case R.id.villa:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Villa");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Villa");
                }
                break;
            case R.id.residentialplot:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Residential Plot");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Residential Plot");
                }
                break;
            case R.id.pentahouse:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Pentahouse");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Pentahouse");
                }
                break;
            case R.id.villa2:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Commercial Shop");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Commercial Shop");
                }
                break;
            case R.id.residentialplot2:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Commercial Office Space");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Commercial Office Space");
                }
                break;
            case R.id.pentahouse2:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Office in IT Park/SEZ");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Office in IT Park/SEZ");
                }
                break;
            case R.id.villa3:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Studio Apartment");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Studio Apartment");
                }
                break;
            case R.id.residentialplot3:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Commercial Showroom");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Commercial Showroom");
                }
                break;
            case R.id.pentahouse3:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Commercial Land");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Commercial Land");
                }
                break;
            case R.id.villa4:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Warehouse Godown");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Warehouse Godown");
                }
                break;
            case R.id.residentialplot4:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Industrial Land");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Industrial Land");
                }
                break;
            case R.id.pentahouse4:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Industrial Building");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Industrial Building");
                }
                break;
            case R.id.villa5:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Industrial Shed");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Industrial Shed");
                }
                break;
            case R.id.residentialplot5:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Agriculture Land");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Agriculture Land");
                }
                break;
            case R.id.pentahouse5:
                textView.setBackgroundColor(Color.WHITE);
                if (textView.isSelected()) {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    propertyType.remove("Farm House");

                } else {
                    view.setSelected(true);
                    textView.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                    propertyType.add("Farm House");
                }
                break;
        }
    }

    public void transtionType(View view) {
        CardView cardView = (CardView) view;
        switch (view.getId()) {
            case R.id.rent:
                cardView.setBackgroundColor(Color.WHITE);
                TextView rent = findViewById(R.id.rentt);
                if (cardView.isSelected()) {
                    cardView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    transitionType.remove("Rent or Lease");
                    rent.setTextColor(Color.BLACK);
                } else {
                    view.setSelected(true);
                    rent.setTextColor(Color.WHITE);
                    cardView.setBackgroundColor(Color.RED);
                    transitionType.add("Rent or Lease");
                }
                break;
            case R.id.booking:
                TextView book = findViewById(R.id.book);
                cardView.setBackgroundColor(Color.WHITE);
                if (cardView.isSelected()) {
                    cardView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    book.setTextColor(Color.BLACK);
                    transitionType.remove("Original Booking");

                } else {
                    view.setSelected(true);
                    cardView.setBackgroundColor(Color.RED);
                    book.setTextColor(Color.WHITE);
                    transitionType.add("Original Booking");
                }
                break;
            case R.id.launch:
                cardView.setBackgroundColor(Color.WHITE);
                TextView launching = findViewById(R.id.launching);
                if (cardView.isSelected()) {
                    cardView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    transitionType.remove("Pre-Launch");
                    launching.setTextColor(Color.BLACK);

                } else {
                    view.setSelected(true);
                    launching.setTextColor(Color.WHITE);
                    cardView.setBackgroundColor(Color.RED);
                    transitionType.add("Pre-Launch");
                }
                break;
            case R.id.resale:
                cardView.setBackgroundColor(Color.WHITE);
                TextView resaling = findViewById(R.id.resaling);
                if (cardView.isSelected()) {
                    cardView.setBackgroundColor(Color.WHITE);
                    resaling.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    transitionType.remove("Resale");

                } else {
                    view.setSelected(true);
                    resaling.setTextColor(Color.WHITE);
                    cardView.setBackgroundColor(Color.RED);
                    transitionType.add("Resale");
                }
                break;
            case R.id.other:
                cardView.setBackgroundColor(Color.WHITE);
                TextView other = findViewById(R.id.others);
                if (cardView.isSelected()) {
                    cardView.setBackgroundColor(Color.WHITE);
                    other.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    transitionType.remove("Others");

                } else {
                    view.setSelected(true);
                    other.setTextColor(Color.WHITE);
                    cardView.setBackgroundColor(Color.RED);
                    transitionType.add("Others");
                }
                break;
        }
    }
}
