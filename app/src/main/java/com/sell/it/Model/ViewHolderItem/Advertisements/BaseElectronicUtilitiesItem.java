package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.R;

import java.util.ArrayList;
import java.util.Map;

public abstract class BaseElectronicUtilitiesItem extends BaseAdvertisementItem {

    public final static String BATTERY_KEY = "BATTERY_KEY";
    public final static String SCREEN_KEY = "SCREEN_KEY";

    private int mBatterySize;
    private float mScreenSize;

    public int getBatterySize() {
        return mBatterySize;
    }

    public void setBatterySize(int mBatterySize) {
        this.mBatterySize = mBatterySize;
    }

    public float getScreenSize() {
        return mScreenSize;
    }

    public void setScreenSize(float mScreenSize) {
        this.mScreenSize = mScreenSize;
    }

    @Override
    protected void initItems(Map<String, Object> items) {
        super.initItems(items);
        mBatterySize = getIntValue(items.get(BATTERY_KEY));
        mScreenSize = getFloatValue(items.get(SCREEN_KEY));
    }

    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_battery_size, intValueToDescriptionString(mBatterySize));
        addToListIfExist(descriptionList, R.string.advertisement_screen_size, floatValueToDescriptionString(mScreenSize));
        return descriptionList;
    }

}
