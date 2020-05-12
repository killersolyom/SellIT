package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.sell.it.Model.ViewHolderItem.BaseValueInputItem;
import com.sell.it.R;
import com.sell.it.Utility.TextUtils;

public class NumberInputViewItem extends BaseInputViewItem {

    private VerificationEditText mInputField;
    private TextView mTitle;

    public NumberInputViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.number_input_component_layout, this, true);
    }

    @Override
    protected void initView() {
        mTitle = findViewById(R.id.input_title);
        mInputField = findViewById(R.id.input_field);
    }

    @Override
    public void bindItem(BaseValueInputItem listener) {
        super.bindItem(listener);
        mTitle.setText(listener.getTitle());
        mInputField.setText(mValueListener.getStringValue());
    }

    @Override
    public boolean isReady() {
        return !mIsNecessary || !TextUtils.isEmpty(mInputField.getItemText()) && mIsNecessary && isValidNumber();
    }

    @Override
    public void showStatus(boolean isError) {
        mInputField.updateItem(isError);
    }

    @Override
    public void writeValue() {
        if (isValidNumber()) {
            mValueListener.writeValue(Float.parseFloat(mInputField.getItemText()));
        }
    }

    private boolean isValidNumber() {
        try {
            Float.parseFloat(mInputField.getItemText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}