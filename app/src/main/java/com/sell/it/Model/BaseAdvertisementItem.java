package com.sell.it.Model;

import com.sell.it.Adapter.ItemType;

public class BaseAdvertisementItem extends BaseItem {

    private String mTitle;

    public BaseAdvertisementItem() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public int getItemType() {
        return ItemType.BASE_ADVERTISEMENT_TYPE;
    }
}
