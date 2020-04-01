package com.sell.it.Model;

import com.sell.it.Model.Constant.Values;

public class TextSeparatorItem extends BaseDefaultItem {

    public TextSeparatorItem() {
    }

    @Override
    public int getItemType() {
        return Values.ItemType.SEPARATOR_ITEM_TYPE;
    }
}
