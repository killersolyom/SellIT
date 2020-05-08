package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class BaseCustomView<Item> extends ConstraintLayout {

    protected View itemView;

    public BaseCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
        initView();
        initializeComponents();
    }

    protected abstract void inflateView(Context context);

    protected abstract void initView();

    protected void initializeComponents() {
    }

    public void bindItem(Item item, ViewGroup.LayoutParams layoutParams) {
    }

    public void bindItem(Item item) {
    }

    public void unbind() {
    }
}