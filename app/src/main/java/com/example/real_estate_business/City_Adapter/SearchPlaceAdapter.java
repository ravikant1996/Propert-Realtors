package com.example.real_estate_business.City_Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.real_estate_business.R;
import com.example.real_estate_business.activity.Start331All;
import com.example.real_estate_business.model.City;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("ALL")
public class SearchPlaceAdapter extends RecyclerView.Adapter<SearchPlaceAdapter.SearchPlaceAdapterViewHolder> implements Filterable {
    Context context;
    public List<City> arrayList;
    public List<City> arrayListFiltered;

    public SearchPlaceAdapter(Context context, List<City> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayListFiltered = arrayList;
        /*arrayListFiltered = new ArrayList<>(arrayList);*/
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    @Override
    public SearchPlaceAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_city, parent, false);

        SearchPlaceAdapterViewHolder viewHolder = new SearchPlaceAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchPlaceAdapterViewHolder holder, final int position)
    {
        final City place = arrayList.get(position);

        holder.txtPlace.setText(arrayList.get(position).getCity());

    }


    public class SearchPlaceAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtPlace;

        public SearchPlaceAdapterViewHolder(View itemView) {
            super(itemView);
            txtPlace = (TextView) itemView.findViewById(R.id.city);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String city = txtPlace.getText().toString();
                        Intent intent = new Intent(context, Start331All.class);
                        //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        int flag = 1;
                        bundle.putInt("FLAG", flag);
                        bundle.putString("CITY_SELECTED", city);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                        //   ((Activity)context).finishAndRemoveTask();
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
            List<City> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(arrayListFiltered);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (City item : arrayListFiltered) {
                    if (item.getCity().toLowerCase().contains(filterPattern)) {
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
            arrayList = (List<City>) results.values;
            notifyDataSetChanged();
        }
    };
}