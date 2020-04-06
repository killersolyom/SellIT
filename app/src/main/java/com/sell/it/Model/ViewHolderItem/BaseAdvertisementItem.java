package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class BaseAdvertisementItem extends BaseDefaultItem {

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
        return Values.ItemType.BASE_ADVERTISEMENT_TYPE;
    }
}
