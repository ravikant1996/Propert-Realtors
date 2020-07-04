package com.example.propertyrealtors.A_EndUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.propertyrealtors.A_EndUser.view_image_adapter;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.activity.MainActivity;
import com.example.propertyrealtors.activity.Start13;
import com.example.propertyrealtors.model.Image;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class view_images extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Image> imageArrayList;
    LinearLayoutManager layoutManager;
    DatabaseReference reference, databaseReference;
    view_image_adapter dataAdapter;
    String UID, propertyId, propertyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_images);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent= new Intent(view_images.this, MainActivity.class);
                startActivity(intent);*/
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        Bundle bundle = getIntent().getExtras();
        propertyId= bundle.getString("PropertyId");
        propertyType= bundle.getString("propertyType");

        imageArrayList = new ArrayList<Image>();
        try {
            reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child(propertyType);

            Query query = reference.child(propertyId).child("images").orderByValue();

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            Image image = areaSnapshot.getValue(Image.class);
                            imageArrayList.add(image);
//                            dataAdapter.notifyDataSetChanged();
                        }
                        recyclerView.setHasFixedSize(true);
                        layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        dataAdapter = new view_image_adapter(getApplicationContext(), imageArrayList);
                        recyclerView.setAdapter(dataAdapter);
                    }
                }
                @Override
                public void onCancelled (DatabaseError databaseError){
                }
            });
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
