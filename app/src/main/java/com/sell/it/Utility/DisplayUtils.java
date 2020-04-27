package com.sell.it.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import static com.sell.it.Model.Constant.Values.Orientation.LANDSCAPE;
import static com.sell.it.Model.Constant.Values.Orientation.PORTRAIT;


public class DisplayUtils {

    private static DisplayMetrics mDisplayMetrics;
    private static Context mContext;

    static void initialize(Activity activity) {
        mContext = activity;
        mDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
    }

    public static int getScreenWidth() {
        return mDisplayMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        return mDisplayMetrics.heightPixels;
    }

    public static int getOrientation() {
        return mContext.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE ? LANDSCAPE : PORTRAIT;
    }

    public static float convertPixelToSp(float pixel) {
        return pixel / mDisplayMetrics.scaledDensity;
    }

    public static float convertSPToPixel(float sp) {
        return sp * mDisplayMetrics.scaledDensity;
    }

    public static float convertDPToPixel(float dp) {
        return dp * mDisplayMetrics.scaledDensity;
    }


}
