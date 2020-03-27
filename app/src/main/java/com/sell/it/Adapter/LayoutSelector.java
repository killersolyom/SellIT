package com.sell.it.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sell.it.Adapter.ViewHolder.AdvertisementViewHolder;
import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Model.ItemType;
import com.sell.it.R;

public class LayoutSelector {

    static BaseViewHolder getLayoutForItem(ViewGroup parent, int viewType) {
        if (viewType == ItemType.BASE_ADVERTISEMENT_TYPE) {
            return new AdvertisementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_advertisement_component, parent, false));
        } else {
            //TODO need a default layout here
            return new AdvertisementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_advertisement_component, parent, false));
        }
    }

}
