package com.example.propertyrealtors.Post_property;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.PropertyModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class sell_residential extends Fragment {

    String UID;
    RecyclerView recyclerView;
    PropertyModel propertyModel;
    ArrayList<PropertyModel> propertyModelArrayList;
    LinearLayoutManager layoutManager;
    DatabaseReference reference;
    DetailsAdapterResiSELL dataAdapter;
    ArrayList<PropertyModel> arrPackage;
    SharedPreferences sharedPreferences;
    String propertyFor;
    String propertyType;

    public sell_residential() {
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
        View view= inflater.inflate(R.layout.fragment_sell_residential, container, false);

        sharedPreferences = getActivity().getSharedPreferences("USER",MODE_PRIVATE);
        arrPackage = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);

        SessionManager session= new SessionManager(getActivity());
        HashMap<String, String> userID = session.getUserIDs();
        UID = userID.get(SessionManager.KEY_ID);

        propertyFor="SELL";
        propertyType= "residential";

        propertyModelArrayList = new ArrayList<PropertyModel>();
    /*    Gson gson = new Gson();
        String json = sharedPreferences.getString("Set", "");
        if (json.isEmpty()) {
            findData();
        } else {
            Type type = new TypeToken<List<PropertyModel>>() {
            }.getType();
            List<PropertyModel> arrPackageData = gson.fromJson(json, type);
            for (PropertyModel data : arrPackageData) {
                propertyModelArrayList.add(data);
            }
        }*/
        findData();
      /*  recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataAdapter = new DetailsAdapterResiRorL(getActivity(), propertyModelArrayList);
        recyclerView.setAdapter(dataAdapter);
*/
        return view;
    }

    private void findData() {
        try {
            reference = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child(propertyType);

            Query query = reference.orderByChild("propertyFor").equalTo(propertyFor);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @SuppressLint("ApplySharedPref")
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                        PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                        if(details.getUID().equals(UID)){
                            propertyModelArrayList.add(details);
                            arrPackage.add(details);
                            Gson gson = new Gson();
                            String json = gson.toJson(arrPackage);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Set",json );
                            editor.commit();
                        }
                    }
                    recyclerView.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    dataAdapter = new DetailsAdapterResiSELL(getActivity(), propertyModelArrayList);
                    recyclerView.setAdapter(dataAdapter);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getActivity(), "Something Error", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
