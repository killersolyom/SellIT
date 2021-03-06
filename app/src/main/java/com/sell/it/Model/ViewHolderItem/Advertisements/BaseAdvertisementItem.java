package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseAdvertisementItem extends DefaultAdvertisementItem {

    public static final String MANUFACTURER_KEY = "MANUFACTURER";

    private String mManufacturer;

    @PropertyName(MANUFACTURER_KEY)
    public String getManufacturer() {
        return mManufacturer;
    }

    @Override
    protected void initItems(HashMap<String, Object> items) {
        super.initItems(items);
        mManufacturer = getStringValue(items.get(MANUFACTURER_KEY));
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_manufacturer, mManufacturer);
        return descriptionList;
    }

}
