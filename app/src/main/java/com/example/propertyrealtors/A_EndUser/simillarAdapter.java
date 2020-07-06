package com.example.propertyrealtors.A_EndUser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.PropertyModel;
import com.like.LikeButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class simillarAdapter extends RecyclerView.Adapter<simillarAdapter.ViewHolder> {
    Context context;
    ArrayList<PropertyModel> arrayList = new ArrayList<>();
    ArrayList<Image> imageArrayList = new ArrayList<>();

    public simillarAdapter(Context context, ArrayList<PropertyModel> propertyModelArrayList, ArrayList<Image> images) {
        this.context = context;
        this.arrayList = propertyModelArrayList;
        this.imageArrayList = images;
    }

    @Override
    public simillarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_show_property, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull simillarAdapter.ViewHolder holder, int position) {
        try {
            if (position < arrayList.size()) {
                holder.rupee.setText("₹" + arrayList.get(position).getPrice());
                if (arrayList.get(position).getBedroom().isEmpty()) {
                    holder.bedroomORSubtype.setText(arrayList.get(position).getPropertySubType());
                } else {
                    holder.bedroomORSubtype.setText(arrayList.get(position).getBedroom() + " BHK " + arrayList.get(position).getPropertySubType());
                }
                holder.localityORCity.setText(arrayList.get(position).getProject());
                holder.status.setText(arrayList.get(position).getProperty_status());
                String urls = imageArrayList.get(position).getImageAddress();
                Picasso.get()
                        .load(urls)
                        .fit()
                        .placeholder(R.drawable.property_logo)
                        .error(R.drawable.property_logo)
                        .into(holder.imageView);
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
                        String imageAddress = imageArrayList.get(position).getImageAddress();
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
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;
                            case "Plot":
                            case "Commercial_Land":
                            case "Agriculture_Land":
                            case "Industrial_Land":
                                intent = new Intent(context, Start331AllResidential2_View.class);
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
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;

                        }
                    }
                });
            }

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size() + imageArrayList.size() - imageArrayList.size();
    }


    public class
    ViewHolder extends RecyclerView.ViewHolder {
        TextView rupee, bedroomORSubtype, localityORCity, status;
        ImageView imageView;
        LikeButton likeButton;
        Button call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rupee = (TextView) itemView.findViewById(R.id.money);
            bedroomORSubtype = (TextView) itemView.findViewById(R.id.bedroom_subtype);
            localityORCity = (TextView) itemView.findViewById(R.id.locality_city);
            status = (TextView) itemView.findViewById(R.id.status);
            imageView = itemView.findViewById(R.id.image);
            likeButton = itemView.findViewById(R.id.star_button);
            call = itemView.findViewById(R.id.calling);
        }
    }
}
