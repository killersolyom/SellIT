package com.sell.it.CustomView;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.ViewHolderItem.ValueListenerItem;
import com.sell.it.R;

public class InputViewItem extends BaseCustomView<ValueListenerItem> {

    private TextView mTitle;
    private EditText mInputField;
    private ValueListener mValueListener;

    public InputViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateView(Context context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.data_input_component_layout, this);
    }

    @Override
    protected void initView() {
        mTitle = findViewById(R.id.input_title);
        mInputField = findViewById(R.id.input_field);
    }

    public void bindItem(ValueListenerItem listener) {
        mValueListener = listener.getListener();
        setInputType(listener.getInputType());
        mTitle.setText(listener.getTitle());
    }

    private void setInputType(int type) {
        switch (type) {
            case ValueListenerItem.InputTypes.DECIMAL:
                mInputField.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case ValueListenerItem.InputTypes.INT:
                mInputField.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case ValueListenerItem.InputTypes.STRING:
                mInputField.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                break;
        }
    }

    public void unbind() {
        mInputField.setText("");
        mTitle.setText("");
    }

}