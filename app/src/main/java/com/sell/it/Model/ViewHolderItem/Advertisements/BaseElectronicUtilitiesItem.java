package com.sell.it.Model.ViewHolderItem.Advertisements;

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

}
