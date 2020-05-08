package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.Constant.Values;

public class ValueListenerItem extends BaseDefaultItem {

    private ValueListener mValueListener;
    private int mInputType;
    private String mTitle;
    private boolean mIsNecessary;

    public ValueListenerItem(ValueListener valueListener, String title, int inputType, boolean isNecessary) {
        this.mValueListener = valueListener;
        this.mInputType = inputType;
        this.mTitle = title;
        this.mIsNecessary = isNecessary;
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

    public boolean isNecessary() {
        return mIsNecessary;
    }

    @Override
    public int getViewType() {
        return Values.ViewType.VALUE_LISTENER_ITEM_TYPE;
    }

}
