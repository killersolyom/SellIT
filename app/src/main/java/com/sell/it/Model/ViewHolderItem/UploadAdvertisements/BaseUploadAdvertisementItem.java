package com.sell.it.Model.ViewHolderItem.UploadAdvertisements;

import com.sell.it.Model.Constant.Values;

public abstract class BaseUploadAdvertisementItem extends DefaultUploadAdvertisementItem {

    private String mManufacturer;
    private String mOwner;

    public String getManufacturer() {
        return mManufacturer;
    }

    public void setManufacturer(String mManufacturer) {
        this.mManufacturer = mManufacturer;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.ADVERTISEMENT_TYPE;
    }
}
