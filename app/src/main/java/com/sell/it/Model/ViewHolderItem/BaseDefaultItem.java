package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Model.Constant.Values;

public class BaseDefaultItem extends BaseItem {

    public BaseDefaultItem() {
    }

    @Override
    public int getViewType() {
        return Values.ViewType.BASE_DEFAULT_TYPE;
    }
}
