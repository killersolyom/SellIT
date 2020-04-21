package com.sell.it.Utility;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class DataCacheUtil {

    private static Map<String, Bundle> mCacheMap = new HashMap<>();

    public static void addItem(String key, Bundle item) {
        if (item != null) {
            mCacheMap.put(key, item);
        }
    }

    public static Bundle getItem(String key) {
        return isExist(key) ? mCacheMap.get(key) : new Bundle();
    }

    public static boolean isExist(String key) {
        return mCacheMap.containsKey(key);
    }

    public static void clearItems() {
        mCacheMap.clear();
    }

}
