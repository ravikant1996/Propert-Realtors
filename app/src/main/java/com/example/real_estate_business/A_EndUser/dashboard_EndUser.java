package com.example.real_estate_business.A_EndUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SessionManager;
import com.example.real_estate_business.activity.searchFilter_1;
import com.example.real_estate_business.model.Image;
import com.example.real_estate_business.model.PropertyModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tuyenmonkey.mkloader.MKLoader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class dashboard_EndUser extends Fragment {
    ShimmerFrameLayout LoadingView;
    TextView HistoryTextView, textView33, textView34, textView35, textView36, textView37;
    ArrayList<PropertyModel> propertyModelArrayList1 = new ArrayList<PropertyModel>();
    ArrayList<Image> imageArrayList1 = new ArrayList<Image>();
    ArrayList<PropertyModel> propertyModelArrayList2 = new ArrayList<PropertyModel>();
    ArrayList<Image> imageArrayList2 = new ArrayList<Image>();
    ArrayList<PropertyModel> propertyModelArrayList3 = new ArrayList<PropertyModel>();
    ArrayList<Image> imageArrayList3 = new ArrayList<Image>();
    ArrayList<PropertyModel> propertyModelArrayList4 = new ArrayList<PropertyModel>();
    ArrayList<Image> imageArrayList4 = new ArrayList<Image>();
    ArrayList<PropertyModel> propertyModelArrayList5 = new ArrayList<PropertyModel>();
    ArrayList<Image> imageArrayList5 = new ArrayList<Image>();

    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6;
    TextView show1, show2, show3, show4, show5;
    View view8, view9, view10, view11, view12;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    // Linear Layout Manager
    LinearLayoutManager horizontalLayout;
    ShowPropertyAdapterR1 adapterR1;
    ShowPropertyAdapterR2 adapterR2;
    ShowPropertyAdapterR3 adapterR3;
    ShowPropertyAdapterR4 adapterR4;
    ShowPropertyAdapterR5 adapterR5;
    DatabaseReference reference, databaseReference;
    String propertyType, UID, propertyFor;
    ImageView imageView;
    Set<Integer> set = new LinkedHashSet<Integer>();

    public dashboard_EndUser() {
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
        View view = inflater.inflate(R.layout.fragment_dashboard__end_user, container, false);
        getActivity().setTitle("Home");


        SessionManager session = new SessionManager(getActivity());
        HashMap<String, String> userID = session.getPropertySearchSession();
        propertyType = userID.get(SessionManager.PROPERTYTYPE_KEY);
        propertyFor = userID.get(SessionManager.PROPERTYFOR_KEY);
        HashMap<String, String> id = session.getUserIDs();
        UID = id.get(SessionManager.KEY_ID);

        try {
            if (propertyFor == null || propertyFor.isEmpty()) {
                propertyFor = "SELL";
            }
            if (propertyType == null || propertyType.isEmpty()) {
                propertyType = "residential";
            }
            Log.e("EndUser propertyFor", propertyFor);
            Log.e("EndUser propertyType", propertyType);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        TextView searchTextView = view.findViewById(R.id.searchBar);
        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   startActivity(new Intent(getActivity(), searchFilter_1.class));
                Intent intent = new Intent(getActivity(), searchFilter_1.class);
                startActivity(intent);

               /* Intent i = new Intent(getActivity(), searchFilter_1.class);
                startActivityForResult(i, 1);
           */
            }
        });
        bindViews(view);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");

        recycler1();
        recycler2();
        recycler3();
        recycler4();
        recycler5();
        return view;
    }

    private void bindViews(View view) {
        LoadingView = view.findViewById(R.id.loadingView);
        LoadingView.setVisibility(View.VISIBLE);

        show1 = view.findViewById(R.id.show1);
        show2 = view.findViewById(R.id.show2);
        show3 = view.findViewById(R.id.show3);
        show4 = view.findViewById(R.id.show4);
        show5 = view.findViewById(R.id.show5);

        HistoryTextView = view.findViewById(R.id.top);
        textView33 = view.findViewById(R.id.textView33);
        textView34 = view.findViewById(R.id.textView34);
        textView35 = view.findViewById(R.id.textView35);
        textView36 = view.findViewById(R.id.textView36);
        textView37 = view.findViewById(R.id.textView37);

        view8 = view.findViewById(R.id.view8);
        view9 = view.findViewById(R.id.view9);
        view10 = view.findViewById(R.id.view10);
        view11 = view.findViewById(R.id.view11);
        view12 = view.findViewById(R.id.view12);

        view8.setVisibility(View.GONE);
        view9.setVisibility(View.GONE);
        view10.setVisibility(View.GONE);
        view11.setVisibility(View.GONE);
        view12.setVisibility(View.GONE);

        textView33.setVisibility(View.GONE);
        textView34.setVisibility(View.GONE);
        textView35.setVisibility(View.GONE);
        textView36.setVisibility(View.GONE);
        textView37.setVisibility(View.GONE);

        show1.setVisibility(View.GONE);
        show2.setVisibility(View.GONE);
        show3.setVisibility(View.GONE);
        show4.setVisibility(View.GONE);
        show5.setVisibility(View.GONE);

        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView3 = view.findViewById(R.id.recyclerView3);
        recyclerView4 = view.findViewById(R.id.recyclerView4);
        recyclerView5 = view.findViewById(R.id.recyclerView5);
        recyclerView6 = view.findViewById(R.id.recyclerView6);


    }

    @Override
    public void onStart() {
        super.onStart();
        recycler1();
        recycler2();
        recycler3();
        recycler4();
        recycler5();
    }

    @Override
    public void onPause() {
        super.onPause();
        LoadingView.stopShimmer();

    }


    @Override
    public void onResume() {
        super.onResume();
        LoadingView.startShimmer();
        //
        if (propertyModelArrayList1.size() != 0) {
            adapterR1 = new ShowPropertyAdapterR1(getActivity(), propertyModelArrayList1, imageArrayList1);
            horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView1.setLayoutManager(horizontalLayout);
            recyclerView1.setAdapter(adapterR1);
        }
        //
        if (propertyModelArrayList2.size() != 0) {

            adapterR2 = new ShowPropertyAdapterR2(getActivity(), propertyModelArrayList2, imageArrayList2);
            horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView2.setLayoutManager(horizontalLayout);
            recyclerView2.setAdapter(adapterR2);
        }
        //
        if (propertyModelArrayList3.size() != 0) {
            adapterR3 = new ShowPropertyAdapterR3(getActivity(), propertyModelArrayList3, imageArrayList3);
            horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView3.setLayoutManager(horizontalLayout);
            recyclerView3.setAdapter(adapterR3);
        }
        //
        if (propertyModelArrayList4.size() != 0) {
            adapterR4 = new ShowPropertyAdapterR4(getActivity(), propertyModelArrayList4, imageArrayList4);
            horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView4.setLayoutManager(horizontalLayout);
            recyclerView4.setAdapter(adapterR4);
        }
        //
        if (propertyModelArrayList5.size() != 0) {
            adapterR5 = new ShowPropertyAdapterR5(getActivity(), propertyModelArrayList5, imageArrayList5);
            horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView5.setLayoutManager(horizontalLayout);
            recyclerView5.setAdapter(adapterR5);
        }
    }


    public void recycler1() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        // Set LayoutManager on Recycler View
        recyclerView1.setLayoutManager(RecyclerViewLayoutManager);

        // layout visibility
        // Adding items to RecyclerView.
        reference = databaseReference.child(propertyType);

        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        LoadingView.stopShimmer();
                        LoadingView.setVisibility(View.INVISIBLE);
                        textView33.setVisibility(View.VISIBLE);
                        show1.setVisibility(View.VISIBLE);
                        view8.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            ArrayList<PropertyModel> list = new ArrayList<>();
                            list.add(details);
                            Collections.shuffle(list);
                            Log.e("List",""+list);
                            for (PropertyModel model : list) {
                                if (!propertyModelArrayList1.contains(model)) {
                                    if (propertyModelArrayList1.size() < 5) {
                                        propertyModelArrayList1.add(model);
                                        String id1 = model.getKeyId();
                                        getImage(id1);
                                    }
                                }
                            }
                        }
                    } else {
                        recyclerView1.setVisibility(View.GONE);
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
                recyclerView1.setHasFixedSize(true);
                adapterR1 = new ShowPropertyAdapterR1(getActivity(), propertyModelArrayList1, imageArrayList1);
                horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView1.setLayoutManager(horizontalLayout);
                //       recyclerView1.setItemAnimator(new DefaultItemAnimator());
                recyclerView1.setAdapter(adapterR1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getImage(String id1) {
        reference = databaseReference.child(propertyType);
        Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Image image = new Image("null");
                if (dataSnapshot.exists()) {
                    //          for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    image = dataSnapshot.getValue(Image.class);
                    imageArrayList1.add(image);
                    adapterR1.notifyDataSetChanged();
//                    }
                } else {
                    imageArrayList1.add(image);
                    adapterR1.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void recycler2() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());

        // Set LayoutManager on Recycler View
        recyclerView2.setLayoutManager(RecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        LoadingView.stopShimmer();
                        LoadingView.setVisibility(View.INVISIBLE);
                        textView34.setVisibility(View.VISIBLE);
                        show2.setVisibility(View.VISIBLE);
                        view9.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            ArrayList<PropertyModel> list2 = new ArrayList<>();
                            list2.add(details);
                            Collections.shuffle(list2);
                            Log.e("List2",""+list2);

                            for (PropertyModel model : list2) {
                                if (!propertyModelArrayList2.contains(model)) {
                                    if (propertyModelArrayList2.size() < 5) {
                                        propertyModelArrayList2.add(model);
                                        String id1 = model.getKeyId();

                                        Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
                                        query.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                                                Image image = new Image("null");
                                                if (dataSnapshot.exists()) {
                                                    //          for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                                    image = dataSnapshot.getValue(Image.class);
                                                    imageArrayList2.add(image);
                                                    adapterR2.notifyDataSetChanged();

                                                } else {
                                                    imageArrayList2.add(image);
                                                    adapterR2.notifyDataSetChanged();
                                                }
                                            }

                                            @Override
                                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                            }

                                            @Override
                                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }

                                        });
                                    }
                                }
                            }
                        }
                    } else {
                        recyclerView2.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recyclerView2.setHasFixedSize(true);
                adapterR2 = new ShowPropertyAdapterR2(getActivity(), propertyModelArrayList2, imageArrayList2);
                horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView2.setLayoutManager(horizontalLayout);
                recyclerView2.setItemAnimator(new DefaultItemAnimator());
                recyclerView2.setAdapter(adapterR2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

    public void recycler3() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());

        // Set LayoutManager on Recycler View
        recyclerView3.setLayoutManager(RecyclerViewLayoutManager);
        reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        LoadingView.stopShimmer();
                        LoadingView.setVisibility(View.INVISIBLE);
                        textView35.setVisibility(View.VISIBLE);
                        show3.setVisibility(View.VISIBLE);
                        view10.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            ArrayList<PropertyModel> list3 = new ArrayList<>();
                            list3.add(details);
                            Collections.shuffle(list3);
                            for (PropertyModel model : list3) {
                                if (!propertyModelArrayList3.contains(model)) {
                                    if (propertyModelArrayList3.size() < 5) {
                                        propertyModelArrayList3.add(model);
                                        String id1 = model.getKeyId();
                                        Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
                                        query.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                                                Image image = new Image("null");
                                                if (dataSnapshot.exists()) {
                                                    //          for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                                    image = dataSnapshot.getValue(Image.class);
                                                    imageArrayList3.add(image);
                                                    adapterR3.notifyDataSetChanged();

                                                } else {
                                                    imageArrayList3.add(image);
                                                    adapterR3.notifyDataSetChanged();
                                                }
                                            }

                                            @Override
                                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                            }

                                            @Override
                                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }

                                        });
                                    }
                                }

                            }
                        }
                    } else {
                        recyclerView3.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recyclerView3.setHasFixedSize(true);
                horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView3.setLayoutManager(horizontalLayout);
                recyclerView3.setItemAnimator(new DefaultItemAnimator());
                adapterR3 = new ShowPropertyAdapterR3(getActivity(), propertyModelArrayList3, imageArrayList3);
                recyclerView3.setAdapter(adapterR3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }

    public void recycler4() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());

        // Set LayoutManager on Recycler View
        recyclerView4.setLayoutManager(RecyclerViewLayoutManager);

        reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        LoadingView.stopShimmer();
                        LoadingView.setVisibility(View.INVISIBLE);
                        textView36.setVisibility(View.VISIBLE);
                        show4.setVisibility(View.VISIBLE);
                        view11.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            ArrayList<PropertyModel> list4 = new ArrayList<>();
                            list4.add(details);
                            Collections.shuffle(list4);
                            if (details.getProperty_status().equals("Immediately") ||
                                    details.getProperty_status().equals("Ready to Move")) {
                                for (PropertyModel model : list4) {
                                    if (!propertyModelArrayList4.contains(model)) {
                                        if (propertyModelArrayList4.size() < 5) {
                                            propertyModelArrayList4.add(model);
                                            String id1 = model.getKeyId();
                                            Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
                                            query.addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                                                    Image image = new Image("null");
                                                    if (dataSnapshot.exists()) {
                                                        //          for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                                        image = dataSnapshot.getValue(Image.class);
                                                        imageArrayList4.add(image);
                                                        adapterR4.notifyDataSetChanged();

                                                    } else {
                                                        imageArrayList4.add(image);
                                                        adapterR4.notifyDataSetChanged();


                                                    }
                                                }

                                                @Override
                                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                                }

                                                @Override
                                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                                }

                                                @Override
                                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }

                                            });
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        recyclerView4.setVisibility(View.GONE);
                        textView36.setVisibility(View.GONE);
                        show4.setVisibility(View.GONE);
                        view11.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recyclerView4.setHasFixedSize(true);
                adapterR4 = new ShowPropertyAdapterR4(getActivity(), propertyModelArrayList4, imageArrayList4);
                horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView4.setLayoutManager(horizontalLayout);
                recyclerView4.setItemAnimator(new DefaultItemAnimator());
                recyclerView4.setAdapter(adapterR4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

    public void recycler5() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());

        // Set LayoutManager on Recycler View
        recyclerView5.setLayoutManager(RecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        LoadingView.stopShimmer();
                        LoadingView.setVisibility(View.INVISIBLE);
                        textView37.setVisibility(View.VISIBLE);
                        show5.setVisibility(View.VISIBLE);
                        view12.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            ArrayList<PropertyModel> list5 = new ArrayList<>();
                            list5.add(details);
                            Collections.shuffle(list5);
                            for (PropertyModel model : list5) {
                                if (!propertyModelArrayList5.contains(model)) {
                                    if (propertyModelArrayList5.size() < 5) {
                                        propertyModelArrayList5.add(model);
                                        String id1 = model.getKeyId();
                                        Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
                                        query.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                                                Image image = new Image("null");
                                                if (dataSnapshot.exists()) {
                                                    //          for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                                    image = dataSnapshot.getValue(Image.class);
                                                    imageArrayList5.add(image);
                                                    adapterR5.notifyDataSetChanged();

                                                } else {
                                                    imageArrayList5.add(image);
                                                    adapterR5.notifyDataSetChanged();
                                                }
                                            }

                                            @Override
                                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                            }

                                            @Override
                                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                }
                            }
                        }
                    } else {
                        recyclerView5.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recyclerView5.setHasFixedSize(true);
                adapterR5 = new ShowPropertyAdapterR5(getActivity(), propertyModelArrayList5, imageArrayList5);
                horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView5.setLayoutManager(horizontalLayout);
                recyclerView5.setItemAnimator(new DefaultItemAnimator());
                recyclerView5.setAdapter(adapterR5);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

   /*
    @Override
    protected void onStart(){
      super.onStart();
      if(reference != null){
          reference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  if (dataSnapshot.exists()){
                      for(DataSnapshot ds : dataSnapshot.getChildren()){
                          propertyModelArrayList.add(ds.getValue(PropertyModel.class));
                      }
                      adapter = new ShowPropertyAdapter(getActivity(), propertyModelArrayList);
                      recyclerView1.setAdapter(adapter);
                  }
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          })
      }
    }
    */

/*
   database.child("usuario").addValueEventListener(new ValueEventListener() {
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                for (DataSnapshot messageSnapshot: snapshot.child("mensagem").getChildren()) {
                    listView.add(messageSnapshot.child("textoMensagem").getValue().toString());
                }
            }
        }
*/


}

