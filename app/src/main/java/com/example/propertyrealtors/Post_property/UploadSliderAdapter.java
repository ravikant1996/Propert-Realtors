package com.example.propertyrealtors.Post_property;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.propertyrealtors.R;
import com.example.propertyrealtors.model.Image;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UploadSliderAdapter extends SliderViewAdapter<UploadSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<Image> mImages = new ArrayList<>();

    public UploadSliderAdapter(Context context, List<Image> sliderItems) {
        this.context = context;
        this.mImages = sliderItems;
    }

    public void deleteItem(int position) {
        this.mImages.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_slider_item, parent,false);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        Image sliderItem = mImages.get(position);
        Picasso.get()
                .load(sliderItem.getImageAddress())
             //   .resize(200, 200)
                .fit()
                .noFade()
                .into(viewHolder.imageViewBackground,
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
    /*    Glide.with(viewHolder.itemView)
                .load(sliderItem.getImageAddress())
                .into(viewHolder.imageViewBackground);
      */ /* Glide.with(mCtx)
                .load(imageUrl)
                .into(textView);
      */  viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mImages.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imageView);
            this.itemView = itemView;
        }
    }

}