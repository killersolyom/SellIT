package com.sell.it.Model.ViewHolderItem.UploadAdvertisements;

import com.sell.it.Model.Constant.Values;

public class MobilePhoneUploadItem extends BaseUploadComputeUnitItem {

    private boolean mHasJack;
    private String mUsbType;
    private String mModel;
    private float mPrimaryCamera;
    private float mSecondaryCamera;

    public boolean hasJack() {
        return mHasJack;
    }

    public void setJack(boolean mHasJack) {
        this.mHasJack = mHasJack;
    }

    public String getUsbType() {
        return mUsbType;
    }

    public void setUsbType(String mUsbType) {
        this.mUsbType = mUsbType;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String mModel) {
        this.mModel = mModel;
    }

    public float getPrimaryCamera() {
        return mPrimaryCamera;
    }

    public void setPrimaryCamera(float mPrimaryCamera) {
        this.mPrimaryCamera = mPrimaryCamera;
    }

    public float getSecondaryCamera() {
        return mSecondaryCamera;
    }

    public void setSecondaryCamera(float mSecondaryCamera) {
        this.mSecondaryCamera = mSecondaryCamera;
    }

    @Override
    public String getItemType() {
        return Values.ItemType.MOBILE_PHONE_TYPE;
    }

    @Override
    public String getCategoryType() {
        return Values.CategoryType.ELECTRONIC_TYPE;
    }

}
