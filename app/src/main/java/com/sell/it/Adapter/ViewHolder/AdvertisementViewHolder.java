package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.AdvertisementViewItem;
import com.sell.it.Model.ViewHolderItem.BaseAdvertisementItem;
import com.sell.it.R;

public class AdvertisementViewHolder extends BaseViewHolder<BaseAdvertisementItem> {

    private AdvertisementViewItem mAdvertisement;

    public AdvertisementViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(BaseAdvertisementItem advertisementItem) {
        mAdvertisement.setOnClickListener(v -> onItemClicked(advertisementItem));
        mAdvertisement.bindItem(advertisementItem);
    }

    @Override
    public void unBindItem() {
        mAdvertisement.unbind();
    }

    @Override
    protected void onItemClicked(BaseAdvertisementItem advertisementItem) {

    }

    @Override
    protected void findView(View itemView) {
        mAdvertisement = itemView.findViewById(R.id.advertisement_item);
    }

}