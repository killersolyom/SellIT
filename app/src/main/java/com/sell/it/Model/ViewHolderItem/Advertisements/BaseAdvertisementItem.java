package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.Map;

public abstract class BaseAdvertisementItem extends DefaultAdvertisementItem {

    public static final String MANUFACTURER_KEY = "MANUFACTURER_KEY";
    public static final String OWNER_KEY = "OWNER_KEY";

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
    protected void initItems(Map<String, Object> items) {
        super.initItems(items);
        mManufacturer = getStringValue(items.get(MANUFACTURER_KEY));
        mOwner = getStringValue(items.get(OWNER_KEY));
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_owner, mOwner);
        addToListIfExist(descriptionList, R.string.advertisement_manufacturer, mManufacturer);
        return descriptionList;
    }

}
