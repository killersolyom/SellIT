package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class MiniImageItem extends BaseDefaultItem {

    private String mImagePath = "";

    public MiniImageItem() {
    }

    public MiniImageItem(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public String getImagePath() {
        return mImagePath;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.MINI_IMAGE_ITEM_TYPE;
    }
}
