package com.example.propertyrealtors.Post_property;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertyrealtors.A_EndUser.Start331AllCommercial_View;
import com.example.propertyrealtors.A_EndUser.Start331AllResidential2_View;
import com.example.propertyrealtors.A_EndUser.Start331AllResidential_View;
import com.example.propertyrealtors.R;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.PropertyModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class edit_image_adapter extends RecyclerView.Adapter<edit_image_adapter.ShowPropertyAdapterViewHolder> {
    Context context;
    List<Image> imageArrayList = new ArrayList<>();

    public edit_image_adapter(Context context, ArrayList<Image> images) {
        this.context = context;
        this.imageArrayList = images;

    }

    @NonNull
    @Override
    public ShowPropertyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.image_view, parent,false);
        ShowPropertyAdapterViewHolder viewHolder = new ShowPropertyAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShowPropertyAdapterViewHolder holder, final int position) {
        try {
            Image sliderItem = imageArrayList.get(position);
            Picasso.get()
                    .load(sliderItem.getImageAddress())
                    .fit()
                    .noFade()
                    .into(holder.Imageview,
                            new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Exception e) {
                                    Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
                                }
                            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("PropertyTable").child("");
                }
            });
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    public class ShowPropertyAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView Imageview;

        ShowPropertyAdapterViewHolder(View itemView) {
            super(itemView);

            Imageview = itemView.findViewById(R.id.imageView);

        }
    }

}
