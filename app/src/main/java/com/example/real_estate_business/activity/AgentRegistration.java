package com.example.real_estate_business.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;
import com.example.real_estate_business.model.Agent;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentRegistration extends AppCompatActivity {

    Spinner operating_since;
    String since;
    ArrayList<String> arrayList;
    Toolbar toolbar;
    DatabaseReference reference;
    SessionManager session;
    List<String> transactionType = new ArrayList<>();
    List<String> propertyType = new ArrayList<>();
    String AddedId;
    EditText Locality, Address, Contact, AgencyName, Description;
    String locality, address, contact, agencyName, description;
    boolean flag = true;
    String usertype, AgentId;
    Agent agent;

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
        reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child("AgentTable");

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> data = session.getUserDetails();
        usertype = data.get(SessionManager.KEY_USERTYPE);
        if (usertype.equals("Agent")) {
            HashMap<String, String> userId = session.getUserIDs();
            AgentId = userId.get(SessionManager.KEY_USERTYPE);
        }
        Locality = findViewById(R.id.locality);
        Address = findViewById(R.id.address);
        Contact = findViewById(R.id.contact);
        AgencyName = findViewById(R.id.agency_name);
        Description = findViewById(R.id.description);
        operating_since = findViewById(R.id.operating_since);

        arrayList = new ArrayList<>();
        checkDetails();

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
        } else if (transactionType.size() == 0) {
            Toast.makeText(this, "Select Transition type", Toast.LENGTH_SHORT).show();
            return;
        } else if (propertyType.size() == 0) {
            Toast.makeText(this, "Select property type", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (AddedId.isEmpty() || AddedId==null) {
                addAgent();
            }else {
                updateDetails();
            }
        }

    }

    private void updateDetails() {
        DatabaseReference db = reference.child(AddedId);
        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("locality", locality);
        updates.put("address", address);
        updates.put("contactPerson", contact);
        updates.put("agencyName", agencyName);
        updates.put("description", description);
        updates.put("operatingSince", since);
        updates.put("transactionType", transactionType);
        updates.put("propertyType", propertyType);
        db.updateChildren(updates);
        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
    }

    private void checkDetails() {
        Query query = reference.orderByChild("agentId").equalTo(AgentId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot areaSnapshot : snapshot.getChildren()) {
                        Agent details = areaSnapshot.getValue(Agent.class);
                        AddedId= details.getId();
                        Locality.setText(details.getLocality());
                        Address.setText(details.getAddress());
                        Contact.setText(details.getContactPerson());
                        AgencyName.setText(details.getAgencyName());
                        Description.setText(details.getDescription());
                        since = details.getOperatingSince();
                        //Spinner
                        operating_since.setSelection(getIndex(operating_since, since));
                        transactionType.addAll(details.getTransactionType());
                        propertyType.addAll(details.getPropertyType());
                        setTransactionTypes();
                        setPropertyTypes();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setPropertyTypes() {
        TextView multistory = findViewById(R.id.multistory);
        TextView builderfloor = findViewById(R.id.builderfloor);
        TextView residentialhouse = findViewById(R.id.residentialhouse);
        TextView villa = findViewById(R.id.villa);
        TextView residentialplot = findViewById(R.id.residentialplot);
        TextView pentahouse = findViewById(R.id.pentahouse);
        TextView villa2 = findViewById(R.id.villa2);
        TextView residentialplot2 = findViewById(R.id.residentialplot2);
        TextView pentahouse2 = findViewById(R.id.pentahouse2);
        TextView villa3 = findViewById(R.id.villa3);
        TextView residentialplot3 = findViewById(R.id.residentialplot3);
        TextView pentahouse3 = findViewById(R.id.pentahouse3);
        TextView villa4 = findViewById(R.id.villa4);
        TextView residentialplot4 = findViewById(R.id.residentialplot4);
        TextView pentahouse4 = findViewById(R.id.pentahouse4);
        TextView villa5 = findViewById(R.id.villa5);
        TextView residentialplot5 = findViewById(R.id.residentialplot5);
        TextView pentahouse5 = findViewById(R.id.pentahouse5);

        if (propertyType.equals("Multistory Apartment")) {
            multistory.setBackgroundColor(Color.BLUE);
            multistory.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Builder Floor Apartment")) {
            builderfloor.setBackgroundColor(Color.BLUE);
            builderfloor.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Residential House")) {
            residentialhouse.setBackgroundColor(Color.BLUE);
            residentialhouse.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Villa")) {
            villa.setBackgroundColor(Color.BLUE);
            villa.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Residential Plot")) {
            residentialplot.setBackgroundColor(Color.BLUE);
            residentialplot.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Pentahouse")) {
            pentahouse.setBackgroundColor(Color.BLUE);
            pentahouse.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Commercial Shop")) {
            villa2.setBackgroundColor(Color.BLUE);
            villa2.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Commercial Office Space")) {
            residentialplot2.setBackgroundColor(Color.BLUE);
            residentialplot2.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Office in IT Park/SEZ")) {
            pentahouse2.setBackgroundColor(Color.BLUE);
            pentahouse2.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Studio Apartment")) {
            villa3.setBackgroundColor(Color.BLUE);
            villa3.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Commercial Showroom")) {
            residentialplot3.setBackgroundColor(Color.BLUE);
            residentialplot3.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Commercial Land")) {
            pentahouse3.setBackgroundColor(Color.BLUE);
            pentahouse3.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Warehouse Godown")) {
            villa4.setBackgroundColor(Color.BLUE);
            villa4.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Industrial Land")) {
            residentialplot4.setBackgroundColor(Color.BLUE);
            residentialplot4.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Industrial Building")) {
            pentahouse4.setBackgroundColor(Color.BLUE);
            pentahouse4.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Industrial Shed")) {
            villa5.setBackgroundColor(Color.BLUE);
            villa5.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Agriculture Land")) {
            residentialplot5.setBackgroundColor(Color.BLUE);
            residentialplot5.setTextColor(Color.WHITE);
        }
        if (propertyType.equals("Farm House")) {
            pentahouse5.setBackgroundColor(Color.BLUE);
            pentahouse5.setTextColor(Color.WHITE);
        }
    }

    private void setTransactionTypes() {
        CardView rent = findViewById(R.id.rent);
        TextView rentText = findViewById(R.id.rentt);
        CardView booking = findViewById(R.id.booking);
        TextView book = findViewById(R.id.book);
        CardView launch = findViewById(R.id.launch);
        TextView launching = findViewById(R.id.launching);
        CardView resale = findViewById(R.id.resale);
        TextView resaling = findViewById(R.id.resaling);
        CardView other = findViewById(R.id.other);
        TextView others = findViewById(R.id.others);

        if (transactionType.equals("Rent or Lease")) {
            rent.setBackgroundColor(Color.RED);
            rentText.setTextColor(Color.WHITE);
        } else if (transactionType.equals("Original Booking")) {
            booking.setBackgroundColor(Color.RED);
            book.setTextColor(Color.WHITE);
        } else if (transactionType.equals("Pre-Launch")) {
            launch.setBackgroundColor(Color.RED);
            launching.setTextColor(Color.WHITE);
        } else if (transactionType.equals("Resale")) {
            resale.setBackgroundColor(Color.RED);
            resaling.setTextColor(Color.WHITE);
        } else if (transactionType.equals("Others")) {
            other.setBackgroundColor(Color.RED);
            others.setTextColor(Color.WHITE);
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

    private void addAgent() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child("AgentTable");
        String id = ref.getKey();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                agent = new Agent(id, AgentId, locality, address, contact, agencyName, description, since, transactionType, propertyType);
                ref.child(id).setValue(agent);
                Intent intent = new Intent(AgentRegistration.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AgentRegistration.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
                    transactionType.remove("Rent or Lease");
                    rent.setTextColor(Color.BLACK);
                } else {
                    view.setSelected(true);
                    rent.setTextColor(Color.WHITE);
                    cardView.setBackgroundColor(Color.RED);
                    transactionType.add("Rent or Lease");
                }
                break;
            case R.id.booking:
                TextView book = findViewById(R.id.book);
                cardView.setBackgroundColor(Color.WHITE);
                if (cardView.isSelected()) {
                    cardView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    book.setTextColor(Color.BLACK);
                    transactionType.remove("Original Booking");

                } else {
                    view.setSelected(true);
                    cardView.setBackgroundColor(Color.RED);
                    book.setTextColor(Color.WHITE);
                    transactionType.add("Original Booking");
                }
                break;
            case R.id.launch:
                cardView.setBackgroundColor(Color.WHITE);
                TextView launching = findViewById(R.id.launching);
                if (cardView.isSelected()) {
                    cardView.setBackgroundColor(Color.WHITE);
                    view.setSelected(false);
                    transactionType.remove("Pre-Launch");
                    launching.setTextColor(Color.BLACK);

                } else {
                    view.setSelected(true);
                    launching.setTextColor(Color.WHITE);
                    cardView.setBackgroundColor(Color.RED);
                    transactionType.add("Pre-Launch");
                }
                break;
            case R.id.resale:
                cardView.setBackgroundColor(Color.WHITE);
                TextView resaling = findViewById(R.id.resaling);
                if (cardView.isSelected()) {
                    cardView.setBackgroundColor(Color.WHITE);
                    resaling.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    transactionType.remove("Resale");

                } else {
                    view.setSelected(true);
                    resaling.setTextColor(Color.WHITE);
                    cardView.setBackgroundColor(Color.RED);
                    transactionType.add("Resale");
                }
                break;
            case R.id.other:
                cardView.setBackgroundColor(Color.WHITE);
                TextView other = findViewById(R.id.others);
                if (cardView.isSelected()) {
                    cardView.setBackgroundColor(Color.WHITE);
                    other.setTextColor(Color.BLACK);
                    view.setSelected(false);
                    transactionType.remove("Others");

                } else {
                    view.setSelected(true);
                    other.setTextColor(Color.WHITE);
                    cardView.setBackgroundColor(Color.RED);
                    transactionType.add("Others");
                }
                break;
        }
    }
}
