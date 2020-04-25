package com.sell.it.CustomView;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sell.it.Adapter.ItemAdapter;
import com.sell.it.Communication.RequestListener;
import com.sell.it.Model.ViewHolderItem.AdvertisementInfoItem;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.Model.ViewHolderItem.TextSeparatorItem;
import com.sell.it.R;
import com.sell.it.Utility.DisplayUtils;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class AdvertisementViewItem extends BaseCustomView<BaseAdvertisementItem> {

    private Handler mReloadHandler;
    private float mItemTextSize;
    private ItemAdapter mInfoAdapter;
    private ImageView mAdvertisementImage;
    private RecyclerView mInfoRecyclerView;
    private TextView mAdvertisementTitleView;

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
        mAdvertisementTitleView = findViewById(R.id.advertisement_title);
        mInfoRecyclerView = findViewById(R.id.advertisement_extra_info_view);
    }

    @Override
    protected void initializeComponents() {
        mReloadHandler = new Handler();
        mInfoAdapter = new ItemAdapter();
        mInfoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), HORIZONTAL, false));
        mInfoRecyclerView.setAdapter(mInfoAdapter);
    }

    private void calculateOptimalSize(ViewGroup.LayoutParams itemParams) {
        mItemTextSize = DisplayUtils.convertPixelToSp((itemParams.height * 0.12f));//12%
        mAdvertisementTitleView.setTextSize(mItemTextSize);
    }

    public void bindItem(BaseAdvertisementItem advertisementItem, ViewGroup.LayoutParams layoutParams) {
        mReloadHandler.removeCallbacksAndMessages(null);
        calculateOptimalSize(layoutParams);
        setTitle(advertisementItem.getTitle());
        loadImage(advertisementItem.getFirstImage());
        fillInfoView(advertisementItem);
    }

    public void unbind() {
        mReloadHandler.removeCallbacksAndMessages(null);
        Glide.with(getContext()).clear(mAdvertisementImage);
        mAdvertisementTitleView.setText(null);
        mInfoAdapter.clearItems();
    }

    private void setTitle(String title) {
        mAdvertisementTitleView.setText(title);
    }

    private void loadImage(String imagePath) {
        Glide.with(getContext())
                .load(imagePath)
                .transition(withCrossFade(500))
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .listener(new RequestListener() {
                    @Override
                    public void onLoadFailed() {
                        mReloadHandler.postDelayed(() -> loadImage(imagePath), 7500);
                    }
                })
                .into(mAdvertisementImage);
    }

    private void fillInfoView(BaseAdvertisementItem advertisementItem) {
        //TODO Dummy data generator, replace it
        for (int i = 0; i < 5; i++) {
            mInfoAdapter.addItem(new AdvertisementInfoItem("Data " + i, mItemTextSize));
            mInfoAdapter.addItem(new TextSeparatorItem((int) (mItemTextSize * 3.8)));
        }
    }

}