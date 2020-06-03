package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.Constant.Values;

public class NumberInputItem extends BaseValueInputItem {

    public NumberInputItem(ValueListener valueListener, String title, boolean isNecessary) {
        super(valueListener, title, isNecessary);
    }

    @Override
    public int getViewType() {
        return Values.ViewType.NUMBER_INPUT_ITEM_TYPE;
    }

}
