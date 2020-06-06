package com.example.propertyrealtors.A_EndUser;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.PropertyModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowPropertyAdapterR5 extends RecyclerView.Adapter<ShowPropertyAdapterR5.ShowPropertyAdapterViewHolder> {
    Context context;
    ArrayList<PropertyModel> arrayList= new ArrayList<>();
    ArrayList<Image> imageArrayList =new ArrayList<>();
    LayoutInflater inflater;

    public ShowPropertyAdapterR5(Context context, ArrayList<PropertyModel> propertyModelArrayList, ArrayList<Image> images) {
        this.context = context;
//        inflater = LayoutInflater.from(context);
        this.arrayList = propertyModelArrayList;
        this.imageArrayList = images;
    }


    @NonNull
    @Override
    public ShowPropertyAdapterR5.ShowPropertyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_show_property, parent,false);
        ShowPropertyAdapterR5.ShowPropertyAdapterViewHolder viewHolder = new ShowPropertyAdapterR5.ShowPropertyAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShowPropertyAdapterViewHolder holder, int position) {
        try {
            if(position < arrayList.size()) {
                holder.rupee.setText(arrayList.get(position).getPrice());
                holder.bedroomORSubtype.setText(arrayList.get(position).getBedroom() + " BHK " + arrayList.get(position).getPropertySubType());
                holder.localityORCity.setText(arrayList.get(position).getProject() + " " + arrayList.get(position).getCity());
                holder.status.setText(arrayList.get(position).getProperty_status());
                String urls= imageArrayList.get(position).getImageAddress();
                Picasso.get()
                        .load(urls)
                        .fit()
                        .placeholder(R.drawable.property_logo)
                        .error(R.drawable.property_logo)
                        .into(holder.imageView);
                Log.e("ShowPropertyAdapter", urls);
            }else {

            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String propertyFor=arrayList.get(position).getPropertyFor();
                String propertyType= "commercial";
                String NoImage= "https://firebasestorage.googleapis.com/v0/b/property-realtor.appspot.com/o/noImagefound%2Fdownload.png?alt=media&token=e17c8304-f452-4261-82e2-b5dc2388baf1" ;
                Toast.makeText(context, "Recycler view Click" + position +" "+ imageArrayList.get(position).getImageAddress(), Toast.LENGTH_SHORT).show();
            /*    Intent intent = new Intent(context, ViewPropertyDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("POSITION", position);
                bundle.putString("PropertyFor", propertyFor);
                bundle.putString("propertyType", propertyType);
                intent.putExtras(bundle);
                context.startActivity(intent);
           */ }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size() + imageArrayList.size()- imageArrayList.size();
    }

    public class ShowPropertyAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView rupee, bedroomORSubtype, localityORCity, status;
        ImageView imageView, favourite;
        Button call;

        public ShowPropertyAdapterViewHolder(View itemView) {
            super(itemView);
            rupee = (TextView) itemView.findViewById(R.id.money);
            bedroomORSubtype = (TextView) itemView.findViewById(R.id.bedroom_subtype);
            localityORCity = (TextView) itemView.findViewById(R.id.locality_city);
            status = (TextView) itemView.findViewById(R.id.status);
            imageView =  itemView.findViewById(R.id.image);
            favourite =  itemView.findViewById(R.id.favourite);
            call =  itemView.findViewById(R.id.calling);

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
