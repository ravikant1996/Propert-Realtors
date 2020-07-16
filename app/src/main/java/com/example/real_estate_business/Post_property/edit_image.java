package com.example.real_estate_business.Post_property;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.real_estate_business.R;
import com.example.real_estate_business.activity.MainActivity;
import com.example.real_estate_business.model.Image;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class edit_image extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Image> imageArrayList;
    LinearLayoutManager layoutManager;
    DatabaseReference reference, databaseReference;
    edit1_image_adapter dataAdapter;
    String UID, propertyId, propertyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bac);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(edit_image.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
       /* SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> userID = session.getUserIDs();
        UID = userID.get(SessionManager.KEY_ID);*/

        Bundle bundle = getIntent().getExtras();
        propertyId= bundle.getString("PropertyId");
        propertyType= bundle.getString("propertyType");
        final List<String > imageId1 = new ArrayList<String >();
        final List<String > imageId2 = new ArrayList<String >();

        imageArrayList = new ArrayList<Image>();
        try {
            reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child(propertyType);

            Query query = reference.child(propertyId).child("images").orderByValue();

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        imageId1.add(propertyType);
                        imageId2.add(propertyId);
                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            Image image = areaSnapshot.getValue(Image.class);
                            imageArrayList.add(image);
                        }
                        recyclerView.setHasFixedSize(true);
                        layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        dataAdapter = new edit1_image_adapter(getApplicationContext(), imageId1, imageId2, imageArrayList);
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
