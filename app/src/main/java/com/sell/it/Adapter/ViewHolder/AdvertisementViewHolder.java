package com.sell.it.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.AdvertisementViewItem;
import com.sell.it.Model.BaseAdvertisementItem;
import com.sell.it.R;

public class AdvertisementViewHolder extends BaseViewHolder<BaseAdvertisementItem> {

    private AdvertisementViewItem mAdvertisement;

    public AdvertisementViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(BaseAdvertisementItem advertisementItem, int orientation) {
        mItemLayout.setOnClickListener(v -> onItemClicked(advertisementItem));
        mAdvertisement.setTitle(advertisementItem.getTitle());
        mAdvertisement.calculatePreferredSize(orientation);
    }

    @Override
    protected void onItemClicked(BaseAdvertisementItem item) {

    }

    @Override
    protected void findView(View itemView) {
        mItemLayout = itemView.findViewById(R.id.recycler_view_item);
        mAdvertisement = itemView.findViewById(R.id.advertisement_item);
    }

}