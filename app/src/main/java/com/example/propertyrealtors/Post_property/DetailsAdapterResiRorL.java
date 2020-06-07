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

public class DetailsAdapterResiRorL extends RecyclerView.Adapter<DetailsAdapterResiRorL.PropertyDetailsAdapterViewHolder> {
    Context mCntx;
    public List<PropertyModel> arrayList;

    public DetailsAdapterResiRorL(Context mCntx, ArrayList<PropertyModel> propertyModelArrayList) {
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
        if(!arrayList.get(position).getPropertySubType().equals("Plot")) {
            holder.maintenance.setText(arrayList.get(position).getSecurity());
        }else {
            holder.maintenance.setVisibility(View.GONE);
        }
        holder.area.setText(arrayList.get(position).getCarpetArea());
        holder.address.setText(arrayList.get(position).getProject()+", "+arrayList.get(position).getCity());
        holder.furnishing.setText(arrayList.get(position).getFurnished());
        holder.plotarea.setText(arrayList.get(position).getPlotArea());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String propertyFor="RENT or LEASE";
                String propertyType= "residential";
                String PropertySubType= arrayList.get(position).getPropertySubType();
                String propertyId = arrayList.get(position).getKeyId();
                String price=arrayList.get(position).getPrice();
                String bedroom = arrayList.get(position).getBedroom()+" BHK "+arrayList.get(position).getPropertySubType();
                String address = arrayList.get(position).getProject()+", "+arrayList.get(position).getCity();
                String carpet = arrayList.get(position).getCarpetArea();
                String propertyStatus = arrayList.get(position).getProperty_status();
                String configuration = arrayList.get(position).getBedroom()+" Bed "+arrayList.get(position).getBathroom()+" Bath "+arrayList.get(position).getBalcony();
                String floorNo = arrayList.get(position).getFloorNo()+" of "+arrayList.get(position).getTotalfloor()+" Floor";
                String furnishing = arrayList.get(position).getFurnished();
                String availableFrom = arrayList.get(position).getAvailablefrom();
                String ageOfconstruction = arrayList.get(position).getAgeOfconstruction();
                String boundaryWall = arrayList.get(position).getBoundary_wall();
                String cafateria = arrayList.get(position).getCafateria();
                String construction_done = arrayList.get(position).getConstruction_done();
                String cornerShop = arrayList.get(position).getCornerShop();
                String TotalFloor = arrayList.get(position).getTotalfloor();
                String gated_colony = arrayList.get(position).getGated_colony();
                String lock_in_periodString = arrayList.get(position).getLock_in_periodString();
                String main_road_facing = arrayList.get(position).getMain_road_facing();
                String open_Sides = arrayList.get(position).getOpen_Sides();
                String personal_washroom = arrayList.get(position).getPersonal_washroom();
                String plotArea = arrayList.get(position).getPlotArea();
                String plot_bredth = arrayList.get(position).getPlot_bredth();
                String plot_length = arrayList.get(position).getPlot_length();
                String roadWidth = arrayList.get(position).getRoadWidth();
                String security = arrayList.get(position).getSecurity();
                String superArea = arrayList.get(position).getSuperArea();
                String token_amount = arrayList.get(position).getToken_amount();
                String washroom = arrayList.get(position).getWashroom();
                String maintenance = arrayList.get(position).getMaintenance();


                String [] strings={propertyId, propertyFor, propertyType, price, bedroom, address, carpet, propertyStatus, configuration,
                        floorNo, furnishing, PropertySubType, availableFrom, ageOfconstruction, boundaryWall, cafateria, construction_done,
                        cornerShop, TotalFloor, gated_colony, lock_in_periodString, main_road_facing, open_Sides, personal_washroom, plotArea, plot_bredth,
                plot_length, roadWidth, security, superArea, token_amount, washroom, maintenance};
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
