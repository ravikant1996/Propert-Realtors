package com.example.real_estate_business.Post_property;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.real_estate_business.R;
import com.example.real_estate_business.model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class edit1_image_adapter extends RecyclerView.Adapter<edit1_image_adapter.ShowPropertyAdapterViewHolder> {
    Context context;
    ArrayList<Image> imageArrayList = new ArrayList<>();
    List<String> arrayList = new ArrayList<>();
    List<String> arrayList2 = new ArrayList<>();
    edit1_image_adapter adapter;

    public edit1_image_adapter(Context context, List<String> arrayList, List<String> arrayList2, ArrayList<Image> images) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayList2 = arrayList2;
        this.imageArrayList = images;
        this.adapter = this;
    }

    @NonNull
    @Override
    public ShowPropertyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_view, parent, false);
        ShowPropertyAdapterViewHolder viewHolder = new ShowPropertyAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ShowPropertyAdapterViewHolder holder, final int position) {
        try {
            final Image sliderItem = imageArrayList.get(position);
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
                    Toast.makeText(context, sliderItem.getImageAddress(), Toast.LENGTH_SHORT).show();

                }
            });
            holder.Imageview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Delete a Image")
                            .setMessage("Do you want to delete this Image?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final String propertyType = arrayList.toString().replace("[", "").replace("]", "");
                                    final String propertyId = arrayList2.toString().replace("[", "").replace("]", "");
                                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child(propertyType);
                                    final DatabaseReference reff = ref.child(propertyId).child("images");
                                    final Query query = reff.orderByChild("imageAddress").equalTo(sliderItem.getImageAddress());

                                    query.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                            dataSnapshot.getRef().removeValue();
                                            int size = imageArrayList.size();
                                            DatabaseReference db = ref.child(propertyId);
                                            Map<String, Object> updates = new HashMap<String, Object>();
                                            updates.put("imageCount", --size);
                                            Log.e("size", "" + size);
                                            Log.e("size", "" + imageArrayList.size());
                                            db.updateChildren(updates);
                                            StorageReference photoRef = FirebaseStorage.getInstance().getReferenceFromUrl(sliderItem.getImageAddress());
//                                            StorageReference photoRef = FirebaseStorage.getInstance().getReference().child("PropertyImages/" + propertyId).child(imageArrayList.get(position).toString())
                                            photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    // File deleted successfully
                                                    Toast.makeText(context, "delete from storage", Toast.LENGTH_LONG).show();

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception exception) {
                                                    // Uh-oh, an error occurred!
                                                    Log.d("TAG", "onFailure: did not delete file");
                                                    Toast.makeText(context, "not delete from storage", Toast.LENGTH_LONG).show();

                                                }
                                            });
                                            imageArrayList.remove(position); //Actually change your list of items here
                                            adapter.imageArrayList.clear();
                                            adapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                        }

                                        @Override
                                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
//
                                }
                            }).setNegativeButton(
                                    "No",
                                    new DialogInterface
                                            .OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {

                                            // If user click no
                                            // then dialog box is canceled.
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return true;
                }
            });

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size() + arrayList.size() - arrayList.size();
    }

    public class ShowPropertyAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView Imageview;

        ShowPropertyAdapterViewHolder(View itemView) {
            super(itemView);

            Imageview = itemView.findViewById(R.id.imageView);

        }
    }

}
