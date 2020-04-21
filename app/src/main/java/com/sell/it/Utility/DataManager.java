package com.sell.it.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.User;

public class DataManager {
    private static final String REMEMBER_ME_KEY = "remember_me_key";
    private static final String PORTRAIT_KEY = "PORTRAIT_key";
    private static final String LANDSCAPE_KEY = "LANDSCAPE_key";
    private static final String LANGUAGE_KEY = "Alpha_key";
    private static final String EMAIL_KEY = "Alpha_key";
    private static final String FIRSTNAME_KEY = "Alpha_key";
    private static final String LASTNAME_KEY = "Alpha_key";
    private static final String USERNAME_KEY = "Alpha_key";
    private static final String PASSWORD_KEY = "Alpha_key";

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

    private static void writeIntData(int value, String key) {
        mPreference.edit().putInt(key, value).apply();
    }

    private static void writeBooleanData(boolean value, String key) {
        mPreference.edit().putBoolean(key, value).apply();
    }

    private static int readIntData(String key, int defaultValue) {
        return mPreference.getInt(key, defaultValue);
    }

    private static boolean readBooleanData(String key) {
        return mPreference.getBoolean(key, false);
    }

    public static void saveLanguage(String language) {
        writeString(language, LANGUAGE_KEY);
    }

    public static String getLanguage() {
        return mPreference.getString(LANGUAGE_KEY, Values.Language.LANGUAGE_KEY_ENGLISH);
    }

    public static void saveUser(User user) {
        writeString(user.getEmailAddress(), EMAIL_KEY);
        writeString(user.getFirstName(), FIRSTNAME_KEY);
        writeString(user.getLastName(), LASTNAME_KEY);
        writeString(user.getUsername(), USERNAME_KEY);
        writeString(user.getPassword(), PASSWORD_KEY);
    }

    public static User getUser() {
        String username = mPreference.getString(USERNAME_KEY, Values.User.USERNAME);
        String email = mPreference.getString(EMAIL_KEY, Values.User.EMAIL);
        String firstName = mPreference.getString(FIRSTNAME_KEY, Values.User.FIRST_NAME);
        String lastName = mPreference.getString(LASTNAME_KEY, Values.User.LAST_NAME);
        String password = mPreference.getString(PASSWORD_KEY, Values.User.PASSWORD);

        return new User(email, firstName, lastName, username, password);
    }

    public static boolean isUserExist(User user) {
        if (user.getEmailAddress().equals("email") || user.getPassword().equals("pass")) {
            return false;
        } else {
            return false;
        }
    }

    public static int getLandscapeColumnNumber() {
        return readIntData(LANDSCAPE_KEY, 1);
    }

    public static int getPortraitColumnNumber() {
        return readIntData(PORTRAIT_KEY, 1);
    }

    public static void saveLandscapeColumnNumber(int value) {
        writeIntData(value, LANDSCAPE_KEY);
    }

    public static void savePortraitColumnNumber(int value) {
        writeIntData(value, PORTRAIT_KEY);
    }

    public static boolean getRememberMeStatus() {
        return readBooleanData(REMEMBER_ME_KEY);
    }

    public static void saveRememberMeStatus(boolean value) {
        writeBooleanData(value, REMEMBER_ME_KEY);
    }

}