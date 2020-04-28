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
        return !isEmpty(name);
    }

    public static boolean isValidUserName(String username) {
        return !isEmpty(username);
    }

    public static String encrypt(String plainText) {
        return DataEncryption.encrypt(plainText);
    }

    public static String decrypt(String encryptedText) {
        return DataEncryption.decrypt(encryptedText);
    }

    public static boolean containsOnlyNumbers(String text) {
        return text.matches("[0-9]+");
    }

    public static String reverseString(String text) {
        return new StringBuilder().append(text).reverse().toString();
    }

}
