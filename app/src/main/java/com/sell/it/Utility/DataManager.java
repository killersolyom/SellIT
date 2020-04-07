package com.sell.it.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.User;

public class DataManager {
    private static final String ALPHA_KEY = "Alpha_key";
    private static final String LANGUAGE_KEY = "Alpha_key";
    private static final String EMAIL_KEY = "Alpha_key";
    private static final String FIRSTNAME_KEY = "Alpha_key";
    private static final String LASTNAME_KEY = "Alpha_key";
    private static final String USERNAME_KEY = "Alpha_key";

    private static SharedPreferences mPreference;

    public static void initialize(Context context) {
        if (mPreference == null) {
            mPreference = context.getSharedPreferences(context.getApplicationContext().getPackageName(), 0);
        }
    }

    private static void writeLongData(long number, String key) {
        mPreference.edit().putLong(key, number).apply();
    }

    private static void writeString(String value, String key) {
        mPreference.edit().putString(key, value).apply();
    }

    private static String readStringData(String key) {
        return mPreference.getString(key, "");
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

    public static void saveLanguage(String language) {
        writeString(language, LANGUAGE_KEY);
    }

    public static String getLanguage() {
        return mPreference.getString(LANGUAGE_KEY, Values.Language.LANGUAGE_KEY_ENGLISH);
    }

    public static void saveUser(User user){
        writeString(user.getmEmailAddress(),EMAIL_KEY);
        writeString(user.getmFirstName(),FIRSTNAME_KEY);
        writeString(user.getmLastName(),LASTNAME_KEY);
        writeString(user.getmUsername(),USERNAME_KEY);
    }
}