package com.sell.it.CustomView;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

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

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.sell.it.Utility.DisplayUtils.convertPixelToSp;

public class AdvertisementViewItem extends BaseCustomView<BaseAdvertisementItem> {

    private Handler mReloadHandler;
    private ItemAdapter mInfoAdapter;
    private ItemAdapter mTitleAdapter;
    private ImageView mAdvertisementImage;
    private RecyclerView mInfoRecyclerView;
    private RecyclerView mAdvertisementTitleView;
    private float mTitleTextSize, mInfoItemTextSize;

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
        mTitleAdapter = new ItemAdapter();
        mInfoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), HORIZONTAL, false));
        mAdvertisementTitleView.setLayoutManager(new LinearLayoutManager(getContext(), HORIZONTAL, false));
        mInfoRecyclerView.setAdapter(mInfoAdapter);
        mAdvertisementTitleView.setAdapter(mTitleAdapter);
    }

    public void calculateOptimalSize(ViewGroup.LayoutParams itemParams) {
        ViewGroup.LayoutParams infoParams = getLayoutParams(mInfoRecyclerView);
        ViewGroup.LayoutParams titleParams = getLayoutParams(mAdvertisementTitleView);

        infoParams.height = (int) (itemParams.height * 0.14);//14%
        titleParams.height = (int) (itemParams.height * 0.13);//13%

        mInfoRecyclerView.setLayoutParams(infoParams);
        mAdvertisementTitleView.setLayoutParams(titleParams);

        mTitleTextSize = convertPixelToSp(titleParams.height * 0.9f);
        mInfoItemTextSize = convertPixelToSp(infoParams.height * 0.9f);
    }

    public void bindItem(BaseAdvertisementItem advertisementItem) {
        mReloadHandler.removeCallbacksAndMessages(null);
        setTitle(advertisementItem.getTitle());
        loadImage(advertisementItem.getFirstImage());
    }

    public void unbind() {
        mReloadHandler.removeCallbacksAndMessages(null);
        Glide.with(getContext()).clear(mAdvertisementImage);
        mTitleAdapter.clearItems();
        mInfoAdapter.clearItems();
    }

    private void setTitle(String title) {
        mTitleAdapter.addItem(new AdvertisementInfoItem(title, mTitleTextSize));
        //TODO Dummy data generator, remove it
        for (int i = 0; i < 5; i++) {
            mInfoAdapter.addItem(new AdvertisementInfoItem("Data " + i, mInfoItemTextSize));
            mInfoAdapter.addItem(new TextSeparatorItem());
        }
    }

    public void loadImage(String imagePath) {
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

}