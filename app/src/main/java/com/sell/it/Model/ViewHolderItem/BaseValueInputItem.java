package com.sell.it.Model.ViewHolderItem;

import com.sell.it.Communication.ValueListener;

public abstract class BaseValueInputItem extends BaseDefaultItem {

    private ValueListener mValueListener;
    private String mTitle;
    private boolean mIsNecessary;

    public BaseValueInputItem(ValueListener valueListener, String title, boolean isNecessary) {
        this.mValueListener = valueListener;
        this.mTitle = title;
        this.mIsNecessary = isNecessary;
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

}
