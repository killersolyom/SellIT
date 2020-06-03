package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.Constant.Values;

public class BooleanValueListenerItem extends BaseValueInputItem {

    public BooleanValueListenerItem(ValueListener valueListener, String title) {
        super(valueListener, title, false);
    }

    @Override
    public int getViewType() {
        return Values.ViewType.CHECK_BOX_ITEM_TYPE;
    }

}
