package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sell.it.Model.ViewHolderItem.BaseValueInputItem;
import com.sell.it.R;

public class BooleanInputViewItem extends BaseInputViewItem {

    private CheckBox mCheckBoxField;
    private TextView mTitle;

    public BooleanInputViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.boolean_input_component_layout, this, true);
    }

    @Override
    protected void initView() {
        mTitle = findViewById(R.id.input_title);
        mCheckBoxField = findViewById(R.id.checkbox_item);
    }

    @Override
    public void bindItem(BaseValueInputItem listener) {
        super.bindItem(listener);
        mTitle.setText(listener.getTitle());
        mCheckBoxField.setChecked(mValueListener.getBooleanValue());
    }

    @Override
    public void writeValue() {
        mValueListener.writeValue(mCheckBoxField.isChecked());
    }

}