package com.example.propertyrealtors.City_Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.activity.Adding_City;
import com.example.propertyrealtors.activity.All331ProjectSearch;
import com.example.propertyrealtors.activity.Login;
import com.example.propertyrealtors.activity.Start331All;
import com.example.propertyrealtors.model.City;
import com.example.propertyrealtors.model.Locality;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchPlaceLocalityAdapter extends RecyclerView.Adapter<SearchPlaceLocalityAdapter.SearchPlaceAdapterViewHolder> implements Filterable {
    Context context;
    public List<Locality> arrayList;
    public List<Locality> arrayListFiltered;

    public SearchPlaceLocalityAdapter(Context context, List<Locality> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayListFiltered = arrayList;
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    @Override
    public SearchPlaceAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_locality, parent, false);

        SearchPlaceAdapterViewHolder viewHolder = new SearchPlaceAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchPlaceAdapterViewHolder holder, final int position)
    {
        final Locality place = arrayList.get(position);

        holder.txtPlace.setText(arrayList.get(position).getLocality());

    }


    public class SearchPlaceAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtPlace, add;
        Button Addbtn;

        public SearchPlaceAdapterViewHolder(View itemView) {
            super(itemView);
            txtPlace = (TextView) itemView.findViewById(R.id.locality);
            add = (TextView) itemView.findViewById(R.id.add);
            Addbtn = itemView.findViewById(R.id.addbtn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String project = txtPlace.getText().toString();
                        Intent intent = new Intent(context, Start331All.class);
                        //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        int flag = 2;
                        bundle.putInt("FLAG", flag);
                        bundle.putString("PROJECT_TYPE", project);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                        //  ((Activity)context).finishAndRemoveTask();

                    }catch (AndroidRuntimeException e){
                        e.printStackTrace();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    public Filter getFilter()
    {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Locality> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(arrayListFiltered);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Locality item : arrayListFiltered) {
                    if (item.getLocality().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
                arrayList = filteredList;
            }

            FilterResults results = new FilterResults();
            results.values = arrayList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
         //   arrayList.clear();
         //   arrayList.addAll((List)results.values);
            arrayList = (List<Locality>) results.values;
            notifyDataSetChanged();
        }
    };
}