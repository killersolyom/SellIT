package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Model.ViewHolderItem.AdvertisementInfoItem;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.Model.ViewHolderItem.TextSeparatorItem;
import com.sell.it.R;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class AdvertisementViewItem extends BaseCustomView<BaseAdvertisementItem> {

    private ImageView mAdvertisementImage;
    private TextView mAdvertisementTitle;
    private RecyclerView mAdvertisementInfoView;
    private ItemAdapter mInfoAdapter;

    public AdvertisementViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.advertisement_component_layout, this);
    }

    @Override
    protected void initializeComponents() {
        mAdvertisementImage = findViewById(R.id.advertisement_image);
        mAdvertisementTitle = findViewById(R.id.advertisement_title);
        mAdvertisementInfoView = findViewById(R.id.advertisement_extra_info_view);
        mAdvertisementInfoView.setLayoutManager(new LinearLayoutManager(getContext(), HORIZONTAL, false));
        mInfoAdapter = new ItemAdapter();
        mAdvertisementInfoView.setAdapter(mInfoAdapter);
    }

    public void bindItem(BaseAdvertisementItem advertisementItem) {
    }

    public void unbind() {
        Glide.with(getContext()).clear(mAdvertisementImage);
        mAdvertisementTitle.setText(null);
        mInfoAdapter.clearItems();
    }

    public void setTitle(String title) {
        mAdvertisementTitle.setText(title);
        //TODO Dummy data generator, remove it
        for (int i = 0; i < 5; i++) {
            mInfoAdapter.addItem(new AdvertisementInfoItem("Data " + i));
            mInfoAdapter.addItem(new TextSeparatorItem());
        }
    }

    public void loadImage(String imagePath) {
        Glide.with(getContext())
                .load(imagePath)
                .transition(withCrossFade(500))
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_error_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(mAdvertisementImage);
    }

}