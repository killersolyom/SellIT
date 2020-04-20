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

    protected ViewGroup.LayoutParams getLayoutParams(View view) {
        return view.getLayoutParams();
    }

    public void calculateOptimalSize(ViewGroup.LayoutParams itemParams) {
    }

    protected abstract void inflateView(Context context);

    protected abstract void initView();

    protected abstract void initializeComponents();

    public abstract void bindItem(Item item);

    public abstract void unbind();
}