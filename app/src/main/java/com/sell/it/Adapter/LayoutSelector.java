package com.sell.it.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sell.it.Adapter.ViewHolder.AdvertisementViewHolder;
import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Adapter.ViewHolder.DefaultViewHolder;
import com.sell.it.R;

class LayoutSelector {

    static BaseViewHolder getLayoutForItem(ViewGroup parent, int viewType) {
        if (viewType == ItemType.BASE_ADVERTISEMENT_TYPE) {
            return new AdvertisementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_advertisement_component, parent, false));
        } else {
            return new DefaultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.default_viewholder,parent,false));
        }
    }

}
