package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.Constant.Values;

public class ValueListenerItem extends BaseDefaultItem {

    private ValueListener mValueListener;
    private int mInputType;
    private String mTitle;

    public ValueListenerItem(ValueListener valueListener, String title, int inputType) {
        this.mValueListener = valueListener;
        this.mInputType = inputType;
        this.mTitle = title;
    }

    public int getInputType() {
        return mInputType;
    }

    public String getTitle() {
        return mTitle;
    }

    public ValueListener getListener() {
        return mValueListener;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.VALUE_LISTENER_ITEM_TYPE;
    }

    public static class InputTypes {
        public static final int INT = 0;
        public static final int DECIMAL = 1;
        public static final int STRING = 2;
    }

}
