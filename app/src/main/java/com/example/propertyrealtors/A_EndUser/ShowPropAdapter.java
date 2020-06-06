package com.example.propertyrealtors.A_EndUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.model.PropertyModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowPropAdapter extends FirebaseRecyclerAdapter<PropertyModel, ShowPropAdapter.ShowPropAdapterViewHolder> {


    public ShowPropAdapter(FirebaseRecyclerOptions<PropertyModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public ShowPropAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_show_property, parent, false);

        return new ShowPropAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowPropAdapterViewHolder holder, int position, PropertyModel propertyModel) {

        holder.rupee.setText(propertyModel.getPrice());
        holder.bedroomORSubtype.setText(propertyModel.getBedroom() + " " + propertyModel.getPropertySubType());
        holder.localityORCity.setText(propertyModel.getProject() + " " + propertyModel.getCity());
        holder.status.setText(propertyModel.getProperty_status());
    }


    public class ShowPropAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView rupee, bedroomORSubtype, localityORCity, status;
        ImageView imageView;
        Button call;

        public ShowPropAdapterViewHolder(View itemView) {
            super(itemView);
            rupee = (TextView) itemView.findViewById(R.id.money);
            bedroomORSubtype = (TextView) itemView.findViewById(R.id.bedroom_subtype);
            localityORCity = (TextView) itemView.findViewById(R.id.locality_city);
            status = (TextView) itemView.findViewById(R.id.status);
            imageView = itemView.findViewById(R.id.image);
            call = itemView.findViewById(R.id.calling);

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
