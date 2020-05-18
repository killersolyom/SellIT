package com.sell.it.Adapter.ViewHolder;

import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

import com.sell.it.CustomView.AdvertisementViewItem;
import com.sell.it.Model.ViewHolderItem.Advertisements.DefaultAdvertisementItem;
import com.sell.it.R;
import com.sell.it.Utility.FragmentNavigation;

public class AdvertisementViewHolder extends BaseViewHolder<DefaultAdvertisementItem> {

    private AdvertisementViewItem mAdvertisement;

    public AdvertisementViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindItem(DefaultAdvertisementItem advertisementItem) {
        mAdvertisement.setOnClickListener(v -> onItemClicked(advertisementItem));
        mAdvertisement.bindItem(advertisementItem, itemView.getLayoutParams());
        mItemView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
    }

    @Override
    public void unBindItem() {
        mItemView.clearAnimation();
        mAdvertisement.unbind();
    }

    @Override
    protected void onItemClicked(DefaultAdvertisementItem advertisementItem) {
        FragmentNavigation.showDetailsFragment(advertisementItem);
    }

    @Override
    protected void findView(View itemView) {
        mAdvertisement = itemView.findViewById(R.id.advertisement_item);
    }

}