package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

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
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_owner, mOwner);
        addToListIfExist(descriptionList, R.string.advertisement_manufacturer, mManufacturer);
        return descriptionList;
    }

}
