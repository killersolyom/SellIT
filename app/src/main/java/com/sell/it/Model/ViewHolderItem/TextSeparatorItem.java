package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class TextSeparatorItem extends BaseDefaultItem {

    private int mSeparatorHeight;

    public TextSeparatorItem(int separatorHeight) {
        mSeparatorHeight = separatorHeight;
    }

    @Override
    public int getItemType() {
        return Values.ItemType.SEPARATOR_ITEM_TYPE;
    }

    public int getSeparatorHeight() {
        return mSeparatorHeight;
    }
}
