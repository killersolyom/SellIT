package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.sell.it.Communication.InputCallbackInterface;
import com.sell.it.Communication.ValueListener;
import com.sell.it.Model.ViewHolderItem.ValueListenerItem;
import com.sell.it.R;
import com.sell.it.Utility.TextUtils;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES;
import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

public class InputViewItem extends BaseCustomView<ValueListenerItem> implements InputCallbackInterface {

    public final static int TEXT_INPUT_TYPE = TYPE_TEXT_FLAG_CAP_SENTENCES | TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_MULTI_LINE;
    public final static int NUMBER_INPUT_TYPE = TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL;

    private TextView mTitle;
    private VerificationEditText mInputField;
    private ValueListener mValueListener;
    private boolean mIsNecessary;

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
        mInputField.setInputType(listener.getInputType());
        mIsNecessary = listener.isNecessary();
        mTitle.setText(listener.getTitle());
        mValueListener.registerCallback(this);
    }

    @Override
    public boolean isReady() {
        return !mIsNecessary || !TextUtils.isEmpty(mInputField.getItemText()) && mIsNecessary;
    }

    @Override
    public void showStatus(boolean isError) {
        mInputField.updateItem(isError);
    }
}