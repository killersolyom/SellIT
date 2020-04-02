package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class BaseDefaultItem extends BaseItem {

    public BaseDefaultItem() {
    }

    @Override
    public int getItemType() {
        return Values.ItemType.BASE_DEFAULT_TYPE;
    }
}
