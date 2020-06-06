package com.example.propertyrealtors.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.Currency_Converter;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.model.City;
import com.example.propertyrealtors.model.Locality;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Adding_City extends AppCompatActivity {
    EditText City, Locality;
    long cid=0;
    long lid=0;
    DatabaseReference reference;
    Locality locality;
    DatabaseReference databaseReference;
    String addcity;
    String addlocality;
    City city;
    TextView textView23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding__city);

        City= findViewById(R.id.city);
        Locality= findViewById(R.id.locality);
        textView23= findViewById(R.id.money);


        addcity = City.getText().toString().trim();
        addlocality = Locality.getText().toString().trim();


        databaseReference= FirebaseDatabase.getInstance().getReference().child("City");
    //    databaseReference= FirebaseDatabase.getInstance().getReference("City");
        City.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = String.valueOf(s);
                convert(ss);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void convert(String num) {
        try {
            BigDecimal bd = new BigDecimal(num);
            long number = bd.longValue();
            long no = bd.longValue();
            int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
            int digits_length = String.valueOf(no).length();
            int i = 0;
            ArrayList<String> str = new ArrayList<>();
            HashMap<Integer, String> words = new HashMap<>();
            words.put(0, "");
            words.put(1, "One");
            words.put(2, "Two");
            words.put(3, "Three");
            words.put(4, "Four");
            words.put(5, "Five");
            words.put(6, "Six");
            words.put(7, "Seven");
            words.put(8, "Eight");
            words.put(9, "Nine");
            words.put(10, "Ten");
            words.put(11, "Eleven");
            words.put(12, "Twelve");
            words.put(13, "Thirteen");
            words.put(14, "Fourteen");
            words.put(15, "Fifteen");
            words.put(16, "Sixteen");
            words.put(17, "Seventeen");
            words.put(18, "Eighteen");
            words.put(19, "Nineteen");
            words.put(20, "Twenty");
            words.put(30, "Thirty");
            words.put(40, "Forty");
            words.put(50, "Fifty");
            words.put(60, "Sixty");
            words.put(70, "Seventy");
            words.put(80, "Eighty");
            words.put(90, "Ninety");
            String digits[] = {"", "Hundred", "Thousand", "Lakh", "Crore", "arab", "kharab", "nil", "padma", "shankh"};
            while (i < digits_length) {
                int divider = (i == 2) ? 10 : 100;
                number = no % divider;
                no = no / divider;
                i += divider == 10 ? 1 : 2;
                if (number > 0) {
                    int counter = str.size();
                    String plural = (counter > 0 && number > 9) ? "s" : "";
                    String tmp = (number < 21) ? words.get(Integer.valueOf((int) number)) + " " + digits[counter] + plural : words.get(Integer.valueOf((int) Math.floor(number / 10) * 10)) + " " + words.get(Integer.valueOf((int) (number % 10))) + " " + digits[counter] + plural;
                    str.add(tmp);
                } else {
                    str.add("");
                }
            }

            Collections.reverse(str);
            String Rupees = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Rupees = String.join(" ", str).trim();
            }

            String paise = (decimal) > 0 ? " And Paise " + words.get(Integer.valueOf((int) (decimal - decimal % 10))) + " " + words.get(Integer.valueOf((int) (decimal % 10))) : " ₹";
            textView23.setText("" + Rupees + paise );
        }catch (NumberFormatException e){
            Toast.makeText(Adding_City.this, "enter", Toast.LENGTH_SHORT).show();
        }
    }
    public void change(View view) {
        String cityy= City.getText().toString().trim();
        if(!cityy.isEmpty()) {
            convert(cityy);
        }
    }
    public void Add(View view) {


        if(City.getText().toString().length()==0){
            City.setError("Enter City");
            Toast.makeText(Adding_City.this, "City?", Toast.LENGTH_SHORT).show();
        }
        else {
            if (Locality.getText().toString().length() == 0) {
                reference = databaseReference.child("CityName");
                Query query= reference.orderByChild("city").equalTo(addcity);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            Toast.makeText(Adding_City.this, "City Existed", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            addcity();
                           }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Adding_City.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                reference = databaseReference.child("Locality").child(addcity);
                Query query= reference.orderByChild("locality").equalTo(addlocality);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(Adding_City.this, "Locality Existed", Toast.LENGTH_SHORT).show();
                        } else {
                            addLocalty();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Adding_City.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }
    public void addLocalty() {
        reference = databaseReference.child("Locality").child(addcity);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    lid = (dataSnapshot.getChildrenCount());
                    locality = new Locality(addlocality);
                    reference.child(String.valueOf(lid + 1)).setValue(locality);
                    //   reference.child("City").setValue(city);
                    City.setText("");
                    Locality.setText("");
                    Toast.makeText(Adding_City.this, "added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void addcity(){

        reference = databaseReference.child("CityName");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    cid = (dataSnapshot.getChildrenCount());
                    city = new City(addcity);
                    reference.child(String.valueOf(cid + 1)).setValue(city);
                    City.setText("");
                    Toast.makeText(Adding_City.this, "added", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}
