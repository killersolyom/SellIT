package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class ImageItem extends BaseDefaultItem {

    private String mImagePath;

    public ImageItem() {
    }

    public ImageItem(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public String getImagePath() {
        return mImagePath;
    }

    @Override
    public int getItemType() {
        return Values.ItemType.IMAGE_ITEM_TYPE;
    }
}
