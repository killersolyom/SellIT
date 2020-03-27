package com.sell.it.Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {
    private static final String ALPHA_KEY = "Alpha_key";
    private static SharedPreferences mPreference;

    public static void initialize(Context context) {
        if (mPreference == null) {
            mPreference = context.getSharedPreferences(context.getApplicationContext().getPackageName(), 0);
        }
    }

    private static void writeLongData(long number, String key) {
        mPreference.edit().putLong(key, number).apply();
    }

    private static long readLongData(String key) {
        return mPreference.getLong(key, 0);
    }

    public static void increaseListenCounter(String title) {
        writeLongData(readLongData(title) + 1, title);
    }

    public static long getListenCounter(String title) {
        return readLongData(title);
    }

    public static void resetCounter(String title) {
        mPreference.edit().remove(title).apply();
    }

    public static void setAlphaValue(int value) {
        writeIntData(value, ALPHA_KEY);
    }

    public static int getAlphaValue() {
        int value = readIntData(ALPHA_KEY);
        return value == 0 ? 15 : value;
    }

    private static void writeIntData(int value, String key) {
        mPreference.edit().putInt(key, value).apply();
    }

    private static int readIntData(String key) {
        return mPreference.getInt(key, 0);
    }
}