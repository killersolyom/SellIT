package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.Map;

public class MobilePhoneItem extends BaseComputeUnitItem {

    public final static String JACK_KEY = "JACK_KEY";
    public final static String USB_KEY = "USB_KEY";
    public final static String MODEL_KEY = "MODEL_KEY";
    public final static String PRIMARY_CAMERA_KEY = "PRIMARY_CAMERA_KEY";
    public final static String SECONDARY_CAMERA_KEY = "SECONDARY_CAMERA_KEY";

    private boolean mHasJack;
    private String mUsbType;
    private String mModel;
    private float mPrimaryCamera;
    private float mSecondaryCamera;

    public MobilePhoneItem() {
    }

    public MobilePhoneItem(Map<String, Object> mItemData) {
        initItems(mItemData);
    }

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
    protected void initItems(Map<String, Object> items) {
        super.initItems(items);
        mUsbType = getStringValue(items.get(USB_KEY));
        mHasJack = getBooleanValue(items.get(JACK_KEY));
        mModel = getStringValue(items.get(MODEL_KEY));
        mPrimaryCamera = getFloatValue(items.get(PRIMARY_CAMERA_KEY));
        mSecondaryCamera = getFloatValue(items.get(SECONDARY_CAMERA_KEY));
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
