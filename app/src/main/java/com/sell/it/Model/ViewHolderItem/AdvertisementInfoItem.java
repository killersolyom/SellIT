package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

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
    public int getItemType() {
        return Values.ItemType.ADVERTISEMENT_INFO_TYPE;
    }
}
