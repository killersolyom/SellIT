package com.sell.it.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.it.Adapter.ViewHolder.AdvertisementInfoViewHolder;
import com.sell.it.Adapter.ViewHolder.AdvertisementViewHolder;
import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Adapter.ViewHolder.DefaultViewHolder;
import com.sell.it.Adapter.ViewHolder.LanguageItemViewHolder;
import com.sell.it.Adapter.ViewHolder.TextSeparatorViewHolder;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;
import com.sell.it.Utility.DisplayUtils;

class LayoutSelector {

    static BaseViewHolder getLayoutForItem(ViewGroup parent, int viewType, int spanCount) {
        View itemView;
        switch (viewType) {
            case Values.ItemType.BASE_ADVERTISEMENT_TYPE:
                itemView = inflateView(parent, R.layout.recyclerview_advertisement_component);
                calculateOptimalSize(itemView, viewType, spanCount);
                return new AdvertisementViewHolder(itemView);
            case Values.ItemType.ADVERTISEMENT_INFO_TYPE:
                itemView = inflateView(parent, R.layout.recyclerview_advertisement_info_component);
                return new AdvertisementInfoViewHolder(itemView);
            case Values.ItemType.SEPARATOR_ITEM_TYPE:
                itemView = inflateView(parent, R.layout.recyclerview_separator_component);
                return new TextSeparatorViewHolder(itemView);
            case Values.ItemType.LANGUAGE_ITEM_TYPE:
                itemView = inflateView(parent, R.layout.recyclerview_language_component);
                return new LanguageItemViewHolder(itemView);
            default:
                itemView = inflateView(parent, R.layout.default_viewholder);
                return new DefaultViewHolder(itemView);
        }
    }

    private static View inflateView(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    private static void calculateOptimalSize(View view, int viewType, int spanCount) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        switch (viewType) {
            case Values.ItemType.BASE_ADVERTISEMENT_TYPE:
                layoutParams.height = (int) (DisplayUtils.getScreenWidth() / 1.45) / spanCount;
                view.setLayoutParams(layoutParams);
                break;
        }
    }

}
