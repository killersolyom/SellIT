package com.sell.it.Model.ViewHolderItem.Advertisements;

import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.ViewHolderItem.BaseDefaultItem;

public class AdvertisementInfoItem extends BaseDefaultItem {

    private String mInfo;
    private float mTextSize;

    public AdvertisementInfoItem(String info, float textSize) {
        mInfo = info;
        mTextSize = textSize;
    }

    public String getInfo() {
        return mInfo;
    }

    public float getTextSize() {
        return mTextSize;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.ADVERTISEMENT_INFO_TYPE;
    }
}
