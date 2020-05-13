package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MobilePhoneItem extends BaseComputeUnitItem {

    public final static String JACK_KEY = "AUDIO_JACK";
    public final static String USB_KEY = "USB_TYPE";
    public final static String MODEL_KEY = "MODEL_TYPE";
    public final static String PRIMARY_CAMERA_KEY = "PRIMARY_CAMERA";
    public final static String SECONDARY_CAMERA_KEY = "SECONDARY_CAMERA";

    private boolean jack;
    private String mUsbType;
    private String mModel;
    private float mPrimaryCamera;
    private float mSecondaryCamera;

    public MobilePhoneItem() {
    }

    public MobilePhoneItem(HashMap<String, Object> mItemData) {
        initItems(mItemData);
    }

    @PropertyName(USB_KEY)
    public String getUsbType() {
        return mUsbType;
    }

    @PropertyName(MODEL_KEY)
    public String getModel() {
        return mModel;
    }

    @PropertyName(PRIMARY_CAMERA_KEY)
    public float getPrimaryCamera() {
        return mPrimaryCamera;
    }

    @PropertyName(SECONDARY_CAMERA_KEY)
    public float getSecondaryCamera() {
        return mSecondaryCamera;
    }

    @PropertyName(JACK_KEY)
    public boolean isJack() {
        return jack;
    }

    @Override
    @PropertyName(ITEM_KEY)
    public String getItemType() {
        return Values.ItemType.MOBILE_PHONE_TYPE;
    }

    @Override
    protected void initItems(HashMap<String, Object> items) {
        super.initItems(items);
        mUsbType = getStringValue(items.get(USB_KEY));
        jack = getBooleanValue(items.get(JACK_KEY));
        mModel = getStringValue(items.get(MODEL_KEY));
        mPrimaryCamera = getFloatValue(items.get(PRIMARY_CAMERA_KEY));
        mSecondaryCamera = getFloatValue(items.get(SECONDARY_CAMERA_KEY));
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_usb_type, mUsbType);
        addToListIfExist(descriptionList, R.string.advertisement_model, mModel);
        addToListIfExist(descriptionList, R.string.advertisement_primary_camera, floatValueToDescriptionString(mPrimaryCamera));
        addToListIfExist(descriptionList, R.string.advertisement_secondary_camera, floatValueToDescriptionString(mSecondaryCamera));
        if (jack) {
            descriptionList.add(new Pair<>(R.string.advertisement_jack, ""));
        }
        return descriptionList;
    }

}
