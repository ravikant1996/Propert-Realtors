package com.example.propertyrealtors.Post_property;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.activity.MainActivity;
import com.example.propertyrealtors.model.Image;
import com.example.propertyrealtors.model.Locality;
import com.example.propertyrealtors.model.ResidentialModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;
import com.tuyenmonkey.mkloader.MKLoader;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagesUpload extends AppCompatActivity {

    ImageView imageView;
    Button Upload, gotoMain;
    MKLoader mkLoader;
    StorageReference reference, ref, imagePath;
    DatabaseReference databaseReference;
    String Id;
    private final int PERMISSION_ALL= 12;
    private ImagePicker imagePicker;
    ResidentialModel residentialModel;
    CardView Image_card;
    SliderView sliderView;
    UploadSliderAdapter adapter;
    ArrayList<Image> pic;
    Image imgModel;
    long noOfImages= 0;
    int images=0;
    Image image;
    String propertyType, propertyId;
    Button addintodatabse;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_upload);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Image Upload");

        try {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        Bundle bundle = getIntent().getExtras();
        Id = bundle.getString("KEY_ID");
        propertyType = bundle.getString("TYPE");
        propertyId = bundle.getString("PROPERTYID_Keyid");

        mkLoader = findViewById(R.id.loader);
        mkLoader.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.imageView);
        Upload = findViewById(R.id.submit);
        gotoMain = findViewById(R.id.done);
        Image_card = findViewById(R.id.image_card);

      /*  try{
            imageSlider();
        }catch (Exception e){
            e.getMessage();
        }
*/
        residentialModel = new ResidentialModel();
        String[] PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
        };

        reference = FirebaseStorage.getInstance().getReference()
                .child("Temp/" + Id);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PropertyTable").child("additionalInfo");

        imagecounter_From_Database();

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasPermissions(ImagesUpload.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(ImagesUpload.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    //  imageView.setImageURI(null);
                    imagePicker.choosePicture(true /*show camera intents*/);
                }
            }
        });
        imagePicker = new ImagePicker(this, /* activity non null*/
                null, /* fragment nullable*/
                new OnImagePickedListener() {
                    @Override
                    public void onImagePicked(Uri imageUri) {
                        UCrop.of(imageUri, getTempUri())
                                .withAspectRatio(1, 1)
                                .start(ImagesUpload.this);
                    }
                });

    }

    private void imagecounter_From_Database() {
        Query query = databaseReference.orderByChild("id").equalTo(Id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    images = dataSnapshot.child(Id).child("imageCount").getValue(Integer.class);
                    if (!(images == 10)) {
                        Upload.setText(images + " out of " + "10 images are uploaded");
                    } else {
                        Upload.setText("Bucket is full");
                        Upload.setEnabled(false);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesUpload.this, "database error "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Uri getTempUri(){
        String dir = Environment.getExternalStorageDirectory()+ File.separator+"Temp";
        File  dirFile = new File(dir);
        dirFile.mkdir();
        String file = dir+File.separator+"temp.png";
        File tempFile = new File(file);
        try {
            tempFile.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Uri.fromFile(tempFile);
    }


    public static boolean hasPermissions(Context context, String... permissions){
        if(context != null && permissions != null){
            for(String permission : permissions){
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissionsList, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissionsList, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
        switch (requestCode){
            case PERMISSION_ALL:{
                if(grantResults.length > 0){
                    boolean flag = true;
                    for(int i=0; i< permissionsList.length; i++){
                        if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                            flag = false;
                        }
                    }
                    if(flag){
                        imagePicker.choosePicture(true /*show camera intents*/);                    }
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode,requestCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);

            upload(resultUri);

        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Log.e("cropError", "OnActivityResult: " + cropError.getMessage());
        }
    }


    void upload(Uri uri) {
        mkLoader.setVisibility(View.VISIBLE);
        ref = reference.child(System.currentTimeMillis()+".png");
        imagePath = reference.child(System.currentTimeMillis()+".png");
        ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        mkLoader.setVisibility(View.GONE);
                        imageView.setImageURI(null);
                        Picasso.get().load(uri).into(imageView);
                        Log.e("url", " "+uri);
                        Toast.makeText(ImagesUpload.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                        images++;
                        updateCounterUpdateInDatabase();
                        imagecounter_From_Database();
                        String link= uri.toString();
                        addIntoDataabase(link);

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("upload Image", "onFailure: "+ e.getMessage());
            }
        });
    }

    private void addIntoDataabase(String link) {
        image = new Image();
        image.setImageAddress(link);
        DatabaseReference reference1 =FirebaseDatabase.getInstance().getReference().child("PropertyTable")
                .child(propertyType).child(propertyId);
        //   Image im = new Image(image);
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    reference1.child("images").child(String.valueOf(images)).setValue(image);
                    Toast.makeText(ImagesUpload.this, "added in database", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesUpload.this, "database error "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateCounterUpdateInDatabase() {
        DatabaseReference db = databaseReference.child(Id);
        Map<String, Object> updates = new HashMap<String,Object>();
        updates.put("imageCount", images);
        db.updateChildren(updates);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void gotoMainactivity(View view) {
        Intent intent = new Intent(ImagesUpload.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void ViewImages(View view) {
        Button removeLastItem;
        List<Image> pic= new ArrayList<>();
        final Dialog dialog = new Dialog(this,R.style.Dialog);

        // Include dialog.xml file
        dialog.setContentView(R.layout.image_dialog);
        // Set dialog title
        dialog.setTitle("Loaded Images");

        SliderView sliderView = dialog.findViewById(R.id.imageSlider);
        removeLastItem = dialog.findViewById(R.id.removeLastItem);

        DatabaseReference reference1 =FirebaseDatabase.getInstance().getReference().child("PropertyTable")
                .child(propertyType).child(propertyId).child("images");
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                        Image img = areaSnapshot.getValue(Image.class);
                        pic.add(img);
                    }
                    //       adapter.renewItems(pic);
                    //  adapter.addItem(imgModel);
                    //   adapter.renewItems(pic);
                    adapter = new UploadSliderAdapter(ImagesUpload.this, pic);
                    sliderView.setSliderAdapter(adapter);
                    sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    sliderView.setIndicatorSelectedColor(Color.WHITE);
                    sliderView.setIndicatorUnselectedColor(Color.GRAY);
                    sliderView.setScrollTimeInSec(800); //set scroll delay in seconds :
                    sliderView.startAutoCycle();
                    //  adapter.deleteItem();
                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dialog.show();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });



        removeLastItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (adapter.getCount() - 1 >= 0)
                        adapter.deleteItem(adapter.getCount() - 1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
