package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

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

    private ItemAdapter mInfoAdapter;
    private TextView mAdvertisementTitle;
    private ImageView mAdvertisementImage;
    private ItemRecyclerView mInfoRecyclerView;

    public AdvertisementViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.advertisement_component_layout, this);
    }

    @Override
    protected void initView() {
        mAdvertisementImage = findViewById(R.id.advertisement_image);
        mAdvertisementTitle = findViewById(R.id.advertisement_title);
        mInfoRecyclerView = findViewById(R.id.advertisement_extra_info_view);
    }

    @Override
    protected void initializeComponents() {
        mInfoAdapter = new ItemAdapter();
        mInfoRecyclerView.initParams(mInfoAdapter, new LinearLayoutManager(getContext(), HORIZONTAL, false));
    }

    public void bindItem(BaseAdvertisementItem advertisementItem) {
        setTitle(advertisementItem.getTitle());
        loadImage(advertisementItem.getFirstImage());
    }

    public void unbind() {
        Glide.with(getContext()).clear(mAdvertisementImage);
        mAdvertisementTitle.setText(null);
        mInfoAdapter.clearItems();
    }

    private void setTitle(String title) {
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
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(mAdvertisementImage);
    }

}