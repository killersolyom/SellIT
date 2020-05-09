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

    public void bindItem(BaseValueInputItem listener) {
        mValueListener = listener.getListener();
        mIsNecessary = listener.isNecessary();
        mValueListener.registerCallback(this);
    }

    @Override
    public boolean isNecessary() {
        return mIsNecessary;
    }

}