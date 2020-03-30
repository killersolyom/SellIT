package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.sell.it.R;

public class AdvertisementViewItem extends ConstraintLayout {

    private ImageView mAdvertisementImage;
    private TextView mAdvertisementTitle;

    public AdvertisementViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.advertisement_component_layout, this);
        mAdvertisementImage = findViewById(R.id.advertisement_image);
        mAdvertisementTitle = findViewById(R.id.advertisement_title);
        Glide.with(context).load("https://picsum.photos/200").into(mAdvertisementImage);
    }

    public void setTitle(String title) {
        mAdvertisementTitle.setText(title);
    }

}
