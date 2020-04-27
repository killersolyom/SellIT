package com.sell.it.Utility;

import com.google.android.material.snackbar.Snackbar;
import com.sell.it.Communication.SnackbarCallback;

public class SnackbarUtility {

    private static SnackbarCallback mSnackbarCallback;
    private static Snackbar mSnackbar;

    static void initialize(SnackbarCallback snackbarCallback){
        mSnackbarCallback = snackbarCallback;
    }

    public static void showWithText(String message){
        mSnackbar = Snackbar.make(mSnackbarCallback.getView(),message,Snackbar.LENGTH_INDEFINITE);
        mSnackbar.setDuration(3000);
        mSnackbar.show();
    }

}
