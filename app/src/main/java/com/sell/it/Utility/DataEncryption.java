package com.sell.it.Utility;

import java.util.Random;

import static com.sell.it.Utility.TextUtils.containsOnlyNumbers;
import static com.sell.it.Utility.TextUtils.reverseString;

class DataEncryption {

    private static final int mOffset = 3573;
    private static final int mOffsetLength = String.valueOf(mOffset).length();
    private static final int mMaxBound = 8999, mMinBound = 1000;
    private static final int mBoundLength = String.valueOf(mMaxBound).length();

    private static final String[] mReplaceArray1 = {
            "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j",
            "╚", "╔", "╩", "╦", "╠",
            "k", "l", "m", "n", "o",
            "§", "↨", "↑", "↓", "→",
            "p", "q", "r", "s", "t",
            "y", "w", "z", "*", "/",
            "╬", "╧", "╨", "╤", "╘",
            "?", "'", "|", " ", "S",
            "!", "`", "~", "@", "#",
            "$", "%", "&", "(", ")",
            "+", "-", "_", "=", ".",
            "<", ">", ",", "[", "]",
            "╥", "☺", "☻", "♥", "♦",
            "♣", "♠", "•", "◘", "◙",
            "²", "▬", "∟", "▼", "»",
            "♀", "♪", "♫", "☼", "►",
            "{", "}", ":", ";"};
    private static final String[] mReplaceArray2 = {
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35",
            "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45",
            "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55",
            "56", "57", "58", "59", "60",
            "61", "62", "63", "64", "65",
            "66", "67", "68", "69", "70",
            "71", "72", "73", "74", "75",
            "76", "77", "78", "79", "80",
            "81", "82", "83", "84", "85",
            "86", "87", "88", "89", "90",
            "91", "92", "93", "94", "95",
            "96", "97", "98", "99"};

    public static String encrypt(String plainText) {
        StringBuilder returnValue = new StringBuilder();
        Random random = new Random();
        for (byte it : reverseString(plainText).getBytes()) {
            returnValue.append(random.nextInt(mMaxBound) + mMinBound).append(it + mOffset);
        }
        String encryptedString = returnValue.append(random.nextInt(mMaxBound) + mMinBound).toString();
        return replace(encryptedString, mReplaceArray2, mReplaceArray1);
    }

    public static String decrypt(String encryptedText) {
        String decodedText = replace(encryptedText, mReplaceArray1, mReplaceArray2).substring(mBoundLength);
        decodedText = decodedText.substring(0, decodedText.length() - mBoundLength);
        byte[] bytes = new byte[(decodedText.length() / (mOffsetLength + mBoundLength)) + 1];
        for (int i = 0; i < decodedText.length(); i += (mOffsetLength + mBoundLength)) {
            String sequence = decodedText.substring(i, i + mOffsetLength);
            if (containsOnlyNumbers(sequence)) {
                bytes[i / (mOffsetLength + mBoundLength)] = (byte) (Integer.parseInt(sequence) - mOffset);
            } else {
                return decodedText;
            }
        }
        return reverseString(new String(bytes));
    }

    private static String replace(String original, String[] replaceItem, String[] replaceTo) {
        for (int i = 0; i < replaceItem.length; i++) {
            original = original.replace(replaceItem[i], replaceTo[i]);
        }
        return original;
    }

}
