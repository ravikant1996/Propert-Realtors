package com.example.real_estate_business.A_EndUser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.real_estate_business.R;
import com.example.real_estate_business.SharedPreference;
import com.example.real_estate_business.model.Image;
import com.example.real_estate_business.model.PropertyModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class favouriteAdapter extends RecyclerView.Adapter<favouriteAdapter.ViewHolder> {
    Context context;
    public List<PropertyModel> arrayList;
    favouriteAdapter adapter;
    String imageAddress;

    public favouriteAdapter(Context context, ArrayList<PropertyModel> propertyModelArrayList) {
        this.context = context;
        this.arrayList = propertyModelArrayList;
        this.adapter = this;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_property_added, parent, false);
        favouriteAdapter.ViewHolder viewHolder = new favouriteAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
//        if (position < arrayList.size()) {
            holder.rupee.setText("₹" + arrayList.get(position).getPrice());
            if (arrayList.get(position).getBedroom().isEmpty()) {
                holder.propertyType.setText(arrayList.get(position).getPropertySubType());
            } else {
                holder.propertyType.setText(arrayList.get(position).getBedroom() + " BHK " + arrayList.get(position).getPropertySubType());
            }
            holder.address.setText(arrayList.get(position).getProject());

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PropertyTable");
            Query query = databaseReference.child(arrayList.get(position).getPropertyType())
                    .child(arrayList.get(position).getKeyId()).child("images").orderByValue().limitToFirst(1);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Image image = new Image("null");
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                            image = areaSnapshot.getValue(Image.class);
                            imageAddress =image.getImageAddress();
                        }
                    } else {
                        imageAddress =image.getImageAddress();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String propertyFor = arrayList.get(position).getPropertyFor();
                    String propertyType = arrayList.get(position).getPropertyType();
                    String PropertySubType = arrayList.get(position).getPropertySubType();
                    String propertyId = arrayList.get(position).getKeyId();
                    String price = arrayList.get(position).getPrice();
                    String bedroom = arrayList.get(position).getBedroom();
                    String locality = arrayList.get(position).getProject();
                    String carpet = arrayList.get(position).getCarpetArea();
                    String propertyStatus = arrayList.get(position).getProperty_status();
                    String bathroom = arrayList.get(position).getBathroom();
                    String floorNo = arrayList.get(position).getFloorNo();
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
                    String balcony = arrayList.get(position).getBalcony();
                    String city = arrayList.get(position).getCity();
                    String carpetAreaParameter, superAreaParameter, plotAreaParameter, maintenance_parameter, roadWidthParameter;
                    carpetAreaParameter = arrayList.get(position).getCarpetAreaParameter();
                    superAreaParameter = arrayList.get(position).getSuperAreaParameter();
                    plotAreaParameter = arrayList.get(position).getPlotAreaParameter();
                    maintenance_parameter = arrayList.get(position).getMaintenance_parameter();
                    roadWidthParameter = arrayList.get(position).getRoadWidthParameter();
                    String refId = arrayList.get(position).getUID();
                    String dateofposting = arrayList.get(position).getDateofposting();
                    String timeofposting = arrayList.get(position).getTimeofposting();

                    String[] strings = {propertyId, propertyFor, propertyType, price, bedroom, locality, carpet, propertyStatus, bathroom,
                            floorNo, furnishing, PropertySubType, availableFrom, ageOfconstruction, boundaryWall, cafateria, construction_done,
                            cornerShop, TotalFloor, gated_colony, lock_in_periodString, main_road_facing, open_Sides, personal_washroom, plotArea, plot_bredth,
                            plot_length, roadWidth, security, superArea, token_amount, washroom, maintenance, balcony, carpetAreaParameter, superAreaParameter,
                            plotAreaParameter, maintenance_parameter, roadWidthParameter, city, imageAddress, refId, dateofposting, timeofposting};
                    Intent intent = null;
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("DATAARRAY", strings);

                    switch (PropertySubType) {
                        case "Flat/Apartment":
                        case "Builder_Floor":
                        case "Pentahouse":
                        case "Studio_Apartment":
                        case "House":
                        case "Farm_House":
                        case "Villa":
                            intent = new Intent(context, Start331AllResidential_View.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            break;
                        case "Plot":
                        case "Commercial_Land":
                        case "Agriculture_Land":
                        case "Industrial_Land":
                            intent = new Intent(context, Start331AllResidential2_View.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            break;

                        case "office":
                        case "IT_Park":
                        case "Shop":
                        case "Showroom":
                        case "Warehouse":
                        case "Industrial_Building":
                        case "Industrial_Shed":
                        case "Coworking_Space":
                            intent = new Intent(context, Start331AllCommercial_View.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            break;
                        default:
                            break;
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new AdapterView.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    try {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext())
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Delete Favourite")
                                .setMessage("Do you want to delete this List?")
                                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SharedPreference sessionManager = new SharedPreference();
                                        String propertyId = arrayList.get(position).getKeyId();
                                        sessionManager.removeFavorite(context, propertyId);
                                        arrayList.remove(position);
                                        adapter.notifyDataSetChanged();
                                        Toast.makeText(context, "clear", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rupee, propertyType, maintenance, Maintenanceheading, area, address, furnishing, plotarea;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rupee = (TextView) itemView.findViewById(R.id.rupee);
            propertyType = (TextView) itemView.findViewById(R.id.propertyType);
            maintenance = (TextView) itemView.findViewById(R.id.maintenance);
            Maintenanceheading = (TextView) itemView.findViewById(R.id.maintenanceheading);
            area = (TextView) itemView.findViewById(R.id.area);
            address = (TextView) itemView.findViewById(R.id.address);
            furnishing = (TextView) itemView.findViewById(R.id.furnishing);
            plotarea = (TextView) itemView.findViewById(R.id.plotarea);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
