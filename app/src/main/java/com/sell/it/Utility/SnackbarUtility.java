package com.sell.it.Utility;

import android.graphics.Color;

import com.google.android.material.snackbar.Snackbar;
import com.sell.it.Communication.SnackbarCallback;

public class SnackbarUtility {

    private static SnackbarCallback mSnackbarCallback;
    private static Snackbar mSnackbar;

    static void initialize(SnackbarCallback snackbarCallback) {
        mSnackbarCallback = snackbarCallback;
    }

    public static void showWithText(String message, boolean isError) {
        mSnackbar = Snackbar.make(mSnackbarCallback.getView(), message, Snackbar.LENGTH_INDEFINITE);
        mSnackbar.setBackgroundTint(isError ? Color.RED : Color.GREEN);
        mSnackbar.setDuration(3000);
        mSnackbar.show();
    }

    public static void showWithText(int textId, boolean isError) {
        showWithText(mSnackbarCallback.getView().getContext().getString(textId), isError);
    }

}
