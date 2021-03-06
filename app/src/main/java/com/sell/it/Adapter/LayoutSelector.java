package com.sell.it.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.it.Adapter.ViewHolder.AdvertisementInfoViewHolder;
import com.sell.it.Adapter.ViewHolder.AdvertisementViewHolder;
import com.sell.it.Adapter.ViewHolder.BaseViewHolder;
import com.sell.it.Adapter.ViewHolder.BooleanInputViewHolder;
import com.sell.it.Adapter.ViewHolder.ButtonInputViewHolder;
import com.sell.it.Adapter.ViewHolder.DefaultViewHolder;
import com.sell.it.Adapter.ViewHolder.DropDownViewHolder;
import com.sell.it.Adapter.ViewHolder.ImageChooserViewHolder;
import com.sell.it.Adapter.ViewHolder.ImageViewHolder;
import com.sell.it.Adapter.ViewHolder.LanguageItemViewHolder;
import com.sell.it.Adapter.ViewHolder.MiniImageViewHolder;
import com.sell.it.Adapter.ViewHolder.NumberInputViewHolder;
import com.sell.it.Adapter.ViewHolder.TextInputViewHolder;
import com.sell.it.Adapter.ViewHolder.TextItemViewHolder;
import com.sell.it.Adapter.ViewHolder.TextPairItemViewHolder;
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
            case Values.ViewType.MINI_IMAGE_ITEM_TYPE:
                return new MiniImageViewHolder(inflateView(parent, R.layout.recyclerview_mini_image_component));
            case Values.ViewType.DROP_DOWN_ITEM_TYPE:
                return new DropDownViewHolder(inflateView(parent, R.layout.recyclerview_drop_down_component));
            case Values.ViewType.TEXT_INPUT_ITEM_TYPE:
                return new TextInputViewHolder(inflateView(parent, R.layout.recyclerview_text_input_component));
            case Values.ViewType.NUMBER_INPUT_ITEM_TYPE:
                return new NumberInputViewHolder(inflateView(parent, R.layout.recyclerview_number_input_component));
            case Values.ViewType.CHECK_BOX_ITEM_TYPE:
                return new BooleanInputViewHolder(inflateView(parent, R.layout.recyclerview_boolean_input_component));
            case Values.ViewType.BUTTON_ITEM_TYPE:
                return new ButtonInputViewHolder(inflateView(parent, R.layout.recyclerview_button_input_component));
            case Values.ViewType.IMAGE_CHOOSER_ITEM_TYPE:
                return new ImageChooserViewHolder(inflateView(parent, R.layout.recyclerview_image_input_component));
            case Values.ViewType.TEXT_ITEM_TYPE:
                return new TextItemViewHolder(inflateView(parent, R.layout.recyclerview_text_item_component));
            case Values.ViewType.TEXT_PAIR_ITEM_TYPE:
                return new TextPairItemViewHolder(inflateView(parent, R.layout.recyclerview_text_pair_item_component));
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
