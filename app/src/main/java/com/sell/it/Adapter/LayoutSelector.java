package com.sell.it.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sell.it.Adapter.ViewHolder.AdvertisementInfoViewHolder;
import com.sell.it.Adapter.ViewHolder.AdvertisementViewHolder;
import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Adapter.ViewHolder.DefaultViewHolder;
import com.sell.it.Adapter.ViewHolder.TextSeparatorViewHolder;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

class LayoutSelector {

    static BaseViewHolder getLayoutForItem(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Values.ItemType.BASE_ADVERTISEMENT_TYPE:
                return new AdvertisementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_advertisement_component, parent, false));
            case Values.ItemType.ADVERTISEMENT_INFO_TYPE:
                return new AdvertisementInfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_advertisement_info_component, parent, false));
            case Values.ItemType.SEPARATOR_ITEM_TYPE:
                return new TextSeparatorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_separator_component, parent, false));
            default:
                return new DefaultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.default_viewholder, parent, false));
        }
    }

}
