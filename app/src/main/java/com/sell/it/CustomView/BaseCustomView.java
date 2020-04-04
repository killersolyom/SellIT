package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class BaseCustomView<Item> extends ConstraintLayout {

    protected View itemView;

    public BaseCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (itemView == null) {
            inflateView(context);
            initializeComponents();
        }
    }

    protected abstract void inflateView(Context context);

    protected abstract void initializeComponents();

    public abstract void bindItem(Item item);

    public abstract void unbind();
}