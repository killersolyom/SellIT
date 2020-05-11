package com.sell.it.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.User;

public class DataManager {
    private static final String REMEMBER_ME_KEY = "remember_me_key";
    private static final String PORTRAIT_KEY = "PORTRAIT_key";
    private static final String LANDSCAPE_KEY = "LANDSCAPE_key";
    private static final String LANGUAGE_KEY = "language_key";
    private static final String EMAIL_KEY = "email_key";
    private static final String FIRSTNAME_KEY = "firstname_key";
    private static final String LASTNAME_KEY = "lastname_key";
    private static final String USERNAME_KEY = "username_key";
    private static final String PASSWORD_KEY = "password_key";
    private static final String PHONE_KEY = "phone_key";

    private static SharedPreferences mPreference;

    public static void initialize(Context context) {
        if (mPreference == null) {
            mPreference = context.getSharedPreferences(context.getApplicationContext().getPackageName(), 0);
        }
    }

    private static void clearItem(String key) {
        mPreference.edit().remove(key).apply();
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

    private static long readLongData(String key, long defaultValue) {
        return mPreference.getLong(key, defaultValue);
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
        writeString(TextUtils.encrypt(user.getPassword()), PASSWORD_KEY);
    }

    public static User getUser() {
        String password = TextUtils.decrypt(getPassword());
        return new User(getEmailAddress(), getFirstName(), getLastName(), getUserName(), password);
    }

    public static void savePhoneNumber(String phoneNumber){
        writeString(phoneNumber,PHONE_KEY);
    }

    public static String getPhoneNumber(){
        return readStringData(PHONE_KEY);
    }

    public static String getUserName() {
        return readStringData(USERNAME_KEY);
    }

    public static String getFirstName() {
        return readStringData(FIRSTNAME_KEY);
    }

    public static String getLastName() {
        return readStringData(LASTNAME_KEY);
    }

    public static String getEmailAddress() {
        return readStringData(EMAIL_KEY);
    }

    public static String getPassword() {
        return readStringData(PASSWORD_KEY);
    }

    public static boolean isUserExist() {
        return TextUtils.isValidEmailAddress(readStringData(EMAIL_KEY)) &&
                TextUtils.isValidPassword(readStringData(PASSWORD_KEY));
    }

    public static void clearUserData() {
        clearItem(EMAIL_KEY);
        clearItem(USERNAME_KEY);
        clearItem(FIRSTNAME_KEY);
        clearItem(LASTNAME_KEY);
        clearItem(PASSWORD_KEY);
        clearItem(REMEMBER_ME_KEY);
        clearItem(PHONE_KEY);
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