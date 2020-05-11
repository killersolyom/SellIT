package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Adapter.CustomPairItem;
import com.sell.it.Communication.ClickInterface;
import com.sell.it.Model.Constant.Values;

public class TextItem extends BaseDefaultItem {

    private CustomPairItem<String, String> mPairItem;
    private ClickInterface mClickInterface;

    public TextItem(CustomPairItem<String, String> item, ClickInterface clickInterface) {
        mPairItem = item;
        mClickInterface = clickInterface;
    }

    public CustomPairItem<String, String> getPairItem() {
        return this.mPairItem;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.TEXT_ITEM_TYPE;
    }

    public ClickInterface getListener() {
        return mClickInterface;
    }

}
