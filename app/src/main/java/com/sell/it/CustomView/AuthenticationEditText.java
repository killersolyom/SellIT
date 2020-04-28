package com.sell.it.CustomView;

import android.content.Context;
import android.util.AttributeSet;

import com.sell.it.R;

public class AuthenticationEditText extends androidx.appcompat.widget.AppCompatEditText {

    private boolean mError;

    public AuthenticationEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground(getContext().getDrawable(R.drawable.item_rounded_background));
    }

    public void showError() {
        mError = true;
        setBackground(getContext().getDrawable(R.drawable.item_rounded_error_background));
    }

    public void hideError() {
        mError = false;
        setBackground(getContext().getDrawable(R.drawable.item_rounded_background));
    }

    public String getItemText() {
        return getText() == null ? "" : getText().toString().trim();
    }

    public boolean isError() {
        return mError;
    }

    public void updateItem(boolean isError) {
        if (isError) {
            showError();
        } else {
            hideError();
        }
    }
}
