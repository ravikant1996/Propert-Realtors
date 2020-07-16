package com.example.real_estate_business.Post_property;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;
import com.example.real_estate_business.model.Image;
import com.example.real_estate_business.model.PropertyModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class sell_commercial extends Fragment {

    String UID;
    RecyclerView recyclerView;
    PropertyModel propertyModel;
    ArrayList<PropertyModel> propertyModelArrayList;
    LinearLayoutManager layoutManager;
    DatabaseReference reference;
    DetailsAdapterCommSELL dataAdapter;
    LinearLayoutManager HorizontalLayout;
    String propertyFor;
    String propertyType;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayList<Image> imageArrayList1 = new ArrayList<Image>();

    public sell_commercial() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sell_commercial, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);

        SessionManager session= new SessionManager(getActivity());
        HashMap<String, String> userID = session.getUserIDs();
        UID = userID.get(SessionManager.KEY_ID);

        propertyFor="SELL";
        propertyType= "commercial";
        final ArrayList<Image> imageArrayList1 = new ArrayList<Image>();

        propertyModelArrayList = new ArrayList<PropertyModel>();

        findData();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }
    private void shuffle() {
        Collections.shuffle(propertyModelArrayList, new Random(System.currentTimeMillis()));
        dataAdapter = new DetailsAdapterCommSELL(getActivity(), propertyModelArrayList, imageArrayList1);
        recyclerView.setAdapter(dataAdapter);
    }

    private void findData() {
        try {
            reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child(propertyType);

            Query query = reference.orderByChild("propertyFor").equalTo(propertyFor);

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                        PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                        if(details.getUID().equals(UID)){
                            propertyModelArrayList.add(details);
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
                                            dataAdapter.notifyDataSetChanged();
                                        }
                                    }else {
                                        imageArrayList1.add(image);
                                        dataAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    recyclerView.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    dataAdapter = new DetailsAdapterCommSELL(getActivity(), propertyModelArrayList, imageArrayList1);
                    recyclerView.setAdapter(dataAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
