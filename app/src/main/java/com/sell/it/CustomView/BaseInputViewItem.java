package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;

import com.sell.it.Communication.InputCallbackInterface;
import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.ViewHolderItem.BaseValueInputItem;

public abstract class BaseInputViewItem extends BaseCustomView<BaseValueInputItem> implements InputCallbackInterface {

    protected ValueListener mValueListener;
    protected boolean mIsNecessary;

    public BaseInputViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bindItem(BaseValueInputItem item) {
        mValueListener = item.getListener();
        mIsNecessary = item.isNecessary();
        mValueListener.registerCallback(this);
    }

    @Override
    public void unbind() {
        writeValue();
    }

}