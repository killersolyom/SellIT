package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sell.it.Communication.InputCallbackInterface;
import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.ViewHolderItem.ValueListenerItem;
import com.sell.it.R;

public class BooleanInputViewItem extends BaseCustomView<ValueListenerItem> implements InputCallbackInterface {

    private TextView mTitle;
    private CheckBox mCheckBoxField;
    private ValueListener mValueListener;

    public BooleanInputViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.boolean_input_component_layout, this);
    }

    @Override
    protected void initView() {
        mTitle = findViewById(R.id.input_title);
        mCheckBoxField = findViewById(R.id.checkbox_item);
    }

    public void bindItem(ValueListenerItem listener) {
        mValueListener = listener.getListener();
        mTitle.setText(listener.getTitle());
        mValueListener.registerCallback(this);
    }

}