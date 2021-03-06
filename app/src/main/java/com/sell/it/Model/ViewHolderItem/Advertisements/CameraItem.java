package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CameraItem extends BaseElectronicUtilitiesItem {

    public final static String MEGA_PIXEL_KEY = "MEGA_PIXEL";

    private int mMegaPixels;

    public CameraItem(HashMap<String, Object> mItemData) {
        initItems(mItemData);
    }

    @PropertyName(MEGA_PIXEL_KEY)
    public int getMegaPixels() {
        return mMegaPixels;
    }

    @Override
    @PropertyName(ITEM_KEY)
    public String getItemType() {
        return Values.ItemType.CAMERA_TYPE;
    }

    @Override
    protected void initItems(HashMap<String, Object> items) {
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
