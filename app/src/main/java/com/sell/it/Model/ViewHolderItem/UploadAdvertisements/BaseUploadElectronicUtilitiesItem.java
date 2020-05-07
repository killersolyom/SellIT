package com.sell.it.Model.ViewHolderItem.UploadAdvertisements;

public abstract class BaseUploadElectronicUtilitiesItem extends BaseUploadAdvertisementItem {

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
