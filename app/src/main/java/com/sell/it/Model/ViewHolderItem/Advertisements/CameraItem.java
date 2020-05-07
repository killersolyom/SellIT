package com.sell.it.Model.ViewHolderItem.Advertisements;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;
import com.sell.it.R;

import java.util.ArrayList;

public class CameraItem extends BaseElectronicUtilitiesItem {

    private int mMegaPixels;

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
    public ArrayList<Pair<Integer, String>> getDescriptionList() {
        ArrayList<Pair<Integer, String>> descriptionList = super.getDescriptionList();
        addToListIfExist(descriptionList, R.string.advertisement_megapixel, intValueToDescriptionString(mMegaPixels));
        return descriptionList;
    }
}
