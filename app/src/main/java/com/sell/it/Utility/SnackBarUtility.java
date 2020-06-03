package com.sell.it.Utility;

import com.google.android.material.snackbar.Snackbar;
import com.sell.it.Communication.MainInterface;
import com.sell.it.R;

public class SnackBarUtility {

    private static MainInterface mMainInterface;
    private static Snackbar mSnackBar;
    private static int mDefaultColor;
    private static int mErrorColor;


    static void initialize(MainInterface mainInterface) {
        mMainInterface = mainInterface;
        mDefaultColor = mainInterface.getContext().getColor(R.color.colorDarkGreen);
        mErrorColor = mainInterface.getContext().getColor(R.color.colorRed);

    }

    public static void showWithText(String message, boolean isError) {
        mSnackBar = Snackbar.make(mMainInterface.getView(), message, Snackbar.LENGTH_INDEFINITE);
        mSnackBar.setBackgroundTint(isError ? mErrorColor : mDefaultColor);
        mSnackBar.setDuration(3000);
        mSnackBar.show();
    }

    public static void showWithText(int textId, boolean isError) {
        showWithText(mMainInterface.getContext().getString(textId), isError);
    }

}
