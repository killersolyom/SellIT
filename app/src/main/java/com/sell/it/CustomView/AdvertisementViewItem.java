package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.R;

public class AdvertisementViewItem extends ConstraintLayout {

    private ImageView mAdvertisementImage;
    private TextView mAdvertisementTitle;
    private RecyclerView mAdvertisementInfoView;
    private ItemAdapter mInfoAdapter;

    public AdvertisementViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.advertisement_component_layout, this);
        mAdvertisementImage = findViewById(R.id.advertisement_image);
        mAdvertisementTitle = findViewById(R.id.advertisement_title);
        mAdvertisementInfoView = findViewById(R.id.advertisement_extra_info_view);
        mAdvertisementInfoView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mInfoAdapter = new ItemAdapter();
        mAdvertisementInfoView.setAdapter(mInfoAdapter);

        Glide.with(context)
                .load("https://picsum.photos/600")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(mAdvertisementImage);
    }

    public void setTitle(String title) {
        mAdvertisementTitle.setText(title);
    }

}