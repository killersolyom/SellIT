package com.sell.it.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.sell.it.Communication.RequestListener;
import com.sell.it.Model.ViewHolderItem.Advertisements.BaseAdvertisementItem;
import com.sell.it.R;
import com.sell.it.Utility.DisplayUtils;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class AdvertisementViewItem extends BaseCustomView<BaseAdvertisementItem> {

    private Handler mReloadHandler;
    private float mItemTextSize;
    private int mItemRadius = 0;
    private ImageView mAdvertisementImage;
    private TextView mInfoTextView;
    private TextView mAdvertisementTitleView;

    public AdvertisementViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.advertisement_component_layout, this, true);
    }

    @Override
    protected void initView() {
        mAdvertisementImage = findViewById(R.id.advertisement_image);
        mAdvertisementTitleView = findViewById(R.id.advertisement_title);
        mInfoTextView = findViewById(R.id.advertisement_extra_info_view);
    }

    @Override
    protected void initializeComponents() {
        setHovered(true);
        mReloadHandler = new Handler();
    }

    private void calculateOptimalSize(ViewGroup.LayoutParams itemParams) {
        mItemRadius = getResources().getDimensionPixelOffset(R.dimen.advertisement_item_radius) + 1;
        mItemTextSize = DisplayUtils.convertPixelToSp((itemParams.height * 0.10f));//10%
        mAdvertisementTitleView.setTextSize(mItemTextSize);
        mInfoTextView.setTextSize(mItemTextSize);
    }

    @SuppressLint("SetTextI18n")
    public void bindItem(BaseAdvertisementItem advertisementItem, ViewGroup.LayoutParams layoutParams) {
        mReloadHandler.removeCallbacksAndMessages(null);
        calculateOptimalSize(layoutParams);
        setTitle(advertisementItem.getTitle());
        loadImage(advertisementItem.getFirstImage().getImagePath());
        Pair<Integer, String> pricePair = advertisementItem.getPriceInfoPair();
        mInfoTextView.setText(getContext().getText(pricePair.first) + pricePair.second);
    }

    public void unbind() {
        mReloadHandler.removeCallbacksAndMessages(null);
        Glide.with(getContext()).clear(mAdvertisementImage);
        mAdvertisementTitleView.setText(null);
    }

    private void setTitle(String title) {
        mAdvertisementTitleView.setText(title);
    }

    private void loadImage(String imagePath) {
        if (itemView == null) {
            return;
        }

        Glide.with(getContext().getApplicationContext())
                .load(imagePath)
                .transition(withCrossFade(500))
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .transform(new CenterCrop(), new RoundedCorners(mItemRadius))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .listener(new RequestListener() {
                    @Override
                    public void onLoadFailed() {
                        mReloadHandler.postDelayed(() -> loadImage(imagePath), 7500);
                    }
                })
                .into(mAdvertisementImage);
    }

}