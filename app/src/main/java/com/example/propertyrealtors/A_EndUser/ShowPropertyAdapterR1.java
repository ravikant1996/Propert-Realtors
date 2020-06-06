package com.example.propertyrealtors.A_EndUser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.SessionManager;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.PropertyModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowPropertyAdapterR1 extends RecyclerView.Adapter<ShowPropertyAdapterR1.ShowPropertyAdapterViewHolder> {
    Context context;
    ArrayList<PropertyModel> arrayList= new ArrayList<>();
    ArrayList<Image> imageArrayList =new ArrayList<>();
  //  private static SharedPreferences prefs;


    public ShowPropertyAdapterR1(Context context, ArrayList<PropertyModel> propertyModelArrayList, ArrayList<Image> images) {
        this.context = context;
        this.arrayList = propertyModelArrayList;
        this.imageArrayList = images;
  //      prefs= context.getSharedPreferences("MYPREFS", 0);

    }

    @NonNull
    @Override
    public ShowPropertyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //    prefs = context.getSharedPreferences("MYPREFS", 0);

        View itemView;
        if(viewType == R.layout.layout_show_property){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_show_property, parent, false);
        }
        else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_show_all, parent, false);
        }

        return new ShowPropertyAdapterViewHolder(itemView);

      /*  View view= LayoutInflater.from(context).inflate(R.layout.layout_show_property, parent,false);
        ShowPropertyAdapterR1.ShowPropertyAdapterViewHolder viewHolder = new ShowPropertyAdapterR1.ShowPropertyAdapterViewHolder(view);
        return viewHolder;*/
    }

    @Override
    public void onBindViewHolder(ShowPropertyAdapterViewHolder holder, int position) {
        try {
            if(position != (arrayList.size() + imageArrayList.size()- imageArrayList.size())) {
                if (position < arrayList.size()) {
                    holder.rupee.setText(arrayList.get(position).getPrice());
                    holder.bedroomORSubtype.setText(arrayList.get(position).getBedroom() + " BHK " + arrayList.get(position).getPropertySubType());
                    holder.localityORCity.setText(arrayList.get(position).getProject() + " " + arrayList.get(position).getCity());
                    holder.status.setText(arrayList.get(position).getProperty_status());
                    String urls = imageArrayList.get(position).getImageAddress();
                    Picasso.get()
                            .load(urls)
                            .fit()
                            .placeholder(R.drawable.property_logo)
                            .error(R.drawable.property_logo)
                            .into(holder.imageView);
                    Log.e("ShowPropertyAdapter", urls);
                }
/*    holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String propertyFor=arrayList.get(position).getPropertyFor();
                String propertyType= "commercial";
                String NoImage= "https://firebasestorage.googleapis.com/v0/b/property-realtor.appspot.com/o/noImagefound%2Fdownload.png?alt=media&token=e17c8304-f452-4261-82e2-b5dc2388baf1" ;
                Toast.makeText(context, "Recycler view Click" + position +" "+ imageArrayList.get(position).getImageAddress(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViewPropertyDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("POSITION", position);
                bundle.putString("PropertyFor", propertyFor);
                bundle.putString("propertyType", propertyType);
                intent.putExtras(bundle);
                context.startActivity(intent);
           }
        });*/
                holder.likeButton.setOnLikeListener(new OnLikeListener() {
                    @Override
                    public void liked(LikeButton likeButton) {
                        Toast.makeText(context, "Added in Favourite", Toast.LENGTH_SHORT).show();
             /*   String propertyId= arrayList.get(position).getKeyId();
                SessionManager session = new SessionManager(context);
                HashMap<String, String> userID = session.getUserIDs();
                String id = userID.get(SessionManager.KEY_ID);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");
          */
                    }
                    @Override
                    public void unLiked(LikeButton likeButton) {
                        Toast.makeText(context, "Remove from Favourite", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Under Construction", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size() + imageArrayList.size()- imageArrayList.size() + 1 ;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == (arrayList.size() + imageArrayList.size() - imageArrayList.size())) ? R.layout.layout_show_all : R.layout.layout_show_property;
    }

    public class ShowPropertyAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView rupee, bedroomORSubtype, localityORCity, status;
        ImageView fav;
        ImageView imageView;
        LikeButton likeButton;
        Button call;

        public ShowPropertyAdapterViewHolder(View itemView) {
            super(itemView);
            rupee = (TextView) itemView.findViewById(R.id.money);
            bedroomORSubtype = (TextView) itemView.findViewById(R.id.bedroom_subtype);
            localityORCity = (TextView) itemView.findViewById(R.id.locality_city);
            status = (TextView) itemView.findViewById(R.id.status);
            imageView =  itemView.findViewById(R.id.image);
            likeButton =  itemView.findViewById(R.id.star_button);
            call =  itemView.findViewById(R.id.calling);


          /*  call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Calling", Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }

}
