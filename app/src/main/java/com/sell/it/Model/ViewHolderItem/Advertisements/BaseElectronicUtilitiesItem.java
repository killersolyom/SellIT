package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.R;

import java.util.ArrayList;

public abstract class BaseElectronicUtilitiesItem extends BaseAdvertisementItem {

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
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_battery_size, intValueToDescriptionString(mBatterySize));
        addToListIfExist(descriptionList, R.string.advertisement_screen_size, floatValueToDescriptionString(mScreenSize));
        return descriptionList;
    }

}
