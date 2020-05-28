package com.sell.it.Model.ViewHolderItem;

import com.google.firebase.database.Exclude;
import com.sell.it.Model.Constant.Values;

public class ImageItem extends BaseDefaultItem {

    protected String mImagePath = "";

    public ImageItem() {
    }

    public ImageItem(String mImagePath) {
        if (mImagePath == null) {
            this.mImagePath = "";
        } else {
            this.mImagePath = mImagePath;
        }
    }

    public String getImagePath() {
        return mImagePath;
    }

    @Exclude
    @Override
    public int getViewType() {
        return Values.ViewType.IMAGE_ITEM_TYPE;
    }
}
