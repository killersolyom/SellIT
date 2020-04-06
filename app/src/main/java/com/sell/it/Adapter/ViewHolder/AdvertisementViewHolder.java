package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.AdvertisementViewItem;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.R;

import java.util.concurrent.ThreadLocalRandom;

public class AdvertisementViewHolder extends BaseViewHolder<BaseAdvertisementItem> {

    private AdvertisementViewItem mAdvertisement;

    public AdvertisementViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(BaseAdvertisementItem advertisementItem) {
        mAdvertisement.setOnClickListener(v -> onItemClicked(advertisementItem));
        mAdvertisement.setTitle(advertisementItem.getTitle());
        int random = (int) (100 * ThreadLocalRandom.current().nextDouble(2, 10));
        mAdvertisement.loadImage("https://picsum.photos/" + random);
        mAdvertisement.bindItem(advertisementItem);
    }

    @Override
    protected void onItemClicked(BaseAdvertisementItem advertisementItem) {

    }

    @Override
    protected void findView(View itemView) {
        mAdvertisement = itemView.findViewById(R.id.advertisement_item);
    }

    @Override
    public void unBindItem() {
        mAdvertisement.unbind();
    }

}