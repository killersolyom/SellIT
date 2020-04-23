package com.sell.it.Utility;

import java.util.Random;
import java.util.regex.Pattern;

public class TextUtils {

    private static final int mOffset = 13759;
    private static final int mOffsetLength = String.valueOf(mOffset).length();
    private static final int mMaxBound = 8999, mMinBound = 1000;
    private static final int mBoundLength = String.valueOf(mMaxBound).length();

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
        StringBuilder returnValue = new StringBuilder();
        for (byte it : reverseString(plainText).getBytes()) {
            returnValue.append(new Random().nextInt(mMaxBound) + mMinBound).append(it + mOffset);
        }
        return returnValue.append(new Random().nextInt(mMaxBound) + mMinBound).toString();
    }

    public static String decrypt(String encryptedText) {
        byte[] bytes = new byte[(encryptedText.length() - mBoundLength) / (mOffsetLength + mBoundLength)];
        for (int i = mBoundLength; i < encryptedText.length(); i += (mOffsetLength + mBoundLength)) {
            String sequence = encryptedText.substring(i, i + mOffsetLength);
            if (containsOnlyNumbers(sequence)) {
                bytes[i / (mOffsetLength + mBoundLength)] = (byte) (Integer.parseInt(sequence) - mOffset);
            } else {
                return encryptedText;
            }
        }
        return reverseString(new String(bytes));
    }

    public static boolean containsOnlyNumbers(String text) {
        return text.matches("[0-9]+");
    }

    public static String reverseString(String text) {
        return new StringBuilder().append(text).reverse().toString();
    }

}
