package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.Constant.Values;

public class BooleanValueListenerItem extends ValueListenerItem {

    public BooleanValueListenerItem(ValueListener valueListener, String title) {
        super(valueListener, title, 0, false);
    }

    public BooleanValueListenerItem(ValueListener valueListener, String title, int inputType, boolean isNecessary) {
        super(valueListener, title, inputType, isNecessary);
    }

    @Override
    public int getViewType() {
        return Values.ViewType.CHECK_BOX_ITEM_TYPE;
    }

}
