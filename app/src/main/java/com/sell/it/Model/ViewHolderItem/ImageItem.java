package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class ImageItem extends BaseDefaultItem {

    protected String mImagePath = "";

    public ImageItem() {
    }

    public ImageItem(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public String getImagePath() {
        return mImagePath;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.IMAGE_ITEM_TYPE;
    }
}
