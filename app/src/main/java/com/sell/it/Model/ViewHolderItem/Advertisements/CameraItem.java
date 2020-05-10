package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.Map;

public class CameraItem extends BaseElectronicUtilitiesItem {

    public final static String MEGA_PIXEL_KEY = "MEGA_PIXEL_KEY";

    private int mMegaPixels;

    public CameraItem() {
    }

    public CameraItem(Map<String, Object> mItemData) {
        initItems(mItemData);
    }

    public int getMegaPixels() {
        return mMegaPixels;
    }

    public void setMegaPixels(int mMegaPixels) {
        this.mMegaPixels = mMegaPixels;
    }

    @Override
    public String getItemType() {
        return Values.ItemType.CAMERA_TYPE;
    }

    @Override
    public String getCategoryType() {
        return Values.CategoryType.ELECTRONIC_TYPE;
    }

    @Override
    protected void initItems(Map<String, Object> items) {
        super.initItems(items);
        mMegaPixels = getIntValue(items.get(MEGA_PIXEL_KEY));
    }

    @Exclude
    @Override
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_megapixel, intValueToDescriptionString(mMegaPixels));
        return descriptionList;
    }
}
