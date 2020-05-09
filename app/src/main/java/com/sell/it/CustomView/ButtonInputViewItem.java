package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.ViewHolderItem.ButtonInputItem;
import com.sell.it.R;

public class ButtonInputViewItem extends BaseInputViewItem {

    protected Button mButton;
    protected ValueListener mValueListener;
    protected boolean mIsNecessary;

    public ButtonInputViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.button_input_component_layout, this);
    }

    @Override
    protected void initView() {
        mButton = findViewById(R.id.input_button);
    }

    public void bindItem(ButtonInputItem item) {
        mButton.setText(item.getTitle());
        mButton.setOnClickListener(v -> item.runRunnable());
    }

}