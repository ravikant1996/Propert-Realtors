package com.example.real_estate_business.A_EndUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.real_estate_business.R;
import com.example.real_estate_business.activity.posted_property;
import com.google.android.material.card.MaterialCardView;

public class dashboard extends Fragment {

    CardView show, contacted, lastview, shortlisted;
    public dashboard() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          /*  mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

        show= view.findViewById(R.id.abc);
        shortlisted= view.findViewById(R.id.shortlisted);
        contacted= view.findViewById(R.id.contacted);
        lastview= view.findViewById(R.id.lastview);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), posted_property.class));
            }
        });
        shortlisted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), shortlisted_property.class));
            }
        });
        contacted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), contacted_property.class));
            }
        });
        lastview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), lastview_property.class));
            }
        });







    return view;
    }
}
