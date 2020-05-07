package com.sell.it.Model.ViewHolderItem.UploadAdvertisements;

import com.sell.it.Model.Constant.Values;

public class CameraUploadItem extends BaseUploadElectronicUtilitiesItem {

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
    public String getCategoryType() {
        return Values.CategoryType.ELECTRONIC_TYPE;
    }

}
