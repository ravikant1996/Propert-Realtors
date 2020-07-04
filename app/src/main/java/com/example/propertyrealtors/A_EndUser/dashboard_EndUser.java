package com.example.propertyrealtors.A_EndUser;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.activity.searchFilter_1;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.PropertyModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class dashboard_EndUser extends Fragment {
    private TextView SearchTextView;
    TextView HistoryTextView, textView33, textView34, textView35, textView36, textView37;
    ArrayList<PropertyModel> propertyModelArrayList;
    ArrayList<PropertyModel> list;
    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6;
    MKLoader loader, loader2, loader3;
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
    String propertyType, propertyFor;
    ImageView imageView;

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


        SessionManager session= new SessionManager(getActivity());
        HashMap<String, String> userID = session.getPropertySearchSession();
        propertyType = userID.get(SessionManager.PROPERTYTYPE_KEY);
        propertyFor = userID.get(SessionManager.PROPERTYFOR_KEY);

        try {
            if (propertyFor == null || propertyFor.isEmpty()) {
                propertyFor = "SELL";
            }
            if ( propertyType == null || propertyType.isEmpty()) {
                propertyType = "residential";
            }
            Log.e("EndUser propertyFor", propertyFor);
            Log.e("EndUser propertyType", propertyType);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        SearchTextView = view.findViewById(R.id.searchBar);
        SearchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   startActivity(new Intent(getActivity(), searchFilter_1.class));
                Intent intent = new Intent(getActivity(), searchFilter_1.class);
                startActivity(intent);

               /* Intent i = new Intent(getActivity(), searchFilter_1.class);
                startActivityForResult(i, 1);
           */ }
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
        imageView = view.findViewById(R.id.gif);
        /* from internet*/

        show1 = view.findViewById(R.id.show1);
        show2 = view.findViewById(R.id.show2);
        show3 = view.findViewById(R.id.show3);
        show4 = view.findViewById(R.id.show4);
        show5 = view.findViewById(R.id.show5);

        HistoryTextView= view.findViewById(R.id.top);
        textView33= view.findViewById(R.id.textView33);
        textView34= view.findViewById(R.id.textView34);
        textView35= view.findViewById(R.id.textView35);
        textView36= view.findViewById(R.id.textView36);
        textView37= view.findViewById(R.id.textView37);

        view8  = view.findViewById(R.id.view8);
        view9  = view.findViewById(R.id.view9);
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

        loader = view.findViewById(R.id.loader);
        loader2 = view.findViewById(R.id.loader2);
        loader3 = view.findViewById(R.id.loader3);
        loader.setVisibility(View.VISIBLE);
        loader2.setVisibility(View.VISIBLE);
        loader3.setVisibility(View.VISIBLE);
    }
    public void recycler1() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        // Set LayoutManager on Recycler View
        recyclerView1.setLayoutManager(RecyclerViewLayoutManager);
        final ArrayList<PropertyModel> propertyModelArrayList1 = new ArrayList<PropertyModel>();
        final ArrayList<Image> imageArrayList1 = new ArrayList<Image>();
        // layout visibility
        // Adding items to RecyclerView.
        reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor).limitToFirst(5);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        loader.setVisibility(View.INVISIBLE);
                        loader2.setVisibility(View.INVISIBLE);
                        loader3.setVisibility(View.INVISIBLE);
                        textView33.setVisibility(View.VISIBLE);
                        show1.setVisibility(View.VISIBLE);
                        view8.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            propertyModelArrayList1.add(details);
                       //     adapter.addPropertyList(details);

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
                                            adapterR1.notifyDataSetChanged();
                                        }
                                    }else {
                                        imageArrayList1.add(image);
                                        adapterR1.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        // }
                    }else {
                        recyclerView1.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
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
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Opsss......", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void recycler2() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());

        // Set LayoutManager on Recycler View
        recyclerView2.setLayoutManager(RecyclerViewLayoutManager);
        final ArrayList<PropertyModel> propertyModelArrayList2 = new ArrayList<PropertyModel>();
        final ArrayList<Image> imageArrayList2 = new ArrayList<Image>();

        // Adding items to RecyclerView.
        reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor).limitToFirst(5);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        loader.setVisibility(View.INVISIBLE);
                        loader2.setVisibility(View.INVISIBLE);
                        loader3.setVisibility(View.INVISIBLE);
                        textView34.setVisibility(View.VISIBLE);
                        show2.setVisibility(View.VISIBLE);
                        view9.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            propertyModelArrayList2.add(details);
                            String id1 = details.getKeyId();
                            Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Image image = new Image("null");
                                    if (dataSnapshot.exists()) {
                                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                            image = areaSnapshot.getValue(Image.class);
                                            imageArrayList2.add(image);
                                            adapterR2.notifyDataSetChanged();
                                        }
                                    } else {
                                        imageArrayList2.add(image);
                                        adapterR2.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }else {
                        recyclerView2.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recyclerView1.setHasFixedSize(true);
                adapterR2 = new ShowPropertyAdapterR2(getActivity(), propertyModelArrayList2, imageArrayList2);
                horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView2.setLayoutManager(horizontalLayout);
                recyclerView2.setItemAnimator(new DefaultItemAnimator());
                recyclerView2.setAdapter(adapterR2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void recycler3() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());

        // Set LayoutManager on Recycler View
        recyclerView3.setLayoutManager(RecyclerViewLayoutManager);
        final ArrayList<PropertyModel> propertyModelArrayList3 = new ArrayList<PropertyModel>();
        final ArrayList<Image> imageArrayList3 = new ArrayList<Image>();
        reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor).limitToFirst(5);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        loader.setVisibility(View.INVISIBLE);
                        loader2.setVisibility(View.INVISIBLE);
                        loader3.setVisibility(View.INVISIBLE);
                        textView35.setVisibility(View.VISIBLE);
                        show3.setVisibility(View.VISIBLE);
                        view10.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            propertyModelArrayList3.add(details);

                            String id1 = details.getKeyId();
                            Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Image image = new Image("null");
                                    if (dataSnapshot.exists()) {
                                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                            image = areaSnapshot.getValue(Image.class);
                                            imageArrayList3.add(image);
                                            adapterR3.notifyDataSetChanged();
                                        }
                                    } else {
                                        imageArrayList3.add(image);
                                        adapterR3.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }else {
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
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void recycler4() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());

        // Set LayoutManager on Recycler View
        recyclerView4.setLayoutManager(RecyclerViewLayoutManager);
        final ArrayList<PropertyModel> propertyModelArrayList4 = new ArrayList<PropertyModel>();
        final ArrayList<Image> imageArrayList4 = new ArrayList<Image>();

        reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor).limitToFirst(5);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        loader.setVisibility(View.INVISIBLE);
                        loader2.setVisibility(View.INVISIBLE);
                        loader3.setVisibility(View.INVISIBLE);
                        textView36.setVisibility(View.VISIBLE);
                        show4.setVisibility(View.VISIBLE);
                        view11.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            propertyModelArrayList4.add(details);

                            String id1 = details.getKeyId();
                            Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Image image = new Image("null");
                                    if (dataSnapshot.exists()) {
                                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                            image = areaSnapshot.getValue(Image.class);
                                            imageArrayList4.add(image);
                                            adapterR4.notifyDataSetChanged();

                                        }
                                    } else {
                                          imageArrayList4.add(image);
                                        adapterR4.notifyDataSetChanged();


                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }else {
                        recyclerView4.setVisibility(View.GONE);
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
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void recycler5() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());

        // Set LayoutManager on Recycler View
        recyclerView5.setLayoutManager(RecyclerViewLayoutManager);
        final ArrayList<PropertyModel> propertyModelArrayList5 = new ArrayList<PropertyModel>();
        final ArrayList<Image> imageArrayList5 = new ArrayList<Image>();

        // Adding items to RecyclerView.
        reference = databaseReference.child(propertyType);
        Query query = reference.orderByChild("propertyFor").equalTo(propertyFor).limitToFirst(5);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        loader.setVisibility(View.INVISIBLE);
                        loader2.setVisibility(View.INVISIBLE);
                        loader3.setVisibility(View.INVISIBLE);
                        textView37.setVisibility(View.VISIBLE);
                        show5.setVisibility(View.VISIBLE);
                        view12.setVisibility(View.VISIBLE);

                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            PropertyModel details = areaSnapshot.getValue(PropertyModel.class);
                            propertyModelArrayList5.add(details);

                            String id1 = details.getKeyId();
                            Query query = reference.child(id1).child("images").orderByValue().limitToFirst(1);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Image image = new Image("null");
                                    if (dataSnapshot.exists()) {
                                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                                            image = areaSnapshot.getValue(Image.class);
                                            imageArrayList5.add(image);
                                            adapterR5.notifyDataSetChanged();

                                        }
                                    } else {
                                          imageArrayList5.add(image);
                                        adapterR5.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }else {
                        recyclerView5.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recyclerView5.setHasFixedSize(true);
                adapterR5 = new ShowPropertyAdapterR5(getActivity(), propertyModelArrayList5 ,imageArrayList5);
                horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView5.setLayoutManager(horizontalLayout);
                recyclerView5.setItemAnimator(new DefaultItemAnimator());
                recyclerView5.setAdapter(adapterR5);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
/*

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {

    if (requestCode == 1) {
        Log.e("FragmentA.java","onActivityResult called");
        if (resultCode == Activity.RESULT_OK) {
            propertyFor = data.getStringExtra("PROPERTY_FOR");
            propertyType = data.getStringExtra("PROPERTY_TYPE");
        }else {
            propertyFor = "SELL";
            propertyType = "residential";
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        }
    }
}
*/

}

