package com.sell.it.Model;

import com.sell.it.Model.Constant.Values;

public class AdvertisementInfoItem extends BaseDefaultItem {

    private String mInfo;

    public AdvertisementInfoItem(String info) {
        mInfo = info;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        this.mInfo = info;
    }

    @Override
    public int getItemType() {
        return Values.ItemType.ADVERTISEMENT_INFO_TYPE;
    }
}
