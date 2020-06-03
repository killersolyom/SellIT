package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class MiniImageItem extends ImageItem {

    public MiniImageItem(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.MINI_IMAGE_ITEM_TYPE;
    }

}
