package com.sell.it.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.sell.it.Model.Constant.Values;
import com.sell.it.Model.User;

import static com.sell.it.Model.Constant.Values.User.EMAIL;
import static com.sell.it.Model.Constant.Values.User.FIRST_NAME;
import static com.sell.it.Model.Constant.Values.User.LAST_NAME;
import static com.sell.it.Model.Constant.Values.User.PASSWORD;
import static com.sell.it.Model.Constant.Values.User.USERNAME;

public class DataManager {
    private static final String ALPHA_KEY = "Alpha_key";
    private static final String LANGUAGE_KEY = "Alpha_key";
    private static final String EMAIL_KEY = "email_key";
    private static final String FIRSTNAME_KEY = "firstname_key";
    private static final String LASTNAME_KEY = "lastname_key";
    private static final String USERNAME_KEY = "username_key";
    private static final String PASSWORD_KEY = "password_key";

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

    private static String readStringData(String key, String defaultValue) {
        return mPreference.getString(key, defaultValue);
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

    public static void saveUser(@NonNull User user){
        writeString(user.getEmailAddress(),EMAIL_KEY);
        writeString(user.getFirstName(),FIRSTNAME_KEY);
        writeString(user.getLastName(),LASTNAME_KEY);
        writeString(user.getUsername(),USERNAME_KEY);
        writeString(user.getPassword(),PASSWORD_KEY);
    }

    public static User getUser(){
        String username = readStringData(USERNAME_KEY,USERNAME);
        String email = readStringData(EMAIL_KEY, EMAIL);
        String firstName = readStringData(FIRSTNAME_KEY, FIRST_NAME);
        String lastName = readStringData(LASTNAME_KEY, LAST_NAME);
        String password = readStringData(PASSWORD_KEY, PASSWORD);

        return new User(email,firstName,lastName,username,password);
    }

    public static boolean isUserExist(User user){
        return !(user.getEmailAddress().equals(EMAIL)
                || user.getPassword().equals(PASSWORD)
                || user.getFirstName().equals(FIRST_NAME)
                || user.getLastName().equals(LAST_NAME)
                || user.getUsername().equals(USERNAME));
    }
}