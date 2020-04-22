package com.sell.it.Utility;

import java.util.regex.Pattern;

public class TextUtils {

    public static boolean isEmpty(String value) {
        return value != null && value.isEmpty();
    }

    public static boolean isValidEmailAddress(String address) {
        return !isEmpty(address) && Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"
        ).matcher(address).matches();
    }

    public static boolean isValidPassword(String password) {
        return !isEmpty(password) && password.length() >= 6;
    }

    public static boolean isValidName(String name) {
        return !isEmpty(name) && name.matches(".*\\d.*");
    }

    public static boolean isValidUserName(String username) {
        return !isEmpty(username) && username.contains(" ");
    }

    public static String encrypt(String plainText) {
        byte[] textBytes = revertString(plainText).getBytes();
        for (int i = 0; i < textBytes.length; ++i) {
            textBytes[i] = (textBytes[i] == Byte.MAX_VALUE) ? textBytes[i] : ++textBytes[i];
        }
        return new String(textBytes);
    }

    public static String decrypt(String encryptedText) {
        byte[] textBytes = revertString(encryptedText).getBytes();
        for (int i = 0; i < textBytes.length; ++i) {
            textBytes[i] = (textBytes[i] == Byte.MIN_VALUE) ? textBytes[i] : --textBytes[i];
        }
        return new String(textBytes);
    }

    public static String revertString(String text) {
        return new StringBuilder().append(text).reverse().toString();
    }

}