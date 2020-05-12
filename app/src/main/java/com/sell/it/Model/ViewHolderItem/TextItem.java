package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Adapter.CustomPairItem;
import com.sell.it.Communication.ClickInterface;
import com.sell.it.Model.Constant.Values;

public class TextItem extends BaseDefaultItem {

    private CustomPairItem<String, Object> mPairItem;
    private ClickInterface mClickInterface;
    private boolean mBigCategory;

    public TextItem(CustomPairItem<String, Object> item, ClickInterface clickInterface, boolean bigCategory) {
        mPairItem = item;
        mClickInterface = clickInterface;
        this.mBigCategory = bigCategory;
    }

    public TextItem(CustomPairItem<String, Object> item, ClickInterface clickInterface) {
        mPairItem = item;
        mClickInterface = clickInterface;
        this.mBigCategory = false;
    }

    public CustomPairItem<String, Object> getPairItem() {
        return this.mPairItem;
    }

    public boolean isBigCategory() {
        return mBigCategory;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.TEXT_ITEM_TYPE;
    }

    public ClickInterface getListener() {
        return mClickInterface;
    }

}
