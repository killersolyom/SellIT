package com.sell.it.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.it.Adapter.ViewHolder.AdvertisementInfoViewHolder;
import com.sell.it.Adapter.ViewHolder.AdvertisementViewHolder;
import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Adapter.ViewHolder.BooleanInputViewHolder;
import com.sell.it.Adapter.ViewHolder.DefaultViewHolder;
import com.sell.it.Adapter.ViewHolder.ImageViewHolder;
import com.sell.it.Adapter.ViewHolder.InputViewHolder;
import com.sell.it.Adapter.ViewHolder.LanguageItemViewHolder;
import com.sell.it.Adapter.ViewHolder.TextSeparatorViewHolder;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;
import com.sell.it.Utility.DisplayUtils;

class LayoutSelector {

    static BaseViewHolder getLayoutForItem(ViewGroup parent, int viewType, int spanCount) {
        switch (viewType) {
            case Values.ViewType.ADVERTISEMENT_TYPE:
                View itemView = inflateView(parent, R.layout.recyclerview_advertisement_component);
                calculateOptimalSize(itemView, viewType, spanCount);
                return new AdvertisementViewHolder(itemView);
            case Values.ViewType.ADVERTISEMENT_INFO_TYPE:
                return new AdvertisementInfoViewHolder(inflateView(parent, R.layout.recyclerview_advertisement_info_component));
            case Values.ViewType.SEPARATOR_ITEM_TYPE:
                return new TextSeparatorViewHolder(inflateView(parent, R.layout.recyclerview_separator_component));
            case Values.ViewType.LANGUAGE_ITEM_TYPE:
                return new LanguageItemViewHolder(inflateView(parent, R.layout.recyclerview_language_component));
            case Values.ViewType.IMAGE_ITEM_TYPE:
                return new ImageViewHolder(inflateView(parent, R.layout.recyclerview_image_component));
            case Values.ViewType.DATA_INPUT_ITEM_TYPE:
                return new InputViewHolder(inflateView(parent, R.layout.recyclerview_value_input_component));
            case Values.ViewType.CHECK_BOX_ITEM_TYPE:
                return new BooleanInputViewHolder(inflateView(parent, R.layout.recyclerview_boolean_input_component));
            default:
                return new DefaultViewHolder(inflateView(parent, R.layout.default_viewholder));
        }
    }

    private static View inflateView(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    private static void calculateOptimalSize(View view, int viewType, int spanCount) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        switch (viewType) {
            case Values.ViewType.ADVERTISEMENT_TYPE:
                float divider = spanCount == 1 ? 1.75f : 1.4f;
                layoutParams.height = (int) (DisplayUtils.getScreenWidth() / divider) / spanCount;
                view.setLayoutParams(layoutParams);
                break;
        }
    }

}
