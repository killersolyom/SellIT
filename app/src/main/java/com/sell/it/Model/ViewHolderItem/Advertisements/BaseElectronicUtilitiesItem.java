package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseElectronicUtilitiesItem extends BaseAdvertisementItem {

    public final static String BATTERY_KEY = "BATTERY_SIZE";
    public final static String SCREEN_KEY = "SCREEN_SIZE";

    private int mBatterySize;
    private float mScreenSize;

    @PropertyName(BATTERY_KEY)
    public int getBatterySize() {
        return mBatterySize;
    }

    @PropertyName(SCREEN_KEY)
    public float getScreenSize() {
        return mScreenSize;
    }

    @Override
    @Exclude
    public String getCategoryType() {
        return Values.CategoryType.ELECTRONIC_TYPE;
    }

    @Override
    protected void initItems(HashMap<String, Object> items) {
        super.initItems(items);
        mBatterySize = getIntValue(items.get(BATTERY_KEY));
        mScreenSize = getFloatValue(items.get(SCREEN_KEY));
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_battery_size, intValueToDescriptionString(mBatterySize));
        addToListIfExist(descriptionList, R.string.advertisement_screen_size, floatValueToDescriptionString(mScreenSize));
        return descriptionList;
    }

}
