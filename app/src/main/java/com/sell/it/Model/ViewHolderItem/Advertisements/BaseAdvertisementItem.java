package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;

import java.util.ArrayList;

public abstract class BaseAdvertisementItem extends DefaultAdvertisementItem {

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

    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        return null;
    }

}
