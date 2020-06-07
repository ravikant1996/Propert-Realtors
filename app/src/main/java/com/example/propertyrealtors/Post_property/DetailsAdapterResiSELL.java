package com.example.propertyrealtors.Post_property;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailsAdapterResiSELL extends RecyclerView.Adapter<DetailsAdapterResiSELL.PropertyDetailsAdapterViewHolder> {
    Context mCntx;
    public List<PropertyModel> arrayList;

    public DetailsAdapterResiSELL(Context mCntx, ArrayList<PropertyModel> propertyModelArrayList) {
        this.mCntx = mCntx;
        this.arrayList = propertyModelArrayList;
    }

    @NonNull
    @Override
    public PropertyDetailsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_property_added, parent, false);
        PropertyDetailsAdapterViewHolder viewHolder = new PropertyDetailsAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyDetailsAdapterViewHolder holder, final int position) {
        PropertyModel propertyModel = arrayList.get(position);

        holder.rupee.setText(arrayList.get(position).getPrice());
        holder.propertySubType.setText(arrayList.get(position).getPropertySubType());
            holder.maintenance.setVisibility(View.GONE);
        holder.area.setText(arrayList.get(position).getCarpetArea());
        holder.address.setText(arrayList.get(position).getProject()+", "+arrayList.get(position).getCity());
        holder.furnishing.setText(arrayList.get(position).getFurnished());
        holder.plotarea.setText(arrayList.get(position).getPlotArea());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String propertyFor="SELL";
                String propertyType= "residential";
                String propertyId = arrayList.get(position).getKeyId();
                String PropertySubType= arrayList.get(position).getPropertySubType();
                String price=arrayList.get(position).getPrice();
                String bedroom = arrayList.get(position).getBedroom()+" BHK "+ arrayList.get(position).getPropertySubType();
                String address = arrayList.get(position).getProject()+", "+arrayList.get(position).getCity();
              //  String covered_area = arrayList.get(position).getSuperArea();
                String carpet = arrayList.get(position).getCarpetArea();
                String propertyStatus = arrayList.get(position).getProperty_status();
                String configuration = arrayList.get(position).getBedroom()+" Bed "
                        +arrayList.get(position).getBathroom()+" Bath "
                        +arrayList.get(position).getBalcony()+" Balcony";
                String floorNo = arrayList.get(position).getFloorNo()+" of "+ arrayList.get(position).getTotalfloor()+" Floor";
                String furnishing = arrayList.get(position).getFurnished();

                String [] strings={propertyId, propertyFor, propertyType, price, bedroom, address,
                        carpet, propertyStatus, configuration,
                        floorNo, furnishing, PropertySubType};
                Toast.makeText(view.getContext(), "Recycler view Click" + propertyId , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCntx, DetailsAdding.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("DATAARRAY", strings);
                /*bundle.putString("POSITION", propertyId);
                bundle.putString("PropertyFor", propertyFor);
                bundle.putString("propertyType", propertyType);*/
                intent.putExtras(bundle);
                mCntx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();    }

    public class PropertyDetailsAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView rupee, propertySubType, maintenance, area, address, furnishing, plotarea;

        public PropertyDetailsAdapterViewHolder(View itemView) {
            super(itemView);
            rupee = (TextView) itemView.findViewById(R.id.rupee);
            propertySubType = (TextView) itemView.findViewById(R.id.propertyType);
            maintenance = (TextView) itemView.findViewById(R.id.maintenance);
            area = (TextView) itemView.findViewById(R.id.area);
            address = (TextView) itemView.findViewById(R.id.address);
            furnishing = (TextView) itemView.findViewById(R.id.furnishing);
            plotarea = (TextView) itemView.findViewById(R.id.plotarea);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
