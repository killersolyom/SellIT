package com.sell.it.Utility;

import com.google.android.material.snackbar.Snackbar;
import com.sell.it.Communication.SnackBarCallback;
import com.sell.it.R;

public class SnackBarUtility {

    private static SnackBarCallback mSnackBarCallback;
    private static Snackbar mSnackBar;
    private static int mDefaultColor;
    private static int mErrorColor;


    static void initialize(SnackBarCallback snackbarCallback) {
        mSnackBarCallback = snackbarCallback;
        mDefaultColor = snackbarCallback.getContext().getColor(R.color.colorDarkGreen);
        mErrorColor = snackbarCallback.getContext().getColor(R.color.colorRed);

    }

    public static void showWithText(String message, boolean isError) {
        mSnackBar = Snackbar.make(mSnackBarCallback.getView(), message, Snackbar.LENGTH_INDEFINITE);
        mSnackBar.setBackgroundTint(isError ? mErrorColor : mDefaultColor);
        mSnackBar.setDuration(3000);
        mSnackBar.show();
    }

    public static void showWithText(int textId, boolean isError) {
        showWithText(mSnackBarCallback.getContext().getString(textId), isError);
    }

}
