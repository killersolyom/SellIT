package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;

public class MobilePhoneItem extends BaseComputeUnitItem {

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

    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_usb_type, mUsbType);
        addToListIfExist(descriptionList, R.string.advertisement_model, mModel);
        addToListIfExist(descriptionList, R.string.advertisement_primary_camera, floatValueToDescriptionString(mPrimaryCamera));
        addToListIfExist(descriptionList, R.string.advertisement_secondary_camera, floatValueToDescriptionString(mSecondaryCamera));
        if (mHasJack) {
            descriptionList.add(new Pair<>(R.string.advertisement_jack, ""));
        }
        return descriptionList;
    }

}
