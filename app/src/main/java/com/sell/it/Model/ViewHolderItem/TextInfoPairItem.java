package com.sell.it.Model.ViewHolderItem;

import android.util.Pair;

import com.sell.it.Model.Constant.Values;

public class TextInfoPairItem extends BaseDefaultItem {

    protected Pair<Integer, String> mTextItem;

    public TextInfoPairItem(Pair<Integer, String> item) {
        mTextItem = item;
    }

    public Pair<Integer, String> getTextPairItem() {
        return mTextItem;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.TEXT_PAIR_ITEM_TYPE;
    }

}
